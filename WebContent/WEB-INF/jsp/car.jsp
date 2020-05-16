<!DOCTYPE html>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<title>学码网</title>
		
		<link rel="stylesheet" type="text/css" href="../css/index.css">
		<link rel="stylesheet" type="text/css" href="../css/comm.css">
		<link rel="stylesheet" type="text/css" href="../css/course.css">
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/course.js"></script>
		<script type="text/javascript" src="../js/index.js"></script>
	
		<script type="text/javascript">
		
			var itemIds = [];
			function isChecked(){
				itemIds = [];
				//var x = 0;
				var totalPrice = 0.0;
				var item_checkboxs = document.getElementsByName("item_checkbox");
				
				for(var i=0;i<item_checkboxs.length;i++){
					if(item_checkboxs[i].checked){
						//itemIds[itemIds.length] = item_checkboxs[i].value;
						//itemIds[x++] = item_checkboxs[i].value;	
						itemIds.push(item_checkboxs[i].value);
						
						var price = $(item_checkboxs[i]).siblings(".price").html();
						
						totalPrice += parseFloat(price.substring(1));
						
					}
				}
				
				totalPrice = totalPrice.toFixed(2);
				
				$("span[name=count]").html(itemIds.length);
				$("span[name=prices]").html(totalPrice);
				
				if (itemIds.length == item_checkboxs.length) {
					//	全部选中
					$("input[name=all]").prop("checked",true);
				} else {
					// 部分选中	
					$("input[name=all]").prop("checked",false);
				}
				
				//	if (itemIds.length > 0) {
					//	改红色，可点击
				//	} else {
					//	改灰色，不可点击
				//	}
				
			}
			
			function deleteItem(){
				
				if (itemIds.length == 0) {
					alert("请选择要删除的商品");
				}else{
					document.location.href = "${pageContext.request.contextPath}/main/batchDelete?itemIds="+itemIds;
				}
				
			}
			
			function checkAll(){
				$("input[type=checkbox]").prop("checked",$("input[name=all]").prop("checked"));
				isChecked();
			}
		
			function toOrder(){
				document.location.href = "${pageContext.request.contextPath}/main/toOrder?itemIds="+itemIds;
				//document.location.href = "${pageContext.request.contextPath}/appli/alipay?itemIds="+itemIds;
			}
			
		</script>
	
	</head>
	<body id="body">
		<!-- 引入头部start -->
		<%@ include file="header.jsp"%>	
		<!-- 引入头部end -->
		
		<!-- car_box -->
		<div class="car_box autoH">
			<div class="auto">
				<h1>购物车</h1>
				<div class="tit2">
					<span class="s1">商品信息</span><span class="s2">金额</span><span>操作</span>
				</div>
				<ul>
					<c:forEach items="${items}" var="item">
						<li>
							<!--  
							<input type="hidden" value="${item.id}"/>
							-->
							<input type="checkbox" name="item_checkbox" value="${item.id}" onclick="isChecked();">
							<a href="#"><img src="${item.c_picture_url}" alt=""></a>
							<span class="proName"><a href="#">${item.c_name}</a></span>
							<span class="price">￥${item.c_price}</span>
							<span class="del" onclick="javascript:document.location.href='${pageContext.request.contextPath}/main/deleteItem?item_id=${item.id}'">删除</span>
						</li>
					</c:forEach>
				</ul>
				<div class="tit2 autoH tit_bottom">
					<p class="left">
						<input type="checkbox" name="all" onclick="checkAll();"> 全选
						<a href="javascript:deleteItem();" class="delAll">删除</a>
					</p>
					<p class="right">
						<span>已选商品<span name="count">0</span>件</span>
						<span>合计:<span name="prices" >0.00</span></span>
						
						<!-- 支付宝支付 -->
						<!-- <a href="${pageContext.request.contextPath}/appli/alipay;"><span name="pay">结算</span></a> -->
						
						<a href="javascript:toOrder();"><span name="pay">结算</span></a>
					</p>
				</div>
			</div>
		</div>
		<!-- foot -->
		<div class="foot foot_blue">
			<a href="#">关于我们</a>
			<a href="#">最新动态</a>
			<a href="#">代理合作</a>
			<span>南京学码思科技教育有限公司</span>       
			<span>@2017</span> 
			<span>京ICP备</span>
			<span>1234567号</span>      
		</div>
	</body>
</html>