<%--
* 사용자 관리 페이지 : 구성원 리스트 
*
* [[개정이력(Modification Information)]]
*   수정일                  수정자               수정내용
* -----------   ---------  -----------------
* 2021. 2. 5.   이재형                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.table-bordered th {
	border: 1px solid #e8e8f7;
	background-color: #efefff;
	text-align: center;
}

tr.job {
	border: hidden;
	border-top: 3px solid #e8e8f7;
}

td.last_space {
	text-align: right;
	padding-right: 25px;
	border: hidden;
	border-left: 1px solid #e8e8f7;
	padding-left: 10px;
	padding-bottom: 0px;
	padding-top: 0px;
}
tr.job>td{
	border: 0;
}

.btn-plus {
	text-align: center;
	height: 1px;
	border-radius: 50%;
	margin: 0px;
	/* 	padding-bottom: 2px; */
	/* 	padding-left: 2px; */
	/* 	padding-right: 2px; */
	/* 	padding-top: 2px; */
}

.badge-primary {
	border-radius: 50%;
	padding: 7.5px 8px;
}

.delPs{
	position:absolute;
	margin-top: 6px;
}
</style>



<script type="text/javascript">

		
	$(function() {
		
		// 		$.addressList = function() {
		// 			let url = document.location.href;

		// 			$.ajax({
		// 				url : $.getContextPath() + "/org/psList.do",
		// 				dataType : "json",
		// 				success : function(resp) {
		// 					let trTags = [];
		// 					if (resp.length > 0) { // 검색결과가 있으면 
		// 						$(resp).each(
		// 								function(idx, psVO) {
		// 									trTags.push($("<tr>").append(
		// 											$("<td>").text(psVO.psCode),
		// 											$("<td>").text(psVO.psName),
		// 											$("<td>").text(psVO.psGrade)).data(
		// 											"psVO", psVO));
		// 								});
		// 					} else {
		// 						trTags.push($("<tr>").html(
		// 								$("<td colspan='4'>").text("검색 결과 없음")));
		// 					}
		// 					listBody.html(trTags);
		// 				}
		// 			}); // ajax end
		// 		}
		$(".delJob").hide();
		$(".delPs").hide();
		
	});
	
	$(document).on("keypress", "#inputPs", function(e){
		if(e.keyCode==13){
			e.preventDefault();
			e.stopPropagation();
			$(this).blur();
		}
	});
	$(document).on("keypress", "#inputJob", function(e){
		if(e.keyCode==13){
			e.preventDefault();
			e.stopPropagation();
			$(this).blur();
		}
	});
	
	
	// 등급 추가
	$(document).on("click", "#btnAddGrade", function(e) {
		e.preventDefault();
		e.stopPropagation();
		var grade = $('#pstbody').children().length + 1;
		console.log(grade);
		if(index > 0){
			index = index+1;
		}
		var naming = 'psVOList['+index+'].';
		var divPs = $('<div>', {'class':'ps mg-15', 'style':'display:inline'});
		var spanPsName = $('<span>', {'class':'ps'});
		var inputPsCode = $('<input>', {'class':'inputPsCode', 'type':'hidden', 'name':naming+'psCode', 'value':'new'});
		var inputPsName = $('<input>', {'class':'inputPsName', 'type' : 'text', 'name':naming+'psName', 'id':'inputPs'});
		var inputPsGrade = $('<input>', {'class':'inputPsGrade', 'type':'hidden', 'name':naming+'psGrade', 'value':grade});
		var inputPsUseYn = $('<input>', {'class':'inputPsUseYn', 'type':'hidden', 'name':naming+'psUseYn', 'value':'Y'});
		var delPs = $('<span>', {'class' : 'fa fa-close delPs'});
		var btnAddPs = $('<span>', {'class':"badge badge-pill badge-primary btnAddPs", "data-placement":"right", "data-toggle":"tooltip", "title":"해당 등급에 직위를 추가할 수 있습니다."});
		e.preventDefault();
		e.stopPropagation();
		if (grade > 30) {
			alert('더 이상 등급을 추가할 수 없습니다.');
			return;
		}
		
		$('<tr>', {'class':'ps'}).append($('<th>', {'scope' : 'row'}).append(grade + '등급'));
		
		
		$("#pstbody").append($("<tr>").append($("<th>").append($("<span>").append(grade	+ "등급")))
									  .append($("<td>").append(divPs.append(spanPsName).append(inputPsCode).append(inputPsName).append(inputPsGrade).append(inputPsUseYn).append(delPs)))
								      .append($("<td>", {'class':'last_space'}).append(btnAddPs.append($("<i>", {'class':"typcn typcn-plus"})))));
		$("#inputPs").focus();
		delPs.hide();
	});

	
	// 직위 추가
	$(document).on("click", ".btnAddPs", function() {
		var grade = $(this).parent().siblings("th").children("span").attr("grade");
		var spanPsName = $('<span>', {'class':'ps'});
		if(index > 0){
			index = index+1;
		}
		var naming = 'psVOList['+index+'].';
		var inputPsCode = $('<input>', {'class':'inputPsCode', 'type':'hidden', 'name':naming+'psCode', 'value':'new'});
		var inputPsName = $('<input>', {'class':'inputPsName', 'type' : 'text', 'name':naming+'psName', 'id':'inputPs'});
		var inputPsGrade = $('<input>', {'class':'inputPsGrade', 'type':'hidden', 'name':naming+'psGrade', 'value':grade});
		var inputPsUseYn = $('<input>', {'class':'inputPsUseYn', 'type':'hidden', 'name':naming+'psUseYn', 'value':'Y'});
		var delPs = $('<span>', {'class' : 'fa fa-close delPs'});
		$(this).parent().siblings('td').append($('<div>', {'class':'ps mg-15','style':'display:inline'}).append(spanPsName).append(inputPsCode).append(inputPsName).append(inputPsGrade).append(inputPsUseYn).append(delPs));
		$("#inputPs").focus();
		delPs.hide();
	});

	
	// 직위 포커스 아웃
	$(document).on("blur","#inputPs",function(e) {
		e.preventDefault();
		e.stopPropagation();
		var tbody = $(this).parents("#pstbody");
		var tr = tbody.children("tr");
		var td = tr.children("td");
		var div = td.children("div.ps").not($(this).parent());
		var span = div.children("span.ps");
		var text = span.text();
		console.log("input제외한 전체:"+(span.text()).trim());
		console.log('input값:'+$(this).val());
		var value = $(this).val();
		var thisParent = $(this).parent();
		console.log(span.text());
		span.each(function(i){
			console.log('span==input:'+($(span[i]).text().trim()==$(this).val()));
			console.log('input값:'+value);
			console.log($(span[i]).siblings('input.inputPsUseYn').val());
			if($(span[i]).text().trim()==value){
				if($(span[i]).siblings('input.inputPsUseYn').val()=='N'){
					$(span[i]).siblings('input.inputPsUseYn').attr('value','Y');				
				}else{
					alert("이미 존재하는 직위명 입니다.");
					thisParent.remove();
				}
				return false;
			}
			console.log('span값:'+$(span[i]).text());
			console.log('-------------------------');
			
		});
// 		console.log($(this).val().length);
// 		console.log($(this).siblings('input[name=psCode]').val());
		if (($(this).val().length == 0||$(this).val() == null||""==$(this).val()) && $(this).siblings('input.inputPsCode').val() == 'new') {
			$(this).parent().remove();
		}else if(($(this).val().length == 0||$(this).val() == null||""==$(this).val()) && $(this).siblings('input.inputPsCode').val() != 'new'){ 
			$(this).siblings("input.inputPsUseYn").attr("value", 'N');
			$(this).attr({'type' : "hidden"});
			$(this).parent().hide();
			$(this).removeAttr('id');
		}else {
			$(this).attr({'type' : "hidden"});
			$(this).siblings('span.ps').text($(this).val().trim());
			$(this).siblings('span.ps').show();
			$(this).removeAttr('id');
		}
	});
	
	// 직위 수정
	$(document).on("click", "span.ps", function(e){
		e.preventDefault();
		e.stopPropagation();
		if(this.length != 0){
			console.log($(this).text());
// 			$(this).parent().append(input.attr("value",$(this).text())).append(delIcon);
			if($(this).siblings('input.inputPsName').val().length != 0 ){
				$(this).siblings('input.inputPsName').attr({'type':'text', 'id':'inputPs'});
			}else{
				$(this).siblings('input.inputPsName').attr({'type':'text', 'id':'inputPs', 'value':($(this).text()).trim()});
			}
			$(this).hide();
			$("#inputPs").focus();
		}
	});
	
	// 직위 삭제 버튼 보이기
	$(document).on("mouseover", "div.ps", function(){
		$(this).find('.delPs').show();
	});
	// 직위 삭제 버튼 숨기기
	$(document).on("mouseout", "div.ps", function(){
		$(this).find('.delPs').hide();		
	});
	// 직위 삭제 버튼 클릭
	$(document).on("click", ".delPs", function(e) {
		// yn을 n으로 바꿈
		$(this).siblings("input.inputPsUseYn").attr("value", 'N');
		$(this).parent().hide();
		if($(this).siblings("input.inputPsCode").val()=='new'){
			$(this).parent().remove();
		}
	});
	// 1. PS_CODE가 있고 PS_NAME이 존재하지 않으면 UPDATE
	// 2. PS_CODE가 NEW이고 PS_NAME이 존재하지 않으면 INSERT
	// 4. PS_CODE가 중복이 되지 않는데 PS_NAME이 중복되면 OUT 
	
	
	
	// 직무 수정
	$(document).on("click", "span.job", function(e){
		e.preventDefault();
		e.stopPropagation();
		$(this).hide();
// 		$(this).parent().append($("<span>").append( $("<input>", {id:"inputJob", type:"text", value:$(this).text().trim()})));
// 		console.log($(this).siblings('input[name=jobName]').val());
		$(this).siblings('input[name=jobName]').attr({'type':'text', 'id':'inputJob', 'class':'inputJobName'});
		$(this).siblings(".delJob").hide();
		$("#inputJob").focus();
	});
	
	// 직무 포커스 아웃
	$(document).on("blur", "#inputJob", function(e){
		e.preventDefault();
		e.stopPropagation();
		$(this).siblings('span.job').text($(this).val()).show();
		console.log($(this).val().length);	
		$(this).attr({'type':'hidden'});
		$(this).removeAttr('id');
		if($(this).val().length == 0){
			$(this).parent().parent().remove();
		}
		$(this).siblings('span.delJob').attr({'data-job-code':$(this).val()});
	});
	
	// 직무 추가
	$(document).on("click", "#btnAddJob", function(e){
		var spanJob = $('<span>', {'class':'job'});
		var hiddenJobCode = $('<input>', {'type':'hidden', 'name':'jobCode', 'class':'inputJobCode'});
		var hiddenJobName = $('<input>', {'type':'text', 'name':'jobName', 'id':'inputJob', 'class':'inputJobName'});
		var delJob = $('<span>', {'class' : 'fa fa-close delJob', 'style':'display:none'});
		$("#jobtbody tr.job").append($("<td>").append($("<div>", {'style':"display:inline-block", 'class':'job'}).append(spanJob).append(hiddenJobCode).append(hiddenJobName).append(delJob)));
		$("#inputJob").focus();
	});
	
	// 직무 삭제
	$(document).on("mouseover", "div.job", function(){
		$(this).children(".delJob").show();
	});
	$(document).on("mouseout", "div.job", function(){
		$(this).children(".delJob").hide();		
	});
