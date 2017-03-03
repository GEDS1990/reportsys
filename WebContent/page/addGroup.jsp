<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>创建分组</title>

<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui/js/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/demo.css">
<script type="text/javascript" src="jquery-easyui/js/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui/js/jquery.easyui.min.js"></script>


</head>
<body>

	<div style="margin: 20px 0;"></div>
	<div class="easyui-panel" title="创建分组"
		style="width: 400px; padding: 10px 60px 20px 60px">
		<form id="ff"  method="post">
			<table cellpadding="5">

				<tr>
					<td>小组名称:</td>
					<td><input name="name" class="easyui-validatebox textbox"
						data-options="required:true"></td>
				</tr>


				<tr>
					<td>组&nbsp;&nbsp;长:</td>
					<td><input id="cc1" class="easyui-combobox" name="leader" style="height: 40px"
						data-options="required:true">

					</td>
				</tr>

				<tr>
					<td>成&nbsp;&nbsp;员:</td>
					<td><input id="cc2" class="easyui-combobox" name="member" style="height: 80px"
						data-options="required:true,multiple:true,multiline:true,valueField:'id',textField:'username',url:'user/findMemberUserList'">

					</td>
				</tr>
				
			</table>
		</form>
		<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清除</a>
	    </div>
	</div>


	<script  type="text/javascript">
	$('#cc1').combobox({
		multiple:true,
		multiline:true,
	    url:'user/findLeaderUserList',
	    valueField:'id',
	    textField:'username'
	});
	
		function submitForm(){
			$('#ff').form('submit',{
				url:'group/addGroup',
				onSubmit:function(){
					if($(this).form('validate')){
						$.messager.progress({
							title:'稍等',
							msg:'正在操作中...',
							text: '操作中'
						});
						return true;
					}else{
						return false;
					}
				},
				success:function(data){
					$.messager.progress('close');
					
					if(data==true||data=='true'){
						clearForm();
						$.messager.alert('消息','操作成功','info');
	
					}
					
					if(data=='false'){
						$.messager.alert('消息','操作失败','error');
					}
				}		
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>


	

</body>
</html>
