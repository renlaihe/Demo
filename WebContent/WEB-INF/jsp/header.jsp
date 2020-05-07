<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- header -->
<c:choose>
	<c:when test="${user == null}">
		<div class="header ">
			<div class="auto">
				<a href="${pageContext.request.contextPath}/main/toIndex"><img class="left logo" src="../images/logo.png"></a>
				<div class="right login_area">
					<span class="car" id="end">
					<a href="${pageContext.request.contextPath}/main/toCar">购物车</a><span class="nums"></span></span>
					<span class="a_btns"><a href="${pageContext.request.contextPath}/login/toLogin">登录</a></span>
					<span class="a_btns"><a href="${pageContext.request.contextPath}/registe/toRegiste">注册</a></span>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="header header_blue">
			<div class="auto">
				<a href="${pageContext.request.contextPath}/main/toIndex"><img class="left logo" src="../images/logo_fff.png"></a>
				<div class="right login_area">
					<span class="car" id="end">
					<a href="${pageContext.request.contextPath}/main/toCar">购物车</a><span class="nums"></span></span>
					<div class="user_head right "><img class="head_pic" src="../images/head_pic.jpg">
						<div class="logout">
							<div class="row_1 overflowH"><img src="../images/head_pic.jpg" class="left"><p class="left"><span name="uname">${user.nickname}</span><span>经验8888&nbsp;&nbsp;积分666</span></p></div>
							<ul class="row_2">
								<li><a href="#">我的课程</a><a href="#">订单中心</a></li>
								<li><a href="#">积分商城</a><a href="#">个人设置</a></li>
							</ul>
							<div class="row_3"><a href="${pageContext.request.contextPath}/login/loginOut">安全退出</a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:otherwise>
</c:choose>
