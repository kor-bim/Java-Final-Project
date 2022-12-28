<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="memberList" value="${pagingVO.dataList }" />
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 8.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">사용자 관리</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">인사관리</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">사용자 관리</li>
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
				<div class="mail-option">
					<button class="btn btn-light" id="insertMemberBtn">사용자 추가</button>
					<div class="chk-all border-0">
						<div class="btn-group">
							<a data-toggle="dropdown" href="#" class="btn mini all" id="dropdown" aria-expanded="false">
								전체
								<i class="fe fe-chevron-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:all();" onclick="select(this);"> 전체</a>
								</li>
								<li>
									<a href="javascript:asOn();" onclick="select(this);"> 재직</a>
								</li>
								<li>
									<a href="javascript:asDelete();" onclick="select(this);">퇴직</a>
								</li>
								<li>
									<a href="javascript:asDormant();" onclick="select(this);">휴직</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<table class="table card-table table-striped table-vcenter text-nowrap mb-0" id="memberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>프로필</span>
							</th>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span>아이디(사번)</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>입사일</span>
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
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span>상태</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty memberList }">
							<c:forEach items="${memberList }" var="member">
								<c:choose>
									<c:when test="${member.asCode eq 'ON' }">
										<tr class="asOn">
											<td>
												<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
											</td>
											<td>${member.memId}</td>
											<td>${member.memName}</td>
											<td>${member.memHdate}</td>
											<td>${member.deptName}</td>
											<td>${member.memComtel}</td>
											<td>${member.memHp}</td>
											<td>${member.psName}</td>
											<td class="text-info">재직</td>
											<td>
												<button type="button" class="btn btn-primary updateMemberBtn" data-memId="${member.memId}">수정</button>
												<button type="button" class="btn btn-danger deleteMemberBtn" data-asCode="${member.asCode}" data-memId="${member.memId}">삭제</button>
											</td>
										</tr>
									</c:when>
									<c:when test="${member.asCode eq 'DELETE' }">
										<tr class="asDelete">
											<td>
												<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
											</td>
											<td>${member.memId}</td>
											<td>${member.memName}</td>
											<td>${member.memHdate}</td>
											<td>${member.deptName}</td>
											<td>${member.memComtel}</td>
											<td>${member.memHp}</td>
											<td>${member.psName}</td>
											<td class="text-danger">퇴직</td>
											<td>
												<button type="button" class="btn btn-primary updateMemberBtn" data-memId="${member.memId}">수정</button>
												<button type="button" class="btn btn-danger deleteMemberBtn" data-asCode="${member.asCode}" data-memId="${member.memId}">삭제</button>
											</td>
										</tr>
									</c:when>
									<c:when test="${member.asCode eq 'DORMANT' }">
										<tr class="asDormant">
											<td>
												<img src="${cPath}/profileImages/${member.memImg}" class="rounded-circle avatar-md mr-2" />
											</td>
											<td>${member.memId}</td>
											<td>${member.memName}</td>
											<td>${member.memHdate}</td>
											<td>${member.deptName}</td>
											<td>${member.memComtel}</td>
											<td>${member.memHp}</td>
											<td>${member.psName}</td>
											<td class="text-warning">휴직</td>
											<td>
												<button type="button" class="btn btn-primary updateMemberBtn" data-memId="${member.memId}">수정</button>
												<button type="button" class="btn btn-danger deleteMemberBtn" data-asCode="${member.asCode}" data-memId="${member.memId}">삭제</button>
											</td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="6">조건에 맞는 사용자가 없음</td>
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
							<option value="">전체</option>
							<option value="name">이름</option>
							<option value="userId">아이디</option>
							<option value="userDept">소속</option>
							<option value="userPosition">직위</option>
							<option value="userAsCode">직위</option>
						</select>
					</div>
					<div class="col-4">
						<input type="text" name="searchWord" class="form-control mr-3" value="" />
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
<form action="<c:url value='/admin/removeMember' />" method="post" id="removeMemberForm">
	<input type="hidden" name="memId" />
	<input type="hidden" name="asCode" value="DELETE" />
</form>
<script src="${cPath}/js/admin/userManagement.js"></script>