<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="preScript"></tiles:insertAttribute>
<tiles:insertAttribute name="postScript"></tiles:insertAttribute>
</head>
<body class="main-body leftmenu light-theme light-horizontal light-leftmenu color-header">
	<!-- Page -->
	<div class="page">
		<!-- Sidemenu -->
		<div class="main-sidebar main-sidebar-sticky side-menu">
			<div class="sidemenu-logo">
				<a class="main-logo" href="${cPath}/home">
					<img src="${cPath}/images/brand/logo-light.png" class="header-brand-img desktop-logo" alt="logo">
					<img src="${cPath}/images/brand/icon-light.png" class="header-brand-img icon-logo" alt="logo">
					<img src="${cPath}/images/brand/logo.png" class="header-brand-img desktop-logo theme-logo" alt="logo">
					<img src="${cPath}/images/brand/icon.png" class="header-brand-img icon-logo theme-logo" alt="logo">
				</a>
			</div>
			<!-- 사이드메뉴 리스트 -->
			<div class="main-sidebar-body">
				<tiles:insertAttribute name="leftMenu"></tiles:insertAttribute>
			</div>
		</div>
		<!-- End Sidemenu -->

		<!-- Main Header-->
		<div class="main-header side-header sticky">
			<div class="container-fluid">
				<tiles:insertAttribute name="topMenu"></tiles:insertAttribute>
			</div>
		</div>
		<!-- End Main Header  -->

		<c:if test="${not empty message }">
			<script type="text/javascript">
				new Noty({
					text : '${message.text }',
					layout : '${message.layout }',
					type : '${message.type }',
					timeout : '${message.timeout }',
					progressBar : true
				}).show();
			</script>
		</c:if>

		<!-- Main Content-->
		<div class="main-content side-content pt-0">
			<div class="container-fluid">
				<div class="inner-body">
					<tiles:insertAttribute name="content"></tiles:insertAttribute>
				</div>
			</div>
		</div>
		<!-- End Main Content-->

		<!-- Main Footer-->
		<div class="main-footer text-center">
			<div class="container">
				<tiles:insertAttribute name="footer"></tiles:insertAttribute>
			</div>
		</div>
		<!--End Footer-->

		<!-- Top Sidebar -->
		<div class="sidebar sidebar-right sidebar-animate">
			<tiles:insertAttribute name="topSidebar"></tiles:insertAttribute>
		</div>
		<!-- End Top Sidebar -->
	</div>

	<!-- Loader -->
	<div id="global-loader">
		<img src="${cPath}/images/loader.svg" class="loader-img" alt="Loader">
	</div>
	<!-- End Loader -->


</body>
</html>