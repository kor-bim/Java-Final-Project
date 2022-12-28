<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 8.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">전자결재</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">전자결재 관리</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">관리자 등록</li>
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">사용자 목록</label>
				</div>
			</div>
			<div class="card-body">
				<nav class="nav nav-pills nav-justified">
					<security:authorize access="hasAnyRole('ROLE_ADMIN')">
						<a class="nav-item nav-link" href="${cPath}/admin/setManager">전체관리자</a>
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_HRADMIN')">
						<a class="nav-item nav-link" href="${cPath}/admin/setHrManager">인사 관리자</a>
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_EAADMIN')">
						<a class="nav-item nav-link active" href="${cPath}/admin/setEaManager">전자결재 관리자</a>
					</security:authorize>
					<security:authorize access="hasAnyRole('ROLE_ADMIN,ROLE_CMADMIN')">
						<a class="nav-item nav-link" href="${cPath}/admin/setCmManager">커뮤니티 관리자</a>
					</security:authorize>
				</nav>

				<form action="${cPath}/admin/updateEaAdmin" method="post" class="row mt-5">
					<div class="col-5">
						<c:set var="memberList" value="${memberList}" />
						<select name="notAdminMemId" id="search" class="form-control" size="8" multiple="multiple">
							<c:if test="${not empty memberList }">
								<c:forEach items="${memberList }" var="member">
									<option value="${member.memId}">${member.memName}(${member.memId})- ${member.psName}/${member.deptName}
										<c:forEach items="${member.roleInfoList}" var="roleInfo">
											${roleInfo.roleName }
										</c:forEach>
									</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="col-2">
						<button type="button" id="search_rightAll" class="btn btn-block btn-light">
							<i class="fas fa-angle-double-right"></i>
						</button>
						<button type="button" id="search_rightSelected" class="btn btn-block btn-light">
							<i class="fas fa-angle-right"></i>
						</button>
						<button type="button" id="search_leftSelected" class="btn btn-block btn-light">
							<i class="fas fa-angle-left"></i>
						</button>
						<button type="button" id="search_leftAll" class="btn btn-block btn-light">
							<i class="fas fa-angle-double-left"></i>
						</button>
					</div>
					<div class="col-5">
						<c:set var="eaAdminList" value="${eaAdminList}" />
						<h6>전자결재 관리자</h6>
						<select name="adminMemId" id="search_to" class="form-control" size="8" multiple="multiple">
							<c:if test="${not empty eaAdminList }">
								<c:forEach items="${eaAdminList }" var="eaAdmin">
									<option value="${eaAdmin.memId}">${eaAdmin.memName}(${eaAdmin.memId})-${eaAdmin.psName}/${eaAdmin.deptName}
										<c:forEach items="${eaAdmin.roleInfoList}" var="roleInfo">
											${roleInfo.roleName }
										</c:forEach>
									</option>
								</c:forEach>
							</c:if>
						</select>
						<button type="submit" class="btn btn-info mt-1">저장하기</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">슈퍼관리자</label>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-striped " id="superMemberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-10p" onclick="event.cancelBubble=true">
								<span>프로필</span>
							</th>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>아이디(사번)</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>소속조직</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>사내전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>휴대전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>직위</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty superAdminList }">
							<c:forEach items="${superAdminList }" var="member">
								<tr>
									<td>
										<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
									</td>
									<td>${member.memId}</td>
									<td>${member.memName}</td>
									<td>${member.deptName}</td>
									<td>${member.memComtel}</td>
									<td>${member.memHp}</td>
									<td>${member.psName}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">조건에 맞는 사용자가 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">인사 관리자</label>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-striped " id="superMemberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-10p" onclick="event.cancelBubble=true">
								<span>프로필</span>
							</th>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>아이디(사번)</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>소속조직</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>사내전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>휴대전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>직위</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty hrAdminList }">
							<c:forEach items="${hrAdminList }" var="member">
								<tr>
									<td>
										<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
									</td>
									<td>${member.memId}</td>
									<td>${member.memName}</td>
									<td>${member.deptName}</td>
									<td>${member.memComtel}</td>
									<td>${member.memHp}</td>
									<td>${member.psName}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">조건에 맞는 사용자가 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">전자결재 관리자</label>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-striped " id="superMemberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-10p" onclick="event.cancelBubble=true">
								<span>프로필</span>
							</th>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>아이디(사번)</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>소속조직</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>사내전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>휴대전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>직위</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty eaAdminList }">
							<c:forEach items="${eaAdminList }" var="member">
								<tr>
									<td>
										<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
									</td>
									<td>${member.memId}</td>
									<td>${member.memName}</td>
									<td>${member.deptName}</td>
									<td>${member.memComtel}</td>
									<td>${member.memHp}</td>
									<td>${member.psName}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">조건에 맞는 사용자가 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">커뮤니티 관리자</label>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-striped " id="superMemberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-10p" onclick="event.cancelBubble=true">
								<span>프로필</span>
							</th>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>아이디(사번)</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>소속조직</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>사내전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>휴대전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>직위</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty cmAdminList }">
							<c:forEach items="${cmAdminList }" var="member">
								<tr>
									<td>
										<img width="100px" src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
									</td>
									<td>${member.memId}</td>
									<td>${member.memName}</td>
									<td>${member.deptName}</td>
									<td>${member.memComtel}</td>
									<td>${member.memHp}</td>
									<td>${member.psName}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">조건에 맞는 사용자가 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
    // make code pretty
    window.prettyPrint && prettyPrint();

    $('#search').multiselect({
        search: {
            left: '<input type="text" name="q" class="form-control mb-1" placeholder="이름/아이디(사번)/직책/부서로 검색" />',
        },
        fireSearch: function(value) {
            return value.length > 1;
        }
    });
});
</script>
