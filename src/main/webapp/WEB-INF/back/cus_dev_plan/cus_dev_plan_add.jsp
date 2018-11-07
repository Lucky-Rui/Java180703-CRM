<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
    <title></title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/static/back/css/font.css">
    <link rel="stylesheet" href="${ctx}/static/back/css/xadmin.css">
   
    <script src="${ctx}/static/back/lib/jquery/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/back/lib/layui/layui.js" charset="utf-8"></script>
    <script src="${ctx}/static/back/js/xadmin.js"></script>
    <script src="${ctx}/static/common/js/mylayer.js"></script>
	<script src="${ctx}/static/common/js/util.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  	<div class="x-body">
		<form id="form_add" class="layui-form layui-form-pane" action="">
		<input type="hidden" id="saleChanceId" name="saleChanceId" value="${id}">
		  <div class="layui-form-item">
		    <label class="layui-form-label">计划项</label>
		    <div class="layui-input-block">
		      <input type="text" name="planItem" autocomplete="off" placeholder="请输入" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		      <label class="layui-form-label">计划日期</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" id="planDate" name="planDate" type="text" placeholder="yyyy-MM-dd">
		      </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">计划效果</label>
		    <div class="layui-input-block">
		      <input type="text" name="exeAffect" autocomplete="off" placeholder="请输入" class="layui-input">
		    </div>
		  </div>
		</form>
		<button class="layui-btn" onclick="submitForm()">添加</button>
	</div>

	<script>
		layui.use(['form','laydate'], function(){
		  var form = layui.form;
		  var laydate = layui.laydate;
		  
		  laydate.render({
			    elem: '#planDate'
			});
		});
		
		//ajax方式提交form表单
		function submitForm(){
			$.ajax({
				url : '${ctx}/cusDevPlan/add.action?saleChanceId=${id}',
				data : $('#form_add').serialize(),
				type : 'POST',
				dataType : 'json',
				success : function(resp) {
					if(resp.code == util.SUCCESS) {
						mylayer.confirm("添加成功，是够跳转到客户开发界面？", "${ctx}/cusDevPlan/getCusDevPlanPage.action");
						refresh();
					} else {
						mylayer.errorMsg(resp.msg);
					}
				}
			});
		}
		
		//更新开发项信息
		function refresh(){
			var id = $("#saleChanceId").val();
			location.href = "${ctx}/cusDevPlan/getCusDevPlanPage.action?id="+id;
		}
		
	</script>
</body>
</html>