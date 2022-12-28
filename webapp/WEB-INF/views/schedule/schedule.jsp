<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 4.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="${cPath }/css/schedule/calendar.css">

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">일정관리</h2>
		<ol class="breadcrumb">
		</ol>
	</div>
</div>
<div class="card custom-card overflow-hidden">
	<div class="card-body">
		<form:form class="row">
			<div class="col-3">
				<div aria-multiselectable="true" class="accordion" id="accordion" role="tablist">
					<div class="card">
						<div class="card-header" id="headingOne" role="tab">
							<a aria-controls="collapseOne" aria-expanded="true" data-toggle="collapse" href="#collapseOne"> 내 캘린더 </a>
							<button type="button" class="btn" id="myCalendar">
								<i class="pull-right fas fa-plus"> 내 캘린더 만들기</i>
							</button>
						</div>
						<div aria-labelledby="headingOne" class="collapse show" data-parent="#accordion" id="collapseOne" role="tabpanel">
							<div class="card-body">
								<c:set value="${calendarList }" var="calendarList" />
								<c:if test="${not empty calendarList}">
									<ul>
										<c:forEach items="${calendarList }" var="cal">
											<li>
												<div class="row">
													<div class="col-9">
														<button type="button" class="label" value="c1">
															<span class="${cal.calColor }"></span>
														</button> ${cal.calName }
													</div>
													<div class="col-3" style="float: right;">
														<button type="button" class="btn btn-outline-light btn-sm updateCalendar" value="${cal.calNo }">수정</button>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</div>
					</div>
					<div class="card">
						<div class="card-header" id="headingTwo" role="tab">
							<a aria-controls="collapseTwo" aria-expanded="false" class="collapsed" data-toggle="collapse" href="#collapseTwo"> 공유 캘린더 </a>
							<button type="button" class="btn" id="sharedCalendar">
								<i class="pull-right fas fa-plus"> 공유 캘린더 만들기</i>
							</button>
						</div>
						<div aria-labelledby="headingTwo" class="collapse" data-parent="#accordion" id="collapseTwo" role="tabpanel">
							<div class="card-body">
								<c:set value="${shareCalendarList }" var="shareCalendarList" />
								<c:if test="${not empty shareCalendarList}">
									<ul>
										<c:forEach items="${shareCalendarList }" var="scal">
											<li>
												<div class="row">
													<div class="col-9">
														<button type="button" class="label" value="c1">
															<span class="${scal.calColor }"></span>
														</button> ${scal.calName }
													</div>
													<div class="col-3" style="float: right;">
														<button type="button" class="btn btn-outline-light btn-sm updateSharedCalendar" value="${scal.calNo }">수정</button>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-9">
				<div id='calendar'></div>
			</div>
		</form:form>
	</div>
</div>

