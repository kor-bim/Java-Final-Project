<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 3. 4.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<style>
.row {
	margin-right: 0px !important;
	margin-left: 0px !important;
}
</style>
<div>
	<div class="row row-sm mt-4">
		<div class="col-2 mb-5">
			<div class="card custom-card our-team" style="height: 100%;">
				<div class="card-body">
					<div class="picture avatar-lg online text-center mt-5">
						<c:if test="${not empty member.memImg }">
							<img alt="avatar" class="rounded-circle"
								src="${cPath}/profileImages/${member.memImg}">
						</c:if>
						<c:if test="${empty member.memImg }">
							<img alt="avatar" class="rounded-circle"
								src="${cPath}/images/pngs/default.png">
						</c:if>
					</div>
					
					<div class="text-center mt-5">
						<h5 class="pro-user-username text-dark mt-2 mb-0">${member.memName}</h5>
						<p class="pro-user-desc text-muted mb-1">${member.jobName}/${member.deptName}/${member.psName}</p>
					</div>
					<div class="contact-info mt-4 mb-5 text-center">
						<a href="${cPath}/profile/profileSetting"
							class="contact-icon border text-primary" data-toggle="tooltip"
							title="프로필 설정"> <i class="fas fa-cog"></i>
						</a> <a href="#" class="contact-icon border text-primary"
							onclick="clickHandler(event);" data-href="${cPath}/login/logout"
							data-toggle="tooltip" title="로그아웃"> <i
							class="fas fa-power-off"></i>
						</a>
						<a href="#" id="btnCompose"
							class="contact-icon border text-primary" data-toggle="tooltip"
							title="메일 작성"> <i class="fas fa-edit"></i>
						</a> <a href="${cPath}/schedule/scheduleList"
							class="contact-icon border text-primary" data-toggle="tooltip"
							title="일정 등록"> <i class="fas fa-calendar-plus"></i>
						</a>
					</div>
				</div>
				<div class="calendar mr-3 mb-5 p-0"></div>
			</div>
		</div>
		<div class="col-10 mb-5">
			<div class="row mb-4" style="height: 40%;">
				<!-- 안녕하세요 START -->
				<div class="col-4">
					<div class="card bg-primary custom-card card-box "
						style="height: 100%;">
						<div class="card-body p-0"   style="background:url(/Forest/images/pngs/work3.png) no-repeat bottom left">
							<div class="row">
								<div class="col-12 p-0">
									<div class="row">
										<div class="col-12 mt-5 text-left">
											<span class=" text-white" style="font-size: 1.5em;">안녕하세요, ${member.memName}님</span>
											<br> <span class="mt-1 text-white" style="font-size: 1.5em;">좋은 하루 되세요</span>
										</div>
