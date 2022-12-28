<%--
* [[개정이력(Modification Information)]]
*   수정일                     수정자              수정내용
* ----------     ---------  -----------------
* 2021. 1. 27.    이운주                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href="${cPath}/css/vote/vote.css" rel="stylesheet">

<!-- 오늘 날짜 -->
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" var="today"/>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">투표게시판</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">투표게시판</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->
<!-- Row -->

<!-- 이미 참여한 구성원인지 check -->
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }"/>
<c:if test="${not empty authMember}">
	<c:if test="${not empty voteBoardVO.voterList  }">
		<c:set var="writerId" value="${voteBoardVO.memId }" />
		<c:set var="writerName" value="${voteBoardVO.memName }" />
		<c:forEach items="${voteBoardVO.voterList }" var="already">
			<c:if test="${already.memId eq authMember.memId}">
				<c:set var="voteAlready" value="true" />
			</c:if>
		</c:forEach>
	</c:if>
</c:if>

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<form:form commandName="voteFormVO" method="POST" id="votingForm" cssClass=" text-center">
					<input type="hidden" id="vbNo" name="voteBoardVO.vbNo"
						value="${voteBoardVO.vbNo }" />
					<input type="hidden" id="vbDup" name="voteBoardVO.vbDup"
						value="${voteBoardVO.vbDup }" />
					<input type="hidden" id="voterId"
						name="voteBoardVO.votePrtcpVO.memId" value="${authMember.memId }" />
					<div class="table-responsive">
						<div class="modal-header btn-primary" id="modal-inner">
							<h5 class="">${voteBoardVO.vbTitle }
								<span><c:out
										value="${voteBoardVO.vbDup eq 'N'? '': ' (중복투표 가능)' }"></c:out>
								</span>
							</h5>
						</div>
						<div class="mdl-layout__content">
							<div class="page-content">
								<div style="text-align: center; padding: 16px;">
									
									<div class="">
										<div>
											작성자 :
											<c:out value="${writerName }" />
										</div>
										<div>
										<c:set var="vbEnd" value="${voteBoardVO.vbEnd }"></c:set>
											투표기간 :
											<c:out value="${voteBoardVO.vbDate}" />
											~
											<c:out value="${voteBoardVO.vbEnd }" />
										</div>
										<br />
										<div class="">${voteBoardVO.vbContent }</div>
										<br/>
										<c:if test="${voteBoardVO.vbDelYn eq 'N' }">
											<table id="voteViewTable"
												class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover">
												<tbody id="viewBody">
													<c:forEach items="${voteBoardVO.voteCateList }" var="vote">
														<tr>
															<td style="width: 10%">
															<input type="checkbox" name="voteBoardVO.votePrtcpVO.vcNos" class="vcNo"
																id="${vote.vbNo + vote.vcNo }" value="${vote.vcNo }" />&nbsp;&nbsp;
																${vote.vcNo }</td>
															<td style="text-align:left; width: 90%">${vote.vcName }</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
	
											<input class="btn ripple btn-primary" type="button"
												id="votingBtn" value='${voteAlready ? "참여완료" : "투표하기"}'
												${voteAlready ? "disabled" : ""} />
											<c:if test="${authMember.memId eq writerId }">
												<button class="btn ripple btn-warning" type="button"
													id="updateBtn">수정</button>
												<button class="btn ripple btn-danger" type="button"
													id="deleteBtn">삭제</button>
											</c:if>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer"></div>
					</div>

					<c:if test="${empty voteBoardVO }">
						<div class="modal-header btn-secondary" id="modal-inner">
							<table>
								<tr>
									<td>게시물이 없습니다.</td>
								</tr>
							</table>
						</div>
					</c:if>
					
<%-- 					<c:if test="${empty voteBoardVO }"> --%>
<!-- 						<div class="modal-header btn-secondary" id="modal-inner"> -->
<!-- 							<table> -->
<!-- 								<tr> -->
<!-- 									<td>게시물이 없습니다.</td> -->
<!-- 								</tr> -->
<!-- 							</table> -->
<!-- 						</div> -->
<%-- 					</c:if> --%>
					
					
				</form:form>
				<!-- 투표 본문 end  -->
				
				<!-- votechart -->
				<div class="column" style="display: flex; justify-content: center;">
					<div class="col-6">
						<div class="card-body">
							<canvas id="voteCompleteChart" style="height: 50px;"></canvas>
						</div>
					</div>
				</div>

				<%-- 				<jsp:include page="/WEB-INF/views/voteBoard/voteComplete.jsp"></jsp:include> --%>
				
				<!-- 댓글 -->
				<div id="reply" class="row">
					<div id="reply-header" class="col-12">
						<strong>댓글</strong>
					</div>
				</div>

				<!-- 댓글 등록 폼 -->
				<div>
					<div id="reply-form" class="row">
						<form class="col-12" action="${cPath}/voteBoard/reply"
							id="replyInsertForm" method="POST">
							<input type="hidden" name="vbrNo" /> 
							<input type="hidden"  name="vbNo" value="${voteBoardVO.vbNo }" /> 
							<input type="hidden" name="memId" value="${authMember.memId }" />
							<input type="hidden" name="parentNo" id="parentNo"/>
								<div class="reply col-12 pl-0 pr-0">
									<div class="mb-1">
										<div>
											<b>${authMember.memName } / ${authMember.deptName }</b>
										</div>
									</div>
									<div class="d-flex">
										<textarea name="vbrContent" style="height:100%;width:90%;"
											id="vbrContent" class="voteReply__input" rows="3" required="required"></textarea>
										<input class="btn ripple btn-primary ml-2" type="submit"
											id="replyInsertBtn" value='등록' />
									</div>
								</div>
						</form>
					</div>
				</div>
				<!-- 댓글 등록 폼 end -->
				<hr>
				<!-- 댓글 리스트 -->
				<div id="reply-list" class="col-12"></div>
				<div id="pagingArea" class="col-12"></div>
				
			</div>
		</div>
	</div>
</div>

<!-- 댓글 관련 -->
<form id="searchForm" action="${cPath}/voteBoard/reply" method="get">
	<input type="hidden" name="vbNo" value="${voteBoardVO.vbNo }" />
	<input type="hidden" name="page" />
</form>





<c:set var="voteBoardList" value="${voteBoardList }" />
<script type="text/javascript">
	var myId = "${authMember.memId}"
	var end = "${vbEnd }"
		
	
	let voteComplete = JSON.parse('${voteBoardList}');
	console.log(voteComplete);
	let voteCateList = voteComplete.voteCateList;
	
	let data = [];
	let labels = [];
	
	$(voteCateList).each(function(idx, vote){
		labels.push(vote.vcName);
		data.push(vote.voteCount);
	});
</script>
<script src="${cPath}/js/vote/voteView.js?v=1"></script>
<script src="${cPath}/js/vote/voteReply.js?v=1"></script>
