<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 16.      길영주         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">진행중인 문서</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${cPath}/approval/approvalAllList">진행중인 문서</a>
				<br>
			</li>
			<li class="breadcrumb-item active" aria-current="page">전체</li>
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-body">
				<table class="table text-nowrap text-md-nowrap table-bordered mg-b-0" id="detail">
					<colgroup>
						<col width="10%" />
						<col width="50%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
						<col width="10%" />
					</colgroup>
					<thead class="text-center">
						<tr>
							<th style="font-size:1.2rem;"  onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.ad_no" />
								</span>
							</th>
							<th style="font-size:1.2rem;" onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.ad_title" />
								</span>
							</th >
							<th style="font-size:1.2rem;" onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.mem_id" />
								</span>
							</th>
							<th style="font-size:1.2rem;" onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.ad_date" />
								</span>
							</th>
							<th style="font-size:1.2rem;" onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.aldt_name" />
								</span>
							</th>
							<th style="font-size:1.2rem;" onclick="event.cancelBubble=true">
								<span>
									<spring:message code="approval.ds_name" />
								</span>
							</th>
						</tr>
					</thead>
					<tbody class="text-center">
						<c:set var="approvalAllList" value="${pagingVO.dataList }" />
						<c:if test="${not empty approvalAllList }">
							<c:forEach items="${approvalAllList }" var="approval">
								<tr>
									<td>${approval.adNo }</td>
									<td class="text-left">${approval.adTitle }</td>
									<td>${approval.memId }</td>
									<td>${approval.adDate }</td>
									<td>${approval.aldtName }</td>
									<td>${approval.dsName }</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty approvalAllList }">
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
				<div id="inputUI" class="row mt-3">
					<div class="col-3">
						<select name="searchType" class="form-control mr-1">
							<option value=""><spring:message code="inBox.all" /></option>
							<option value="no"><spring:message code="approval.ad_no" /></option>
							<option value="title" ${'title' eq param.searchType?"selected":"" }><spring:message code="approval.ad_title" /></option>
							<option value="writer" ${'writer' eq param.searchType?"selected":"" }><spring:message code="approval.mem_id" /></option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="searchWord" class="form-control mr-3" value="${pagingVO.searchVO.searchWord }" />
					</div>
					<div class="col-auto">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary" />
					</div>
				</div>
			</div>
			<div id="pagingArea">${pagingVO.pagingHTML}</div>
		</div>
	</div>
	<!-- COL END -->
</div>
<script src="${cPath}/js/e_approvel/approvalAllList.js"></script>
