<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 23.   서대철      최초작성
*        3.  1.   이운주      의견 부분수정
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="${cPath}/css/e_approvel/approvalDertail_Y.css?v=1">

<style>
table th{
	text-align: center;
}

th {
	background-color: #efefff;
}

h1 {
	text-align: center;
}

</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">삭제 문서 상세 조회</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${cPath }/admin/deleteDocumentBox">뒤로가기</a></li>
		</ol>
	</div>
</div>
<!-- End Page Header -->
    
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<form:form commandName="approvalDocVO">
					<form:hidden path="adNo" value="${approvalDocVO.adNo }"/>
				</form:form>
				<button type="button" class="btn btn-outline-light backUpBtn">복원</button>
				<button type="button" class="btn btn-outline-light realDeleteBtn">완전 삭제</button>
				<h1>${approvalDocVO.adTitle }</h1> 
				<fieldset>
					<table class="table table-bordered">
						<colgroup>
							<col width="12.09%">
							<col width="37.91%">
							<col width="12.09%">
							<col width="37.91%">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">문서 분류</th>
								<td>${approvalDocVO.documentsVO.docTypeName }&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;&nbsp;${approvalDocVO.adTitle }</td>
								<th scope="row">문서 번호</th>
								<td>${approvalDocVO.adNo }</td>
							</tr>
							<tr>
								<th scope="row">기안부서</th>
								<td>${approvalDocVO.memberVO.deptName }</td>
								<th scope="row">기안자</th>
								<td>${approvalDocVO.memberVO.memName }</td>
							</tr>
							<tr>
								<th scope="row">보존 연한</th>
								<td>${approvalDocVO.documentsVO.presName }</td>
								<th scope="row">보안등급</th>
								<td>${approvalDocVO.documentsVO.scuCode }등급</td>
							</tr>
							<tr>
								<th scope="row">기안 일시</th>
								<td>${approvalDocVO.adDate }</td>
								<th scope="row">완료 일시</th>
								<td>${approvalDocVO.adFinishDate }</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
				<div id="lineFormDiv">
				
				</div>
				<!-- 문서들어오는 곳 -->
				<div>
					<p>${approvalDocVO.adContent }</p>
				</div>
				
				<!-- 의견 -->
				<div class="board_comment_tab" id="approvalCommentsTab">
					<a href="javascript:void(0);" class="gt-nav-item gt-active approval-comments-tab1" data-id="tab1-1" onclick="ApprovalProcess.getApprovalComments();">의견</a>
				</div>
				<div id="divApprovalComments" class="board_comment approval">
					<div class="row">
						<div class="col-12">
							<div class="row ml-2">
								<c:set var="comList" value="${commentList }" />
								<c:if test="${not empty comList}">
									<c:forEach items="${comList }" var="comment">
											<div class="col-1 mt-2 p-0">
												<img alt="img" src="${cPath}/profileImages/${comment.memberVO.memImg}">
											</div>
											<div class="col-11 mt-2">
												<span class="">${comment.memberVO.memName } </span> <span style='color: grey; font-size:0.9rem;'> ${comment.adAd }</span>
												<p class="mt-1" style="font-size: 1rem;">${comment.adComment }</p>
											</div>
									</c:forEach>
								</c:if>
								<c:if test="${empty comList }">
									<div>등록된 의견이 없습니다.</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 상세페이지 문서 삭제 확인  modal-->
<div class="modal fade bd-example-modal-sm" id="documentDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="documentDeleteModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">문서를 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="documentDeleteBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	let detail = ${detailJSON};
	
	const LINEFORMDIV = $("#lineFormDiv");
	LINEFORMDIV.empty();
	LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function(){
		var lineData = makeLineDataByJSON(detail);
		lineData.needBtns = false;
		lineData.needSigns = true;
		makeApprovalLineForm(lineData);  // ok 
	}); 
	
	// ================ 백업 ===============================================
	$(".backUpBtn").on("click", function(){
		let adNum = adNo.value;
		location.href = $.getContextPath() + "/admin/approvalDocumentBackUpDetail/" + adNum;
	});
	
	// ================= 삭제확인 modal ===========================================================
	let documentDeleteModal = $("#documentDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$(".realDeleteBtn").on("click", function(){
		documentDeleteModal.modal();
		$("#documentDeleteBtn").on("click", function(){
			let adNum = adNo.value;
			console.log(adNo.value);
			location.href = $.getContextPath() + "/admin/approvalDocumentRealDelete/" + adNum;
		});
	});
</script>