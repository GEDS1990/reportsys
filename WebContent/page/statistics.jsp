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
	<table class="easyui-datagrid" style="width: 100%; height: 20%"
		data-options="url:'statistics/getReportCount',fitColumns:true,singleSelect:true">
		<thead>
			<tr>
				<th field="type" width="100">类型</th>
				<th field="content" width="700">内容</th>
			</tr>
		</thead>
	</table>

	<table id="tt" style="width: 100%; height: 80%">

		<thead>
			<tr>

				<th field="account" width="100">帐号</th>
				<th field="username" width="150">用户名</th>
				<th field="daily" width="150">日报表</th>
				<th field="weekly" width="150">周报表</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		$(function() {

			$('#tt').datagrid({
				url : 'statistics/getMemberUserReport',
				pagination : true,
				title : '用户列表',
				fitColumns : false,
				pagination : true,
				iconCls : 'icon-save',
				singleSelect : true
			});
			 //设置分页控件 
		    var p = $('#tt').datagrid('getPager'); 
		    $(p).pagination({ 
		        pageSize: 10,//每页显示的记录条数，默认为10 
		        pageList: [2,5,10,15],//可以设置每页记录条数的列表 
		        beforePageText: '第',//页数文本框前显示的汉字 
		        afterPageText: '页    共 {pages} 页', 
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
		        
		    }); 
		});
	</script>
</body>
</html>