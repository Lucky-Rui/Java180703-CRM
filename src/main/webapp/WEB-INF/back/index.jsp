<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<%@taglib prefix="myFn" uri="http://situ.com/rbac" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>后台系统管理</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/static/back/css/font.css">
	<link rel="stylesheet" href="${ctx}/static/back/css/xadmin.css">
    
    <script src="${ctx}/static/back/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/back/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/back/js/xadmin.js"></script>
</head>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="./index.html">后台管理系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <ul class="layui-nav left fast-add" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('资讯','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>资讯</a></dd>
              <dd><a onclick="x_admin_show('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a></dd>
               <dd><a onclick="x_admin_show('用户','http://www.baidu.com')"><i class="iconfont">&#xe6b8;</i>用户</a></dd>
            </dl>
          </li>
        </ul>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">admin</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>
              <dd><a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a></dd>
              <dd><a href="./login.html">退出</a></dd>
            </dl>
          </li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
        	<c:if test="${myFn:checkPermission('spgl:/product/getPrductPage.action')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe6b8;</i>
	                    <cite>商品管理</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                    <li>
	                        <a _href="${ctx}/product/getProductPage.action">
	                            <i class="iconfont">&#xe6a7;</i>
	                            <cite>商品列表</cite>
	                        </a>
	                    </li >
	                </ul>
	            </li>
            </c:if>
            <c:if test="${myFn:checkPermission('yxgl:/saleChance/getSaleChancePage.action')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe6b8;</i>
	                    <cite>营销管理</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                    <li>
	                        <a _href="${ctx}/saleChance/getSaleChancePage.action">
	                            <i class="iconfont">&#xe6a7;</i>
	                            <cite>营销列表</cite>
	                        </a>
	                    </li >
	                </ul>
	            </li>
            </c:if>
            <c:if test="${myFn:checkPermission('ddgl:/customer/getCustomerPage.action')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe6b8;</i>
	                    <cite>客户管理</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                    <li>
	                        <a _href="">
	                            <i class="iconfont">&#xe6a7;</i>
	                            <cite>客户列表</cite>
	                        </a>
	                    </li >
	                </ul>
	            </li>
            </c:if>
            <c:if test="${myFn:checkPermission('ddgl:/order/getOrderPage.action')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe723;</i>
	                    <cite>订单管理</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                    <li>
	                        <a _href="${ctx}/order/getOrderPage.action">
	                            <i class="iconfont">&#xe6a7;</i>
	                            <cite>订单列表</cite>
	                        </a>
	                    </li >
	                </ul>
	            </li>
            </c:if>
            <c:if test="${myFn:checkPermission('tjtb')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe6ce;</i>
	                    <cite>统计图表</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                	<c:if test="${myFn:checkPermission('tjtb:/charts/getProductAmount.action')}">
		                    <li>
		                        <a _href="">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>商品数量统计</cite>
		                        </a>
		                    </li >
	                    </c:if>
	                    <c:if test="${myFn:checkPermission('tjtb:/charts/getProductSale.action')}">
		                    <li>
		                        <a _href="">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>商品销量统计</cite>
		                        </a>
		                    </li>
	                    </c:if>
	                </ul>
	            </li>
            </c:if>
            <c:if test="${myFn:checkPermission('yhqx')}">
	            <li>
	                <a href="javascript:;">
	                    <i class="iconfont">&#xe726;</i>
	                    <cite>用户&权限管理</cite>
	                    <i class="iconfont nav_right">&#xe697;</i>
	                </a>
	                <ul class="sub-menu">
	                	<c:if test="${myFn:checkPermission('yhqx:/user/getUserPage.action')}">
		                    <li>
		                        <a _href="${ctx}/user/getUserPage.action">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>用户管理</cite>
		                        </a>
		                    </li >
	                    </c:if>
	                    <c:if test="${myFn:checkPermission('yhqx:/user/getRolePage.action')}">
		                    <li>
		                        <a _href="${ctx}/role/getRolePage.action">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>角色管理</cite>
		                        </a>
		                    </li >
	                    </c:if>
	                    <c:if test="${myFn:checkPermission('yhqx:/user/getPermissionPage.action')}">
		                    <li>
		                        <a _href="${ctx}/permission/getPermissionPage.action">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>权限管理</cite>
		                        </a>
		                    </li >
	                    </c:if>
	                </ul>
	            </li>
            </c:if>
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='./welcome.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">made in china</div>  
    </div>
    <!-- 底部结束 -->

    
</body>
</html>