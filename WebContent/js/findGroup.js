$(function(){


	$('#tt').datagrid({
		url:'group/getGroupList',
		toolbar:'#tb',
		pagination:true,
		title:'分组列表',
		fitColumns:false,
		pagination:true,
		iconCls:'icon-save',
		singleSelect:true,
		pageList:[2,5,10,15]
	});


});


