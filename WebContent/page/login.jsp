<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报表管理系统</title>
<link rel="stylesheet" href="css/main.css" type="text/css"></link>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<script type="text/javascript" src="jquery-easyui/js/html5shiv.js"></script>
<script type="text/javascript" src="jquery-easyui/js/respond.min.js"></script>
</head>
<body>
	<div id="login_area">
		<div id="login_box">
			<div id="login_picture">
				<img src="images/login/background.jpg" />
			</div>
			<div id="login_form">
				<form id="loginForm" method="post" action="user/checkUser" 
					class="form-horizontal" role="form">
					<div class="form-group">
						<label for="name" class="col-sm-4 control-label">帐&nbsp;&nbsp;&nbsp;&nbsp;号:
							</h2>
						</label>
						<div class="col-sm-6">
							<input type="text" id="name" name="name"
								class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-4 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码:
							</h2>
						</label>
						<div class="col-sm-6">
							<input type="password" id="pass" name="pass"
								class="form-control">
						</div>
					</div>
					<div class="form-group" id="c1">
						<div class="col-sm-offset-4 col-sm-10">
							<button type="submit" class="btn btn-success ">登录</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-success ">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<script type="text/javascript"
		src="jquery-easyui/js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="jquery-easyui/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="jquery-easyui/js/jquery.validate.js"></script>
	<script type="text/javascript" src="jquery-easyui/js/form.js"></script>
	<script type="text/javascript" charset="utf-8">
		 MyValidator.init();
	</script>
</body>
</html>
