package com.midnight.reportsys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midnight.reportsys.pojo.Notice;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.NoticeService;
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
	
	@ResponseBody
	@RequestMapping(value="/findNotice",method=RequestMethod.POST, produces = {
			"application/json;charset=UTF-8"})
	public String findNotice()throws Exception{
		
		Map<String, Object> jsonMap = new HashMap<>();
		List<Notice> listDaily = noticeService.findNotice("daily");
		List<Notice> listWeekly = noticeService.findNotice("weekly");
		listDaily.addAll(listWeekly);
		jsonMap.put("rows", listDaily);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] {"type"});

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}
}
