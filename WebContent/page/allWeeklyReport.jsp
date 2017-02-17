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
<script type="text/javascript" src='js/allweeklyreport.js'></script>

</head>
<body>

	<hr />
	<hr />
	<hr />

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
	
		
	</div>
</body>
</html>