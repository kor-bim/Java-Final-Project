<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 24.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<style>
table tr th {
	text-align: center;
	background-color: #efefff;
}

#insertBtn {
	margin-bottom: 20px;
}

#adContentBody{
	display: none;
}
</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">휴가 신청</h2>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div id="adContentBody">
					<div style="line-height: 1.8; font-family: &amp; quot; 맑은 고딕&amp;quot;; font-size: 16px; margin-top: 0px; margin-bottom: 0px;">
						<div style="margin: 0px; padding: 0px; line-height: 1.8; font-family: &amp; quot; 맑은 고딕&amp;quot;; font-size: 16px;">
							<h1>휴가 신청서</h1>
							<table border="1" style="border-collapse: collapse; width: 100%">
								<tbody>
									<tr>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 136px">
											<span>신청자</span>
										</td>
										<td colspan="2" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 402.47px">
											<span>${vacaStatusVO.memName }</span>
										</td>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 226.19px">
											<span>직위</span>
										</td>
										<td colspan="2" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 203.8px">
											<span>${vacaStatusVO.psName }</span>
										</td>
									</tr>
									<tr>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 136px">
											<span>기간</span>
										</td>
										<td colspan="5" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 805px">
											<span id="date">
												
											</span>
										</td>
									</tr>
									<tr>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 136px">
											<span>휴가종류</span>
										</td>
										<td colspan="2" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 402.47px">
											<span id="vacationKind"></span>
										</td>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 226.19px">
											<span>일수</span>
										</td>
										<td colspan="2" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 203.8px">
											<span id="day"></span>
										</td>
									</tr>
									<tr>
										<td style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; text-align: center; width: 136px">
											<span>사유</span>
										</td>
										<td colspan="5" style="border-color: #cdcdcd; border-image: none; border-style: solid; border-width: 1px; height: 0px; width: 852px">
											<span id="content"></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<form:form commandName="vacaRecVO" id="vacationForm" action="${cPath }/vacation/vacationInsert">
					<input type="hidden" name="approvalDocVO.approvalMember" value="${vacaStatusVO.memId }" />
					<input type="hidden" name="approvalDocVO.adContent" id="adContent" value="" />
					<input type="hidden" name="approvalDocVO.dfNo" value="${vacaRecVO.approvalDocVO.documentsVO.dfNo }" />
					<input type="hidden" name="approvalDocVO.memId" value="${vacaStatusVO.memId }" />
					
					<input type="submit" class="btn btn-outline-light" value="기안하기" id="insertBtn" />
					<table class="table table-bordered">
						<colgroup>
							<col width="15.0%">
							<col width="85.0%">
						</colgroup>
						<tbody>
							<tr>
								<th>현황</th>
								<td>
									<c:set value="${vacaStatusVO.vacaStatList }" var="vacaList" />
									생성 : <b>${vacaStatusVO.vacaInday + vacaList[1].vacaInday }일</b> &nbsp;&nbsp;&nbsp; / &nbsp;&nbsp;&nbsp; 
									사용 : <b>${(vacaStatusVO.vacaInday - vacaStatusVO.vacaRmday) + (vacaList[1].vacaInday - vacaList[1].vacaRmday)}일</b>&nbsp;&nbsp;&nbsp; / &nbsp;&nbsp;&nbsp; 
									잔여 : <b>${vacaStatusVO.vacaRmday + vacaList[1].vacaRmday}일</b> (정기 : <b>${vacaStatusVO.vacaRmday }</b>일,&nbsp;&nbsp; 
									포상 : <b>${vacaList[1].vacaRmday }</b>일)
								</td>
							</tr>
							<tr>
								<th>작성자</th>
								<td>${vacaStatusVO.memName }</td>
							</tr>
							<tr>
								<th>결재</th>
								<td>
									<div id="alineBody" class="row">
										<div class="col-auto pt-2">
											<span>${vacaStatusVO.memName }</span>
											<input type="hidden" name="memId" value="${vacaStatusVO.memId }" />
										</div>
										<div class="col-auto" id="aline">
											<button type="button" class="btn btn-outline-link" id="approvalLineBtn">결재선 설정</button>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td>
									<input type="text" class="form-control" name="approvalDocVO.adTitle" />
								</td>
							</tr>
							<tr>
								<th>종류</th>
								<td>
									<c:if test="${not empty vacaTypeList}">
										<select class="form-control" name="vtCode" id="vtCode" onchange="makeContent(this)">
											<option>휴가 종류 선택</option>
											<c:forEach items="${vacaTypeList }" var="vaca">
												<option value="${vaca.vtCode }">${vaca.vtName }</option>
											</c:forEach>
										</select>
									</c:if>
								</td>
							</tr>
							<tr>
								<th>기간 및 일시</th>
								<td>
									<input type="date" id="startDay" name="vacaBegin" onchange="call('start')" />
									&nbsp;&nbsp;~&nbsp;&nbsp;
									<input type="date" id="endDay" name="vacaEnd" onchange="call()" min="" disabled/>
									&nbsp;&nbsp;&nbsp;&nbsp; 사용일수 :
									<input type="text" id="days" disabled style="width: 40px" />
								</td>
							</tr>
							<tr>
								<th>사유</th>
								<td>
									<textarea rows="10" class="form-control" name="vcContent" id="vcContent" onchange="makeContent()"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<th>참고</th>
								<td>
									<p>1. 연차의 사용은 근로기준법에 따라 전년도에 발생한 개인별 잔여 연차에 한하여 사용함을 원칙으로 한다. 단, 최초 입사시에는 근로 기준법에 따라 발생 예정된 연차를 차용하여 월 1회 사용 할 수 있다.</p>
									<p>2. 경조사 휴가는 행사일을 증명할 수 있는 가족 관계 증명서 또는 등본, 청첩장 등 제출</p>
									<p>3. 공가(예비군/민방위)는 사전에 통지서를 사후에 참석증을 반드시 제출</p>
								</td>
							</tr>
						</tfoot>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- 결재선 설정 modal-->
