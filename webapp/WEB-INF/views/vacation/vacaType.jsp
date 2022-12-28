<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 8.      이운주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link href="${cPath}/css/vacation/vacation.css" rel="stylesheet">

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">휴가 종류 관리</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">휴가관리</a></li>
			<li class="breadcrumb-item active" aria-current="page">휴가 종류 관리</li>
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
					<div class="justify-content-center">
	            	<span role="button" id="vacaTypeFormBtn" style="color:#1b9783">휴가종류 추가+</span>
	            </div>
					<br>
				</div>
				<div class="table-responsive">
					<form:form path="vacaTypeVO" id="vacaTypeForm" method="post" action="${cPath}/admin/vacatype/insert.do">
						<table id="vacaTypeTable"
							class="table table-bordered border-t0 key-buttons text-nowrap w-100">
							<thead>
								<tr>
									<th>휴가명</th>
									<th>차감여부</th>
								</tr>
							</thead>
							<tbody id="listBody">
								
							</tbody>
						</table>
						<div class="justify-content-center">
							<input class="btn ripple btn-primary" id="vacaTypeSaveBtn" type="submit" value="저장"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<form id="searchForm" action="${cPath}/voteBoard/reply" method="get">
	<input type="hidden" name="vbNo" value="${voteBoardVO.vbNo }" />
	<input type="hidden" name="page" />
</form>



<script src="${cPath}/js/vacation/vacaType.js"></script>
<script type="text/javascript">
	getListAjax();
</script>
