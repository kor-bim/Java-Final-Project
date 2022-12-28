<%--
* 커뮤니티 : 공지사항 목록 
*
* [[개정이력(Modification Information)]]
*   수정일                  수정자               수정내용
* -----------   ---------  -----------------
* 2021. 1. 25.   길영주                최초작성
* Copyright (c) 2021 by DDIT All right reserved
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- Page Header -->

<style>
	.txta{
		 
		text-align: center;
	}
	.alCenter{
		text-align: center;
	}
</style>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">공지사항</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">공지사항</li>
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
						<input type="button" id="NBInsertBtn" class="btn btn-primary" style="font-size:1.2rem;" value="글쓰기">
					</h5>
	            </div>
				<div class="table-responsive">
					<table id="noticeTable" class="table table-bordered border-t0 key-buttons text-nowrap w-100 table-hover">
						<thead>
							<tr>
<%-- 								<th><spring:message code="notice.rnum" /></th> --%>
								<th class="txta" style="font-size: 16px;"><spring:message code="notice.nb_no" /></th>
								<th class="txta" style="font-size: 16px;"><spring:message code="notice.nb_title" /></th>
								<th class="txta" style="font-size: 16px;"><spring:message code="notice.nb_date" /></th>
								<th class="txta" style="font-size: 16px;"><spring:message code="notice.mem_id" /></th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 상세조회  -->
<div class="modal fade" id="noticeViewModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="noticeViewModalLabel">
	<div class="modal-dialog" role="document" style="max-width: 100%; width: auto; display: table;">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="noticeViewModalLabel">공지사항 상세조회</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
			<div class="modal-footer">
				<c:url value="/noticeBoard/noticeUpdate.do" var="updateUrl">
					<c:param name="nbNo" value="${notice.nbNo }" />
				</c:url>
				<input type="button" value="수정" class="btn btn-outline-primary" id="updateBtn" />
				<button type="button" class="btn btn-outline-warning" id="removeBtn">삭제</button>
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>
<form id="noticeBoardDeleteForm" method="post" action="${cPath }/noticeBoard/noticeDelete.do">
	<input type="hidden" name="nbNo" value="${notice.nbNo }" /> <input type="hidden" name="nbPass" />
</form>





<!-- 공지사항 등록(Insert) -->
<div class="modal fade" id="noticeBoardInsertModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="noticeBoardInsertModalLabel">
	<div class="modal-dialog" role="document" style="max-width: 100%; width: auto; display: table;">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="noticeBoardInsertModalLabel"><span id="textArea"></span></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			</div>
		</div>
	</div>
</div>


<!-- 공지사항 수정(Update) -->
<div class="modal fade" id="noticeBoardUpdateModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="noticeBoardUpdateModalLabel">
	<div class="modal-dialog" role="document" style="max-width: 100%; width: auto; display: table;">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="noticeBoardUpdateModalLabel">공지사항 수정</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="noticeBoardDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="noticeBoardDeleteModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="noticeBoardDeleteModalLabel">공지사항 삭제</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td>삭제하시겠습니까?</td>
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


<script type="text/javascript">
	$(function() {
		$.noticeList();
	});
</script>
<script type="text/javascript">
	var memId = $
	{
		memId
	};
</script>
<script src="${cPath}/js/notice/notice.js"></script>