// 	$(document).on("click", ".delJob", function(e){
// 		e.preventDefault();
// 		e.stopPropagation();
// 		$(this).parent().parent().remove();
// 		$(this).siblings("input[name=jobCode]").attr({"name":"delJobCode"});
// 		$(this).siblings("input[name=jobName]").attr({"name":"delJobName"});
// 		$(this).hide();
// 	});
	
	
</script>




<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">직위/직무 관리</h2>
	</div>
</div>
<!-- End Page Header -->
<div class="row row-sm">
	<div class="col-lg-12 col-md-12">
		<div class="card custom-card">
			<form action="${cPath}/admin/org/updatePosition" class="row g-3" id="psForm" name="psForm" method="post">
				<div class="card-body ml-3">
					<div>
						<h4 class="main-content-label">직위 관리</h4>
						<!-- 						<p class="text-muted card-sub-title">Add borders on all sides of the table and cells.</p> -->
					</div>
					<div class="mb-3 mt-3">
						<button class="btn ripple btn-primary btn-rounded" id="btnAddGrade" data-placement="right" data-toggle="tooltip" title="맨 아래 등급을 추가할 수 있습니다.">
							<i class="typcn typcn-plus"></i> 등급 추가
						</button>
<!-- 						<span> -->
<!-- 							<input type="text" data-role="tagsinput"> -->
<!-- 							<span class="fa fa-close"></span> -->
<!-- 						</span> -->
<!-- 						<div class="tags"> -->
<!-- 							<span class="tag tag-default"> -->
<!-- 								One <a href="javascript:void(0)" class="tag-addon"><i class="fe fe-x"></i></a> -->
<!-- 							</span> -->
<!-- 						</div> -->
					</div>
					<div class="row">
						<div class="col-10">
							<div class="table-responsive">
								<table class="table text-nowrap border-top text-md-nowrap table-bordered mg-b-0 ">
									<colgroup>
										<col width="20%">
										<col width="80%">
									</colgroup>
									<tbody id="pstbody">
										<c:if test="${not empty psVOList }">
											<c:forEach items="${psVOList }" var="psVO" varStatus="status">
