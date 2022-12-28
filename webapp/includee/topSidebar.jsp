<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<div class="sidebar-icon">
	<a href="#" class="text-right float-right text-dark fs-20" data-toggle="sidebar-right" data-target=".sidebar-right">
		<i class="fe fe-x"></i>
	</a>
</div>
<div class="sidebar-body">
	<div class="d-flex justify-content-center p-3 row">
		<h6 class="clock font-weight-bold" id="currentDate" onload="showTime()"></h6>
		<br>
		<span class="clock font-weight-bold" id="clock" onload="showTime()" style="font-family: sans-serif; font-size: 2em;"></span>
	</div>
</div>
<hr class="m-0">
<div class="d-flex p-3 justify-content-center border-top">
	<c:set var="now" value="<%=new java.util.Date()%>" />
	<c:set var="nowDate">
		<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" />
	</c:set>
	<c:forEach items="${member.attendanceList }" var="attendance">
		<c:if test="${attendance.attdDate eq nowDate}">
			<c:set var="workStatus" value="working" />
		</c:if>
	</c:forEach>
	<c:if test="${empty workStatus }">
		<button type="button" id="startWorkBtn" class="btn btn-info bg-teal wd-150 text-white workBtn">
			<h1 class="font-weight-bold m-2">출근</h1>
		</button>
		<button type="button" id="endWorkBtn" class="btn btn-secondary bg-pink wd-150 workBtn" style="display: none;">
			<h1 class="font-weight-bold m-2">퇴근</h1>
		</button>
	</c:if>
	<c:if test="${workStatus eq 'working' }">
		<button type="button" id="endWorkBtn" class="btn btn-secondary bg-pink wd-150 workBtn">
			<h1 class="font-weight-bold m-2">퇴근</h1>
		</button>
	</c:if>
</div>
<div class="d-flex p-3 justify-content-center border-top">
	<select class="selectpicker" id="wsSelect">
		<option value="1">업무</option>
		<option value="3">외근</option>
		<option value="3">외출</option>
		<option value="3">휴식</option>
	</select>
</div>
<div class="d-flex p-3 border-top">
	<table class="table table-borderless text-center">
		<tr>
			<th class="font-weight-bold">출근 시간</th>
			<td id="startWorkTime" class="font-weight-bold"></td>
		</tr>
		<tr>
			<th class="font-weight-bold">퇴근 시간</th>
			<td id="endWorkTime" class="font-weight-bold"></td>
		</tr>
	</table>
</div>

<script>
	$(function() {
		$.ajax({
			url : contextPath + "/work/workDate",
			data : {
				memId : memId,
				attdDate : attdDate
			},
			method : "post",
			dataType : "json",
			success : function(resp) {
				if (resp.attdStarth != null) {
					$("#startWorkTime").html(
							resp.attdStarth + " : " + resp.attdStartm);
				}
				if (resp.attdEndh != null) {
					$("#endWorkTime").html(
							resp.attdEndh + " : " + resp.attdEndm);
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	});
	var memId = ${member.memId}
	$("#startWorkBtn").on("click", function() {
		var sth = attdh;
		var stm = attdm;
		$.ajax({
			url : contextPath + "/work/startWork",
			data : {
				memId : memId,
				attdDate : attdDate,
				attdStarth : attdh,
				attdStartm : attdm
			},
			method : "post",
			dataType : "text",
			success : function(resp) {
				console.log(resp);
				if (resp == '"OK"') {
					alert("출근 처리가 성공하였습니다");
					$("#startWorkTime").html(sth + " : " + stm)
					$("#startWorkBtn").css("display", "none");
					$("#endWorkBtn").css("display", "block");
				} else if (resp == '"DISABLE"') {
					alert("아직 출근 할 수 없습니다");
				} else {
					alert("출근 등록이 실패하였습니다");
				}
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	});

	$("#endWorkBtn").on("click", function() {
		var edh = attdh;
		var edm = attdm;
		if (confirm("퇴근 처리하시겠습니까?")) {
			$.ajax({
				url : contextPath + "/work/endWork",
				data : {
					memId : memId,
					attdDate : attdDate,
					attdEndh : attdh,
					attdEndm : attdm
				},
				method : "post",
				dataType : "text",
				success : function(resp) {
					if (resp == '"OK"') {
						$("#endWorkTime").html(edh + " : " + edm);
						alert("퇴근 처리가 성공하였습니다");
					} else {
						alert("퇴근 처리가 실패하였습니다");
					}
				},
				error : function(xhr) {
				}
			});
		}
	});
	$("#wsSelect").on("change", function() {
		let optionText = $("#wsSelect option:selected").text();
		if (confirm("근무상태를 '" + optionText + "' 변경하시겠습니까?")) {
			$.ajax({
				url : contextPath + "/work/changeWorkState",
				data : {
					memId : memId,
					attdDate : attdDate,
					wsCode : $(this).val()
				},
				method : "post",
				dataType : "text",
				success : function(resp) {
					if (resp == '"OK"') {
						alert("업무 변경 완료");
					} else {
						alert("업무 변경 실패");
					}
				},
				error : function(xhr) {
				}
			});
		}
	});
	
	
	
</script>
<script src="${cPath}/js/clock.js"></script>
