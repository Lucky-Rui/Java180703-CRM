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
	<script src="${ctx}/static/common/kindeditor/kindeditor.js"></script>
	<script src="${ctx}/static/common/kindeditor/lang/zh_CN.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
  	<div class="x-body">
		<form id="form_add" onsubmit="return false" class="layui-form layui-form-pane" action="">
		<input type="hidden" id="id" name="id" value="${product.id}">
		  <div class="layui-form-item">
		    <label class="layui-form-label">商品名称</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${product.name}" name="name" autocomplete="off" value="" placeholder="请输入" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">单位</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${product.unit}" name="unit" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">商品价格</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${product.price}" name="price" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		  <div class="layui-form-item">
		    <label class="layui-form-label">库存数量</label>
		    <div class="layui-input-inline">
		      <input type="text" value="${product.stock}" name="stock" lay-verify="required" placeholder="请输入" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  
		 <div class="layui-form-item">
		    <label class="layui-form-label">商品图片</label>
		    <input type="hidden" name="image" id="image"/>
		    <div class="layui-input-block layui-upload">
			  <button type="button" class="layui-btn" id="uploadBtn">上传图片</button>
			  <div class="layui-upload-list">
			    <img width="150px" height="150px" class="layui-upload-img" id="mainImg" src="/pic/${product.image}">
			    <p id="demoText"></p>
			  </div>
			</div> 
		  </div>
		  <!-- 富文本编辑器 -->
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">商品详情</label>
		    <div class="layui-input-block">
		      <!-- 富文本加id="editor_id" --> 
		      <textarea value="${product.detail}" id="editor_id" name="detail" placeholder="请输入内容" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <input type="button" class="layui-btn" onclick="submitForm()" value="提交"/>
		</form>
	</div>

	<script>
	 	layui.config({
		    base : '${ctx}/static/lib/'
		  }).extend({
		    selectM: './layui_extends/selectM'
		  }).use(['form','upload','selectM'],function(){
		  	var form = layui.form;
		  	var upload = layui.upload;
		  	var selectM = layui.selectM;
		  	
		  //图片上传
		  var uploadInst = upload.render({
			    elem: '#uploadBtn'
			    ,url: '${ctx}/upload/uploadImg.action'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#mainImg').attr('src', result); //图片链接（base64）
			      });
			    }
			    ,done: function(resp){
			      //如果上传失败
			      if(resp.code == util.SUCCESS){
			    		//给这个隐藏标签填上value值<input type="hidden" name="mainImage" id="mainImage"/>
			    	  	$("#image").val(resp.data);
			      }
			    }
			    ,error: function(){
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			});
		});
		
		//ajax方式提交form表单
		function submitForm(){
			$.ajax({
				url : '${ctx}/product/update.action',
				data : $('#form_add').serialize(),
				type : 'POST',
				dataType : 'json',
				success : function(resp) {
					if(resp.code == util.SUCCESS) {
						mylayer.confirm("修改成功，是够跳转到商品管理界面？", "${ctx}/product/getProductPage.action");
					} else {
						mylayer.errorMsg(resp.msg);
					}
				}
			});
		}
		
		//富文本编辑器
		var myKindEditor ;
        KindEditor.ready(function(K) {
            var kingEditorParams = {
                 //指定上传文件参数名称
                 filePostName  : "file",
                 //指定上传文件请求的url。
                 uploadJson : '${ctx}/upload/uploadImgByEditor.action',
                 //上传类型，分别为image、flash、media、file
                 dir : "image",
                 afterBlur: function () { this.sync(); }
           }
           var editor = K.editor(kingEditorParams);
           
          //富文本编辑器
          myKindEditor = KindEditor.create('#form_update[name=detail]', kingEditorParams);
        });
		
	</script>
</body>
</html>