<%-- 												<c:forEach var="" begin="0" end="${fn:length(psVOList)}" items="${psVOList }" varStatus="vs" > --%>
												<c:if test="${psVO.psUseYn eq 'Y'}">
													<c:if test="${grade ne psVO.psGrade}">
														<tr>
															<th>
																<span grade="${psVO.psGrade }"> ${psVO.psGrade }등급 </span>
															</th>
															<td>
																<c:forEach items="${psVOList }" var="ps" varStatus="status">
																	<c:if test="${ps.psGrade eq psVO.psGrade}">
																		<c:if test="${ps.psUseYn eq 'Y' }">
																			<div class="ps mg-15" style="display:inline">
																				<span class="ps"> ${ps.psName } </span>
																				<input class="inputPsCode" type="hidden" name="psVOList[${status.index }].psCode" value="${ps.psCode }">
																				<input class="inputPsName" type="hidden" name="psVOList[${status.index }].psName" value="${ps.psName }">
																				<input class="inputPsGrade" type="hidden" name="psVOList[${status.index }].psGrade" value="${ps.psGrade }">
																				<input class="inputPsUseYn" type="hidden" name="psVOList[${status.index }].psUseYn" value="${ps.psUseYn }">
																				<span class="fa fa-close delPs"></span>
																				<c:set var="index" value="${status.index }" />
																			</div>
																		</c:if>
																	</c:if>
																</c:forEach>
															</td>
															<td class="last_space">
																<span class="badge badge-pill badge-primary btnAddPs" data-placement="right" data-toggle="tooltip" title="맨 아래 등급을 추가할 수 있습니다.">
																	<i class="typcn typcn-plus"></i>
																</span>
															</td>
														</tr>
													</c:if>
													<c:set var="grade" value="${psVO.psGrade }" />
												</c:if>
											</c:forEach>
