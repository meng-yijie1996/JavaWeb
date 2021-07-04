<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html><!--第一阶段：表单验证-->
<html>
<head>
<meta charset="UTF-8">
<title>极客社区会员注册页面</title>

	<%--静态包含，base标签、css样式、jQuery文件--%>
	<%@ include file="/pages/common/head.jsp"%>

	<script>
		//页面加载完成后
		$(function () {
			$("#username").blur(function () {
				//获取用户名
				var username=this.value;
				$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistsUsername&username="+username,function (data) {
					if (data.existsUsername){
						$("span.errorMsg").text("用户名已存在！");
					}else {
						$("span.errorMsg").text("用户名可用！");
					}
				});
			});




			//给验证码的图片绑定单击事件
			$("#code_img").click(function () {
				// alert(11);
				// 在事件响应的 function 函数中有一个 this 对象。这个 this 对象，是当前正在响应事件的 dom 对象
				// src 属性表示验证码 img 标签的 图片路径。它可读，可写
				this.src="${basePath}kaptcha.jpg?d="+new Date();
			});

			//给注册绑定单击事件
			$("#sub_btn").click(function () {
				//验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位

				//1.获取用户名输入框里的内容
				var $name = $("#username").val();
				//2.创建正则表达式对象
				var namepatt=/^\w{5,12}$/;
				//3.使用test()方法验证
				//如果不合法：
				if(!namepatt.test($name)){
					//4.提示用户结果
					$("span.errorMsg").text("用户名不合法！");
					//不合法则不会跳转
					return false;
				}

				// 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位

				//1.获取密码输入框里的内容
				var $password = $("#password").val();
				//2.创建正则表达式对象
				var passwordpatt=/^\w{5,12}$/;
				//3.使用test()方法验证
				//如果不合法：
				if(!passwordpatt.test($password)){
					//4.提示用户结果
					$("span.errorMsg").text("密码不合法！");
					//不合法则不会跳转
					return false;
				}
				// 验证确认密码：和密码相同

				//1.获取确认密码内容
				var $repwd = $("#repwd").val();
				//2.和密码比较
				if ($repwd!=$password){
					//3.提示用户
					$("span.errorMsg").text("确认密码和密码不一致！");

					//不合法则不会跳转
					return false;
				}

				// 邮箱验证：xxxxx@xxx.com

				//1.获取邮箱内容
				var emailText = $("#email").val();
				//2.创建正则表达式对象
				var emailPatt=/^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				//3.使用test()方法验证
				if (!emailPatt.test(emailText)){
					//4.提示用户结果
					$("span.errorMsg").text("邮箱格式不合法！");

					//不合法则不会跳转
					return false;
				}

				// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。

				var codeText = $("#code").val();
				codeText=$.trim(codeText);
				if(codeText == null || codeText == ""){
					$("span.errorMsg").text("验证码不能为空！");

					//不合法则不会跳转
					return false;
				}
				//合法后：提示不能出现
				$("span.errorMsg").text("");

			});
		});
	</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册极客社区会员</h1>
								<span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null? "":request.getAttribute("msg")%>--%>
                                    ${requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist" />
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
<%--										   value="<%=request.getAttribute("username")==null? "":request.getAttribute("username")%>"--%>
										   value="${requestScope.username}"
                                           autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
<%--										   value="<%=request.getAttribute("email")==null? "":request.getAttribute("email")%>"--%>
										   value="${requestScope.email}"
                                           autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 80px;" id="code"/>
									<img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />

								</form>
							</div>

						</div>
					</div>
				</div>
			</div>

		<%--静态包含，页脚	--%>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>