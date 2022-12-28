<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 1. 27.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
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
								<img src="${pageContext.request.contextPath}/images/brand/logo-light.png" class="header-brand-img mb-4 ml-5" alt="logo">
								<div class="clearfix"></div>
								<h5 class="mt-4 text-white ml-5">Forest GroupWare</h5>
							</div>
						</div>
						<div class="col-lg-6 col-xl-7 col-xs-12 col-sm-12 login_form ">
							<div class="container-fluid">
								<div class="row row-sm">
									<div class="card-body mt-2 mb-2">
										<img src="${pageContext.request.contextPath}/images/brand/logo.png" class=" d-lg-none header-brand-img text-left float-left mb-4" alt="logo">
										<div class="clearfix"></div>
										<form id="findIdForm" action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
											<h5 class="text-left mb-2" style="font-weight: bold;">아이디 찾기</h5>
											<div id="usernameDiv" class="form-group text-left">
												<label>휴대폰 번호</label>
												<input class="form-control" name="username" id="username" placeholder="휴대폰 번호를 입력하세요" type="text">
											</div>
											<div id="emailDiv" class="form-group text-left">
												<label>이메일</label>
												<input class="form-control" name="email" id="email" placeholder="이메일을 입력하세요" type="text">
											</div>
											<div id="authNumberDiv" class="form-group text-left" style="display: none;">
												<label>인증번호</label>
												<input class="form-control" name="authNumber" id="authNumber" placeholder="인증번호를 입력하세요" type="text">
											</div>
											<button type="button" class="btn ripple btn-main-primary btn-block" id="sendMailBtn">인증번호 발급</button>
											<button type="button" class="btn ripple btn-main-primary btn-block" id="checkAuthBtn" style="display: none;">인증번호 확인</button>
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

	<!-- Modal -->
	<div class="modal fade" id="findIdModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">아이디(사번)</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<input type="hidden" id="memberHp" />
					<input type="hidden" id="memberEmail" />
					<h5>조회된 아이디</h5>
					<p id="findMemId"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
					<button type="button" class="btn btn-info" id="goSignForm" onclick="location.href='${pageContext.request.contextPath}/sign/signForm'">로그인하러 가기</button>
					<button type="button" class="btn btn-primary" id="sendMailModalBtn" style="display: none;">인증번호 발급</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal End -->

	<!-- Jquery js-->
	<script src="${pageContext.request.contextPath}/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap js-->
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<!-- Select2 js-->
	<script src="${pageContext.request.contextPath}/plugins/select2/js/select2.min.js"></script>

	<!-- Custom js -->
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>

	<!-- Switcher js -->

	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	<script src="${pageContext.request.contextPath}/js/sign/findId.js"></script>

</body>
</html>