<%-- 											<c:set var="minus" value="0" /> --%>
<%-- 											<c:set var="grade" value="0" /> --%>
<%-- 											<c:forEach items="${psVOList }" var="psVO" > --%>
<%-- 												<c:if test="${psVO.psUseYn eq 'Y'}"> --%>
<%-- <%-- 													<c:choose> --%> 
<%-- <%-- 														<c:when test="${psVO.psGrade ne grade }"> --%> 
<%-- <%-- 															<c:set var="grade" value="${grade+1 }"/> --%> 
<%-- <%-- 														</c:when> --%> 
<%-- <%-- 														<c:when test="${psVO.psGrade > grade+1 }"> --%> 
<%-- <%-- 															<c:set var="minus" value="${psVO.psGrade - grade }"/>											 --%> 
<%-- <%-- 														</c:when> --%> 
<%-- <%-- 													</c:choose> --%> 
<%-- 													<c:if test="${psVO.psGrade ne grade }"> --%>
<%-- 															<c:set var="grade" value="${grade+1 }"/> --%>
<%-- 															<c:if test="${psVO.psGrade > grade+1 }"> --%>
<%-- 																<c:set var="minus" value="${psVO.psGrade - grade }"/> --%>
<%-- 															</c:if> --%>
<!-- 														<tr> -->
<!-- 															<th> -->
<%-- 																<span> ${grade }등급</span> --%>
<!-- 															</th> -->
<!-- 															<td> -->
<%-- 																<c:forEach items="${psVOList }" var="ps" varStatus="status"> --%>
<%-- 																	<c:if test="${ps.psUseYn eq 'Y' }"> --%>
<%-- 																		<c:if test="${ps.psGrade eq grade}"> --%>
<!-- 																			<div class="ps mg-15" style="display:inline"> -->
<%-- 																				<span class="ps"> ${ps.psName } </span> --%>
<%-- 																				<input class="inputPsCode" type="hidden" name="psVOList[${status.index }].psCode" value="${ps.psCode }"> --%>
<%-- 																				<input class="inputPsName" type="hidden" name="psVOList[${status.index }].psName" value="${ps.psName }"> --%>
<%-- 																				<input class="inputPsGrade" type="hidden" name="psVOList[${status.index }].psGrade" value="${grade - minus}"> --%>
<%-- 																				<input class="inputPsUseYn" type="hidden" name="psVOList[${status.index }].psUseYn" value="${ps.psUseYn }"> --%>
<!-- 																				<span class="fa fa-close delPs"></span> -->
<%-- 																				<c:set var="index" value="${status.index }" /> --%>
<%-- 																				<c:set var="lastGrade" value="${grade - minus}"/> --%>
<!-- 																			</div> -->
<%-- 																		</c:if> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
<!-- 															</td> -->
<!-- 															<td class="last_space"> -->
<!-- 																<span class="badge badge-pill badge-primary btnAddPs" data-placement="right" data-toggle="tooltip" title="맨 아래 등급을 추가할 수 있습니다."> -->
<!-- 																	<i class="typcn typcn-plus"></i> -->
<!-- 																</span> -->
<!-- 															</td> -->
<!-- 														</tr> -->
<%-- 													</c:if> --%>
<%-- 												</c:if> --%>
<%-- 											</c:forEach> --%>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-12 mt-4">
							<button type="button" id="btnUpdatePosition" class="btn btn-primary">저장</button>
							<button class="btn btn-outline-danger" type="reset" onclick="javascript:window.location.reload(true);" data-placement="top" data-toggle="tooltip" title="이전의 상태로 초기화 합니다.">취소</button>
							<span style="color:red;">추가 및 수정 후에는 반드시 저장 버튼을 누르셔야 반영이 됩니다.</span>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="card custom-card">
			<form action="${cPath}/admin/org/updateJob" class="row g-3" id="jobForm" name="jobForm" method="post">
				<div class="card-body ml-3">
					<div>
						<h6 class="main-content-label">직무 관리</h6>