<div class="modal fade" id="approvalLineModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="approvalLineModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="approvalLineModalLabel">결재선 설정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body text-center">
				<div id="deptTreeDIV"></div>
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-outline-primary" value="저장" id="approvalLineSetting" />
				<input type="button" class="btn btn-outline-secondary" value="취소" data-dismiss="modal" />
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$("#vacationForm").validate({
		rules : {
			vtCode : {
				required : true
			}
			, vacaBegin : {
				required : true
			}
			, vcContent : {
				required : true
			}
		},
		messages : {
			vtCode : {
				vtCode : "종류 선택은 필수 입력값입니다"
			}
			, vacaBegin : {
				required : "시작일은 필수 입력값입니다."
			}
			, vcContent : {
				required : "사유는  필수 입력값입니다."
			}
		},
	    errorPlacement: function(error, element) {
			element.tooltip({
			title: error.text()
	             , placement: "right"
	             , trigger: "manual"
	             , delay: { show: 500, hid: 100 }
			}).on("shown.bs.tooltip", function() {
				let tag = $(this);
	            setTimeout(() => {
					tag.tooltip("hide");
	            }, 2000)
			}).tooltip('show');
	    }
	});
})

	function myOnCheck(event, treeId, treeNode) {
		$("#approvalLineSetting").on("click", function(){
			$("#aline").before(
				$("<div>").text(">" + treeNode.name).addClass("col-auto pt-2")
				, $("<input type='hidden'>").attr({value:treeNode.value, name:"approvalDocVO.approvalMember"})
			)
			approvalLineModal.modal('hide');
		});
	} // myOnCheck end 

	
	$("#startChk").on("click", function(){
		if($("input:checkbox[name=startChk]").is(":checked")==true){
			$("#startAm").prop("disabled", false);
			$("#startPm").prop("disabled", false);
		} else {
			$("#startAm").prop("disabled", true);
			$("#startPm").prop("disabled", true);
			$("input[name='startOrder']").prop("checked", "");
		}
	});
	
	$("#endChk").on("click", function(){
		if($("input:checkbox[name=endChk]").is(":checked")==true){
			$("#endAm").prop("disabled", false);
			$("#endPm").prop("disabled", false);
			
		} else {
			$("#endAm").prop("disabled", true);
			$("#endPm").prop("disabled", true);
			$("input[name='endOrder']").prop("checked", "");
		}
	});
	
	$("#halfRest ").on("change", "input[type=radio]" ,function(){
		if($(this).val() == "start_am"){
			$("#endChk").prop("disabled", true);
			$("#endDay").val($("#startDay").val());
			call('start');
		}else{
			$("#endChk").prop("disabled", false);
		}
		
	})
	
	// 일수 계산
	var days = 0;
	function call(tmp){
		let start = document.getElementById("startDay").value;
		let endTag = $("#endDay"); 
		if(tmp == 'start'){
			endTag.prop("disabled", false);
			endTag.val(start);
			endTag.attr("min", start);
		}
		let end = document.getElementById("endDay").value;
			
		let arr1 = start.split('-');
		let arr2 = end.split('-');
		
		let date1 = new Date(arr1[0], arr1[1], arr1[2]-1);
		let date2 = new Date(arr2[0], arr2[1], arr2[2]);
		
		let sub = date2 - date1;
		let day = 24 * 60 * 60 * 1000;
		days = parseInt(sub/day);
		
		if(start && end){
			document.getElementById("days").value = days;
			$("#day").text(days);
		}
		
		$("#startChk").prop("disabled", false);
		
		$("#date").text(start + "~" + end);
	}
	
	// 반차
	$(document).on("change", "#vacationForm", function(){
		let startflag = $("#startChk").prop("checked");  // 시작일 반차여부
		let endflag = $("#endChk").prop("checked");      // 종료일 반차여부 
		let halfdayCnt = $(".halfday:checked").length;
		
		document.getElementById("days").value = parseInt(days) - (0.5*halfdayCnt);
		$("#day").text(parseInt(days) - (0.5*halfdayCnt));
	});
	
	const DEPT_TREE_DIV = $("#deptTreeDIV");
	DEPT_TREE_DIV.load($.getContextPath() + "/ztree/deptMem");
	
	let approvalLineModal = $("#approvalLineModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$("#approvalLineBtn").on("click", function(){
		approvalLineModal.modal();
	});
	
	function makeContent(tmp){
		$("#vacationKind").text($("#vtCode").find("option:selected").text());
		$("#content").text($("#vcContent").val());
	}
	
	$("#insertBtn").on("click", function(){
		$("#adContent").val($("#adContentBody").html());
		console.log($("#adContent").val());
	});
	
	
</script>
