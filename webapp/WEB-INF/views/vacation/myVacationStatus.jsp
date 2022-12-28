<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 25.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<style>
table tr th{
	background-color: #efefff;
	text-align: center;
}

.hide {
	display: none;
}

</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">휴가 현황</h2>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#" id="myVaca">내 휴가</a></li>
					<security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_HRADMIN')">
						<li class="breadcrumb-item"><a href="#" id="allList">휴가신청관리</a></li>
					</security:authorize>
				</ol>
				<div class="myVacation">
					<div class="creDiv">
						<h4>휴가 생성 내역</h4>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th class="align-middle" rowspan="2">생성연도</th>
									<th class="align-middle" colspan="2">생성 내역</th>
									<th class="align-middle" rowspan="2">내용</th>
								</tr>
								<tr>
									<th scope="col">발생</th>
									<th scope="col">남은 일수</th>
								</tr>
							</thead>
							<tbody id="creList">
								
							</tbody>
						</table>
						<h5>
							<c:set value="${vacaStatusVO.vacaStatList }" var="vacaList"/>
							올해 휴가 현황 : <span class="normal">
							총 휴가 ${vacaStatusVO.vacaInday + vacaList[1].vacaInday }일 / 
							사용 ${(vacaStatusVO.vacaInday - vacaStatusVO.vacaRmday) + (vacaList[1].vacaInday - vacaList[1].vacaRmday)}일 / 
							잔여 ${vacaStatusVO.vacaRmday + vacaList[1].vacaRmday}일 
							(정기 ${vacaStatusVO.vacaRmday }일, 포상 ${vacaList[1].vacaRmday }일)</span>
						</h5>
					</div>
					<br/>
					<div class="appDiv">
						<h4>휴가 신청 내역</h4>
						<table class="table table-bordered" id="appTable">
							<thead>
								<tr>
									<th scope="row">휴가종류</th>
									<th scope="row">일수</th>
									<th scope="row">기간</th>
									<th scope="row">상태</th>
									<th scope="row">상세</th>
								</tr>
							</thead>
							<tbody id="appList">
							</tbody>
						</table>
					</div>
				</div>
				<div class="ListDiv hide">
					<h4>휴가 신청 내역</h4>
					<table class="table table-bordered" id="allListTable">
						<thead>
							<tr>
								<th scope="row">신청자</th>
								<th scope="row">소속</th>
								<th scope="row">종류</th>
								<th scope="row">일수</th>
								<th scope="row">기간</th>
								<th scope="row">상태</th>
								<th scope="row">상세</th>
								<th scope="row">휴가신청취소</th>
							</tr>
						</thead>
						<tbody id="allListBody">
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 휴가 내역 상세조회 modal -->
<div class="modal fade" id="vacationViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="vacationViewModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="vacationViewModalLabel">휴가 신청 상세</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div id="lineFormDiv">
					
				</div>
				<br/>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<th scope="row">신청 일시</th>
							<td id="adDate"></td>
							<th scope="row">상태</th>
							<td id="status"></td>
						</tr>
						<tr>
							<th scope="row">신청자</th>
							<td colspan="3" id="memName"></td>
						</tr>
						<tr>
							<th scope="row">소속</th>
							<td colspan="3" id="dept"></td>
						</tr>
						<tr>
							<th scope="row">종류</th>
							<td id="kind"></td>
							<th scope="row">일수</th>
							<td id="days"></td>
						</tr>
						<tr>
							<th scope="row">기간</th>
							<td colspan="3" id="date">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="휴가신청취소" class="btn btn-outline-primary vacationReturnBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 휴가 반려  modal-->
<div class="modal fade bd-example-modal-sm" id="vacationReturnModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="vacationReturnModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="vacationReturnModalLabel">휴가 신청 취소</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">결재된 휴가문서를 신청 취소하시겠습니까? <br/>
						취소 시 사용자의 휴가가 환원(취소)되고, <br/>
						전자결재에서 [문서함>반려]로 이동합니다.</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="확인" class="btn btn-outline-warning" id="returnBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>



