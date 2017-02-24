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
			<!-- <a class="easyui-linkbutton" iconCls="icon-download" plain="true"
				onclick="downloadReport()">下载报表</a> -->
			<input id="name" class="easyui-searchbox"
				data-options="prompt:'Please Input Value',searcher:doSearch"
				style="width: 300px"></input>
			<script>
			function doSearch(){
				var reportName = $.trim($('#name').val());
				reportName = "周报表_"+reportName;
				
				if(reportName != '' && reportName != null){
					
					$('#tt').datagrid({
						url : 'report/getReportList/'+encodeURI(encodeURI(reportName)),
						toolbar : '#tb',
						pagination : true,
						title : '报表列表',
						fitColumns : false,
						pagination : true,
						iconCls : 'icon-save',
						singleSelect : true,
						pageList : [ 2, 5, 10, 15 ]
					});
				}
				
			}
			</script>
		</div>

	</div>
</body>
</html>