<!-- 										<div class="col-12 text-align-bottom"> -->
<%-- 											<img src="${cPath}/images/pngs/work3.png" alt="user-img" --%>
<!-- 												class=""> -->
<!-- 										</div> -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-4">
					<div class="card bg-teal custom-card card-box text-white"
						style="height: 100%;">
						<div class="card-body">
							<div class="row align-items-center"
								style="width: 100%; height: 100%;">
								<div class="col-12">
									<div class="row">
										<div class="col-3 text-center">
											<h1 id="weather" style="font-size: 4em;"></h1>
										</div>
										<div class="col-9 text-center">
											<div class="row">
												<div class="col-12 mt-1">
													<span class="font-weight-bold" style="font-size: 1.5em;" id="city">대전</span> &nbsp;&nbsp; <span
														class="font-weight-bold" style="font-size: 1.5em;" id="tempature"></span>
												</div>
												<div class="col-12">
													<span class="font-weight-bold" id="deatailWeather"></span>
												</div>
											</div>
										</div>
									</div>
									<div class="row mt-2">
										<div class="col-12 mt-2">
											<div class="row">
												<div class="col-4 text-center">
													<h1>
														<i class="wi wi-strong-wind"></i>
													</h1>
													<span class="font-weight-bold" id="wind"></span>
												</div>
												<div class="col-4 text-center">
													<h1>
														<i class="wi wi-humidity"></i>
													</h1>
													<span class="font-weight-bold" id="water"></span>
												</div>
												<div class="col-4 text-center">
													<h1>
														<i class="wi wi-cloud"></i>
													</h1>
													<span class="font-weight-bold" id="cloud"></span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 안녕하세요 END -->

				<!-- 전자결재 START -->
				<div class="col-4">
					<div class="card custom-card" style="height: 100%;">
						<div class="card-header border-bottom-0 pb-0">
							<div class="d-flex justify-content-between">
								<label class="main-content-label mb-0 pt-1" style="font-size:1.2rem;">전자 결재</label>
							</div>
						</div>
						<div class="card-body">
							<div class="row mt-3">
								<div class="col-12">
									<div class="card-order ">
										<h4 class="text-right card-item-icon card-icon">
											<i class="fas fa-pause icon-size float-left text-primary"
												style="float: left"></i> <span class="ml-2 mt-1"
												style="float: left; color: black;"> <a
												href="${cPath }/approval/approvalAwait"
												style="color: black;">결재할 문서</a>
											</span> <span class="font-weight-bold">${awaitCount} 건</span>
										</h4>
									</div>
								</div>
								<div class="col-12">
									<hr>
									<div class="card-order ">
										<h4 class="text-right card-item-icon card-icon">
											<i
												class="fas fa-pencil-ruler icon-size float-left text-primary"></i>
											<span class="ml-2 mt-1" style="float: left;"> <a
												href="${cPath }/approval/inBoxDraft" style="color: black;">결재가
													완료된 문서</a>
											</span> <span class="font-weight-bold">${draftCount} 건</span>
										</h4>
									</div>
								</div>
								<div class="col-12">
									<hr>
									<div class="card-order ">
										<h4 class="text-right card-item-icon card-icon">
											<i class="fas fa-eye icon-size float-left text-primary"></i>
											<span class="ml-2 mt-1" style="float: left; color: black;">
												<a href="${cPath }/approval/approvalConfirm"
												style="color: black;">확인할 문서</a>
											</span> <span class="font-weight-bold">${confirmCount} 건</span>
										</h4>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 전자결재 END -->

			</div>
			<div class="row" style="height: 60%;">
				<div class="col-8 mb-4">

					<!-- 메일함 START -->
					<div class="card custom-card our-team" style="height: 100%;">
						<div class="card-header border-bottom-0 pb-0">
							<div class="d-flex justify-content-between">
								<label class="main-content-label mb-0 pt-1" style="font-size:1.2rem;">받은 메일함</label> <a
									href="${cPath}/mail/inbox"
									class="main-content-label mb-0 pt-1 text-success" style="font-size:1rem;">받은 메일함으로
									이동</a>
							</div>
						</div>
						<div class="card-body">
							<div style="height:80%;">
							<c:set var="mailList" value="${mailInboxPagingVO.dataList }" />
							<table
								class="table table-inbox text-md-nowrap table-hover text-nowrap mt-2"
								id="mailTable">
								<thead class="text-center">
									<tr >
										<th class="wd-lg-80p" onclick="event.cancelBubble=true">
											<span style="font-size:1.2rem;">제목</span>
										</th>
										<th class="wd-lg-8p" onclick="event.cancelBubble=true"><span style="font-size:1.2rem;">작성자</span>
										</th>
										<th class="wd-lg-8p" onclick="event.cancelBubble=true"><span style="font-size:1.2rem;">날짜</span>
										</th>
									</tr>
								</thead>
								<tbody class="">
									<c:if test="${not empty mailList }">
										<c:forEach items="${mailList }" var="mail">
											<tr>
												<td class="mailNo text-center" style="display: none"
													data-mail-no="${mail.mailNo }"
													data-rec-id="${mail.mailReceiverVO.receiverId }"
													data-rec-star="${mail.mailReceiverVO.recStar }"
													data-sender-id="${mail.senderId }"
													data-mail-star="${mail.mailStar }"
													data-mem-id="${member.memId }">${mail.mailNo}</td>
												<td class="view-message text-left">${mail.mailTitle }</td>
												<td class="view-message text-center dont-show font-weight-semibold">${mail.senderId}@ddit.or.kr</td>
												<td class="view-message text-right font-weight-semibold">
													<fmt:parseDate var="mailDate" value="${mail.mailDate}"
														pattern="yyyy-MM-dd HH:mm" /> <fmt:formatDate
														value="${mailDate}" pattern="MM-dd HH:mm" />
												</td>
											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty mailList }">
										<tr class="text-center">
											<td colspan="6" onclick="event.cancelBubble=true">받은 메일이
												없음.</td>
										</tr>
									</c:if>
								</tbody>
							</table>
							</div>
							<div class="mt-2" id="mailInboxPagingArea">${mailInboxPagingVO.pagingHTML }</div>
							<form id="mailInboxSearchForm">
								<input type="hidden" name="mailInboxPage" />
							</form>
						</div>
					</div>
					<!-- 메일함 END -->

				</div>
				<!-- 근무통계 START -->
				<div class="col-4 mb-4">
					<div class="card custom-card" style="height: 100% !important;">
						<div class="card-header border-bottom-0 pb-0">
							<div class="d-flex justify-content-between">
								<label class="main-content-label mb-0 pt-1"  style="font-size:1.2rem;">이번주 근무시간</label>
							</div>
						</div>
						<div class="card-body mt-4">
							<div class="chart-container" style="width: 100%; height: 100%;">
								<canvas id="weekWorkChart"
									style="width: 90%; height: 100%; margin: 0 auto;"></canvas>
							</div>
						</div>
					</div>
				</div>
				<!-- 근무통계 END -->
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	let mailInboxSearchForm = $("#mailInboxSearchForm");
	$("#mailInboxPagingArea").on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		mailInboxSearchForm.find("[name='mailInboxPage']").val(page);
		mailInboxSearchForm.submit();
		return false;
	});

	$(function() {
		$("body").addClass("main-sidebar-hide");

		$('.calendar').pignoseCalendar({
			theme : 'dark', // light, dark, blue,
			lang : 'ko'
		});
		$('.owl-carousel').owlCarousel(
				{
					items : 2,
					loop : true,
					nav : true,
					navText : [ "<i class='fas fa-chevron-left'></i>",
							"<i class='fas fa-chevron-right'></i>" ],
					autoplay : true,
					autoplayTimeout : 4000
				});
	});
	let workTimeList = JSON.parse('${workTimeList}');
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
	var ctx1 = document.getElementById('weekWorkChart').getContext('2d');
	var weekWorkChart = new Chart(ctx1, {
		type : 'bar',
		data : {
			labels : [ '월', '화', '수', '목', '금', '토', '일' ],
			datasets : [ {
				label : '일주일 근무시간',
				data : [ mon, tue, wed, thu, fri, sat, sun ],
				backgroundColor : [ 'rgba(0, 184, 148,1.0)',
						'rgba(0, 206, 201,1.0)', 'rgba(129, 236, 236,1.0)',
						'rgba(108, 92, 231,1.0)', 'rgba(253, 203, 110,1.0)',
						'rgba(253, 121, 168,1.0)', 'rgba(255, 234, 167,1.0)' ],
				borderColor : [ 'rgba(0, 184, 148,1.0)',
						'rgba(0, 206, 201,1.0)', 'rgba(129, 236, 236,1.0)',
						'rgba(108, 92, 231,1.0)', 'rgba(253, 203, 110,1.0)',
						'rgba(253, 121, 168,1.0)', 'rgba(255, 234, 167,1.0)' ],
				borderWidth : 1
			} ]
		},
		options : {
			responsive : false,
			maintainAspectRatio : false,
			legend : {
				display : true,
				position : 'bottom',
				labels : {
					boxWidth : 20,
					fontColor : '#111',
					padding : 15
				}
			},
			tooltips : {
				titleFontSize : 16,
				titleFontColor : '#0066ff',
				bodyFontColor : '#FFF',
				bodyFontSize : 24,
				displayColors : false
			}
		}
	});

	$("#mailTable tr td.view-message").on("click", function() {
		let mailNo = $(this).siblings(".mailNo").text();
		location.href = contextPath + "/mail/mailDetail/" + mailNo;
	});

	$(function() {
		var apiURI = "https://api.openweathermap.org/data/2.5/weather?q=Daejeon&appid=886705b4c1182eb1c69f28eb8c520e20";
		$.ajax({
					url : apiURI,
					dataType : "json",
					type : "GET",
					async : "false",
					success : function(resp) {
						$("#tempature").text(
								Math.round((resp.main.temp - 273.15)) + " ℃");
						var deatilWeather = wDescEngToKor(resp.weather[0].id);
						$("#deatailWeather").text(deatilWeather);
						$("#wind").text(resp.wind.speed);
						$("#water").text(resp.main.humidity + "%");
						$("#cloud").text((resp.clouds.all) + "%");

						switch (resp.weather[0].icon) {
						case "01d":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-sunny"));
							break;
						case "02d":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-cloudy"));
							break;
						case "03d":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-cloudy-high"));
							break;
						case "04d":
							$("#weather").append(
									$("<i>").addClass("wi wi-storm-showers"));
							break;
						case "09d":
							$("#weather").append(
									$("<i>").addClass("wi wi-storm-showers"));
							break;
						case "10d":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-sleet-storm"));
							break;
						case "11d":
							$("#weather").append(
									$("<i>").addClass("wi wi-thunderstorm"));
							break;
						case "50d":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-fog"));
							break;
						case "01n":
							$("#weather").append(
									$("<i>").addClass("wi wi-night-clear"));
							break;
						case "02n":
							$("#weather")
									.append(
											$("<i>").addClass(
													"wi wi-night-alt-cloudy"));
							break;
						case "03n":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-cloudy"));
							break;
						case "04n":
							$("#weather").append(
									$("<i>").addClass("wi wi-night-alt-hail"));
							break;
						case "09n":
							$("#weather").append(
									$("<i>").addClass(
											"wi wi-night-alt-rain-wind"));
							break;
						case "10n":
							$("#weather").append(
									$("<i>").addClass(
											"wi wi-night-alt-rain-wind"));
							break;
						case "11n":
							$("#weather").append(
									$("<i>").addClass(
											"wi wi-night-alt-thunderstorm"));
							break;
						case "13n":
							$("#weather").append(
									$("<i>").addClass(
											"wi wi-night-alt-snow-wind"));
							break;
						case "50n":
							$("#weather").append(
									$("<i>").addClass("wi wi-day-fog"));
							break;
						}
					}
				});

		function wDescEngToKor(w_id) {
			var w_id_arr = [ 201, 200, 202, 210, 211, 212, 221, 230, 231, 232,
					300, 301, 302, 310, 311, 312, 313, 314, 321, 500, 501, 502,
					503, 504, 511, 520, 521, 522, 531, 600, 601, 602, 611, 612,
					615, 616, 620, 621, 622, 701, 711, 721, 731, 741, 751, 761,
					762, 771, 781, 800, 801, 802, 803, 804, 900, 901, 902, 903,
					904, 905, 906, 951, 952, 953, 954, 955, 956, 957, 958, 959,
					960, 961, 962 ];
			var w_kor_arr = [ "가벼운 비를 동반한 천둥구름", "비를 동반한 천둥구름", "폭우를 동반한 천둥구름",
					"약한 천둥구름", "천둥구름", "강한 천둥구름", "불규칙적 천둥구름",
					"약한 연무를 동반한 천둥구름", "연무를 동반한 천둥구름", "강한 안개비를 동반한 천둥구름",
					"가벼운 안개비", "안개비", "강한 안개비", "가벼운 적은비", "적은비", "강한 적은비",
					"소나기와 안개비", "강한 소나기와 안개비", "소나기", "악한 비", "중간 비", "강한 비",
					"매우 강한 비", "극심한 비", "우박", "약한 소나기 비", "소나기 비", "강한 소나기 비",
					"불규칙적 소나기 비", "가벼운 눈", "눈", "강한 눈", "진눈깨비", "소나기 진눈깨비",
					"약한 비와 눈", "비와 눈", "약한 소나기 눈", "소나기 눈", "강한 소나기 눈", "박무",
					"연기", "연무", "모래 먼지", "안개", "모래", "먼지", "화산재", "돌풍", "토네이도",
					"구름 한 점 없는 맑은 하늘", "약간의 구름이 낀 하늘", "드문드문 구름이 낀 하늘",
					"구름이 거의 없는 하늘", "구름으로 뒤덮인 흐린 하늘", "토네이도", "태풍", "허리케인",
					"한랭", "고온", "바람부는", "우박", "바람이 거의 없는", "약한 바람", "부드러운 바람",
					"중간 세기 바람", "신선한 바람", "센 바람", "돌풍에 가까운 센 바람", "돌풍",
					"심각한 돌풍", "폭풍", "강한 폭풍", "허리케인" ];
			for (var i = 0; i < w_id_arr.length; i++) {
				if (w_id_arr[i] == w_id) {
					return w_kor_arr[i];
					break;
				}
			}
			return "none";
		}
	});
</script>
<jsp:include page="/WEB-INF/views/mail/mailForm/mailForm.jsp"></jsp:include>