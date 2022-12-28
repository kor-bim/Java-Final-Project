<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 2.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mb-3">부서 게시판</h2>
		<ol class="breadcrumb mb-2">
			<li class="breadcrumb-item"><a href="">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">부서게시판</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div class="justify-content-center">
					<h5>
						<input type="button" value="새글쓰기" id="insertBtn" style="font-size:1.2rem;" onclick="location.href='${cPath}/departMentBoard/insert'" class="btn btn-primary" />
					</h5>
	            </div>
				<div>
					<table id="dbtable" class="table table-bordered table-hover">
						<colgroup>
							<col width="5%" />
							<col width="40%" />
							<col width="5%" />
							<col width="7%" />
							<col width="5%" />
						</colgroup>
						<thead class="text-center">
							<tr>
								<th>
									<spring:message code="departmentboard.db_no" />
								</th>
								<th>
									<spring:message code="departmentboard.db_title" />
								</th>
								<th>
									<spring:message code="departmentboard.db_writer" />
								</th>
								<th>
									<spring:message code="departmentboard.db_date" />
								</th>
								<th>
									<spring:message code="departmentboard.db_hit" />
								</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<c:set var="boardList" value="${pagingVO.dataList }" />
							<c:if test="${not empty boardList }">
								<c:forEach items="${boardList }" var="board">
									<tr>
										<td style="display: none">${board.dbNo }</td>
										<td>${board.rnum }</td>
										<td class="text-left">${board.dbTitle }</td>
										<td>${board.memId }</td>
										<td>${board.dbDate }</td>
										<td>${board.dbHit }</td>
										<td style="display: none">${board.dbDelYn }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty boardList }">
								<tr>
									<td colspan="6" onclick="event.cancelBubble=true">조건에 맞는 게시글이 없음.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<form id="searchForm">
						<input type="hidden" name="page" />
						<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }" />
						<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }" />
					</form>
					<div id="inputUI" class="row mb-3">
						<div class="col-2">
							<select name="searchType" class="form-control mr-1">
								<option value="">전체</option>
								<option value="title" ${'title' eq param.searchType?"selected":"" }>제목</option>
								<option value="writer" ${'writer' eq param.searchType?"selected":"" }>작성자</option>
								<option value="content" ${'content' eq param.searchType?"selected":"" }>내용</option>
							</select>
						</div>
						<div class="col-4">
							<input type="text" name="searchWord" class="form-control mr-3" value="${pagingVO.searchVO.searchWord }" />
						</div>
						<div class="col-auto">
							<input type="button" value="검색" id="searchBtn" class="btn btn-primary" />
						</div>
					</div>
					<div id="pagingArea">${pagingVO.pagingHTML }</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${cPath }/js/departMentBoard/list.js"></script>
