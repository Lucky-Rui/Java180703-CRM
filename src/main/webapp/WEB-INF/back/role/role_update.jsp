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
		<form id="form_add" onsubmit="return false" class="layui-form layui-form-pane" action="">
			<input id="id" type="hidden" name="id"/>
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色名称</label>
		    <div class="layui-input-block">
		      <input type="text" id="name" name="name" autocomplete="off" placeholder="请输入标题" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">角色编号</label>
		    <div class="layui-input-block">
		      <input type="text" id="sn" name="sn" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
			<label class="layui-form-label">权限</label>
			<div class="layui-input-block" id="permissions" name="permissions">
      	  </div>
		  <input type="button" class="layui-btn" onclick="submitForm()" value="提交"/>
		</form>
	</div>

	<script>
		var tagData = [{"id":1,"name":"长者","status":1},{"id":2,"name":"工厂"},{"id":14,"name":"小学生"},{"id":15,"name":"大学生"},{"id":16,"name":"研究生"},{"id":17,"name":"教师"},{"id":18,"name":"记者"}]; 
	 	layui.config({
		    base : '${ctx}/static/lib/'
		  }).extend({
		    selectM: './layui_extends/selectM'
		  }).use(['form','upload','selectM'],function(){
		  	var form = layui.form;
		  	var upload = layui.upload;
		  	var selectM = layui.selectM;
		  	
		  	$.ajax({
				url : '${ctx}/role/selectRoleAndPermisssions.action',
				type : 'POST',
				data : {"roleId" : "${param.roleId}"},
				dataType : 'json',
				success : function(resp) {
					if(resp.code == util.SUCCESS) {
						mylayer.success(resp.msg);
						
						var role = resp.data["role"];
						var allPermissions = resp.data["allPermissions"];
						//[6,10,11]
						var selectIds = resp.data["selectIds"];
						$("#id").val(role.id);
						$("#name").val(role.name);
						$("#sn").val(role.sn);
						
						 //多选标签-基本配置
					    var tagIns = selectM({
					      //元素容器【必填】
					      elem: '#permissions'
					      //候选数据【必填】
					      ,data: allPermissions
					      //默认值
					      ,selected: selectIds
					   	   // 默认最多选中5个
					      ,max : 100
					    });
					} else {
						mylayer.errorMsg(resp.msg);
					}
				}
			});
		});
		
		//ajax方式提交form表单
		function submitForm(){
			$.ajax({
				url : '${ctx}/role/update.action',
				data : $('#form_add').serialize(),
				type : 'POST',
				dataType : 'json',
				success : function(resp) {
					if(resp.code == util.SUCCESS) {
						//mylayer.success(jsonObj.msg);
						mylayer.confirm("添加成功，是够跳转到商品列表界面？", "${ctx}/role/getRolePage.action");
					} else {
						mylayer.errorMsg(resp.msg);
					}
				}
			});
		}
		
	</script>
</body>
</html>