package com.midnight.reportsys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midnight.reportsys.pojo.Statistics;
import com.midnight.reportsys.service.ReportService;
import com.midnight.reportsys.service.UserService;
import com.midnight.reportsys.util.DateTimeUtil;
import com.midnight.reportsys.util.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 用于统计分析
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
	@RequestMapping(value="/getReportCount",method=RequestMethod.POST, produces = {
			"application/json;charset=UTF-8"})
	public String getReportCount()throws Exception{
		String today = DateTimeUtil.getDate();
		today = "'" + today + "%'";
		//已提交的日报表的数量
		int dailyCount  = reportService.getDailyOrWeeklyCount("daily", today);
		
		String weekday = DateTimeUtil.getWeekDate();
		weekday = "'" + weekday + "%'";
		//已提交的周报表的数量
		int weeklyCount  = reportService.getDailyOrWeeklyCount("weekly", weekday);
		
		//需要提交报表的人数
		int count = userService.getReportMemberCount();
		
		String content1 = " 今天需要提交日报表  "+count+" 份; "+"已提交 "+dailyCount+" 份 ";
		String content2 = " 本周需要提交周报表  "+count+" 份; "+"已提交 "+weeklyCount+" 份 ";
		
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
		jsonConfig.setExcludes(new String[] {"id"});

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}
}
