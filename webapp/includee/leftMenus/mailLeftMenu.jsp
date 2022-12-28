<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 27.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">메일</span>
	</li>
	<li class="nav-item">
		<input type="button" id="btnCompose" class="btn btn-primary col-10 ml-3" value="작성하기">
	</li>
	<li class="nav-item show">
		<a class="nav-link with-sub" href="${cPath}/mail/inbox">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-home sidemenu-icon"></i>
			<span class="sidemenu-label">메일</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath}/mail/inbox">받은 메일함</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/mail/starred">표시 메일함</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/mail/important">중요 메일함</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/mail/sentbox">보낸 메일함</a>
			</li>
			<li class="nav-sub-item">
				<a class="nav-sub-link" href="${cPath }/mail/trash">휴지통</a>
			</li>
		</ul>
	</li>
</ul>
