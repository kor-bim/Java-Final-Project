<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 19.      윤한빈         최초작성
*  2021. 2. 27    이운주      페이지 헤더 수정 
* Copyright (c) 2021 by DDIT All right reserved
 --%>
 
<link href="${cPath}/css/e_approvel/userApprovalSetting.css" rel="stylesheet" />

<style>
.sign{
	display: inline;
	border-right: 1px solid #d3d3d3;
	margin-right: 60px;
}
</style>
<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">설정</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">전자결재</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">개인설정</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row square">
	<div class="col-lg-2"></div>
	<div class="col-lg-12 col-md-12">
		<div class="card custom-card">
			<div class="card-body">
				<div class="row row-sm mg-b-20">
					<div class="col-lg-5 sign">
						<p class="mg-b-10 font-weight-bold">서명 설정</p>
						<form class="row mg-b-20" action="${cPath}/approval/upateSignImg" method="post" enctype="multipart/form-data" id="signImgForm">
							<div class="col-lg-8">
								<security:authentication property="principal" var="principal" />
								<c:set var="authMember" value="${principal.realMember }" />
								<c:if test="${not empty authMember.memSignImg }">
									<img width="500px" id="memSignImg" src="${cPath}/profileImages/${authMember.memSignImg }" alt="img">
								</c:if>
								<c:if test="${empty authMember.memSignImg }">
									<img width="500px" id="memSignImg" src="${cPath}/images/pngs/defaultSignature.png" alt="img">
								</c:if>
							</div>
							<div class="col-lg-3 mg-t-20 mg-lg-t-0">
								<div class="filebox">
									<label for="signImageUpload">업로드</label>
									<input type="file" id="signImageUpload" name="memSignImage" accept=".png, .jpg, .jpeg" id="ex_file">
								</div>
							</div>
						</form>
					</div>
					<!-- 					<div class="col-lg-6 mg-t-20 mg-lg-t-0"> -->
					<!-- 						<p class="mg-b-10 font-weight-bold">부재중시 대결자 지정</p> -->

					<%-- 						<form id="deputyForm" class="row" action="${cPath}/approval/updateDeputy" method="post"> --%>
					<!-- 							<table class="table table-bordered"> -->
					<!-- 								<tr> -->
					<!-- 									<th>대결자 선택</th> -->
					<!-- 									<td> -->
					<!-- 										<select name="deputyId" id="deputyId" class="selectpicker" data-live-search="true"> -->
					<!-- 											<option value="">대결자 선택</option> -->
					<%-- 											<c:if test="${not empty memberList }"> --%>
					<%-- 												<c:forEach items="${memberList }" var="member"> --%>
					<%-- 													<option value="${member.memId}">${member.memName}(${member.memId})-${member.psName}/${member.deptName}</option> --%>
					<%-- 												</c:forEach> --%>
					<%-- 											</c:if> --%>
					<!-- 										</select> -->
					<!-- 									</td> -->
					<!-- 								</tr> -->
					<!-- 								<tr> -->
					<!-- 									<th>기간</th> -->
					<!-- 									<td> -->
					<!-- 										<span>시작일</span> -->
					<!-- 										<input type="date" class="form-control" name="dpapBegin"> -->
					<!-- 										<span class="mb-1">종료일</span> -->
					<!-- 										<input type="date" class="form-control" name="dpapEnd"> -->
					<!-- 									</td> -->
					<!-- 								</tr> -->
					<!-- 								<tr> -->
					<!-- 									<th>사용여부</th> -->
					<!-- 									<td> -->
					<!-- 										<label class="custom-switch"> -->
					<!-- 											<input type="checkbox" name="custom-switch-checkbox" class="custom-switch-input" checked="checked"> -->
					<!-- 											<span class="custom-switch-indicator"></span> -->
					<!-- 										</label> -->
					<!-- 									</td> -->
					<!-- 								</tr> -->
					<!-- 							</table> -->
					<%-- 							<input type="hidden" name="requestorId" value="${authMember.memId}"> --%>
					<!-- 							<button class="btn btn-info mt-1" type="submit">대결자 저장</button> -->
					<!-- 						</form> -->
					<!-- 					</div> -->
					<div class="col-lg-6 mg-lg-t-0">
						<p class="mg-b-10 font-weight-bold">
							자주쓰는 결재선
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal1">결재선 추가하기</button>
						</p>
						<table id="aprvlLineTable" class="table table-bordered table-hover">
							<colgroup>
								<col width="5%" />
								<col width="40%" />
								<col width="10%" />
							</colgroup>
							<thead class="thead-dark text-center">
								<tr>
									<th onclick="event.cancelBubble=true">
										<span>번호</span>
									</th>
									<th onclick="event.cancelBubble=true">
										<span>결재선 명</span>
									</th>
									<th onclick="event.cancelBubble=true">
										<span>관리</span>
									</th>
								</tr>
							</thead>
							<tbody id="listBody" class="text-center">
								<c:set var="aprvlLineList" value="${pagingVO.dataList }" />
								<c:if test="${not empty aprvlLineList }">
									<c:forEach items="${aprvlLineList }" var="aprvlLine">
										<tr>
											<td style="display: none">${aprvlLine.alNo }</td>
											<td>${aprvlLine.rnum}</td>
											<td>${aprvlLine.alName}</td>
											<td onclick="event.cancelBubble=true">
												<button type="button" class="btn btn-danger deleteApvlLineBtn" data-alNo="${aprvlLine.alNo}">삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty aprvlLineList }">
									<tr>
										<td colspan="6">조건에 맞는 결재선이 없음</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<div id="pagingArea">${pagingVO.pagingHTML}</div>
						<form id="searchForm">
							<input type="hidden" name="page" />
							<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }" />
							<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-2"></div>
