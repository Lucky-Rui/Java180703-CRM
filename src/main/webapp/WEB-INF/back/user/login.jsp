<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>后台系统管理</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/static/back//css/font.css">
	<link rel="stylesheet" href="${ctx}/static/back//css/xadmin.css">
	
    <script src="${ctx}/static/back/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/back/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/back/js/xadmin.js"></script>
    <script src="${ctx}/static/common/js/mylayer.js"></script>
	<script src="${ctx}/static/common/js/util.js"></script>

</head>
<body class="login-bg">
    
    <div class="login">
        <div class="message">管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" id="login-form" class="layui-form" >
            <input id="name" name="name" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input id="password" name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
        </form>
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" onclick="submitForm()" type="button">
            <hr class="hr20" >
    </div>

    <script>
	    function submitForm() {
			$.ajax({
		        url : "${ctx}/user/login.action",
		        type : "POST",
		        dataType : "json",
		        data : $("#login-form").serialize(),
		        success : function(resp) {
		           if(resp.code == util.SUCCESS) {
		        	   window.location.href = "${ctx}/indexBack.action";
		           } else {
		              alert(resp.data);
		           }
		        }
		     });
		 }
    </script>

    
    <!-- 底部结束 -->
    
</body>
</html>