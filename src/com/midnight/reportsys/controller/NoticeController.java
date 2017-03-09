package com.midnight.reportsys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midnight.reportsys.dto.ReportDTO;
import com.midnight.reportsys.pojo.Notice;
import com.midnight.reportsys.service.NoticeService;
import com.midnight.reportsys.service.ReportService;
import com.midnight.reportsys.util.Tools;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 对公告进行管理
 * 
 * @author Midnight
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private ReportService reportService;

	@ResponseBody
	@RequestMapping(value = "/findNotice", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	public String findNotice(HttpSession session) throws Exception {

		Map<String, Object> jsonMap = new HashMap<>();
		Notice notice1 = noticeService.findNotice("daily");
		Notice notice2 = noticeService.findNotice("weekly");
		ArrayList<Notice> notices = new ArrayList<>();
		notices.add(notice1);
		notices.add(notice2);
		jsonMap.put("rows", notices);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "type" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getTemplate", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getTemplate(HttpSession session) throws Exception {

		Map<String, Object> jsonMap = new HashMap<>();
		// 获取模板就行，不需要分页参数
		List<ReportDTO> lists = reportService.getReportList(1, 10, "template", 1);
		jsonMap.put("rows", lists);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "type", "userId" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}
}
