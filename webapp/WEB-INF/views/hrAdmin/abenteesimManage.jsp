<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<c:set var="memberList" value="${pagingVO.dataList }" />
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 25.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">근태 관리</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">근태 관리</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">직원 근태 관리</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-header border-bottom-0 pb-0">
				<div class="d-flex justify-content-between">
					<label class="main-content-label mb-0 pt-1">사용자 목록</label>
				</div>
				<div id="inputUI" class="row mt-3">
					<div class="col-3 text-black">
						<select name="searchType" class="form-control mr-1">
							<option value="">전체</option>
							<option value="name">이름</option>
							<option value="userId">아이디</option>
							<option value="userDept">소속</option>
							<option value="userPosition">직위</option>
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
			<div class="card-body">
				<table class="table card-table table-dark table-vcenter text-nowrap mb-0 table-hover text-white rounded-lg" id="memberListTable">
					<thead class="text-center">
						<tr>
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span class="text-white">프로필</span>
							</th >
							<th class="wd-lg-8p" onclick="event.cancelBubble=true">
								<span class="text-white">아이디(사번)</span>
							</th >
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">이름</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">입사일</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">소속조직</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">사내전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">휴대전화</span>
							</th>
							<th class="wd-lg-20p" onclick="event.cancelBubble=true">
								<span class="text-white">직위</span>
							</th>
						</tr>
					</thead>
					<tbody id="listBody" class="text-center">
						<c:if test="${not empty memberList }">
							<c:forEach items="${memberList }" var="member">
								<tr>
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
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="9" onclick="event.cancelBubble=true">조건에 맞는 사용자가 없음</td>
							</tr>
						</c:if>
					</tbody>
				</table>
				<form id="searchForm">
					<input type="hidden" name="page" />
					<input type="hidden" name="searchType" value="${pagingVO.searchVO.searchType }" />
					<input type="hidden" name="searchWord" value="${pagingVO.searchVO.searchWord }" />
				</form>
			</div>
			<div id="pagingArea">${pagingVO.pagingHTML}</div>
		</div>
	</div>

	<div class="col-sm-8 col-md-8 col-lg-8 col-xl-8 grid-margin">
		<div class="card custom-card">
			<div class="card-body">
				<div id="calendar"></div>
			</div>
		</div>
	</div>
	<div class="col-sm-4 col-md-4 col-lg-4 col-xl-4 grid-margin">
		<div class="card custom-card">
			<div class="card-body">
				<div class="row">
					<div class="col-12 d-flex justify-content-center">
						<span class="font-weight-bold">이번주 근태시간 통계</span>
					</div>
					<div class="col-12">
						<canvas id="weekWorkChart" width="400px" height="400px" style="display: block; height: 400px; width: 400px;"></canvas>
						<hr>
					</div>
					<div class="col-12 d-flex justify-content-center">
						<span class="font-weight-bold">이번달 근태 통계</span>
					</div>
					<div class="col-12">
						<hr>
						<canvas id="monthWorkChart" width="400px" height="400px" style="display: block; height: 500px; width: 500px;"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<c:set var="attendanceList" value="${attendanceList }" />
<c:set var="attendanceList" value="${attendanceList }" />
<c:set var="vacaRecList" value="${vacaRecList }" />
<c:set var="monthWorkTimeList" value="${monthWorkTimeList }" />
<script type="text/javascript">
	var eventsArr = [];
	var mon;
	var tue;
	var wed;
	var thu;
	var fri;
	var sat;
	var sun;
	var leftWorkDay;
	var vacationDay;
	var workDay;
	let monthWorkTimeList = JSON.parse('${monthWorkTimeList}');
	let workTimeList = JSON.parse('${workTimeList}');
	let attendanceList = JSON.parse('${attendanceList}');
	let vacaRecList = JSON.parse('${vacaRecList}');
	
	$(monthWorkTimeList).each(function(idx, work) {
		leftWorkDay = work.leftDay;
		workDay = work.workDay;
		vacationDay = work.vacaDays;
	});
	
	$(workTimeList).each(function(idx, work) {
		if (work.week == 'MON') {
			mon = work.workTime;
		} else if (work.week == 'TUE') {
			tue = work.workTime;
		} else if (work.week == 'WED') {
			wed = work.workTime;
		} else if (work.week == 'THU') {
			thu = work.workTime;
		} else if (work.week == 'FRI') {
			fri = work.workTime;
		} else if (work.week == 'SAT') {
			sat = work.workTime;
		} else if (work.week == 'SUN') {
			sun = work.workTime;
		}
	});

	$(attendanceList).each(function(idx, attend) {
		eventsArr.push({
			title : "출근",
			start : attend.workStartTime,
			end : attend.workStartTime,
			id : attend.attdNo,
			color : "#7FE6C2"
		}, {
			title : "퇴근",
			start : attend.workEndTime,
			end : attend.workEndTime,
			id : attend.attdNo,
			color : "#F67441"
		});
	});
	$(vacaRecList).each(function(idx, vaca) {
		eventsArr.push({
			title : vaca.vtCode,
			start : vaca.vacaBegin,
			end : vaca.vacaEnd,
			id : vaca.vacaNo,
			color : "#E8EB5D"
		});
	});
</script>
<script src="${cPath}/js/hr/abenteesimManage.js"></script>
