<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 1. 29.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<style>
.avatar-upload {
	position: relative;
	max-width: 205px;
	margin: 50px auto;
}

.avatar-upload .avatar-edit {
	position: absolute;
	right: 12px;
	z-index: 1;
	top: 10px;
}

.avatar-upload .avatar-edit input {
	display: none;
}

.avatar-upload .avatar-edit input+label {
	display: inline-block;
	width: 34px;
	height: 34px;
	margin-bottom: 0;
	border-radius: 100%;
	background: #FFFFFF;
	border: 1px solid transparent;
	box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.12);
	cursor: pointer;
	font-weight: normal;
	transition: all 0.2s ease-in-out;
}

.avatar-upload .avatar-edit input+label:hover {
	background: #f1f1f1;
	border-color: #d6d6d6;
}

.avatar-upload .avatar-edit input+label:after {
	color: #F6B21B;
	position: absolute;
	top: 10px;
	left: 0;
	right: 0;
	text-align: center;
	margin: auto;
}

.avatar-upload .avatar-preview {
	width: 192px;
	height: 192px;
	position: relative;
	border-radius: 100%;
	border: 6px solid #F6B21B;
	box-shadow: 0px 2px 4px 0px rgba(0, 0, 0, 0.1);
}

.avatar-upload .avatar-preview>div {
	width: 100%;
	height: 100%;
	border-radius: 100%;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center;
}

i {
	float: none;
	margin: 8px;
}
</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mb-10">프로필 설정</h2>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-3">
	</div>
	<div class="col-lg-6">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div class="row">
					<form class="col-md-12" action="${cPath}/profile/upateImg" method="post" enctype="multipart/form-data" id="imgForm">
						<div id='profile-upload'>
							<div class="avatar-upload">
								<div class="avatar-edit">
									<input type='file' name="memImage" id="imageUpload" accept=".png, .jpg, .jpeg" />
									<label for="imageUpload">
										<i class="fas fa-camera center-block"></i>
									</label>
								</div>
								<div class="avatar-preview">
									<c:if test="${not empty member.memImg }">
										<div id="imagePreview" style="background-image: url(${cPath}/profileImages/${member.memImg});"></div>
									</c:if>
									<c:if test="${empty member.memImg }">
										<div id="imagePreview" style="background-image: url(${cPath}/images/pngs/noImage.png);"></div>
									</c:if>
								</div>
							</div>
						</div>
					</form>
					<div class="col-md-12 ml-5" >
						<form class="row" method="post" class="align-items-center" style="margin-left: 200px" action="${cPath}/profile/updateMemberPass">
							<input type="hidden" id="oldPass" name="oldPass" />
							<div class="col-md-12">
								<label for="memberName" class="col-sm-12 col-form-label">아이디</label>
								<div class="col-sm-12">
									<input type="text" readonly class="form-control-plaintext" id="memId" name="memId" value="${member.memId }">
								</div>
								<label for="memberName" class="col-sm-12 col-form-label">이름</label>
								<div class="col-sm-10">
									<input type="text" readonly class="form-control-plaintext" name="memName" id="memName" value="${member.memName }">
								</div>
								<div class="col-sm-4 border border-success rounded">
									<div class="row">
										<label for="memberName" class="col-sm-12 col-form-label">비밀번호</label>
										<div class="col-12 mb-2">
											<input type="text" class="form-control" id="memPass" name="newPass" placeholder="비밀번호" style="display: none;">
										</div>
										<div class="col-12 mb-2">
											<button type="button" id="changePassBtn" class="btn btn-primary" data-toggle="modal" data-target="#changePassModal">
												<b class="text-warning">비밀번호</b> 변경하기
											</button>
											<button type="submit" id="updatePassBtn" class="btn btn-primary" style="display: none">등록</button>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3">
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="changePassModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="changePassModalLabel">비밀번호 변경</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label for="exampleInputPassword1">비밀번호</label>
				</div>
				<div class="input-group">
					<input type="password" class="form-control" id="memberPass" name="memPass" placeholder="이전 비밀번호를 입력해주세요">
					<button type="button" id="checkPassBtn" class="btn btn-primary">확인</button>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var memId = ${member.memId}
</script>
<script src="${cPath}/js/profile/profileSetting.js"></script>