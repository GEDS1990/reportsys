<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/mycss.css" />
<script type="text/javascript"
	src="jquery-easyui/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src='js/user.js'></script>


</head>

<body>



	<table id="tt" style="width: 100%; height: 500px">

		<thead>
			<tr>
				<th field="id" width="100">序号</th>
				<th field="name" width="100">用户名1111</th>
				<th field="pass" width="150">密码</th>
			</tr>
		</thead>
	</table>

	<div id="tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="addUser()">添加用户</a> 
			<a class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="editUser()">编辑用户</a>
			<a class="easyui-linkbutton"
				iconCls="icon-remove" plain="true" onclick="deleteUser()">删除用户</a>
		</div>
	</div>

	<div id="newUser" class=" easyui-dialog"
		style="top: 40px; padding: 5px; width: 700px; height: 200px;"
		title="添加用户" iconCls="icon-ok" closed="true" modal="true">

		<form id="newUser_form" name="ff" enctype="multipart/form-data"
			method="post">

			<table style="width: 100%; font-size: 12px; font-weight: normal">



				<tr>
					<td class="td1">用户名:</td>
					<td style="width: 70%"><input class="easyui-textbox"
						type="text" name="name"
						data-options="required:true,multiline:true"
						style="width: 100px; height: 30px;"></td>
				</tr>

				<tr>
					<td class="td1">密码:</td>
					<td style="width: 70%"><input class="easyui-textbox"
						type="text" name="pass" style="width: 100px; height: 30px;"></td>
				</tr>
				<tr>
					<td style="text-align: right" colspan=2><input type="button"
						onclick="newUser_cancel()" class="subbtn" value="取消"> <input
						type="submit" class="subbtn" value="提交"></td>
				</tr>

			</table>
		</form>
	</div>


	<div id="editUser" class=" easyui-dialog"
		style="top: 40px; padding: 5px; width: 700px; height: 200px;"
		title="修改用户" iconCls="icon-ok" closed="true" modal="true">

		<form id="editUser_form" name="ff" enctype="multipart/form-data"
			method="post">

			<table style="width: 100%; font-size: 12px; font-weight: normal">


				<input id="editUserID" type="hidden" name="id">
				<tr>
					<td class="td1">用户名:</td>
					<td style="width: 70%"><input id="editUserName"
						class="easyui-textbox" type="text" name="name"
						data-options="required:true"
						style="width: 100px; height: 30px;"></td>
				</tr>

				<tr>
					<td class="td1">密码:</td>
					<td style="width: 70%"><input id="editUserPass"
						class="easyui-textbox" type="text" name="pass"
						style="width: 100px; height: 30px;"></td>
				</tr>
				<tr>
					<td style="text-align: right" colspan=2><input type="button"
						onclick="editUser_cancel()" class="subbtn" value="取消"> <input
						type="submit" class="subbtn" value="提交"></td>
				</tr>

			</table>
		</form>
	</div>





</body>


</html>
