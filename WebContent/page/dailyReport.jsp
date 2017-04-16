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


</head>
<body>
	<div style="margin: 0 120 0 10; float: left;">
		<div style="margin: 20px 0;"></div>
		<div class="easyui-panel" title="上传日报表"
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
						clearForm();
						if (data == true || data == 'true') {
							$('#dailyReport').datagrid('reload');
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
	</div>


	<div style="margin: 0 0 10 0; float: right;">

		<div id="daily" class="easyui-calendar"
			style="width: 250px; height: 250px;"
			data-options="formatter:formatDay"></div>

		<script type="text/javascript">
			var s = "";
			$.ajax({
				cache : false,
				async : false,
				type : 'post',

				url : "statistics/memberStatistics/daily",
				success : function(data) {

					s = data;
				}
			});
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
		<p style="font-size: 3px">
			<img alt="打钩表示当天报表已提交" src="images/ok.png" />表示当天报表已提交
		</p>
	</div>



	<br />



	<table id="dailyReport" style="width:100%; height: 400px">

		<thead>
			<tr>
			
				<th field="name" width="30%">名称</th>
				<th field="createTime" width="30%">上传时间</th>
				<th field="downloadUrl" width="20%">下载</th>
				<th field="preview" width="20%">查看</th>
				
			</tr>
		</thead>

	</table>

	<div id="tb">
		<div>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true"
				onclick="deleteDailyReport()">删除报表</a>

		</div>

	</div>
<script type="text/javascript">
$(function() {

	$('#dailyReport').datagrid({
		url : 'report/getReportList/daily',
		toolbar : '#tb',
		pagination : true,
		title : '报告列表',
		fitColumns : false,
		pagination : true,
		iconCls : 'icon-save',
		singleSelect : true,
		pageList : [ 2, 5, 10, 15 ]
	});

});

function deleteDailyReport() {
	var row = $('#dailyReport').datagrid('getSelected');
	
	if (row) {
		
		$.messager.confirm('操作提示', '是否要删除数据?', function(r) {
			
			if (r) {
				
				$.post('report/deleteReport', {
					'id' : row.id
				}, function(data) {

					if (data == true || data == 'true') {
						$('#dailyReport').datagrid('reload');
						$.messager.alert("操作提示", "操作成功", "info");
					} else {
						$.messager.show({ // show error message
							title : 'info',
							msg : '删除失败'
						});
					}
				});
			}
		});

	} else {
		$.messager.alert("操作提示", "请选择要删除的报表", "error");
	}

	return true;
}
</script>
</body>
</html>