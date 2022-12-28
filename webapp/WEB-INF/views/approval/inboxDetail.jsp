<%--
* 문서함 
* 컨트롤러 : ElecapprovalInBoxController

* [[개정이력(Modification Information)]]
* 수정일                      수정자          수정내용
* ----------  ---------  -----------------
* 2021. 2. 13.   길영주        최초작성

* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<c:set var="member" value="${principal.realMember }" />


<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">문서함</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="${cPath }/approval/inBoxAllList">문서함</a></li>
			<li class="breadcrumb-item active" aria-current="page">상세조회</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- 기안 복사 결제선 변경 인쇄 -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
<!-- 				<div class="header_title"> -->
<%-- 					<form> --%>
<!-- 						<fieldset> -->
<!-- 							<span class="detail_select"> -->
<!-- 								<a href="javascript:void(0);" class="fl" onclick="ApprovalProcess.copyDocument();"> 기안복사 </a> -->
<!-- 							</span> -->
<!-- 						</fieldset> -->
<%-- 					</form> --%>
<!-- 				</div> -->
				<!-- 결재 할 곳 -->
				<div class="content_inbox">
					<div class="cont_box">
						<div class="approval-wrap write view">
							<div>
								<h2 style="text-align: center">${Detail.adTitle } </h2>
								<div>
								<br/>
									<table class="table table-bordered">
										<colgroup>
											<col style="width: 12.09%;">
											<col style="width: 37.62%">
											<col style="width: 22.17%">
											<col style="width: 28.12%">
										</colgroup>
										<tbody>
											<tr>
												<th scope="row">기안 부서</th>
												<td>${Detail.memberVO.deptName }</td>
												<th scope="row">기안자</th>
												<td>${Detail.memId }</td>
											</tr>
											<tr>
												<th scope="row">문서 번호</th>
												<td>${Detail.adNo }</td>
												<th scope="row">보존 연한 / 보안 등급</th>
												<td>${Detail.documentsVO.presName } / ${Detail.documentsVO.scuCode }등급</td>
											</tr>
										</tbody>
									</table>
									
									<!-- 결재라인 -->
									<div id="lineFormDiv">
									</div>
									<!-- 문서 내용 -->
									<div>
										<p>${Detail.adContent }</p>
									</div>
								</div>
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

	// ================= 삭제확인 modal ===========================================================
	let documentDeleteModal = $("#documentDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$(".viewDeleteBtn").on("click", function(){
		documentDeleteModal.modal();
		$("#documentDeleteBtn").on("click", function(){
			let adNum = adNo.value;
			location.href = $.getContextPath() + "/admin/approvalDocumentDetailDelete/" + adNum;
		});
	});
</script>