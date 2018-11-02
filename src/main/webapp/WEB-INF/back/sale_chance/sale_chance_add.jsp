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
		  <div class="layui-form-item">
		    <label class="layui-form-label">机会来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="chanceSource" autocomplete="off" placeholder="请输入标题" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">所属客户</label>
		    <div class="layui-input-block">
		    	<select name="customerId">
		    		<option value="">--选择客户--</option>
		    		<c:forEach items="${customerList}" var="customer">
		    			<option value="${customer.id}">${customer.name}</option>
		    		</c:forEach>
		    	</select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">意向产品</label>
		    <div class="layui-input-block">
		    	<select name="productId">
		    		<option value="">--选择客户--</option>
		    		<c:forEach items="${productList}" var="product">
		    			<option value="${product.id}">${product.name}</option>
		    		</c:forEach>
		    	</select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">指派营销人员</label>
		    <div class="layui-input-block">
		    	<select name="userId">
		    		<option value="">--选择客户--</option>
		    		<c:forEach items="${userList}" var="user">
		    			<option value="${user.id}">${user.name}</option>
		    		</c:forEach>
		    	</select>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">成功几率</label>
		    <div class="layui-input-inline">
		      <input type="text" name="successRate" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">机会描述</label>
		    <div class="layui-input-inline">
		      <input type="text" name="description" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">创建人</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${user.name}" name="createMan" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		</form>
		<button class="layui-btn" onclick="submitForm()">添加</button>
	</div>

	<script>
		layui.use(['form'], function(){
		  var form = layui.form;
		  
		});
		
		//ajax方式提交form表单
		function submitForm(){
			$.ajax({
				url : '${ctx}/saleChance/add.action',
				data : $('#form_add').serialize(),
				type : 'POST',
				dataType : 'json',
				success : function(resp) {
					if(resp.code == util.SUCCESS) {
						//mylayer.success(jsonObj.msg);
						mylayer.confirm("添加成功，是够跳转到商品列表界面？", "${ctx}/product/getProductPage.action");
					} else {
						mylayer.errorMsg(resp.msg);
					}
				}
			});
		}
		
	</script>
</body>
</html>