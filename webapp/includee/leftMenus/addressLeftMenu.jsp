<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 1. 29.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">주소록</span>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath }/addressbook/private">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-user sidemenu-icon"></i>
			<span class="sidemenu-label">개인 주소록</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/addressbook/public">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-cloud sidemenu-icon"></i>
			<span class="sidemenu-label">공유 주소록</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
</ul>