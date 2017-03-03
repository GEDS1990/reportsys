package com.midnight.reportsys.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.helpers.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.midnight.reportsys.dto.UserDTO;
import com.midnight.reportsys.pojo.Report;
import com.midnight.reportsys.pojo.Statistics;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.ReportService;
import com.midnight.reportsys.service.UserService;
import com.midnight.reportsys.util.DateTimeUtil;
import com.midnight.reportsys.util.Tools;
import com.midnight.reportsys.util.ValidateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 用于统计分析
 * 
 * @author Administrator
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/getReportCount", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getReportCount() throws Exception {
		String today = DateTimeUtil.getDate();
		today = "'" + today + "%'";
		// 已提交的日报表的数量
		int dailyCount = reportService.getDailyOrWeeklyCount("daily", today);

		String weekday = DateTimeUtil.getWeekDate();
		weekday = "'" + weekday + "%'";
		// 已提交的周报表的数量
		int weeklyCount = reportService.getDailyOrWeeklyCount("weekly", weekday);

		// 需要提交报表的人数
		int count = userService.getReportMemberCount();

		String content1 = " 今天需要提交日报表  " + count + " 份; " + "已提交 " + dailyCount + " 份 ";
		String content2 = " 本周需要提交周报表  " + count + " 份; " + "已提交 " + weeklyCount + " 份 ";

		Statistics s1 = new Statistics();
		s1.setType("日报表");
		s1.setContent(content1);
		Statistics s2 = new Statistics();
		s2.setType("周报表");
		s2.setContent(content2);

		Map<String, Object> jsonMap = new HashMap<>();
		List<Statistics> list = new ArrayList<>();
		list.add(s1);
		list.add(s2);
		jsonMap.put("rows", list);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "id" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	/**
	 * 使用日历方式展现报表统计
	 * 
	 * @param session
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@RequestMapping(value = "/memberStatistics/{type}", method = RequestMethod.POST)
	public void memberStatistics(HttpSession session, HttpServletResponse response, @PathVariable("type") String type)
			throws Exception {
		PrintWriter pw = response.getWriter();
		User user = (User) session.getAttribute("user");
		List<Report> reports = null;
		if (user != null) {
			reports = reportService.findReportListByUserIdAndType(user.getId(), type);
		}

		String s = "";
		if (reports != null) {
			for (int i = 0; i < reports.size(); ++i) {
				String name = reports.get(i).getName();
				int year = Integer.parseInt(name.substring(0, 4));
				int month = Integer.parseInt(name.substring(5, 7));
				int day = Integer.parseInt(name.substring(8, 10));
				String string = year + "-" + month + "-" + day;
				s += string + ",";

			}
		}
		if (ValidateUtil.isValid(s)) {
			pw.write(s.substring(0, s.length() - 1));
		}

	}

	@RequestMapping("/memberStatistics/{type}")
	public String memberStatistics(@RequestParam("id") int userId, @PathVariable("type") String type, Model model)
			throws Exception {

		List<Report> reports = reportService.findReportListByUserIdAndType(userId, type);

		String s = "";
		if (reports != null) {
			for (int i = 0; i < reports.size(); ++i) {
				String name = reports.get(i).getName();
				int year = Integer.parseInt(name.substring(0, 4));
				int month = Integer.parseInt(name.substring(5, 7));
				int day = Integer.parseInt(name.substring(8, 10));
				String string = year + "-" + month + "-" + day;
				s += string + ",";

			}

		}

		String report_str = "";
		if (ValidateUtil.isValid(s)) {
			report_str = s.substring(0, s.length() - 1);
		}

		if (type.equals("daily"))
			model.addAttribute("type", "日报表");
		else {
			model.addAttribute("type", "周报表");
		}
		model.addAttribute("report_str", report_str);
		model.addAttribute("report_user", userService.getUserById(userId).getUsername());
		return "memberStatistics";
	}

	@ResponseBody
	@RequestMapping(value = "/getMemberUserReport", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getMemberUserReport(int page, int rows) throws Exception {
		//当前页
		int intPage = page == 0 ? 1 : page;
		//每页显示条数
		int number = rows == 0 ? 10 : rows;
		//每页的开始记录  第一页为1  第二页为number +1   
        int start = (intPage-1)*number;  
       
		Map<String, Object> jsonMap = new HashMap<>();
		List<UserDTO> uDtos = userService.findMemberUser(intPage, number);
		int total = uDtos.size();
		
		List<UserDTO> subList = uDtos.subList(start,  ((number*intPage)>total?total:(number*intPage)));
		
		jsonMap.put("total", total);
		jsonMap.put("rows", subList);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "id","password" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}
}
