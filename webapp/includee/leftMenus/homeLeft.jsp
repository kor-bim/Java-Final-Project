<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 3. 4.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<security:authorize access="hasAnyRole('ROLE_ADMIN')">
<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">시스템 설정</span>
	</li>
	<li class="nav-item">
		<a class="nav-link with-sub" href="${cPath}/admin/adminSetting">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-settings sidemenu-icon"></i>
			<span class="sidemenu-label">환경설정</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/admin/setManager">관리자 설정</a>
			</li>
		</ul>
	</li>
	
</ul>
</security:authorize>