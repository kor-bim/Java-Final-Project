<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<!-- Favicon -->
<link rel="icon" href="${pageContext.request.contextPath}/images/brand/favicon.ico" type="image/x-icon" />
<!-- Title -->
<title>Forest GroupWare</title>

<!-- Bootstrap css-->
<link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />

<!-- Icons css-->
<link href="${pageContext.request.contextPath}/plugins/web-fonts/icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/web-fonts/font-awesome/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/plugins/web-fonts/plugin.css" rel="stylesheet" />

<!-- Style css-->
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/skins.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/dark-style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/colors/default.css" rel="stylesheet">

</head>

<body class="main-body leftmenu">

	<!-- Loader -->
	<div id="global-loader">
		<img src="${pageContext.request.contextPath}/images/loader.svg" class="loader-img" alt="Loader">
	</div>
	<!-- End Loader -->


	<!-- Page -->
	<div class="page main-signin-wrapper">

		<!-- Row -->
		<div class="row signpages text-center">
			<div class="col-md-12">
				<div class="card">
					<div class="row row-sm">
						<div class="col-lg-6 col-xl-5 d-none d-lg-block text-center bg-primary details">
							<div class="mt-5 pt-4 p-2 pos-absolute">
								<img src="${pageContext.request.contextPath}/images/brand/logo-light.png" class="header-brand-img mb-4" alt="logo">
								<div class="clearfix"></div>
								<img src="${pageContext.request.contextPath}/images/svgs/user.svg" class="ht-100 mb-0" alt="user">
								<h5 class="mt-4 text-white">Forest GroupWare</h5>
								<span class="tx-white-6 tx-13 mb-5 mt-xl-0">2020. 07. 20 405th Forest GroupWare Team5 Final_project</span>
							</div>
						</div>
						<div class="col-lg-6 col-xl-7 col-xs-12 col-sm-12 login_form ">
							<div class="container-fluid">
								<div class="row row-sm">
									<div class="card-body mt-2 mb-2">
										<img src="${pageContext.request.contextPath}/images/brand/logo.png" class=" d-lg-none header-brand-img text-left float-left mb-4" alt="logo">
										<div class="clearfix"></div>
										${sessionScope['SPRING_SECURITY_LAST_EXCEPTION']['message']}
										<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />

										<form id="loginForm" action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
											<h5 class="text-left mb-2" style="font-weight: bold;">로그인</h5>
											<div class="form-group text-left">
												<label>아이디</label>
												<input class="form-control" name="memId" value="${cookie.idCookie.value }" placeholder="아이디를 입력하세요" type="text">
											</div>
											<div class="form-group text-left">
												<label>비밀번호</label>
												<input class="form-control" name="memPass" placeholder="비밀번호를 입력하세요" type="password">
											</div>
											<button type="submit" class="btn ripple btn-main-primary btn-block">로그인 하기</button>
											<div class="text-left mt-5 ml-0">
												<div class="form-check col-auto">
													<input class="form-check-input" type="checkbox" value="saveId" name="saveId" id="saveId" ${not empty cookie.idCookie ? "checked" : ""}>
													<label class="form-check-label" for="saveId">아이디 기억하기</label>
												</div>
												<div class="form-check col-auto">
													<input class="form-check-input" type="checkbox" value="on" name="rememberMe" id="rememberMe">
													<label class="form-check-label" for="rememberMe">자동 로그인</label>
												</div>
												<br>
												<div class="mb-1">
													<a href="${pageContext.request.contextPath}/sign/findId">아이디를 잊으셨습니까?</a>
												</div>
												<div class="mb-1">
													<a href="${pageContext.request.contextPath}/sign/findPass">비밀번호를 잊으셨습니까?</a>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Row -->

	</div>
	<!-- End Page -->

	<!-- Jquery js-->
	<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>

	<!-- Bootstrap js-->
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>



	<!-- Custom js -->
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>

	<!-- Switcher js -->
</body>
</html>

