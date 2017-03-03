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


	<p style="font-size: 3px">
		<img alt="打钩表示当天报表已提交" src="images/ok.png" />表示当天报表已提交
	</p>

	<div style="margin: 60px"></div>

	<div style="margin: 0 60 0 10; float: left;">
		<p style="font-size: 23px">${report_user}的${type}</p>
		<div id="daily" class="easyui-calendar"
			style="width: 250px; height: 250px;"
			data-options="formatter:formatDay"></div>

		<script type="text/javascript">
			var s = "${report_str}";

			var strs = new Array();
			strs = s.split(","); //字符分割 

			function formatDay(date) {
				var d = date.getDate();
				var opts = $(this).calendar('options');
				for (i = 0; i < strs.length; ++i) {
					var ymd = opts.year + "-" + opts.month + "-" + d;

					if (ymd == strs[i]) {
						return '<div class="icon-ok md">' + d + '</div>';
					}
				}

				return d;
			}
		</script>
		<style scoped="scoped">
.md {
	height: 16px;
	line-height: 16px;
	background-position: 2px center;
	text-align: right;
	font-weight: bold;
	padding: 0 2px;
	color: red;
}
</style>
	</div>



</body>
</html>