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
<script type="text/javascript" src='js/findGroup.js'></script>


</head>

<body>



	<table class="easyui-datagrid" style="width: 100%; height: 20%"
		data-options="url:'group/findMyGroups',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'name',width:150">小组名称</th>
				<th data-options="field:'leader',width:200">组长</th>
				<th data-options="field:'member',width:400">组员</th>
			</tr>
		</thead>

	</table>


	<table class="easyui-datagrid" style="width: 100%; height: 80%"
		data-options="url:'group/findMyGroupsReport',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th data-options="field:'role',width:150"></th>
				<th data-options="field:'name',width:200"></th>
				<th data-options="field:'daily',width:200"></th>
					<th data-options="field:'weekly',width:200"></th>
			</tr>
		</thead>

	</table>

</body>


</html>
