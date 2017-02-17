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
	<table class="easyui-datagrid" style="width:1200px;height:600px"
        data-options="url:'statistics/getReportCount',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th field="type" width="100">类型</th>
				<th field="content" width="700">内容</th>
			</tr>
		</thead>
	</table>

</body>
</html>