<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<script type="text/javascript" src='js/dailyreport.js'></script>

</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="上传报表"
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
				url : 'report/uploadReport/daily',
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


	<br />



	<table id="tt" style="width: 100%; height: 500px">

		<thead>
			<tr>
		
				<th field="name" width="300">名称</th>
				<th field="createTime" width="240">上传时间</th>
				<th field="downloadUrl" width="240">下载</th>
			</tr>
		</thead>

	</table>

	<div id="tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteReport()">删除报表</a> 

		</div>

	</div>

</body>
</html>