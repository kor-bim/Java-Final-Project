<%--
* 전직원 휴가 현황
*
* [[개정이력(Modification Information)]]
*   수정일                 수정자              수정내용
* ----------  ---------  -----------------
* 2021. 2. 15.      이운주                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="${cPath}/css/vacation/vacation.css" rel="stylesheet">

<c:set var="statMemberList" value="${vacaMemberList }"/>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">직원 휴가 관리</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">휴가관리</a></li>
			<li class="breadcrumb-item active" aria-current="page">직원 휴가 관리</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
			<form:form commandName="vacaStatusVO" action="${cPath }/admin/vacaStatus" id="vacaStatForm">
				<form:hidden path="searchYear"    id="searchYear"/>
				<form:hidden path="deptCode"      id="searchDept"/>
				
				<div class="row col-12 ">
					<div class="col-auto d-flex mr-4 align-self-center">
						<a href="javascript:void(0)" class="layerShow">
							<span id="selected_filter_year" >2021년<input type="hidden" name="vacaYear" id="vacaYearSelect" value="2021"/></span> 
							<i class="fas fa-angle-down"></i>
						</a>
					</div>
					<div class="col-auto d-flex mr-4 align-self-center">
						<a href="javascript:void(0)" class="deptLayerShow blue_color">
							<span id="filtered_org_name">조직</span>
							<i class="fas fa-angle-down"></i>
						</a>
						<div class="dropdown-menu depttree" id="deptTreeDIV" style="padding:0; overflow:auto; height:250px; width:250px; ">
						
						</div>
					</div>
					<div class="col-auto d-flex">
						<input type="text" name="searchKeyword" class="form-control">
						<input type="button" value="검색" id="searchBtn" class="btn btn-primary ml-1" placeholder="이름검색"/>
					</div>
				</div>
				<table class="table table-bordered border-t0 key-buttons text-nowrap w-100 text-center" id="vacaStatTable">
					<thead>
						<tr>
							<c:set var="vacaTypeCnt" value="${vacaTypeList.size() }"/>
							<th class="align-middle" rowspan="2">이름</th>
							<th class="align-middle" rowspan="2">입사일</th>
							<th class="align-middle" colspan="2">생성내역</th>
							<th class="align-middle" colspan="${vacaTypeCnt }" id="useSummary">사용현황</th>
							<th class="align-middle" rowspan="2">잔여</th>
							<th class="align-middle" rowspan="2">수정</th>
						</tr>
						<tr >
							<th class="align-middle">연차</th>
							<th class="align-middle">포상</th>
							
							<c:forEach var="vacaType" items="${vacaTypeList }">
								<th class="align-middle">${vacaType.vtName }</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody id="listBody">
						<c:if test="${not empty vacaMemberList  }">
						<c:forEach var="statMember" items="${vacaMemberList }" >
							<tr data-memid="${statMember.memId }">
								<td>${statMember.memName }</td>
								<td>${statMember.memHdate }</td>
								
								<td>${statMember.vacaStatList[0].vacaInday }</td>
								<td>${statMember.vacaStatList[1].vacaInday }</td>
								
								<c:forEach var="stat" items="${statMember.vacaStatList }">
									<td class="${stat.vtCode }">${stat.useCnt }</td>
								</c:forEach>
								<td>${statMember.vacaRmday }</td>
								<td>
					            	<a class="ripple vacaStatUpdateBtn" data-target="vacaStatUpdateModal" data-toggle="modal" href="" id="${statMember.memId }">수정</a>
								</td>
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${empty vacaMemberList  }">
							<tr>
								<td colspan="${vacaTypeCnt+6 }">검색 결과가 없습니다.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</form:form>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<div class="modal fade" id="vacaStatUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="vacaStatUpdateModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="vacaStatUpdateModalLabel">휴가 수정</h5>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<input type="button" value="저장" class="btn btn-outline-primary" id="modifyBtn" />
				<input type="button" value="취소" class="btn btn-outline-danger" data-dismiss="modal" aria-label="Close" id="modifyBtn" />
			</div>
		</div>
	</div>
</div>

<script src="${cPath}/js/vacation/vacaStatus.js?v=1"></script>