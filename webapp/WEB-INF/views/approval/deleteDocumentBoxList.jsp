<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 23.   서대철	  최초작성
*  2021. 2. 27    이운주      페이지 헤더 수정 
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<style>
h6 {
	float : left;
}

table {
	cursor: pointer;
}

.approvalBtn {
	float : right;
	display: none;
	font-weight: normal;
}

.realDeleteBtn {
	float : right;
	display: none;
	font-weight: normal;
	margin-left: 10px;
}

.count {
	float : right;
	display:none;
	margin-right: 5px; 
	margin-left: 10px; 
	font-weight: normal;
}
</style>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">삭제 문서 목록</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="#">관리자</a>
			</li>
			<li class="breadcrumb-item active" aria-current="page">삭제 문서 목록</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->
    
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div>
					<h6 class="main-content-label">
						삭제 목록
						<a class="realDeleteBtn" href="#">완전 삭제</a> 
						<a class="approvalBtn" id="backupBtn" href="#">복원</a> 
						<a class="count" href="#"></a>
					</h6>
				</div>
				<div class="table-responsive"> 
					<form:form>
						<table id="deleteDocumentTable" class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover">
							<colgroup>
								<col width="10%" />
								<col width="50%" />
								<col width="10%" />
								<col width="10%" />
								<col width="20%" />
							</colgroup>
							<thead class="text-center">
								<tr>
									<th style="font-size:1.2rem;">문서번호</th>
									<th style="font-size:1.2rem;">제목</th>
									<th style="font-size:1.2rem;">기안자</th>
									<th style="font-size:1.2rem;">기안일</th>
									<th style="font-size:1.2rem;">삭제사유</th>
								</tr>
							</thead>
							<tbody id="listBody" class="text-center">
								
							</tbody>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<!-- 삭제문서목록 삭제 확인  modal-->
<div class="modal fade bd-example-modal-sm" id="realDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="realDeleteModalLabel">
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
						<td align="center">문서를 완전 삭제하시겠습니까?</td>
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

<script type="text/javascript" src="${cPath }/js/e_approvel/approvalDeleteDocumentBox.js"></script>

<script type="text/javascript">
	$(function(){
		$.approvalDeleteDocumnetBoxList();
	});
	
	function approvalCheck(e) {
		e.classList.toggle("on");
		
		let counts = document.getElementsByClassName("on").length
			
		if($("input:checkbox[name=adNo]").is(":checked")==true){
			$(".approvalBtn").show();
			$(".realDeleteBtn").show();
			$(".count").html(counts);
			$(".count").show();
		}else{
			$(".realDeleteBtn").hide();
			$(".approvalBtn").hide();
			$(".count").hide();
		}
	}
	
	// =========== 문서 백업 ====================
	$("#backupBtn").on("click", function(){
		let deleteAdNo = [];
		$("input[name=adNo]:checked").each(function(i){
			deleteAdNo.push($(this).val())
		});
		$.ajax({
			url : $.getContextPath() + "/admin/approvalDocumentBackUp"
			, method : 'post'
			, data : {deleteAdNo : deleteAdNo}
			, dataType : 'json'
			, traditional : true
			, success : function(resp) {
				let message = resp.message;
				new Noty({
					 text: message.text , 
					 layout: message.layout ,
					 type: message.type,
					 timeout: message.timeout ,
					 progressBar: true
				}).show();
				$.approvalDeleteDocumnetBoxList();
				$(".realDeleteBtn").hide();
				$(".approvalBtn").hide();
				$(".count").hide();
			}
		});
	});
	
	// ================= 삭제확인 modal ===========================================================
	let realDeleteModal = $("#realDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	// =========== 삭제문서 완전 삭제 ====================
	$(".realDeleteBtn").on("click", function(){
		realDeleteModal.modal();
		$("#deleteBtn").on("click", function(){
			realDeleteModal.modal('hide');
			let deleteAdNo = [];
			$("input[name=adNo]:checked").each(function(i){
				deleteAdNo.push($(this).val())
			});
			$.ajax({
				url : $.getContextPath() + "/admin/approvalDocumentRealDelete"
				, method : 'post'
				, data : {deleteAdNo : deleteAdNo}
				, dataType : 'json'
				, traditional : true
				, success : function(resp) {
					let message = resp.message;
					new Noty({
						 text: message.text , 
						 layout: message.layout ,
						 type: message.type,
						 timeout: message.timeout ,
						 progressBar: true
					}).show();
					$.approvalDeleteDocumnetBoxList();
					$(".realDeleteBtn").hide();
					$(".approvalBtn").hide();
					$(".count").hide();
				}
			});
		});
	});
	
	listBody.on('click','tr',function(event){
		let adNo = $(this).data("all").adNo;
		if(event.target.type == 'checkbox'){
			return;
		}
		location.href = $.getContextPath() + "/admin/approvalDeleteDocumentDetail/" + adNo;
	});
</script>

