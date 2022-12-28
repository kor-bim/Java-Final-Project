<%--
* 진행중인 문서 상세조회
* 
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 13.      길영주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="${cPath}/css/e_approvel/approvalDertail_Y.css?v=1">

<style type="text/css">
</style>
<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<!-- 기안 복사 결제선 변경 인쇄 -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">전자결재</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">진행 중인 문서</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">전체</li>
		</ol>
	</div>
			
</div>

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div class="content_inbox">
					<div class="cont_box">
						<div class="approval-wrap write view">
							<div>
								<h2 class="text-center">${Detail.adTitle }</h2>
								<div>
									<br />
									<table class="table table-bordered">
										<caption>전자결재 문서보기</caption>
										<colgroup>
											<col style="width: 12.09%; background-color: #efefff !important;">
											<col style="width: 37.62%">
											<col style="width: 22.17%; background-color: #efefff;">
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
												<td>${Detail.documentsVO.presName }/${Detail.documentsVO.scuCode }등급</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="afterLoad hide" id="lineFormDiv">
									<!-- 결재선 나오는 곳 -->
								</div>
								<div>
									<!-- 문서 들어올 곳 -->
									<div class="docu-common-wrap">
										<div class="contents after">${Detail.adContent }</div>
									</div>
								</div>
							</div>
							<br />

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
														<span class="">${comment.memberVO.memName } </span>
														<span style='color: grey; font-size: 0.9rem;'> ${comment.adAd }</span>
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


<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">결재</h4>
				<button type="button" class="close" data-dismiss="modal">x</button>
			</div>
			<div class="modal-body">
				<form action="${cPath }/approval/mainProcess" id="approvalForm" method="post">
					<input type="hidden" name="adNo" value="${Detail.adNo}">
					<input type="hidden" name="memId" value="${member.memId}">
					<input type="hidden" name="aprvlStateCode" value="" id="aprvlStateCode" />
					<div class="select-list text-center" id="approvalEnableValue">
						<span>
							<label class="mr-3">
								<input type="radio" name="aprvlTypeCode" value="OK" onchange="approvalChange();">
								승인
							</label>
						</span>
						<span>
							<label class="mr-3">
								<input type="radio" name="aprvlTypeCode" value="REQUEST" onchange="approvalChange();">
								협의 요청
							</label>
						</span>
						<span>
							<label class="mr-3">
								<input type="radio" name="aprvlTypeCode" value="RETURN" onchange="approvalChange();">
								반려
							</label>
						</span>
						<br />
						<c:if test="${Detail.lastApprovalId ne member.memId }">
							<span>
								<label class="mr-3">
									<input type="radio" name="aprvlTypeCode" value="ALLOK" onchange="approvalChange();">
									전결
								</label>
							</span>
						</c:if>
					</div>
					<br />
					<p id="approvalMessage" class="hide pdb_10 text-center" style="display: block;">승인하시겠습니까?</p>
					<textarea name="adComment" rows="10" cols="60" class="form-control" id="msgArea" placeholder="" id="comment" style="resize: none;"></textarea>
					<br />
					<div class="layer_button text-center">
						<button type="submit" class="btn btn-outline-success" id="approvalBtn">확인</button>
						<button type="button" class="btn btn-outline-danger" data-dismiss="modal">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<form action="${cPath }/approval/mainProcess" id="referenceForm" method="post">
	<input type="hidden" name="adNo" value="${Detail.adNo}">
	<input type="hidden" name="memId" value="${member.memId}">
	<input type="hidden" name="aprvlTypeCode" value="CHECK" />
	<input type="hidden" name="aprvlStateCode" value="COMPLETE" />
	<input type="hidden" name="adComment" value="" />
</form>

<script type="text/javascript">
	let detail = ${detailJSON};

	const LINEFORMDIV = $("#lineFormDiv");
	LINEFORMDIV.empty();
	LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function() {
		var lineData = makeLineDataByJSON(detail);
		lineData.needBtns = true;
		lineData.needSigns = true;
		makeApprovalLineForm(lineData); // ok 
	});

	function approvalChange() {
		// message 
		let msgPtag = $("#approvalMessage");
		let msgArea = $("#msgArea");

		// 선택한 값 (radio)
		let val = $("input[name=aprvlTypeCode]:checked").val();

		// 결재 상태 코드 : COMPLETE, CONTINUE, EXPECTED, WAIT
		let stateCode = $("#aprvlStateCode");

		msgPtag.html('');
		msgArea.attr("placeholder", '');
		switch (val) {
		case "OK":
			msgPtag.html("승인하시겠습니까?");
			msgArea.attr("placeholder", '의견을 입력하세요');
			stateCode.val("COMPLETE");
			break;
		case "REQUEST":
			msgArea.attr("placeholder", '협의 요청 사유를 입력하세요');
			stateCode.val("CONTINUE");
			break;
		case "RETURN":
			msgArea.attr("placeholder", '반려 사유를 입력하세요');
			stateCode.val("WAIT");
			break;
		case "ALLOK":
			msgPtag.html("최종 결재자 대신 승인하시겠습니까?");
			stateCode.val("COMPLETE");
			msgArea.attr("placeholder", '의견을 입력하세요');
			break;
		}
	};

	$("#approvalBtn").on("click", function() {
		let val = $("input[name=aprvlTypeCode]:checked").val();
		if(val == "RETURN"){
			if($("#msgArea").val() == ''){
				alert("반려 사유를 입력해주세요");
				return false;				
			}
		}
		$("#approvalForm").submit();
	})
	
</script>