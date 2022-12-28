<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 1. 26.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">주소록</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">주소록</a></li>
			<c:choose>
				<c:when test="${adbkType == 'private' }">
					<li class="breadcrumb-item"><a href="#">개인 주소록</a></li>
				</c:when>
				<c:otherwise>
					<li class="breadcrumb-item"><a href="#">공유 주소록</a></li>
				</c:otherwise>
			</c:choose>
		</ol>
	</div>
</div>
<!-- End Page Header -->
<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<input type="button" class="btn btn-outline-dark" value="주소 추가" id="insertBtn"/>
				<br/><br/>
				<div class="table-responsive">
					<table id="addressTable"
						class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover" style="cursor: pointer">
						<thead>
							<tr>
								<th>이름</th>
								<th>이메일</th>
								<th>전화번호</th>
								<th>회사주소</th>
							</tr>
						</thead>
						<tbody id="listBody">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<!-- 주소 상세조회 modal -->
<div class="modal fade" id="addressViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="addressViewModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="addressViewModalLabel">주소록 상세조회</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<c:url value="/addressbook/update.do" var="updateUrl">
					<c:param name="adbkCode" value="${address.adbkCode }" />
				</c:url>
				<input type="button" value="수정" class="btn btn-outline-primary" id="modifyBtn" />
				<button type="button" class="btn btn-outline-warning" id="removeBtn">삭제</button>
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 주소 추가 modal-->
<div class="modal fade" id="addressInsertModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="addressInsertModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="addressInsertModalLabel">주소 추가</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<!-- 주소 수정  modal-->
<div class="modal fade" id="addressUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="addressUpdateModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="addressInsertModalLabel">주소 수정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
		</div>
	</div>
</div>

<!-- 주소 삭제  modal-->
<div class="modal fade bd-example-modal-sm" id="addressDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="addressDeleteModalLabel">
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
						<td align="center">주소를 삭제하시겠습니까?</td>
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

<script src="${cPath }/js/address/addressbook.js"></script>

<script type="text/javascript">
	var memId = ${memId };
</script>

<script type="text/javascript">
	$(function() {
		$.addressList();
	});
</script>
