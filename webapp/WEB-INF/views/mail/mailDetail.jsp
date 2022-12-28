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
			<li class="breadcrumb-item active" aria-current="page">${mail.mailTitle }</li>
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-lg-12 col-xl-12 col-md-12">
		<div class="card custom-card mg-t-25">
			<div class="card-body h-100" id="divID">
				<div class="email-media">
					<div class="mb-4 d-lg-flex">
						<h3>${mail.mailTitle }</h3>
						<div class="ml-auto d-none d-md-flex fs-18">
							<a href="javascript:divPrint();" class="mr-3 tx-inverse">
								<i class="fe fe-printer fa-3x" data-toggle="tooltip" title="" data-original-title="인쇄"></i>
							</a>
						</div>
					</div>
					<div class="media mt-0">
						<div class="main-img-user avatar-md mr-3 online">
							<c:if test="${not empty mail.memImg }">
								<img class="rounded-circle" src="${cPath}/profileImages/${mail.memImg}">
							</c:if>
							<c:if test="${empty mail.memImg }">
								<img class="rounded-circle" src="${cPath}/images/pngs/noImage.png">
							</c:if>
						</div>
						<div class="media-body tx-inverse">
							<div class="float-right d-none d-md-flex fs-15">
								<span class="mr-2">
									<fmt:parseDate var="mailDate" value="${mail.mailDate}" pattern="yyyy-MM-dd HH:mm" />
									<fmt:formatDate value="${mailDate}" pattern="MM월 dd일  HH:mm (E)" />
								</span>
								<small class="mr-2">
									<i class="fe fe-star" data-toggle="tooltip" title="" data-original-title="Rated"></i>
								</small>
								<small class="mr-2">
									<i class="fa fa-reply" data-toggle="tooltip" title="" data-original-title="Reply"></i>
								</small>
								<div class="mr-2">
									<a href="#" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
										<i class="fe fe-more-horizontal text-dark" data-toggle="tooltip" title="" data-original-title="View more"></i>
									</a>
									<div class="dropdown-menu dropdown-menu-right shadow">
										<a class="dropdown-item" href="javascript:history.back()">뒤로가기</a>
										<a class="dropdown-item" href="${cPath}/mail/moveToTrash/${mail.mailNo}">삭제</a>
										<a class="dropdown-item" href="javascript:divPrint();">인쇄</a>
									</div>
								</div>
							</div>
							<div class="media-title font-weight-semiblod">
								<span class="tx-18 font-weight-bold">${mail.memName}</span>
								<p class="mb-0 text-muted">${mail.senderId}@ddit.or.kr</p>
								<span class="mr-2 d-md-none">
									<fmt:parseDate var="mailDate" value="${mail.mailDate}" pattern="yyyy-MM-dd HH:mm" />
									<fmt:formatDate value="${mailDate}" pattern="MM월 dd일  HH:mm (E)" />
								</span>
								<small class="mr-2 d-md-none">
									<i class="fe fe-star" data-toggle="tooltip" title="" data-original-title="Rated"></i>
								</small>
								<small class="mr-2 d-md-none">
									<i class="fa fa-reply" data-toggle="tooltip" title="" data-original-title="Reply"></i>
								</small>
							</div>
						</div>
					</div>
				</div>
				<div class="eamil-body">
					<h5 class="mb-3">
						수신자 :
						<c:forEach items="${mail.mailReceiverList}" var="receiver" varStatus="vs">
							<span class="bg-teal text-white rounded p-1">${receiver.memName}/${receiver.receiverId}@ddit.or.kr</span>
							${not vs.last?", ":"" }
						</c:forEach>
					</h5>
					<hr>
					${mail.mailContent}
					<hr>
					<c:if test="${not empty mail.attatchList }">
						<div class="email-attch">
							<p>첨부파일</p>
							<div class="emai-img">
								<div class="row row-sm">
									<c:forEach items="${mail.attatchList }" var="attatch" varStatus="vs">
										<c:choose>
											<c:when test="${attatch.maExtns eq 'image/jpeg' }">
												<div class="col-sm-3">
													<a href="${cPath}/mail/download/${attatch.maNo }">
														<img class="w-100" src="${cPath}/mailFiles/${attatch.maName}" alt="Generic placeholder image">
													</a>
													<h6 class="mb-3 mt-3 mb-lg-0">
														${attatch.maRealname }
														<small class="text-muted">${attatch.maFancy }</small>
													</h6>
												</div>
											</c:when>
											<c:otherwise>
												<c:if test="${attatch.maNo ne null}">
													<div class="col-sm-2 m-0">
														<a href="${cPath}/mail/download/${attatch.maNo }">
															<span title="다운로드:">${attatch.maRealname }</span>
															${not vs.last?",":"" }
														</a>
													</div>
												</c:if>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<div class="card-footer">
				<a class="btn ripple btn-primary mt-1 mb-1" href="#" onclick="location.href = document.referrer; ">
					<i class="fa fa-reply"></i>
					뒤로가기
				</a>
			</div>
		</div>
	</div>
</div>
<script src="${cPath}/js/mail/mailDetail.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/views/mail/mailForm/mailForm.jsp"></jsp:include>