<!-- 일정 추가 modal-->
<div class="modal fade" id="scheduleInsertModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleInsertModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="scheduleInsertModalLabel">일정 추가</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<!-- 일정 상세조회 modal-->
<div class="modal fade" id="scheduleViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleViewModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="scheduleViewModalLabel">일정 상세조회</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<input type="button" value="수정" class="btn btn-outline-info" id="modifyBtn" />
				<button type="button" class="btn btn-outline-warning" id="removeBtn">삭제</button>
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 일정 수정 modal-->
<div class="modal fade" id="scheduleUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleUpdateModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="scheduleUpdateModalLabel">일정 수정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<!-- 일정 삭제  modal-->
<div class="modal fade bd-example-modal-sm" id="scheduleDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="scheduleDeleteModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">일정을 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="deleteBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 내 캘린더 Modal -->
<div class="modal fade" id="myCalendarModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myCalendarModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="myCalendarModalLabel">내 캘린더</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<!-- 내 캘린더 수정 Modal -->
<div class="modal fade" id="myCalendarUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myCalendarUpdateModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="myCalendarUpdateModalLabel">내 캘린더</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<!-- 공유 캘린더 Modal -->
<div class="modal fade" id="sharedCalendarModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="sharedCalendarModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="sharedCalendarModalLabel">공유 캘린더</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<!-- 공유 캘린더 수정 Modal -->
<div class="modal fade" id="mySharedCalendarUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="mySharedCalendarUpdateModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="mySharedCalendarUpdateModalLabel">공유 캘린더 수정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<c:set var="scheduleList" value="${scheduleList }" />
<c:set var="calendarList" value="${calendarList }" />
<script type="text/javascript">
	var eventsArr = [];
	let scheduleList = JSON.parse('${scheduleList}');
	
	$(scheduleList).each(function(idx, scheduleVO) {
		eventsArr.push({
			title : scheduleVO.schdlName,
			start : scheduleVO.schdlBegin,
			end : scheduleVO.schdlEnd,
			id : scheduleVO.schdlNo,
			classNames : scheduleVO.calColor,
		});
	});
	
	
	let scheduleViewModal = $("#scheduleViewModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	document.addEventListener('DOMContentLoaded', function() {
		let Calendar = FullCalendar.Calendar;

		let containerEl = document.getElementById('external-events');
		let calendarEl = document.getElementById('calendar');
		let checkbox = document.getElementById('drop-remove');

		let calendar = new FullCalendar.Calendar(calendarEl, {
			locale : 'ko',
			initialView : 'dayGridMonth',
			themeSystem : 'bootstrap',
			selectable : true,
			dayMaxEvents: true,
			headerToolbar : {
				left : 'prevYear,prev,next,nextYear today scheduleInsert',
				center : 'title',
				right : 'dayGridMonth,dayGridWeek,dayGridDay'
			},
			select : function(schedule) {
				scheduleInsertModal.find(".modal-body").load($.getContextPath() + "/schedule/scheduleForm.do", function() {
					scheduleInsertModal.find("#dateStart").val(schedule.startStr);
					scheduleInsertModal.find("#dateEnd").val(schedule.endStr);
					scheduleInsertModal.modal();
				});
			},
			customButtons : {
				scheduleInsert : {
					text : '일정추가'
				}
			},
			events : eventsArr,
			eventClick : function(info) {
				let schdlNo = info.event.id;
				scheduleViewModal.find(".modal-body").load(
						$.getContextPath() + "/schedule/" + schdlNo,
						function() {
							scheduleViewModal.modal();
						});
			}
			, editable : true
		});
		calendar.render();
	});
	// ================ 일정 상세조회 modal ==========================================================
	let scheduleInsertModal = $("#scheduleInsertModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	$(document).on("click",	".fc-scheduleInsert-button", function() {
		scheduleInsertModal.find(".modal-body").load($.getContextPath() + "/schedule/scheduleForm.do", function() {
			scheduleInsertModal.modal();
		});
	});

	// ================= 일정 수정 modal ===========================================================
	let scheduleUpdateModal = $("#scheduleUpdateModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	$("#modifyBtn").on("click", function() {
		scheduleViewModal.modal("hide");
		let schdlNo = $("#schdlNo").val();
		scheduleUpdateModal.find(".modal-body").load($.getContextPath() + "/schedule/updateScheduleForm/" + schdlNo, function() {
			scheduleUpdateModal.modal();
		});
	});

	// ================= 일정 삭제 modal ===========================================================
	let scheduleDeleteModal = $("#scheduleDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	$("#removeBtn").on("click",	function() {
		scheduleViewModal.modal("hide");
		scheduleDeleteModal.modal("show");
		let schdlNo = $("#schdlNo").val();
		$("#deleteBtn").on("click", function() {
			location.href = $.getContextPath() + "/schedule/delete.do/" + schdlNo;
		});
	});

	// ================= 내 캘린더 modal ===========================================================
	let myCalendarModal = $("#myCalendarModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	$("#myCalendar").on("click", function() {
		myCalendarModal.find(".modal-body").load($.getContextPath() + "/calendar/insertForm.do", function() {
			myCalendarModal.modal();
		});
	});
	
	// ================= 내 캘린더 수정 modal ===========================================================
	let myCalendarUpdate = $("#myCalendarUpdateModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
	
	$(".updateCalendar").on("click", function(){
		let calNo = this.value;
		myCalendarUpdate.find(".modal-body").load($.getContextPath() + "/schedule/updateForm.do/" + calNo, function(){
			myCalendarUpdate.modal();
		});
	});

	// ================= 공유 캘린더 modal ===========================================================
	let sharedCalendarModal = $("#sharedCalendarModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
 
	$("#sharedCalendar").on("click", function() {
		sharedCalendarModal.find(".modal-body").load($.getContextPath() + "/calendar/sharedCalendarForm.do", function() {
			sharedCalendarModal.modal();
		});
		$("#deptTreeDIV").load($.getContextPath() + "/ztree/dept");
	});
	
	// ================= 공유 캘린더 수정 modal ===========================================================
	let sharedCalendarUpdate = $("#mySharedCalendarUpdateModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
	
	$(".updateSharedCalendar").on("click", function(){
		let calNo = this.value;
		sharedCalendarUpdate.find(".modal-body").load($.getContextPath() + "/calendar/sharedCalendarUpdateForm.do/" + calNo, function(){
			sharedCalendarUpdate.modal();
		});
	});
	
</script>
