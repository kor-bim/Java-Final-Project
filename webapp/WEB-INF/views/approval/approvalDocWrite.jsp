<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 18.      이운주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="${cPath}/css/e_approvel/approvalDetail2.css">

<!-- 로그인 회원 -->
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">작성하기</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a
				href="${cPath }/approval/approvalAwait">전자결재</a></li>
			<li class="breadcrumb-item active" aria-current="page">작성하기</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">

				<form:form commandName="approvalDocVO" id="approvalDocForm" action="${cPath }/approval/document/draft">
					<input type="hidden" name="memId" id="memId" value="${authMember.memId }" />
					<form:hidden path="alNo" id="alNo" value=""/>
					<div class="content_inbox">
						<h5>
							<input type="button" id="btnWriteSaveDocument" class="btn btn-primary" style="font-size:1.2rem;" onclick="saveDocument('DRAFT');" value="기안하기">
						</h5>
					</div>
					<br />

					<div class="content_inbox">
						<div class="cont_box write">
							<div class="approval-wrap write">
								<h6 style="display: inline-block" class="font-weight-bold">기본설정</h6>
								<table
									class="table table-bordered border-t0 key-buttons text-nowrap w-100">
									<colgroup>
										<col style="width: 12.15%;">
										<col style="width: 37.85%">
										<col style="width: 12.15%">
										<col style="width: 37.85%">
									</colgroup>
									<tbody>
										<tr>
											<th scope="row">문서 종류</th>
											<td>
												<div class="row">
													<div class="col-6">
														<input type="hidden" value=''/>
														<select id="docTypeSelect" class="form-control" style="color: black;" onchange="getDocFormList()">
															<c:forEach items="${docTypeList }" var="docType" varStatus="status">
																<option value="${docType.docTypeCode }" ${status.first ? 'selected':'' }>${docType.docTypeName }</option>
															</c:forEach>
														</select> 
													</div>
													<div class="col-6">
														<select id="docFormSelect" class="form-control" name="dfNo" style="color: black;" required>
															<option value="">선택</option>
														</select>
													</div>
												</div>
											</td>
											<th scope="row">작&nbsp;&nbsp;성&nbsp;&nbsp;자</th>
											<td>
												${authMember.deptName } <span>${authMember.psName }&nbsp;${authMember.memName }</span>
											</td>
										</tr>
										<tr>
											<th scope="row">보존 연한</th>
											<td>
												<div class="row">
													<div class="col-6" id="presName"></div>
												</div>
											</td>

											<th scope="row">보안 등급</th>
											<td>
												<div class="row">
													<div class="col-6" id="slevel"></div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<br />
								<div class="after">
									<h6 class="fl mgr_20 font-weight-bold">결재선</h6>
									<button type="button" class="green vt hide afterLoad"
										id="btnApprovalAdressbookSelect"
										onclick="getApprovalLineSelectModal();">결재선설정</button>
								</div>
								<br />
								<div id="approvalDocumentLine" class="beforeLoad">문서 종류 선택시 결재선이 노출됩니다.</div>
								<div class="afterLoad hide" id="lineFormDIV">
								<!-- 결재선 나오는 곳 -->
								</div>
								
								<div class="write_input js-approval-input mgt_50 afterLoad hide">
									<h6 class="fl mt-2 font-weight-bold">제목</h6>
									<div class="ml-5">
										<input type="text" name="adTitle" class="form-control" id="approval_document_title" value="">
									</div>
								</div>

								<h6 class="mgt_50 font-weight-bold">상세 입력</h6>
								<br />
								<div class="write_input js-approval-input-guide">
									<span id="noContent" class="beforeLoad">문서 종류 선택시 상세 입력이 노출됩니다.</span>
									<div class="afterLoad hide" id="contentDIV">
										<textarea name="adContent" id="adContent" class="form-control"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- 결재선 설정 모달  -->
<div class="modal fade" id="lineSettingModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="approvalLIneModalLabel">
	<div class="modal-dialog modal-xl" role="document">
		<div class="modal-content setLineModal">
			<div class="modal-header">
				<h5 class="modal-title h4" id="approvalLIneModalLabel">결재선 설정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="row mt-1 p-0">
					<div class="col-4 p-0 form-control area-box" id="deptTreeDIV" style="height:600px; overflow:auto">트리</div>
					<div class="col-2 p-0 text-center">
						<br /> <br /> <br /> <br /><br /> <br /><br /><br /><br />
						<button type="button" class="btn btn-outline-primary btn-set-approval" id="approvalBtn" style="width:80%;">결재 <i class="fas fa-chevron-right"></i></button>
						<br/><br/>
						<button type="button" class="btn btn-outline-primary btn-set-approval" id="referenceBtn" style="width:80%;">참조 <i class="fas fa-chevron-right"></i></button>
					</div>
					<div class="col-6 p-0 ">
						<div class="col-12 p-0" style="visibility: visible;">
							<select name="lineDetailSelect" id="savedLineSelect" class=" form-control" style="color: black;">
							</select>
						</div>
						<br/>
						<div class="col-12 p-0">
							<b>결재</b>
							<div class="col-12 p-0 form-control" style="height: 400px" id="approvalArea">
								<div class="list-group-item border-secondary rounded m-1" value="${authMember.memId }" id="drafter">
									${authMember.memName } ( ${authMember.deptName } / ${authMember.psName } ) 
								</div>
							</div>
						</div>
						<br/>
						<div class="col-12 p-0">
							<p class="gt-mt-30 font-size-14 text-333 row-space">참조</p>
							<div class="form-control" style="height:80px;">
								<div class="approval-type-F step-scroll-box-short gt-mt-15">
									<div class="approval-sortable-table horizontal gt-p-0 ui-sortable" id="referenceArea">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br/>
				<br/>
				<div class="col-12 text-center p-0">
					<div class="col-12 text-center">
						<input type="button" value="등록" id="approvalLineMakeBtn" class="btn btn-outline-primary btn-set-command" style="width:100px;"/>
						<button type="button" class="btn btn-outline-secondary ml-2 btn-set-command" data-dismiss="modal" style="width:100px;">닫기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- End Row -->
<script src="${cPath}/js/e_approvel/approvalDocWrite.js"></script>

<script type="text/javascript">
$(function(){
	$("#approvalDocForm").validate({
		rules : {
			dfNo : {
				required : true
			}
			, adTitle : {
				required : true
			}
		},
		messages : {
			dfNo : {
				required : "양식을 선택해주세요."
			}
			, adTitle : {
				required : "제목을 입력해주세요."
			}
		},
	    errorPlacement: function(error, element) {
			element.tooltip({
			title: error.text()
	             , placement: "top"
	             , trigger: "manual"
	             , delay: { show: 500, hid: 100 }
			}).on("shown.bs.tooltip", function() {
				let tag = $(this);
	            setTimeout(() => {
					tag.tooltip("hide");
	            }, 2000)
			}).tooltip('show');
	    }
	});
});
</script>
