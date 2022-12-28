<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 3.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<!-- Page Header -->
<div class="page-header row">
	<div class="col-2"></div>
	<div class="col-8">
		<h2 class="main-content-title tx-24 mb-2">부서 게시판</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">부서게시판</li>
		</ol>
		<input type="button" value="목록으로" class="btn btn-info"onclick="location.href='${cPath}/departMentBoard'"/>
		<c:if test="${member.memName eq board.memId}">
			<input type="button" value="수정" id="updateBtn" onclick="location.href='${cPath}/departMentBoard/update/${board.dbNo}'" class="btn btn-primary" />
			<input type="button" value="삭제" id="deleteBtn" class="btn btn-danger" />
		</c:if>
	</div>
	<div class="col-2"></div>
</div>
<!-- End Page Header -->

<div class="row row-sm">
	<div class="col-lg-2"></div>
	<div class="col-lg-8">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div>
					<div class="row g-3">
						<div class="col-md-6">${board.rnum }${board.dbDate }</div>
						<div class="col-md-6 d-flex justify-content-end">
							<h5>${board.memId }</h5>
							<i class="fas fa-eye ml-3">${board.dbHit }</i>
						</div>
						<div class="col-12">
							<h3>
								<strong>${board.dbTitle }</strong>
							</h3>
						</div>
						<div class="col-12 mt-4">${board.dbContent }</div>
					</div>
					<div class="row g-3">
						<div class="col-12">
							<c:if test="${not empty board.attatchList }">
								<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
									<a href="${cPath}/departMentBoard/download/${attatch.dbaNo }">
										<span title="다운로드:">${attatch.dbaRealname }</span>
										${not vs.last?"|":"" }
									</a>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<hr>
					<!-- 댓글 -->
					<div id="reply" class="row">
						<div id="reply-header" class="col-12">
							<h3>
								<i class="fas fa-comments"></i>
							</h3>
						</div>
						<!-- 댓글 리스트 -->
						<div id="reply-list" class="col-12"></div>
						<div id="pagingArea" class="col-12"></div>
						<!-- 댓글 등록 폼 -->
						<div id="reply-form" class="col-12">
							<form class="row g-3" action="${cPath}/departMentBoard/reply/insert" id="replyInsertForm" method="post">
								<input type="hidden" name="dbrNo" />
								<input type="hidden" name="dbNo" value="${board.dbNo}" />
								<input type="hidden" name="memId" value="${member.memId }" />
								<div class="col-12">
									<textarea name="dbrContent" class="form-control" required="required"></textarea>
								</div>
								<div class="col-12 mt-1">
									<button type="submit" class="btn btn-primary">등록</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-2"></div>
</div>

<form id="deleteForm" action="${cPath}/departMentBoard/delete" method="post">
	<input type="hidden" name="dbNo" value="${board.dbNo}" />
</form>
<form id="searchForm" action="${cPath}/departMentBoard/reply/list" method="get">
	<input type="hidden" name="dbNo" value="${board.dbNo}" />
	<input type="hidden" name="page" />
</form>

<script>
	var myId = "${member.memName}"
</script>
<script type="text/javascript" src="${cPath}/js/departMentBoard/boardView.js"></script>
<script type="text/javascript" src="${cPath}/js/departMentBoard/reply.js"></script>
