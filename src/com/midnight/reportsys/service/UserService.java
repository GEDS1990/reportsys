package com.midnight.reportsys.service;

import java.util.ArrayList;
import java.util.List;

import com.midnight.reportsys.dto.UserDTO;
import com.midnight.reportsys.pojo.Role;
import com.midnight.reportsys.pojo.User;

/**
 * ClassName: UserService <br/>
 * Function: 处理与用户信息相关的业务逻辑. <br/>
 * date: 2016年12月31日 下午5:00:26 <br/>
 * 
 * @author Midnight
 */
public interface UserService {

	// 添加用户
	void addUser(User user, String roleId) throws Exception;

	// 添加用户角色
	void addUserRole(int userId, int roleId) throws Exception;

	// 获取全部用户
	List<User> findAllUser() throws Exception;

	// 获取用户数量
	int getUserCount() throws Exception;

	// 根据用户id获取用户
	User getUserById(int id) throws Exception;

	// 根据用户account获取用户
	User getUserByAccount(String account) throws Exception;

	// 根据用户id获取所有角色
	ArrayList<Role> getRoleByUserId(int id) throws Exception;

	// 根据帐号和密码获取用户
	User getUserByAccountAndPass(User user) throws Exception;

	// 获取指定分页的全部用户
	List<User> getUserList(int intPage, int number) throws Exception;

	// 获取全部用户
	List<User> getUserList() throws Exception;

	// 获取需要提交报表成员的数量
	int getReportMemberCount() throws Exception;

	// 更改用户密码
	void updatePassword(String pass, int id) throws Exception;

	// 删除用户
	void deleteUserById(int id);

	// 获取成员角色的用户列表
	List<User> findMemberUserList();

	// 获取组长或者管理角色的用户列表
	List<User> findLeaderUserList();
	
	//需要提交报表的成员
	List<UserDTO> findMemberUser(int intPage, int number);
	
	//需要提交报表的成员的數量
	int findMemberUserCount() throws Exception;
}
