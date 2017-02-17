$(function() {

	$('#tt').datagrid({
		url : 'user/getUserList',
		toolbar : '#tb',
		pagination : true,
		title : '用户列表',
		fitColumns : false,
		pagination : true,
		iconCls : 'icon-save',
		singleSelect : true,
		pageList : [ 2, 5, 10, 15 ]
	});
});

function deleteUser() {
	var row = $('#tt').datagrid('getSelected');
	if (row) {
		$.messager.confirm('操作提示', '是否要删除数据?', function(r) {
			if (r) {
				$.post('user/deleteUser', {
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
		$.messager.alert("操作提示", "请选择要修改的用户", "error");
	}

	return true;
}
