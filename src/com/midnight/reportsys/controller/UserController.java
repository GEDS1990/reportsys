package com.midnight.reportsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.midnight.reportsys.pojo.Permission;
import com.midnight.reportsys.pojo.Role;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.UserService;
import com.midnight.reportsys.util.Tools;
import com.midnight.reportsys.util.ValidateUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * ClassName: UserController <br/>
 * Function: 用户控制层. <br/>
 * date: 2016年12月31日 下午5:17:05 <br/>
 * 
 * @author Midnight
 */
@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/main")
	public String main(HttpSession session, Model model) throws Exception {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			Set<Permission> parentMenu = new TreeSet<>();
			Set<Permission> childMenu = new TreeSet<>();

			// 获取用户的角色
			ArrayList<Role> roles = userService.getRoleByUserId(user.getId());
			// 获取用户的权限菜单,
			for (int i = 0; i < roles.size(); i++) {
				Role role = roles.get(i);
				if(role.getName().equals("管理员")){
					session.setAttribute("admin", "admin");
				}
				ArrayList<Permission> lists = role.getPermissions();
				for (Permission p : lists) {
					if (p.getPid() == 0) {
					
						
						parentMenu.add(p);// 父菜单
					} else {
						childMenu.add(p);// 子菜单
					}
				}

			}
			model.addAttribute("user", user);
			model.addAttribute("parentMenu", parentMenu);
			model.addAttribute("childMenu", childMenu);
			return "main";
		}

		return "redirect:/user/login";
	}

	@RequestMapping("/checkUser")
	public String checkUser(@RequestParam("name") String account, @RequestParam("pass") String password,
			HttpSession session) throws Exception {
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		// 从数据库中获取用户
		user = userService.getUserByAccountAndPass(user);
		if (user != null) {
			session.setAttribute("user", user);
			return "redirect:/user/main";
		}

		return "redirect:/user/login";
	}

	// 添加用户
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(@RequestParam("roleId") String roleId, User user, HttpServletResponse response) throws Exception {
		PrintWriter pw = response.getWriter();
		if (ValidateUtil.isValid(user.getAccount())) {
			User sqluser = userService.getUserByAccount(user.getAccount());
			if (sqluser != null) {
				pw.write("account_exist");
			} else {
				userService.addUser(user, roleId);
				pw.write("true");
			}
		} else {
			pw.write("false");
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String getUserList(int page, int rows) throws Exception {
		int intPage = page == 0 ? 1 : page;
		int number = rows == 0 ? 10 : rows;

		Map<String, Object> jsonMap = new HashMap<>();
		List<User> list = userService.getUserList(intPage, number);
		int total = userService.getUserCount();

		
		jsonMap.put("total", total);
		jsonMap.put("rows", list);

		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] {});

		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);

		return jsonObject.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/findUserList", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public String findUserList(HttpServletResponse response, HttpServletRequest request) throws Exception {

		List<User> users = userService.getUserList();

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("users", users);
		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { "account", "password" });
		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);
		return jsonObject.getString("users");
	}

	// 更改用户密码
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public void changePass(@RequestParam("pass") String pass, HttpServletResponse response, HttpSession session)
			throws Exception {
		PrintWriter pw = response.getWriter();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			userService.updatePassword(pass, user.getId());
			pw.write("true");
		} else {
			pw.write("false");
		}

	}

	// 用户退出
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOut(HttpServletResponse response, HttpSession session) throws Exception {
		PrintWriter pw = response.getWriter();
		try {
			session.invalidate();
			pw.write("true");
		} catch (Exception e) {
			// TODO: handle exception
			pw.write("false");
		}
		return "redirect:/user/login";
	}

	// 删除用户
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestParam("id") int id, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		try {
			userService.deleteUserById(id);
			pw.write("true");
		} catch (Exception e) {
			// TODO: handle exception
			pw.write("false");
		}
	}

	/**
	 * 获取成员角色用户列表
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/findMemberUserList", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public String findMemberUserList(HttpServletResponse response, HttpServletRequest request) throws Exception {

		List<User> users = userService.findMemberUserList();

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("users", users);
		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { });
		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);
		return jsonObject.getString("users");
	}

	/**
	 * 获取组长角色用户列表
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/findLeaderUserList", method = RequestMethod.POST, produces = {
	"application/json;charset=UTF-8" })
	public String findLeaderUserList(HttpServletResponse response, HttpServletRequest request) throws Exception {

		List<User> users = userService.findLeaderUserList();

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("users", users);
		JsonConfig jsonConfig = Tools.getJsonConfig();
		// 排除不需要转换的字段new String[]{“id”，“name”}
		jsonConfig.setExcludes(new String[] { });
		JSONObject jsonObject = JSONObject.fromObject(jsonMap, jsonConfig);
		return jsonObject.getString("users");
	}
}
