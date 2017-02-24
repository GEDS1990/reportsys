package com.midnight.reportsys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.midnight.reportsys.mapper.UserMapper;
import com.midnight.reportsys.pojo.Role;
import com.midnight.reportsys.pojo.User;
import com.midnight.reportsys.service.UserService;

/**
 * ClassName: UserServiceImpl <br/>
 * Function: 处理与用户信息相关的业务逻辑. <br/>
 * date: 2016年12月31日 下午5:05:37 <br/>
 * 
 * @author Midnight
 */
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 添加用户
	public void addUser(User user, int roleId) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);
		userMapper.addUser(user);// 添加用户信息到数据库
		user = getUserByAccount(user.getAccount());// 从数据库获取用户id
		addUserRole(user.getId(), roleId); // 给用户添加角色

	}

	// 添加用户角色
	public void addUserRole(int userId, int roleId) throws Exception {
		userMapper.addUserRole(userId, roleId);
	}

	// 获取全部用户
	public List<User> findAllUser() throws Exception {

		// TODO Auto-generated method stub
		return null;
	}

	// 获取用户数量
	public int getUserCount() throws Exception {

		return userMapper.getUserCount();
	}

	// 根据用户id获取用户
	public User getUserById(int id) throws Exception {

		// TODO Auto-generated method stub
		return null;
	}

	// 根据用户account获取用户
	public User getUserByAccount(String account) throws Exception {
		return userMapper.getUserByAccount(account);
	}

	// 根据用户id获取所有角色
	public ArrayList<Role> getRoleByUserId(int id) throws Exception {

		ArrayList<Role> roles = userMapper.getRoleByUserId(id);
		return roles;
	}

	// 根据帐号和密码获取用户
	public User getUserByAccountAndPass(User user) throws Exception {
		String md5pass = DigestUtils.md5Hex(user.getPassword());
		user.setPassword(md5pass);
		
		return userMapper.getUserByAccountAndPass(user);
	}

	// 获取全部用户
	public List<User> getUserList(int intPage, int number) throws Exception {

		PageHelper.startPage(intPage, number);
		return userMapper.findAllUser();
	}

	// 获取全部用户
	public List<User> getUserList() throws Exception{
		return userMapper.findAllUser();
	}

	//获取需要提交报表成员的数量 
	public int getReportMemberCount()throws Exception{
		return userMapper.getReportMemberCount();
	}
	
	//更改用户密码
	public void updatePassword(String pass,int id)throws Exception{
		String md5pass = DigestUtils.md5Hex(pass);
		userMapper.updatePassword(md5pass, id);
	}

	//删除用户
	public void deleteUserById(int id) {
		userMapper.deleteUser(id);
		
	}
}