<!-- 						<p class="text-muted card-sub-title">Add borders on all sides of the table and cells.</p> -->
					</div>
					<div class="mb-3 mt-3">
						<button class="btn ripple btn-primary btn-rounded" id="btnAddJob" type="button">
							<i class="typcn typcn-plus"></i> 직무 추가
						</button>
					</div>
					<div class="row">
						<div>
							<div class="table-responsive">
								<table class="table text-nowrap border-top text-md-nowrap table-bordered mg-b-0 ">
									<colgroup>
										<col width="20%">
										<col width="80%">
									</colgroup>
									<tbody id="jobtbody">
										<tr>
										</tr>
										<tr class="job">
											<c:forEach items="${jobVOList }" var="jobVO">
												<td>
													<div style="display:inline-block" class="job">
														<span class="job">${jobVO.jobName }</span>
														<input type="hidden" name="jobCode" value="${jobVO.jobCode }" class="inputJobCode">
														<input type="hidden" name="jobName" value="${jobVO.jobName }" class="inputJobName">
														<span class="fa fa-close delJob" data-job-code="${jobVO.jobCode}"></span>
													</div>
												</td>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="col-12 mt-4">
							<button type="button" class="btn btn-primary" id="btnUpdateJob">저장</button>
							<button class="btn btn-outline-danger" type="reset" onclick="javascript:window.location.reload(true);" data-placement="top" data-toggle="tooltip" title="이전의 상태로 초기화 합니다.">취소</button>
							<span style="color:red;">추가 및 수정 후에는 반드시 저장 버튼을 누르셔야 반영이 됩니다.</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
var index = ${index};
	$("#btnUpdatePosition").on("click", function(e) {
		psForm.submit();
	});
	let psForm = $("#psForm").ajaxForm({
		dataType : "json",
		success : function(resp) {
			console.log(resp);
			window.location.reload(true);
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
	
	
	$("#btnUpdateJob").on("click", function(e){
// 		var arrJob = [];
// 		var div = $("tr.job").children("td").children("div.job");
// 		var inputJobCode = div.children("input.inputJobCode");
// 		var inputJobName = div.children("input.inputJobName");
// 		inputJobCode.each(function(i){
// 			console.log($(inputJobCode[i]).val());
// 			var jobVO = {"jobCode":$(inputJobCode[i]).val().trim(), "jobName":$(inputJobName[i]).val().trim()};
// 			console.log(jobVO);
// 			arrJob.push(jobVO);
// 		});
// 		console.log(arrJob);
		
		
		
		let jobForm = $("#jobForm").ajaxForm({
			url : "${cPath}/admin/org/updateJob",
			type: "POST",
			dataType : "text",
			success : function(resp){
				console.log(resp);
				if(resp=="OK"){
					alert("저장했습니다");
				}else{
					alert("저장에 실패 했습니다");
				}
			},
			error : function(xhr,status, error){
				console.log("xhr:"+xhr+", status:"+status+", error:"+error);
			}
		});
		jobForm.submit();
	});
	$(document).on("click", "span.delJob", function() {
		let jobCode = $(this).data("jobCode");
		$(this).parent("div.job").append($("<input>").attr({
			"type" : "hidden",
			"name" : "delJobCode"
		}).val(jobCode));
		$(this).parent().parent().hide();
		$(this).siblings('input[name=jobCode]').remove();
		$(this).siblings('input[name=jobName]').remove();
	});
</script>

