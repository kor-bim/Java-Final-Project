<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 1.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">커뮤니티</span>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/departMentBoard">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-announcement sidemenu-icon"></i>
			<span class="sidemenu-label">부서 게시판</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/noticeBoard">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-announcement sidemenu-icon"></i>
			<span class="sidemenu-label">공지사항</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/voteBoard">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-announcement sidemenu-icon"></i>
			<span class="sidemenu-label">투표 게시판</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_CMADMIN')">
	<li class="nav-header">
		<span class="nav-label">관리자메뉴</span>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/admin/setCmManager">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-announcement sidemenu-icon"></i>
			<span class="sidemenu-label">커뮤니티 관리자 등록</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	</sec:authorize>
</ul>