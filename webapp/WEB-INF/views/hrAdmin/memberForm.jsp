<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 8.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<script type="text/javascript" src="${cPath}/js/org/jusoPopup.js"></script>
<input type="hidden" value="${pageContext.request.contextPath }" id="contextPath" name="contextPath">
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

.form-label {
	color: black;
}
</style>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">사용자 등록</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">인사관리</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">사용자 등록</li>
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">사용자 등록</label>
				</div>
			</div>
			<div class="card-body">
				<form:form commandName="memFormVO" name="form" id="form" method="post" enctype="multipart/form-data" cssClass="row g-3">
					<div class="col-5">
						<div id='profile-upload'>
							<div class="avatar-upload">
								<div class="avatar-edit">
									<input type='file' name="memberVO.memImage" id="imageUpload" accept=".png, .jpg, .jpeg" />
									<label for="imageUpload">
										<i class="fas fa-camera center-block"></i>
									</label>
								</div>
								<div class="avatar-preview">
									<div id="imagePreview" style="background-image: url(${cPath}/images/pngs/noImage.png);"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-7">
						<div class="row">
							<div class="col-3 border-right">
								<label for="memName" class="form-label">이름</label>
								<form:input path="memberVO.memName" cssClass="form-control" id="memName" required="true" />
								<label for="memId" class="form-label">아이디</label>
								<form:input path="memberVO.memId" id="memId" cssClass="form-control" required="true" />
								<c:if test="${empty modify }">
									<label for="memPass" class="form-label">비밀번호</label>
									<form:input path="memberVO.memPass" id="memPass" type="password" cssClass="form-control" required="true" />
								</c:if>
								<div class="form-check mt-5">
									<form:radiobutton path="memberVO.asCode" value="ON" cssClass="form-check-input" label="활성화" />
								</div>
								<div class="form-check">
									<form:radiobutton path="memberVO.asCode" value="DORMANT" cssClass="form-check-input" label="비활성화" />
								</div>
							</div>
							<div class="col-6">
								<div class="row">
									<div class="col-6">
										<label for="memHdate" class="form-label">입사일</label>
										<form:input path="memberVO.memHdate" id="memHdate" cssClass="form-control" placeholder="ex)20201030" />
									</div>
									<div class="col-6">
										<label for="jobCode" class="form-label">부서</label>
										<form:select path="memberVO.deptCode" id="deptCode" cssClass="form-control">
											<form:options items="${memFormVO.deptList }" itemLabel="deptName" itemValue="deptCode" />
										</form:select>
									</div>
									<div class="col-6">
										<label for="jobCode" class="form-label">직무</label>
										<form:select path="memberVO.jobCode" id="jobCode" cssClass="form-control">
											<form:options items="${memFormVO.jobVOList }" itemLabel="jobName" itemValue="jobCode" />
										</form:select>
									</div>
									<div class="col-6">
										<label for="jobCode" class="form-label">직위</label>
										<form:select path="memberVO.psCode" id="psCode" cssClass="form-control">
											<form:options items="${memFormVO.psVOList }" itemLabel="psName" itemValue="psCode" />
										</form:select>
									</div>
									<div class="col-6">
										<label for="memMail" class="form-label">이메일</label>
										<form:input path="memberVO.memMail" id="memMail" cssClass="form-control" />
									</div>
									<div class="col-6">
										<label for="memComtel" class="form-label">사내전화</label>
										<form:input path="memberVO.memComtel" id="memComtel" cssClass="form-control" />
									</div>
									<div class="col-6">
										<label for="memHp" class="form-label">휴대전화</label>
										<form:input path="memberVO.memHp" id="memHp" cssClass="form-control" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12">
						<hr style="border-top-color: #A9A9A9">
					</div>
					<div class="col-12">
						<div class="row ml-4">
							<div class="col-sm-1">
								<label for="btnZipcode" class="form-label">우편번호</label>
								<form:input path="memberVO.memZip" cssClass="form-control" id="memZip" />
							</div>
							<div class="col-md-1 mt-4">
								<input id="btnZipcode" type="button" class="btn ripple btn-light" value="우편번호 검색" onClick="goPopup();">
							</div>
							<div class="col-md-12"></div>
							<div class="col-md-8 mt-2">
								<label for="btnZipcode" class="form-label">주소</label>
								<form:input path="memberVO.memAdd" cssClass="form-control" id="memAdd" />
							</div>
							<div class="col-4"></div>
							<div class="col-3">
								<label for="btnZipcode" class="form-label">생년월일</label>
								<form:input path="memberVO.memBirth" cssClass="form-control" placeHolder="YYYYMMDD" />
							</div>
							<div class="col-9"></div>
							<div class="col-md-8 mt-4">
								<label for="inputComment" class="form-label">기타 정보</label>
								<form:textarea path="memberVO.memEtc" cssClass="form-control" rows="5" cols="144" id="inputComment" />
							</div>
							<div class="col-12 mt-4">
								<button type="submit" class="btn btn-primary">저장</button>
								<button class="btn btn-outline-danger" type="button" id="cancleBtn">취소</button>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- COL END -->
</div>
<script src="${cPath}/js/admin/memberForm.js"></script>

