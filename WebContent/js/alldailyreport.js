$(function() {

	$('#tt').datagrid({
		url : 'report/getReportList/allDaily',
		toolbar : '#tb',
		pagination : true,
		title : '报表列表',
		fitColumns : false,
		pagination : true,
		iconCls : 'icon-save',
		singleSelect : true,
		pageList : [ 2, 5, 10, 15 ]
	});

});

function deleteReport() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		$.messager.confirm('操作提示', '是否要删除数据?', function(r) {
			if (r) {
				$.post('report/deleteReport', {
					'id' : row.id
				}, function(data) {

					if (data == true || data == 'true') {
						$('#tt').datagrid('reload');
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
function downloadReport() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		window.location.href="report/downloadReport/daily?id="+row.id;  
		

	} else {
		$.messager.alert("操作提示", "请选择要下载的报表", "error");
	}

	return true;
}
