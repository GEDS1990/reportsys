package com.midnight.reportsys.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.midnight.reportsys.pojo.Report;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.ReportService;
import com.midnight.reportsys.util.DateTimeUtil;
import com.midnight.reportsys.util.Tools;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 对报表进行管理
 * 
 * @author Midnight
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private ReportService reportService;

	// 上传报表
	@RequestMapping(value = "/uploadReport/{type}", method = RequestMethod.POST)
	public void uploadReport(@RequestParam("reportUrl") MultipartFile reportUrl, @PathVariable("type") String type,
			HttpSession session, HttpServletResponse response) throws Exception {
		PrintWriter pw = response.getWriter();
		Report report = new Report();
		report.setId(UUID.randomUUID().toString());
		User user = (User) session.getAttribute("user");
		if (user != null && reportUrl != null) {

			// 获取截止时间，超过了截止时间就提交失败
		/*	Date deadline = reportService.getReportDeadline(type);
			if (deadline != null) {
				if (deadline.before(new Date())) {
					pw.write("deadline");
					return;
				}
			}*/

			// 将报表文件上传,然后获得一个新名称作为路径
			String url = reportService.saveFileupload(reportUrl, type);
			report.setUrl(url);
			String name = null;
			if (type.equals("daily")) {
				name = DateTimeUtil.getDate() + "_日报表_" + user.getUsername() + url.substring(url.lastIndexOf("."));
				report.setType(type);
			}
			if (type.equals("weekly")) {
				name = DateTimeUtil.getWeekDate() + "_周报表_" + user.getUsername() + url.substring(url.lastIndexOf("."));
				report.setType(type);
			}
			if (type.equals("template")) {
				name = reportUrl.getOriginalFilename();
				report.setType(type);
			}
			report.setName(name);

			String createTime = DateTimeUtil.getDateAndTime();
			report.setCreateTime(createTime);
			report.setUserId(user.getId());
			String downloadUrl = "<a href=\"report/downloadReport/" + type + "?id=" + report.getId() + "\">下载</a>";
			report.setDownloadUrl(downloadUrl);

			reportService.saveReport(report);
			pw.write("true");

			return;
		} else {
			pw.write("false");
		}

		return;
	}

	// 获取报表列表
	@ResponseBody
	@RequestMapping(value = "/getReportList/{type}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getReportList(int page, int rows, @PathVariable("type") String type, HttpSession session)
			throws Exception {
		int intPage = page == 0 ? 1 : page;
		int number = rows == 0 ? 10 : rows;
		String type2 = URLDecoder.decode(type, "UTF-8");

		User user = (User) session.getAttribute("user");
		if (user == null)
			return "redirect:/user/login";
		Map<String, Object> jsonMap = new HashMap<>();

		List<Report> list = reportService.getReportList(intPage, number, type2, user.getId());

		int total = reportService.getReportCount(type2, user.getId());

		jsonMap.put("total", total);
		jsonMap.put("rows", list);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{"id", "type", "userId" }
		jsonConfig.setExcludes(new String[] { "type", "userId" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	// 下载报表
	@RequestMapping(value = "/downloadReport/{type}", method = RequestMethod.GET)
	public void downloadReport(@PathVariable("type") String type, @RequestParam("id") String reportId,
			HttpServletResponse response) throws Exception {

		String filePath = null;
		if (type.equals("daily")) {
			filePath = "E:\\develop\\reportsys\\dailyreport\\";
		}
		if (type.equals("weekly")) {
			filePath = "E:\\develop\\reportsys\\weeklyreport\\";
		}
		if (type.equals("template")) {
			filePath = "E:\\develop\\reportsys\\template\\";
		}

		// 根据id获取将要下载的报表
		Report report = reportService.getReportById(reportId);

		String url = report.getUrl();
		String fileName = report.getName();
		String reportfileName = filePath + url;
		File file = new File(reportfileName);

		String mimeType = URLConnection.guessContentTypeFromName(fileName);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		// 设置响应的文本类型
		response.setContentType(mimeType);
		// 解决不同浏览器 文件名中文乱码问题
		response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8")
				+ ";filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));
		response.setContentLength((int) file.length());

		InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

		FileCopyUtils.copy(inputStream, response.getOutputStream());

	}

	// 删除报表
	@RequestMapping(value = "/deleteReport", method = RequestMethod.POST)
	public void deleteReport(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		PrintWriter pw = response.getWriter();
		try {
			reportService.deleteReportById(id);
			// 向浏览器输出数据，data接收
			pw.write("true");
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("false");
		}

	}
}
