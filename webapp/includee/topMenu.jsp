<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!-- Main left-->

<div class="main-header-left">
	<a class="main-header-menu-icon" href="${cPath}/home" id="mainSidebarToggle">
		<span></span>
	</a>
</div>
<div class="main-header-center">
	<div class="responsive-logo">
		<a href="${cPath}/home">
			<img src="${cPath}/images/brand/logo.png" class="mobile-logo" alt="logo">
		</a>
		<a href="${cPath}/home">
			<img src="${cPath}/images/brand/logo-light.png" class="mobile-logo-dark" alt="logo">
		</a>
	</div>
	<nav class="navbar navbar-expand-xl">
		<div class="collapse navbar-collapse" id="main_nav">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 90px" href="${cPath}/mail/inbox">메일</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 120px" href="${cPath}/approval/approvalAllList">전자결재</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 120px" href="${cPath}/schedule/scheduleList">일정관리</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 120px" href="${cPath}/departMentBoard">커뮤니티</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 100px" href="${cPath}/fileBox/myFile">자료실</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 100px" href="${cPath}/addressbook/private">주소록</a>
				</li>
				<li class="nav-item">
					<a class="nav-link text-light" style="width: 120px" href="${cPath}/work/absenteeism">인사관리</a>
				</li>
			</ul>
		</div>
		<!-- navbar-collapse.// -->
	</nav>
</div>
<!-- End Main left-->
<!-- Main right-->
<div class="main-header-right">
	<div class="dropdown d-md-flex">
		<a class="nav-link icon full-screen-link" href="">
			<i class="fe fe-maximize fullscreen-button fullscreen header-icons"></i>
			<i class="fe fe-minimize fullscreen-button exit-fullscreen header-icons"></i>
		</a>
	</div>
	<security:authorize access="isAuthenticated()">
		<script type="text/javascript">
			// push 알림용 websocket
			const PROTOCOL = location.protocol == "http:" ? "ws:" : "wss";
			const DOMAIN = location.hostname;
			const CONTEXTPATH = $.getContextPath();
			const PORT = location.port ? ":" + location.port : "";
			var pushWs = new WebSocket(PROTOCOL + "//" + DOMAIN + PORT
					+ CONTEXTPATH + "/message");
			var consolePrintHandler = function(event) {
				console.log(event);
			}
			pushWs.onmessage = function(event) {
				let payload = event.data;
				let messageVO = JSON.parse(payload);
				alert(messageVO.message);
			}
			pushWs.onerror = consolePrintHandler;
		</script>
	</security:authorize>
	<div class="dropdown main-profile-menu">
		<a class="d-flex" href="">
			<span class="main-img-user">
				<security:authentication property="principal" var="principal" />
				<c:set var="member" value="${principal.realMember }" />
				<c:if test="${not empty member.memImg }">
					<img id="memProfileImg" alt="avatar" src="${cPath}/profileImages/${member.memImg}">
				</c:if>
				<c:if test="${empty member.memImg }">
					<img alt="avatar" src="${cPath}/images/pngs/noImage.png">
				</c:if>
			</span>
		</a>
		<div class="dropdown-menu">
			<div class="header-navheading">
				<h6 class="main-notification-title">${member.memName}</h6>
			</div>
			<a class="dropdown-item border-top" href="${cPath}/profile/profileSetting">
				<i class="fe fe-user"></i>
				프로필 설정
			</a>
			<a class="dropdown-item" href="#" onclick="clickHandler(event);" data-href="${cPath}/login/logout">
				<i class="fe fe-power"></i>
				로그아웃
			</a>
		</div>
	</div>
	<form name="logoutForm" method="post"></form>
	<script type="text/javascript">
		function clickHandler(event) {
			event.preventDefault();
			let href = event.target.dataset["href"];
			let logoutForm = document.logoutForm;
			logoutForm.action = href;
			logoutForm.submit();
			return false;
		}
	</script>
	<div class="dropdown d-md-flex header-settings">
		<a href="#" class="nav-link icon" data-toggle="sidebar-right" data-target=".sidebar-right">
			<i class="fe fe-align-right header-icons"></i>
		</a>
	</div>
	<button class="navbar-toggler navresponsive-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent-4" aria-controls="navbarSupportedContent-4" aria-expanded="false" aria-label="Toggle navigation">
		<i class="fe fe-more-vertical header-icons navbar-toggler-icon"></i>
	</button>
	<!-- Navresponsive closed -->
</div>
<!-- End Main right-->