<script type="text/javascript" src="${cPath }/js/vacation/vacaList.js"></script>

<script type="text/javascript">


	var memId = ${vacaStatusVO.memId}
	
	$(function(){
		$.appList();
		$.creList();
		$.allList();
	});
	
	$("#allList").on("click", function(){
		$(".myVacation").css("display", "none");
		$(".hide").css("display", "block");
	});
	
	$("#myVaca").on("click", function(){
		$(".myVacation").css("display", "block");
		$(".hide").css("display", "none");
	});
	
	// 휴가 상세 Modal
	let vacationViewModal = $("#vacationViewModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	// 휴가 신청 취소
	let vacationReturnModal = $("#vacationReturnModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$(document).on("click", ".detailBtn", function(){
		let vacaData =  $(this).closest("tr").data("vaca");
		let adNo = vacaData.adNo;
		let memId = vacaData.memId;
		
		$.ajax({
			url : $.getContextPath() + "/approval/get/line/" + adNo,
			dataType : "json",
			success : function(resp) {
				LINEFORMDIV = $("#lineFormDiv");
				LINEFORMDIV.empty();
				LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function(){
					var lineData = makeLineDataByJSON(resp);
					lineData.needBtns = false;
					lineData.needSigns = true;
					makeApprovalLineForm(lineData);  // ok 
				});
			}
		});
		
		$("#adDate").text(vacaData.adDate);
		$("#status").text(vacaData.dsName);
		$("#memName").text(vacaData.memName);
		$("#dept").text(vacaData.deptName);
		$("#kind").text(vacaData.vtName);
		$("#days").text(vacaData.days + "일");
		$("#date").text(vacaData.vacaBegin + " ~ " + vacaData.vacaEnd);
		if(vacaData.dsCode == 'RETURN'){
			$(".vacationReturnBtn").css("display", "none");
		} else {
			$(".vacationReturnBtn").css("display", "block");
		}
		vacationViewModal.modal();
		
		$(".vacationReturnBtn").on("click", function(){
			vacationViewModal.modal('hide');
			vacationReturnModal.modal();
			$("#returnBtn").on("click", function(){
				location.href = $.getContextPath() + "/vacation/vacationReturn/" + adNo + "/" + memId;
			});
		});
	});
	
	
	// 관리자 휴가 신청 목록 상세 조회
	$(document).on("click", ".allDetailBtn", function(){
		let vacaData =  $(this).closest("tr").data("vc");
		let adNo = vacaData.adNo;
	
		$.ajax({
			url : $.getContextPath() + "/approval/get/line/" + adNo,
			dataType : "json",
			success : function(resp) {
				LINEFORMDIV = $("#lineFormDiv");
				LINEFORMDIV.empty();
				LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function(){
					var lineData = makeLineDataByJSON(resp);
					lineData.needBtns = false;
					lineData.needSigns = true;
					makeApprovalLineForm(lineData);  // ok 
				});
			}
		});
		
		$("#adDate").text(vacaData.adDate);
		$("#status").text(vacaData.dsName);
		$("#memName").text(vacaData.memName);
		$("#dept").text(vacaData.deptName);
		$("#kind").text(vacaData.vtName);
		$("#days").text(vacaData.days + "일");
		$("#date").text(vacaData.vacaBegin + " ~ " + vacaData.vacaEnd);
		if(vacaData.dsCode == 'RETURN'){
			$(".vacationReturnBtn").css("display", "none");
		} else {
			$(".vacationReturnBtn").css("display", "block");
		}
		vacationViewModal.modal();
	});

	
	$(document).on("click", ".vcReturn", function(){
		let vacaData =  $(this).closest("tr").data("vc");
		let adNo = vacaData.adNo
		let memId = vacaData.memId;

		vacationReturnModal.modal();
		$("#returnBtn").on("click", function(){
			location.href = $.getContextPath() + "/vacation/vacationReturn/" + adNo + "/" + memId;
		});
	});
	
</script>