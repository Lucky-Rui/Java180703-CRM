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
</head>
<body>
	<div class="demoTable">
	    名字：
	  <div class="layui-inline">
		  <input class="layui-input" id="searchName" />
	  </div>
	    副标题：
	  <div class="layui-inline">
		  <input class="layui-input" id="searchSubtitle" />
	  </div>
	   时间：
	  <div class="layui-inline">
		  <input class="layui-input" id="searchTime" />
	  </div>
	  <button class="layui-btn" data-type="search">搜索</button>
	  <button class="layui-btn" data-type="deleteAll">批量删除</button>
	  <button class="layui-btn" data-type="add">添加</button>
	  <button class="layui-btn" data-type="exportExcel">导出报表</button>
	</div>
	<table id="tableId" lay-filter="tableFilter"></table>

	<script type="text/html" id="barDemo">
  		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
  		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	<script type="text/html" id="statusTpl">
		 {{#  if(d.status == 1){  }}
		 	           上架
		 {{#  } else {            }}
				  下架
		 {{#  }					  }}
	</script>
	
	<script type="text/html" id="mainImageTpl">
		 <img src="{{d.fullUrl}}"/>
	</script>

	<script>
		layui.use(['table','laydate'], function() {
			var table = layui.table;
			var laydate = layui.laydate;
			
			//执行一个laydate实例
			laydate.render({
			   elem: '#searchTime' //指定元素
			});
			
			table.render({
			    elem: '#tableId'
			    ,url: '${ctx}/user/pageList.action' //数据接口
			    ,page: true //开启分页
			    ,id : "layUITableId" //设定容器唯一ID，id值是对表格的数据操作方法上是必要的传递条件，它是表格容器的索引
			    ,cols: [[ //表头
			      {type: 'checkbox', fixed: 'left'}
			      ,{field: 'id', title: 'ID', sort: true, fixed: 'left'}
			      ,{field: 'name', title: '用户名', sort: true, fixed: 'left'}
			      ,{field: 'password', title: '密码', sort: true, fixed: 'left'}
			      ,{field: 'trueName', title: '真实姓名', sort: true, fixed: 'left'}
			      ,{field: 'email', title: '邮箱', sort: true, fixed: 'left'}
			      ,{field: 'phone', title: '电话', sort: true, fixed: 'left'}
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
		    		url:"${ctx}/user/deleteById.action",
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
		        layer.alert('编辑行：<br>'+ JSON.stringify(data))
		        console.log(data);
		    	location.href = "${ctx}/user/getUpdatePage.action?userId="+data.id;
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
			    		url:"${ctx}/user/deleteAll.action",
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
		    //搜索
			search : function() {
				table.reload('layUITableId', {
				  where: { //设定异步数据接口的额外参数，任意设
					  name:$("#searchName").val(),
					  subtitle:$("#searchSubtitle").val(),
					  time:$("#searchTime").val()
				  }
				  ,page: {
				    curr: 1 //重新从第 1 页开始
				  }
				});
			},
			//添加
			add : function() {
				location.href = "${ctx}/user/getAddPage.action";
			},
			exportExcel : function() {
				location.href = "${ctx}/user/exportExcel.action";
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