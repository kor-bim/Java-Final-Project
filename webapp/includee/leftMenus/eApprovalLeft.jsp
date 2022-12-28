<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 09.      길영주      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">전자결재</span>
	</li>
	<li class="nav-item">
		<input type="button" class="btn btn-primary col-10 ml-3" value="작성하기" onclick="location.href='${cPath}/approval/document/write'">
	</li>
	<li class="nav-item">
		<a class="nav-link with-sub" href="${cPath}/approval/approvalAllList">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-settings sidemenu-icon"></i>
			<span class="sidemenu-label">진행중인 문서</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/approvalAllList">전체</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/approvalAwait">
					대기
					<span class="ml-4 badge badge-warning" id="awaitCount"></span>
				</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/approvalConfirm">
					확인
					<span class="ml-4 badge badge-info" id="confirmCount"></span>
				</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/approvalProgress">
					진행
					<span class="ml-4 badge badge-primary" id="progressCount"></span>
				</a>
			</li>
		</ul>
	</li>
	<li class="nav-item">
		<a class="nav-link with-sub" href="${cPath}/approval/inBoxAllList">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-user sidemenu-icon"></i>
			<span class="sidemenu-label">문서함</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/inBoxAllList">전체</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/inBoxDraft">기안</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/inBoxApproval">결재</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/inBoxPassAlong">참조</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/approval/inBoxReturn">반려</a>
			</li>
		</ul>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/approval/userSetting">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-announcement sidemenu-icon"></i>
			<span class="sidemenu-label">설정</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_EAADMIN')">
		<li class="nav-header">
			<span class="nav-label">관리자메뉴</span>
		</li>
		<li class="nav-item">
			<a class="nav-link with-sub" href="${cPath}/admin/setEaManager">
				<span class="shape1"></span>
				<span class="shape2"></span>
				<i class="ti-user sidemenu-icon"></i>
				<span class="sidemenu-label">관리자설정</span>
				<i class="angle fe fe-chevron-right"></i>
			</a>
			<ul class="nav-sub">
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath}/admin/setEaManager">전자결재 관리자</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath }/admin/approvalFarmboxList">양식함관리</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath}/admin/allDocumentBox">전체 문서 목록</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath}/admin/deleteDocumentBox">삭제 문서 목록</a>
				</li>
			</ul>
		</li>
	</sec:authorize>
</ul>
<script>
	$(function(){
		$.ajax({
			url : contextPath+"/approval/approvalCount",
			method : "get",
			dataType : "json",
			success : function(resp) {
				$(resp).each(function(idx, count){
					$("#awaitCount").html(count.awaitCount);
					$("#confirmCount").html(count.confirmCount);
					$("#progressCount").html(count.progressCount);
				});
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	});
</script>