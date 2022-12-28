<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* ${date}      이운주     최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://egovframework.gov/ctl/ui" prefix="ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">투표게시판</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">투표게시판</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div class="justify-content-center">
					<h5>
						<input type="button" id="voteFormModalBtn" class="btn btn-primary" style="font-size:1.2rem;"value="글쓰기">
					</h5>
	            </div>
				<br>
				<div class="table-responsive">
<%-- 					<form:form commandName="voteFormVO" id="listForm" name="listForm" method="post"> --%>
					<table id="voteListTable" class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover">
						<thead>
							<tr class="dtCenter">
								<th><spring:message code="vote.vb_no"/></th>
								<th><spring:message code="vote.vb_title"/></th>
								<th><spring:message code="vote.vb_writer"/></th>
								<th><spring:message code="vote.vb_date"/></th>
								<th><spring:message code="vote.vb_end"/></th>
								<th><spring:message code="vote.vb_status"/></th>
							</tr>
						</thead>
						<tbody id="listBody">
							
						</tbody>
					</table>
<%-- 					</form:form> --%>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<!-- vote 게시글 등록하기 위한 modal FORM  -->
<!-- <div class="modal" id="voteFormModal"> -->
<!-- 	<div class="modal-dialog modal-dialog-scrollable" role="document"> -->
<!-- 	    <div class="modal-content modal-content-demo" id="modal-content"> -->
	        
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<script src="${cPath}/js/vote/vote.js"></script>

<script type="text/javascript">
$(function(){
	// 목록 조회
	$.voteList();
	
}) // doc ready end 

</script>
