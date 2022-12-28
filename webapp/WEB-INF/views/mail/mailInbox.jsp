<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 8.     이재형         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">메일</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${cPath}/mail/inbox">메일</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">받은 메일함</li>
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-xl-12 col-lg-12  main-content-body-mail1">
		<div class="card custom-card mail-container task-container overflow-hidden">
			<div class="inbox-body p-4">
				<div class="mail-option">
					<div class="chk-all border-0">
						<div class="btn-group">
							<a data-toggle="dropdown" href="#" class="btn mini all" aria-expanded="false" id="dropdown">
								전체
								<i class="fe fe-chevron-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:all();" onclick="select(this);"> 전체</a>
								</li>
								<li>
									<a href="javascript:read();" onclick="select(this);"> 읽음</a>
								</li>
								<li>
									<a href="javascript:unRead();" onclick="select(this);"> 안 읽음</a>
								</li>
							</ul>
						</div>
					</div>
					<div class="btn-group">
						<a data-original-title="Refresh" data-placement="top" data-toggle="dropdown" href="#" class="btn mini tooltips mailsToTrashBtn">
							<i class="fe fe-trash mr-2"></i>
							삭제
						</a>
					</div>
					<ul class="unstyled" style="display: inline-block; list-style: none; float: right;">
						<li>
							<strong id="pagingArea">${pagingVO.pagingHTML }</strong>
						</li>
					</ul>
				</div>
				<div class="table-responsive">
					<table class="table table-inbox text-md-nowrap table-hover text-nowrap" id="mailTable">
						<c:set var="mailList" value="${pagingVO.dataList }" />
						<tbody>
							<c:if test="${not empty mailList }">
								<c:forEach items="${mailList }" var="mail">
									<c:choose>
										<c:when test="${((mail.mailReceiverVO.receiverId eq member.memId) and (mail.mailReceiverVO.recRead eq 'Y')) or ((mail.senderId eq member.memId) and (mail.mailRead eq 'Y'))}">
											<tr class="text-muted read">
												<td class="mailNo" style="display: none" data-mail-no="${mail.mailNo }" data-rec-id="${mail.mailReceiverVO.receiverId }" data-rec-star="${mail.mailReceiverVO.recStar }" data-sender-id="${mail.senderId }" data-mail-star="${mail.mailStar }" data-mem-id="${member.memId }">${mail.mailNo}</td>
												<td class="inbox-small-cells">
													<label class="custom-control custom-checkbox mb-0">
														<input type="checkbox" class="custom-control-input" name="trashMailNo" value="${mail.mailNo }">
														<span class="custom-control-label"></span>
													</label>
												</td>
												<c:if test="${mail.mailReceiverVO.recStar eq 'N'}">
													<td class="inbox-small-cells">
														<i class="fa fa-star inbox-started"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recStar eq 'Y'}">
													<td class="inbox-small-cells">
														<i class="fa fa-star inbox-started text-warning"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recImport eq 'N'}">
													<td class="inbox-small-cells">
														<i class="fa fa-bookmark"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recImport eq 'Y'}">
													<td class="inbox-small-cells">
														<i class="fa fa-bookmark text-danger"></i>
													</td>
												</c:if>
												<td class="view-message dont-show font-weight-semibold">${mail.senderId}@ddit.or.kr</td>
												<td class="view-message">${mail.mailTitle }</td>
												<td class="view-message text-right font-weight-semibold">
													<fmt:parseDate var="mailDate" value="${mail.mailDate}" pattern="yyyy-MM-dd HH:mm" />
													<fmt:formatDate value="${mailDate}" pattern="MM월 dd일  HH:mm (E)" />
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr class="unRead">
												<td class="mailNo" style="display: none" data-mail-no="${mail.mailNo }" data-rec-id="${mail.mailReceiverVO.receiverId }" data-rec-star="${mail.mailReceiverVO.recStar }" data-sender-id="${mail.senderId }" data-mail-star="${mail.mailStar }" data-mem-id="${member.memId }">${mail.mailNo}</td>
												<td class="inbox-small-cells">
													<label class="custom-control custom-checkbox mb-0">
														<input type="checkbox" class="custom-control-input" name="trashMailNo" value="${mail.mailNo }">
														<span class="custom-control-label"></span>
													</label>
												</td>
												<c:if test="${mail.mailReceiverVO.recStar eq 'N'}">
													<td class="inbox-small-cells">
														<i class="fa fa-star inbox-started"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recStar eq 'Y'}">
													<td class="inbox-small-cells">
														<i class="fa fa-star inbox-started text-warning"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recImport eq 'N'}">
													<td class="inbox-small-cells">
														<i class="fa fa-bookmark"></i>
													</td>
												</c:if>
												<c:if test="${mail.mailReceiverVO.recImport eq 'Y'}">
													<td class="inbox-small-cells">
														<i class="fa fa-bookmark text-danger"></i>
													</td>
												</c:if>
												<td class="view-message dont-show font-weight-semibold">${mail.senderId}@ddit.or.kr</td>
												<td class="view-message">${mail.mailTitle }</td>
												<td class="view-message text-right font-weight-semibold">
													<fmt:parseDate var="mailDate" value="${mail.mailDate}" pattern="yyyy-MM-dd HH:mm" />
													<fmt:formatDate value="${mailDate}" pattern="MM월 dd일  HH:mm (E)" />
												</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
							<c:if test="${empty mailList }">
								<tr class="text-center">
									<td colspan="6" onclick="event.cancelBubble=true">받은 메일이 없음.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<!-- mail-content -->
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/mail/mailForm/mailForm.jsp"></jsp:include>
<form id="searchForm">
	<input type="hidden" name="page" />
</form>
<script src="${cPath}/js/mail/mailCommon.js" type="text/javascript"></script>