</div>
<form action="<c:url value='/approval/removeAprvlLine'/>" method="post" id="removeAprvlLineForm">
	<input type="hidden" name="alNo" />
</form>

<!-- The Modal -->
<div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="modalStep1" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content p-4">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">자주 쓰는 결재선 추가</h4>
				<button type="button" class="close ml-1" data-dismiss="modal">&times;</button>
			</div>
			<form action="${cPath}/approval/insertAprvlLine" method="post">
				<input type="hidden" name="memId" value="${authMember.memId}" />
				<div class="modal-body">
					<div class="row">
						<div class="col-8 mb-2">
							<input type="text" class="form-control text-dark" name="alName" id="alName" placeholder="결재선 명을 입력하세요" required="required" />
							<input type="hidden" name="alCode" id="APPROVAL" value="APPROVAL">
						</div>
					</div>
					<div class="row mt-2">
						<div class="col-12">
							<hr>
							<h6 class="font-weight-bold">결재선 설정</h6>
						</div>
						<div class="org col-4 m-0 border border-primary rounded" id="deptTreeDIV">
							<ul id="deptMemTree" class="ztree" style="width: 100%;">
							</ul>
						</div>
						<div class="col-4">
							<br /> <br /> <br /> <br /> <br /> <br />
							<button type="button" id="approvalBtn" class="btn btn-outline-success btn-block">결재</button>
							<button type="button" id="referenceBtn" class="btn btn-outline-info btn-block">참조</button>
						</div>
						<div class="col-4">
							<div class="row">
								<b>결재</b>
								<div class="col-12 ">
									<ul class="list-group border border-success rounded" id="approvalMemList">
									</ul>
								</div>
								<br />
								<hr />
								<b>참조</b>
								<div class="col-12 ">
									<ul class="list-group border border-info rounded" id="referenceMemList">
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
					<button type="submit" class="btn btn-primary">저장</button>
				</div>
			</form>
		</div>
	</div>
</div>

<!-- The Modal -->
<div class="modal fade" id="modal2" tabindex="-1" role="dialog" aria-labelledby="modalStep1" aria-hidden="true">
	<div class="modal-dialog modal-xl">
		<div class="modal-content p-4">
			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title" id="alNameSpan">자주 쓰는 결재선 추가</h4>
				<button type="button" class="close ml-1" data-dismiss="modal">&times;</button>
			</div>
			<input type="hidden" name="memId" value="${authMember.memId}" />
			<div class="modal-body">
				<div class="row mt-2">
					<div class="col-12">
						<div class="row">
							<div class="col-10">
								<b>결재</b>
								<ul class="list-group border border-success rounded" id="approvalMemberList">
								</ul>
							</div>
							<div class="col-2">
								<img class="mt-4" src="${cPath }/images/pngs/arrow.png" />
							</div>
							<br />
							<hr />
							<div class="col-12 ">
								<b>참조</b>
								<ul class="list-group border border-info rounded" id="referenceMemberList">
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<script src="${cPath}/js/e_approvel/userApprovalSetting.js"></script>
<style type="text/css">
.ztree * {
	font-size: 1rem;
	font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Web Light",
		"Segoe UI Light", "Segoe UI Web Regular", "Segoe UI",
		"Segoe UI Symbol", "Helvetica Neue", Arial
}

.ztree li ul {
	margin: 0;
	padding: 0
}

.ztree li {
	line-height: 30px;
}

.ztree li a {
	width: 200px;
	height: 30px;
	padding-top: 0px;
}

.ztree li a:hover {
	text-decoration: none;
	background-color: #E7E7E7;
}

.ztree.showIcon li a span.button.switch {
	visibility: visible
}

.ztree li a.curSelectedNode {
	background-color: #D4D4D4;
	border: 0;
	height: 30px;
}
/* 	.ztree li span {line-height:30px;} */
/* 	.ztree li span.button {margin-top: -7px;} */
.ztree li span.button.switch {
	width: 16px;
	height: 16px;
}

.ztree li a .deptSpan {
	font-weight: bold;
}
/* 	.ztree li a.level0 .deptSpan {font-size: 150%} */
.ztree li span.switch {
	background-image:
		url("${cPath }/plugins/zTree_v3-master/demo/en/super/left_menuForOutLook.png");
	*background-image:
		url("${cPath }/plugins/zTree_v3-master/demo/en/super/left_menuForOutLook.gif")
}

.ztree li span.button.switch.level0 {
	width: 20px;
	height: 20px
}

.ztree li span.button.switch.level1 {
	width: 20px;
	height: 20px
}

.ztree li span.button.noline_open {
	background-position: 0 0;
}

.ztree li span.button.noline_close {
	background-position: -18px 0;
}

.ztree li span.button.noline_open.level0 {
	background-position: 0 -18px;
}

.ztree li span.button.noline_close.level0 {
	background-position: -18px -18px;
}
</style>


