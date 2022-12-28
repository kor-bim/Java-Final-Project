<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">휴가/근태</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">휴가/근태</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">근태현황</li>
		</ol>
	</div>
</div>

<!-- End Page Header -->
<div class="row row-sm">
	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12" style="display: flex; justify-content: center;">
		<div class="card custom-card mr-2" style="width: 35%;">
			<div class="card-header border-bottom-0 pb-0">
				<p class="pt-5" style="text-align: center">&lt&nbsp;Today 현황&nbsp;&gt</p>
			</div>
			<div class="column  pl-3 pr-3">
				<table class="table table-bordered">
					<colgroup>
						<col style="width: 22%; background-color: #efefff !important;">
						<col style="width: 28%">
						<col style="width: 22%; background-color: #efefff;">
						<col style="width: 28%">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">이름</th>
							<td>${member.memName }</td>
							<th scope="row">부서</th>
							<td>${member.deptName }</td>
						</tr>
						<tr>
							<th scope="row">출근 시간</th>
							<td>${attendanceVO2.attdStarth }시 ${attendanceVO2.attdStartm }분</td>
							<th scope="row">퇴근 시간</th>
							<c:choose>
								<c:when test="${empty attendanceVO2.attdEndh }">
									<td>미등록</td>
								</c:when>
								<c:otherwise>
									<td>${attendanceVO2.attdEndh }시 ${attendanceVO2.attdEndm }분</td>
								</c:otherwise>
							</c:choose>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="card custom-card mr-2" style="width: 30%;">
			<div class="card-header border-bottom-0 pb-0">
				<p class="pt-5" style="text-align: center">&lt&nbsp;이번주 근태시간 통계&nbsp;&gt</p>
			</div>
			<div class="column">
				<canvas id="weekWorkChart" style="height: 240px;"></canvas>
			</div>
		</div>
		<div class="card custom-card mr-2" style="width: 30%;">
			<div class="card-header border-bottom-0 pb-0">
				<p class="pt-5" style="text-align: center">&lt&nbsp;이번달 근태 통계&nbsp;&gt</p>
			</div>
			<div class="column">
				<canvas id="monthWorkChart"></canvas>
			</div>
		</div>
	</div>

	<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 grid-margin">
		<div class="card custom-card">
			<div class="card-body">

				<div id="calendar"></div>
			</div>
		</div>
	</div>
</div>
<c:set var="attendanceList" value="${attendanceList }" />
<c:set var="vacaRecList" value="${vacaRecList }" />
<c:set var="workTimeList" value="${workTimeList }" />
<c:set var="totalList" value="${totalList }" />
<script type="text/javascript">
	var eventsArr = [];
	var mon;
	var tue;
	var wed;
	var thu;
	var fri;
	var sat;
	var sun;
	let monthWorkTimeList = JSON.parse('${monthWorkTimeList}');
	let workTimeList = JSON.parse('${workTimeList}');
	let attendanceList = JSON.parse('${attendanceList}');
	let vacaRecList = JSON.parse('${vacaRecList}');

	$(monthWorkTimeList).each(function(idx, work) {
		leftWorkDay = work.leftDay;
		workDay = work.workDay;
		vacationDay = work.vacaDays;
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
</script>
<script src="${cPath}/js/hr/absenteeism.js"></script>
