<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<base href="<%=basePath%>">
<title>报表管理系统</title>
<META http-equiv="X-UA-Compatible" content="IE=8"></META>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/icon.css" />
<script type="text/javascript"
	src="jquery-easyui/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/js/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" src='js/main.js'></script>


<style type="text/css">
a {
	color: #385170;
	text-decoration: none !important;
	++
}

a:HOVER {
	color: red;
}

/* .nav-item {
	background: url("jquery-easyui/images/btn.jpg") no-repeat;
	width: 200px;
	height: 30px;
	text-align: center;
	padding-top: 13px;
	display: block;
} */
.nav-item {
	
}

.nav-item:HOVER {
	bgcolor: green;
	color: red;
}
#parent{
overflow: auto; font-size: 20px; display: block; width: 201px; height: 100px !important;

}
</style>


</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="jquery-easyui/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>

	</noscript>




	<div class="easyui-layout" data-options="fit:true"
		style="width: 100%; height: 450px;">


		<div data-options="region:'north',split:true"
			style="overflow: hidden; height: 30px; background: url(jquery-easyui/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
			<span style="float: right; padding-right: 20px;" class="head">${user.username}

				<a class="easyui-linkbutton" plain="true" onclick="editPass()">修改密码</a>

				<a class="easyui-linkbutton" plain="true" href="user/logOut">安全退出</a>
			</span> <span style="padding-left: 10px; font-size: 16px;"><img
				src="jquery-easyui/images/blocks.gif" width="20" height="20"
				align="absmiddle" /> 欢迎使用该系统</span>
		</div>

		<div data-options="region:'west',split:true" title="导航菜单"
			style="width: 208px; height: 800px" id="west">



			<div id="aa" class="easyui-accordion" fit="true" border="false">


				<c:forEach items="${parentMenu}" var="parent" varStatus="ddd">

					<c:if test="${!empty parent.url}">
						<ul>
							<div class="nav-item">
								<a
									href="javascript:addTab('${parent.name}','page/${parent.url}')">
									<span class="icon ${parent.iconCls}"></span>${parent.name}</a>
							</div>
						</ul>
					</c:if>

					<c:if test="${empty parent.url}">
						<div id="parent" title="${parent.name}"
							data-options="iconCls:'${parent.iconCls}'">
							
							<ul>
								<c:forEach items="${childMenu}" var="child" varStatus="dddd">
									<c:if test="${parent.id == child.pid}">
										<li><div class="nav-item">
												<a
													href="javascript:addTab('${child.name}','page/${child.url}')">
													<span class="icon ${child.iconCls}"></span>${child.name}</a>
											</div></li>
									</c:if>

								</c:forEach>
							</ul>
						</div>
					</c:if>

				</c:forEach>
			</div>

		</div>
		<div data-options="region:'south'"
			style="height: 30px; background: #D2E0F2;">
			<div class="footer">大数据应用技术团队</div>
		</div>
		<div id="mainPanle" data-options="region:'center'"
			style="padding: 10px">



			<div id="tabs" class="easyui-tabs" fit="true" border="false">
				<div title="系统公告"
					style="width: 1150px; height: 800px" id="home">
					<table class="easyui-datagrid" style="width: 1150px; height: 160px"
						data-options="url:'notice/findNotice',fitColumns:true,singleSelect:true">
						<thead>
							<tr>
								<th data-options="field:'content',width:650">公告信息</th>
								<th data-options="field:'createTime',width:500">发布时间</th>
							</tr>
						</thead>
					</table>
<hr />
	<table class="easyui-datagrid" style="width: 1150px; height: 160px"
		data-options="url:'notice/getTemplate',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'name',width:350">名称</th>
				<th data-options="field:'createTime',width:300">上传时间</th>
				<th data-options="field:'downloadUrl',width:500">下载</th>
			</tr>
		</thead>
	</table>

	<hr />
	<c:if test="${sessionScope.admin != null }">
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="上传模板文件"
		style="width: 400px; padding: 10px 60px 20px 60px">
		<form id="ff" enctype="multipart/form-data" method="post">
			<table cellpadding="5">
				<p>文件不要超过5M</p>
				<tr>

					<td><input type="file" name="reportUrl"
						class="easyui-validatebox textbox" data-options="required:true"></td>
				</tr>


			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">清除</a>
		</div>
	</div>
	<br></br>
	<br></br>
<div style="margin:400px;"></div>


	<script type="text/javascript">
		function submitForm() {
			$('#ff').form('submit', {
				url : 'report/uploadReport/template',
				onSubmit : function() {
					if ($(this).form('validate')) {
						$.messager.progress({
							title : '稍等',
							msg : '正在操作中...',
							text : '操作中'
						});
						return true;
					} else {
						return false;
					}
				},
				success : function(data) {
					$.messager.progress('close');

					if (data == true || data == 'true') {
						$('#tt').datagrid('reload');
						$.messager.alert('消息', '操作成功', 'info');

					}
					if (data == 'deadline') {
						$.messager.alert('消息', '超过截止时间', 'info');
					}
					if (data == 'oversize') {
						$.messager.alert('消息', '文件过大', 'info');
					}
					if (data == 'false') {
						$.messager.alert('消息', '操作失败', 'error');
					}
				}
			});
		}
		function clearForm() {
			$('#ff').form('clear');
		}
	</script>

	
	</c:if>
				</div>
			</div>
		</div>
	</div>



	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton"
					onclick="javascript:changePass()"> 确定</a> <a
					class="easyui-linkbutton" href="javascript:void(0)"
					onclick="closeLogin()">取消</a>
			</div>
		</div>
	</div>
	<!--
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
  -->


	<div id="editPass" class=" easyui-dialog"
		style="top: 40px; padding: 5px; width: 700px; height: 200px;"
		title="修改密码" iconCls="icon-ok" closed="true" modal="true">

		<form id="editPass_form" name="ff" enctype="multipart/form-data"
			method="post">

			<table style="width: 100%; font-size: 12px; font-weight: normal">



				<tr>
					<td>新密码：</td>
					<td><input id="newPass" type="Password" /></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input id="rePass" type="Password" /></td>
				</tr>
				<tr>
					<td style="text-align: right" colspan=2><input type="button"
						onclick="editPass_cancel()" class="subbtn" value="取消"> <input
						type="button" onclick="changePass_submit()" class="subbtn"
						value="提交"></td>
				</tr>

			</table>
		</form>
	</div>
	<script type="text/javascript">
		function editPass() {
			$('#editPass').dialog('open');
			return true;
		}
		function editPass_cancel() {
			$(".panel-tool-close").click();
			$('#editPass_form').form('clear');
		}

		//修改密码
		function changePass_submit() {
			var $newpass = $('#newPass');
			var $rePass = $('#rePass');

			if ($newpass.val() == '') {
				msgShow('系统提示', '请输入密码！', 'warning');
				return false;
			}
			if ($rePass.val() == '') {
				msgShow('系统提示', '请在一次输入密码！', 'warning');
				return false;
			}

			if ($newpass.val() != $rePass.val()) {
				msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
				return false;
			}

			$.post('user/changePass?pass=' + $newpass.val(), function(data) {

				if (data == true || data == 'true') {
					msgShow('消息', '密码修改成功！', 'info');
				}
				if (data == false || data == 'false') {
					msgShow('消息', '操作失败!', 'error');
				}

				$newpass.val('');
				$rePass.val('');
				$('#editPass_form').form('clear');
				$('#editPass').dialog('close');

			});

		}
		function logOut() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					$.post('user/logOut', function(data) {

					});

				}
			});
		}
	</script>
</body>
</html>
