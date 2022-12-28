<%--

* 양식함 관리 > 리스트
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 15.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
.hiddenBtn{
	visibility: hidden;
}

.showBtn{
	visibility: visible;
}

.rightBtn{
	text-align: right;
}
	
table {
	cursor: pointer;
}
</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">양식함 관리</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">관리자</a></li>
			<li class="breadcrumb-item"><a href="#">양식함 관리</a></li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div>
					<button type="button" class="btn btn-outline-dark" id="documentFormBtn">양식생성</button>
					<button type="button" class="btn btn-outline-dark" id="docTypeBtn">분류설정</button>
					<br/><br/>
				</div>
				<div class="table-responsive">
					<table id="farmboxTable" class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover">
						<colgroup>
							<col width="10%" />
							<col width="10%" />
							<col width="30%" />
							<col width="50%" />
						</colgroup>					
						<thead class="text-center" > 
							<tr>
								<th style="font-size:1.2rem;">분류</th>
								<th style="font-size:1.2rem;">사용여부</th>
								<th style="font-size:1.2rem;">양식명</th>
								<th style="font-size:1.2rem;">설명</th>
							</tr>
						</thead>
						<tbody id="listBody" class="text-center">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<!-- 분류 설정 modal-->
<div class="modal fade bd-example-modal-sm" id="docTypeModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="docTypeModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="approvalSettingModalLabel">분류 설정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form:form commandName="documentsVO" id="docTypeForm" action="${cPath }/admin/approvalDocTypeInsert">
				<div class="modal-body">
					<table class="table table-hover">
						<tbody id="docTypeBody">
						</tbody>
					</table>
					<div class="text-center">
						<input type="submit" value="저장" class="btn btn-outline-success" id="saveBtn"/>
						<input type="button" value="추가" class="btn btn-outline-primary" id="insertBtn"/>
						<button type="button" class="btn btn-outline-dark" data-dismiss="modal">닫기</button>
					</div>
				</div>
			</form:form>

		</div>
	</div>
</div>

<!-- 분류 삭제  modal-->
<div class="modal fade bd-example-modal-sm" id="docTypeDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="docTypeDeleteModalLabel">
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
						<td align="center">분류를 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="deleteConfirmBtn"/>
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${cPath }/js/e_approvel/approvalFarmBoxList.js"></script>

<script type="text/javascript">
	$(function(){
		$.approvalFarmboxList();
	});
	
	$("#listBody").on("click", "tr", function(){
		let dfNo = $(this).data("farm").dfNo;
		location.href = $.getContextPath() + "/admin/approvalFarm/" + dfNo;
	});
	
	// ================= 분류 설정 modal ===========================================================
	let docTypeModal = $("#docTypeModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$("#docTypeBtn").on("click", function(){
		$.ajax({
			url : $.getContextPath() + "/admin/approvalDocTypeList",
			dataType : "json",
			success : function(resp) {
				let trTags = [];
				if(resp.length>0){ // 검색결과가 있으면 
					$(resp).each(function(idx, doc){
						trTags.push(
							$("<tr>").append(
									$("<td>").append($("<input type='text'>").attr({name:'docTypeName', value : doc.docTypeName, 'class':'old'})
																	  	     .css({border:'0',width:'200px'})
								)
								, $("<td>").append(
										$("<input>").attr({type:"button", value:"수정"}).addClass("btn hiddenBtn btn-sm updateBtn")
										, $("<input>").attr({type:"button", value:"삭제"}).addClass("btn hiddenBtn btn-sm deleteBtn")
								).css("float", "right")
							).data("doc", doc)
						);
					});
				}else{
					trTags.push(
						$("<tr>").html($("<td>").text("검색 결과 없음"))
					);
				}
				$("#docTypeBody").html(trTags);
			}
		});
		docTypeModal.modal();
	});
	
	 // ========================= 분류 등록 =====================================================================
	$("#insertBtn").on("click", function(){
		$("#docTypeBody").append(
			$("<input type='text'>").attr({name:'docTypeName','class':"form-control new",placeholder:'분류명 입력'})
									.prop("required", true)
									.css('width', '200px')
		);
	});

	/** 마우스오버/마우스아웃 할때마다 삭제버튼(X) 보였다안보였다 */
	$(document).on("mouseover", "tr", function(){
		$(this).find("input[type='button']").removeClass("hiddenBtn").addClass("showBtn");
	});
	
	$(document).on("mouseout", "tr", function(){
		$(this).find("input[type='button']").removeClass("showBtn").addClass("hiddenBtn");
	});
		
	$("#saveBtn").on("click", function(){
		$(".old").prop('disabled', true);
	});
	
	// ========================= 분류 수정 =====================================================================
	$(document).on("click",".updateBtn", function(){
		let tr = $(this).closest("tr");
		let data = $(this).closest("tr").data("doc");
		
		let docTypeCode = data.docTypeCode;
		let docTypeName = tr.find("input[name='docTypeName']").val();
		
		$.ajax({
			url : $.getContextPath() + "/admin/approvalDocTypeUpdate",
			data : {
				docTypeCode : docTypeCode
				, docTypeName : docTypeName
			},
			dataType : "json"
		});
	});
	
	// ========================= 분류 삭제 =====================================================================
	let docTypeDeleteModal = $("#docTypeDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});	
	
	$(document).on("click",".deleteBtn", function(){
		let tr = $(this).closest("tr");
		let data = $(this).closest("tr").data("doc");
		let docTypeCode = data.docTypeCode;
		let docTypeName = tr.find("input[name='docTypeName']").val();
		
		docTypeModal.modal('hide');
		let message = '';
		$.ajax({
			url : $.getContextPath() + "/admin/approvalFarmCheck/" + docTypeCode,
			data : {docTypeCode:docTypeCode},
			dataType : "json",
			traditional : true,
			success : function(resp) {
				if(resp.cnt > 0){
					message = resp.message;
					new Noty({
						 text: message.text , 
						 layout: message.layout ,
						 type: message.type,
						 timeout: message.timeout ,
						 progressBar: true
					}).show();
				}else{
					docTypeDeleteModal.modal();
				}
			}
		});
		
		$("#deleteConfirmBtn").on("click", function(){
			location.href=$.getContextPath() +  "/admin/approvalDocTypeDelete/" + docTypeCode + "/" + docTypeName;
		});
	});
	
	$("#documentFormBtn").on("click", function(){
		location.href = $.getContextPath() + "/admin/approvalFarmForm";
	});
	
</script>