<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 19.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
table th{
	text-align: center;
}

th {
	background-color: #efefff;
}
</style>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">양식 조회</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${cPath }/admin/approvalFarmboxList">목록으로</a></li>
		</ol>
	</div>
</div>

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<button type="button" class="btn btn-outline-light" id="modifyBtn">수정</button>
				<button type="button" class="btn btn-outline-light" id="deleteBtn">삭제</button>
				<form:form commandName="documentsVO">
					<fieldset>
						<strong>기본 설정</strong>
							<input type="hidden" name="dfNo" value="${documentsVO.dfNo }" />
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th scope="row">분류</th>
									<td>${documentsVO.docTypeName }</td>
									<th scope="row">사용 여부</th>
									<td>${documentsVO.dfUseYn eq 'Y' ? "사용" : "사용안함" }</td>
								</tr>
								<tr>
									<th scope="row">보안 등급</th>
									<td>${documentsVO.scuCode }등급</td>
									<th scope="row">양식명</th>
									<td>${documentsVO.dfName }</td>
								</tr>
								<tr>
									<th scope="row">보존 연한</th>
									<td>${documentsVO.presName }</td>
									<th scope="row">설명</th>
									<td>${documentsVO.dfDescription }</td>
								</tr>
							</tbody>
						</table>
					</fieldset>
					<div class="form">
						<b>결재양식</b>
						<ul>
							<li>결재 포맷 : ${documentsVO.alName }</li>
						</ul>
					</div>
					<div>
						<b>양식 내용</b>
						<p>
							${documentsVO.dfContent }
						</p>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>

<!-- 양식 삭제 modal-->
<div class="modal fade bd-example-modal-sm" id="farmDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="farmDeleteModalLabel">
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
						<td align="center">양식을 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="delete" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#modifyBtn").on("click", function(){
		let dfNo = $("input[name='dfNo']").val();
		location.href = $.getContextPath() + "/admin/approvalFormUpdate/" + dfNo;
	});
	
	// ================= 양식 삭제 modal ===========================================================
	let farmDeleteModal = $("#farmDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body");
	});
	
	$("#deleteBtn").on("click", function(){
		let dfNo = $("input[name='dfNo']").val();
		farmDeleteModal.modal();
		$("#delete").on("click", function(){
			location.href = $.getContextPath() + "/admin/approvalFarmDelete/" + dfNo;
		})
	})
</script>