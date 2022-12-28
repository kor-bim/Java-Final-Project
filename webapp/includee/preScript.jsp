<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
<meta name="description" content="Forest GroupWare">
<meta name="author" content="Forest GroupWare">
<meta name="keywords" content="Forest GroupWare, Forest, GroupWare, forest, groupware, forest groupware">

<!-- Favicon -->
<link rel="icon" href="${cPath}/images/brand/favicon.ico" type="image/x-icon" />
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">


<!-- Title -->
<title>Forest GroupWare</title>

<!-- Bootstrap css-->
<link href="${cPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Noty css -->
<link rel="stylesheet" href="${cPath }/plugins/noty-3.1.4/noty.css">

<!-- Icons css-->
<link href="${cPath}/plugins/web-fonts/icons.css" rel="stylesheet" />
<link href="${cPath}/plugins/web-fonts/font-awesome/font-awesome.min.css" rel="stylesheet">
<link href="${cPath}/plugins/web-fonts/plugin.css" rel="stylesheet" />

<!-- Style css-->
<link href="${cPath}/css/style.css" rel="stylesheet">
<link href="${cPath}/css/skins.css" rel="stylesheet">
<link href="${cPath}/css/dark-style.css" rel="stylesheet">
<link href="${cPath}/css/colors/default.css" rel="stylesheet">

<!-- Color css-->
<link id="theme" rel="stylesheet" type="text/css" media="all" href="${cPath}/css/colors/color.css">

<!-- Select2 css-->
<link href="${cPath}/plugins/select2/css/select2.min.css" rel="stylesheet">

<!-- Mutipleselect css-->
<link rel="stylesheet" href="${cPath}/plugins/multipleselect/multiple-select.css">

<!-- Sidemenu css-->
<link href="${cPath}/css/sidemenu/sidemenu.css" rel="stylesheet">

<!-- Switcher css-->
<link href="${cPath}/css/switcher/css/switcher.css" rel="stylesheet">
<link href="${cPath}/css/switcher/demo.css" rel="stylesheet">

<!-- Internal DataTables css-->
<link href="${cPath}/plugins/datatable/dataTables.bootstrap4.min.css" rel="stylesheet" />
<link href="${cPath}/plugins/datatable/responsivebootstrap4.min.css" rel="stylesheet" />
<link href="${cPath}/plugins/datatable/fileexport/buttons.bootstrap4.min.css" rel="stylesheet" />

<!-- material Icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- address css -->
<link rel="stylesheet" href="${cPath }/css/address/address.css">

<!-- fullcalendar css -->
<link rel="stylesheet" href="${cPath }/plugins/fullcalendar-5.5.1/lib/main.css">

<!-- talk css -->
<%-- <link rel="stylesheet" href="${cPath }/css/cooperation/talk.css"> --%>

<!-- z-tree css -->
<link href="${cPath }/plugins/zTree_v3-master/css/metroStyle/metroStyle.css" rel="stylesheet">

<!-- bootstrap select css -->
<link href="${cPath }/plugins/bootstrap-select-1.13.14/dist/css/bootstrap-select.min.css" rel="stylesheet">

<!-- inputTags css -->
<link href="${cPath }/plugins/inputtags/bootstrap-tagsinput.css" rel="stylesheet">

<!-- pg calendar -->
<link href="${cPath }/plugins/pg-calendar-master/dist/css/pignose.calendar.min.css" rel="stylesheet">

<!-- owl css -->
<link href="${cPath }/plugins/OwlCarousel2-2.3.4/dist/assets/owl.carousel.min.css" rel="stylesheet">

<!-- weather-icons css -->
<link href="${cPath }/plugins/weather-icons-master/css/weather-icons-wind.min.css" rel="stylesheet">
<link href="${cPath }/plugins/weather-icons-master/css/weather-icons.min.css" rel="stylesheet">


<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />
<script type="text/javascript">
	var contextPath = "${cPath}";
	var myInfo = {
		memId : "${authMember.memId}",
		memName : "${authMember.memName}",
		deptName : "${authMember.deptName}",
		psName : "${authMember.psName}",
		signImg : "${authMember.memSignImg}"
	}
</script>

<style type="text/css">
.menuH6 {
	display: inline-block;
}

.error {
	color: red;
}

#external-events {
	border: 1px #ccc;
	background: white;
	text-align: left;
	botton: 50px;
}

.schedule-modal {
	max-width: 100%;
	width: auto;
	display: table;
}

.fc-day-sun {
	color: #FD5E5E;
}

.fc-day-sat {
	color: #73B1F3;
}
</style>