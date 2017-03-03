package com.midnight.reportsys.controller;

import java.io.PrintWriter;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midnight.reportsys.dto.GroupDTO;
import com.midnight.reportsys.pojo.Groups;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.GroupService;
import com.midnight.reportsys.util.Tools;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 分组控制层
 * 
 * @author Midnight
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public void addGroup(Groups group, HttpServletResponse response) throws Exception {
		PrintWriter pw = response.getWriter();
		if (group != null) {
			groupService.addGroup(group);
			pw.write("true");
		} else {
			pw.write("false");
		}

	}

	// 获取报表列表
	@ResponseBody
	@RequestMapping(value = "/getGroupList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getGroupList(int page, int rows) throws Exception {
		int intPage = page == 0 ? 1 : page;
		int number = rows == 0 ? 10 : rows;

		Map<String, Object> jsonMap = new HashMap<>();

		List<Groups> list = groupService.findAllGroup(intPage, number);
		int total = groupService.getGroupCount();

		jsonMap.put("total", total);
		jsonMap.put("rows", list);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] {});

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/findMyGroups", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String findMyGroups(HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int id = user.getId();
		String ids = "'%" + id + "%'";
		Map<String, Object> jsonMap = new HashMap<>();
		List<Groups> groups = groupService.findMyGroups(ids);

		jsonMap.put("rows", groups);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "id", "ids" });

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/findMyGroupsReport", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String findMyGroupsReport(HttpSession session) throws Exception {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		int id = user.getId();
		String ids = "'%" + id + "%'";
		Map<String, Object> jsonMap = new HashMap<>();
		List<GroupDTO> gDtos = new ArrayList<>();
		List<Groups> lists = groupService.findMyGroups(ids);
		for (int i = 0; i < lists.size(); ++i) {
			Groups groups = lists.get(i);

			String name = groups.getName();
			String[] leaders = groups.getLeader().split(",");
			String[] members = groups.getMember().split(",");
			String[] groupsDTO = new String[leaders.length + members.length + 1];
			groupsDTO[0] = name;
			for (int j = 0; j < leaders.length; ++j) {
				groupsDTO[j + 1] = leaders[j];
			}
			for (int j = 0; j < members.length; ++j) {
				groupsDTO[j + 1 + leaders.length] = members[j];
			}

			String userids = groups.getIds();
			String[] _userids = userids.split(",");
			for (int j = 0; j < groupsDTO.length; j++) {
				GroupDTO g = new GroupDTO();
				if (j == 0) {
					g.setRole("组名");
					g.setName(groupsDTO[j]);
				} else if (j > 0 && j < leaders.length+1) {
					g.setRole("组长");
					g.setName(groupsDTO[j]);
					g.setId(Integer.parseInt(_userids[j-1]));
					g.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + g.getId() + "\">日报表分析</a>");
					g.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + g.getId() + "\">周报表分析</a>");
				}else {
					g.setRole("组员");
					g.setName(groupsDTO[j]);
					g.setId(Integer.parseInt(_userids[j-1]));
					g.setDaily("<a href=\"statistics/memberStatistics/daily"  + "?id=" + g.getId() + "\">日报表分析</a>");
					g.setWeekly("<a href=\"statistics/memberStatistics/weekly"  + "?id=" + g.getId() + "\">周报表分析</a>");
				}
				gDtos.add(g);
			}

		}
		jsonMap.put("rows", gDtos);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] {});

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}
}
