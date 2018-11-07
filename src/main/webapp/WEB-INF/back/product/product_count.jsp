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
	<script src="${ctx}/static/common/echarts/echarts.min.js"></script>
	<script src="${ctx}/static/common/kindeditor/kindeditor.js"></script>
	<script src="${ctx}/static/common/kindeditor/lang/zh_CN.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
   <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
   
    <script type="text/javascript">
       $(function(){
	    	// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
			$.ajax({
				url : "${ctx}/product/productCount.action",
				type : "POST",
				dataType : "json",
				success : function(resp){
					console.log(resp);
					var data = resp.data;
					console.log(data);
					//x数据，存放分类的名字
					var xArray = new Array();
					//y数据，存放分类的数量
					var yArray = new Array();
					
					for(var i = 0;i < data.length;i++){
						xArray.push(data[i].name);
						yArray.push(data[i].stock);
					}
					// 指定图表的配置项和数据
			        var option = {
			            title: {
			                text: '商品库存统计'
			            },
			            tooltip: {},
			            legend: {
			                data:['库存']
			            },
			            xAxis: {
			                data: xArray
			            },
			            yAxis: {},
			            series: [{
			                name: '库存',
			                type: 'bar',
			                data: yArray
			            }]
			        };
			       
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
				}
				
			});
	       
       }); 
    </script>
</body>
</html>