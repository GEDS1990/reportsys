<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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


</head>
<body>
	<table class="easyui-datagrid" style="width: 1150px; height: 160px"
		data-options="url:'notice/findNotice',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'content',width:200">公告信息</th>
				<th data-options="field:'createTime',width:200">发布时间</th>
			</tr>
		</thead>
	</table>

	<hr />
	<table class="easyui-datagrid" style="width: 1150px; height: 160px"
		data-options="url:'notice/getTemplate',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'name',width:100">名称</th>
				<th data-options="field:'createTime',width:240">上传时间</th>
				<th data-options="field:'downloadUrl',width:100">下载</th>
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
	</body>
</html>