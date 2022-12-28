<%--
* [[개정이력(Modification Information)]]
*   수정일                 수정자              수정내용
* ----------  ---------  -----------------
* 2021. 2. 18.      이운주                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">인사관리</span>
	</li>

	<li class="nav-item">
		<a class="nav-link with-sub" href="javascript:void(0);">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-home sidemenu-icon"></i>
			<span class="sidemenu-label">휴가/근태</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/vacation/vacationWrite">휴가 신청</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/vacation/vacationStatus">휴가 현황</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/work/absenteeism">근태 현황</a>
			</li>
		</ul>
	</li>
	<li class="nav-item">
		<a class="nav-link with-sub" href="javascript:void(0);">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-home sidemenu-icon"></i>
			<span class="sidemenu-label">인사정보</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="dropdown-item" href="${cPath}/org/memlist">조직도</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/org/memberInfo">내 정보 관리</a>
			</li>
		</ul>
	</li>
	<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_HRADMIN')">
		<li class="nav-header">
			<span class="nav-label">관리자메뉴</span>
		</li>
		<li class="nav-item manager-menu">
			<a class="nav-link with-sub" href="javascript:void(0);">
				<span class="shape1"></span>
				<span class="shape2"></span>
				<i class="ti-home sidemenu-icon"></i>
				<span class="sidemenu-label">인사관리</span>
				<i class="angle fe fe-chevron-right"></i>
			</a>
			<ul class="nav-sub">
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath }/admin/userManagement">사용자 관리</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath }/admin/org/posJob">직위/직무 관리</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath}/admin/setHrManager">인사관리자</a>
				</li>
			</ul>
		</li>
	
		<li class="nav-item">
			<a class="nav-link with-sub" href="${cPath }/admin/vacatype">
				<span class="shape1"></span>
				<span class="shape2"></span>
				<i class="ti-user sidemenu-icon"></i>
				<span class="sidemenu-label">휴가 관리</span>
				<i class="angle fe fe-chevron-right"></i>
			</a>
			<ul class="nav-sub">
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath }/admin/vacatype">휴가 종류 관리</a>
				</li>
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath }/admin/vacaStatus">직원 휴가 관리</a>
				</li>
			</ul>
		</li>
		<li class="nav-item">
			<a class="nav-link with-sub" href="javascript:void(0);">
				<span class="shape1"></span>
				<span class="shape2"></span>
				<i class="ti-user sidemenu-icon"></i>
				<span class="sidemenu-label">근태 관리</span>
				<i class="angle fe fe-chevron-right"></i>
			</a>
			<ul class="nav-sub">
				<li class="nav-sub-item">
					<a class="nav-sub-link" href="${cPath}/admin/abenteesimManage">직원 근태 관리</a>
				</li>
			</ul>
		</li>
	</sec:authorize>
</ul>