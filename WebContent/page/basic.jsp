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
	<h2>Custom Calendar</h2>
	<p>This example shows how to custom the calendar date by using 'formatter' function.</p>
	<div style="margin:20px 0"></div>
	
	<div class="easyui-calendar" style="width:250px;height:250px;" data-options="formatter:formatDay"></div>
			
	<script>
		var d1 = Math.floor((Math.random()*30)+1);
		var array = [01,02,03,04,5,6,7,8,9];
		var d2 = Math.floor((Math.random()*30)+1);
		function formatDay(date){
			var m = date.getMonth()+1;
			var d = date.getDate();
			var opts = $(this).calendar('options');
			for( i=0; i<array.length; ++i){
				if (opts.month == m && d == array[i]){
					return '<div class="icon-ok md">' + d + '</div>';
				} 
				
				
			}
		
				return d;
			
		}
	</script>
	<style scoped="scoped">
		.md{
			height:16px;
			line-height:16px;
			background-position:2px center;
			text-align:right;
			font-weight:bold;
			padding:0 2px;
			color:red;
		}
	</style>
</body>
</html>