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
  	<div class="demoTable">
	  <button class="layui-btn" data-type="win">开发成功</button>
	  <button class="layui-btn" data-type="loser">开发失败</button>
	  <button class="layui-btn" data-type="add"><i class="layui-icon">&#xe654;</i>添加</button>
	</div>
	<table id="tableId" lay-filter="tableFilter"></table>

	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	
	<script>
		layui.use(['table','laydate'], function() {
			var table = layui.table;
			var laydate = layui.laydate;
			
			//执行一个laydate实例
			laydate.render({
			   elem: '#searchTime', //指定元素
			});
			
			table.render({
			    elem: '#tableId'
			    ,url: '${ctx}/cusDevPlan/pageList.action?id=${id}' //数据接口
			    ,page: true //开启分页
			    ,id : "layUITableId" //设定容器唯一ID，id值是对表格的数据操作方法上是必要的传递条件，它是表格容器的索引
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
			      ,{field: 'saleChanceId', title: '销售ID', sort: true, fixed: 'left'}
			      ,{field: 'planItem', title: '计划项', sort: true, fixed: 'left'}
			      ,{field: 'planDate', title: '计划日期', sort: true, fixed: 'left'}
			      ,{field: 'exeAffect', title: '执行效果', sort: true, fixed: 'left'}
			      ,{field: 'createTime', title: '创建时间', sort: true}
			      ,{field: 'updateTime', title: '更新时间', sort: true}
			      ,{fixed:'right', width: 178, toolbar:'#barDemo'}
			    ]]
			  });
			
		  //监听工具条
		  //tool是工具条事件名，tableFilter是table容器属性lay-filter="tableFilter"对应的值
		  table.on('tool(tableFilter)', function(obj){
		    var data = obj.data;//获得当前行数据,json格式对象
		    var layEvent = obj.event;//获得lay-event对应的值
		    if(layEvent === 'detail'){
		      layer.msg('ID：'+ data.id + ' 的查看操作');
		    } else if(layEvent === 'del'){
		      layer.confirm('真的删除行么', function(index){
		    	$.ajax({
		    		url:"${ctx}/cusDevPlan/deleteById.action",
		    		data:{"id":data.id},
		    		dataType:"json",
		    		success:function(resp) {
		    			if(resp.code == util.SUCCESS){
		    				mylayer.success(resp.msg);
		    				table.reload("layUITableId");
		    			} else {
		    				mylayer.errorMsg(resp.msg);
		    			}
			        layer.close(index);
		    		}
		    	});
		      });
		    } else if(obj.event === 'edit'){
		    	location.href = "${ctx}/cusDevPlan/getUpdatePage.action?id="+data.id;
		    }
		  });
		  
		  var active = {
		    deleteAll: function(){ //获取选中数据
		       var checkStatus = table.checkStatus('layUITableId')
		       var data = checkStatus.data;
		       console.log(data);//选中行的数据
		       console.log(checkStatus.data.length);//获取选中行的数量
		       console.log(checkStatus.isAll);//表格是否全选
		       var ids = util.getSelectedIds(data);
		       console.log(ids);
		       layer.confirm('真的删除行么', function(index){
			    	$.ajax({
			    		url:"${ctx}/cusDevPlan/deleteAll.action",
			    		data:{"ids":ids},
			    		dataType:"json",
			    		success:function(resp) {
			    			if(resp.code == util.SUCCESS){
			    				mylayer.success(resp.msg);
			    				table.reload("layUITableId");
			    			} else {
			    				mylayer.errorMsg(resp.msg);
			    			}
				        layer.close(index);
			    		}
			    	});
			   });
		    },
		    
		  	//添加
		  	add : function() {
			  location.href = "${ctx}/cusDevPlan/getAddPage.action?id=${id}";
			},
			
		  	//开发成功
		 	win : function(){
		 	  $.ajax({
		 		url : "${ctx}/saleChance/selectById.action",
	    		data : {"id":"${id}"},
	    		dataType : "json",
	    		success : function(resp) {
	    			if(resp.code == util.SUCCESS){
	    				mylayer.successUrl("订单生成成功",{icon:1,time:1000},function(){
	    					//获取当前弹出层的层级
	    					var index = parent.layer.getFrameIndex(window.name);
	    					//关闭弹出层
	    					parent.layer.close(index);
	    					layer.closeAll();	
	    				});
	    				location.href="${ctx}/order/getOrderPage.action"
	    			} else {
	    				mylayer.errorMsg(resp.msg);
	    			}
	    		}
		 	  });
		  	},
		  	
		  	//开发失败
		  	loser : function(){
		  		$.ajax({
		    		url : "${ctx}/saleChance/loser.action",
		    		data : {"id":"${id}"},
		    		dataType : "json",
		    		success : function(resp) {
		    			if(resp.code == util.SUCCESS){
		    				mylayer.successUrl("开发失败","${ctx}/saleChance/getSaleChancePage.action?id=${id}");
		    			} else {
		    				mylayer.errorMsg(resp.msg);
		    			}
		    		}
			    });
		  	}
		  	
		  };
		  
		  $('.demoTable .layui-btn').on('click', function(){
			   var type = $(this).data('type');
			   active[type] ? active[type].call(this) : '';
		   });
		});
		
	</script>
</body>
</html>