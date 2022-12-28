<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 3. 2.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
#pagingArea {
	float: right;
}
.fileChk{
	width: 20px;
	height: 20px;
}
#fileFancy{
	float: right;
}

</style>

<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">삭제 파일보관함</h2>
		<ol class="breadcrumb">
		</ol>
	</div>
</div>

<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div class="card custom-card mb-3" style="display: inline;">
					<input class="btn btn-outline-primary" type="button" id="backUpFileBtn" value="복원" />
					<input type="button" class="btn btn-outline-secondary" value="완전삭제" id="realDeleteBtn" />
				</div>
				<br/>
				<br/>
				<div>
					<table class="table text-nowrap text-md-nowrap table-bordered mg-b-0 table-hover" id="fileBoxTable">
						<colgroup>
							<col width="4%">
							<col width="45%">
							<col width="17%">
							<col width="17%">
							<col width="17%">
						</colgroup>
						<thead class="text-center">
							<tr>
								<th>
									<input type="checkbox" class="fileChk" id="allFileChk"/>
								</th>
								<th>이름</th>
								<th>크기</th>
								<th>확장자</th>
								<th>삭제날짜</th>
							</tr>
						</thead>
						<tbody id="deleteFileListBody">
							<c:set var="deleteFileBoxList" value="${pagingVO.dataList }" />
							<c:if test="${not empty deleteFileBoxList }">
								<c:forEach items="${deleteFileBoxList }" var="file">
									<tr>
										<td class="text-center">
											<input type="checkbox" name="fileNo" class="fileChk" value="${file.fileNo }"/>
										</td>
										<td>
											<c:if test="${file.fileDir eq 'N' }">
												<span>${file.fileRealName }</span>
											</c:if>
											<c:if test="${file.fileDir eq 'Y' }">
												<a href="#">
													<i class="fa fa-folder-open" aria-hidden="true"></i>
													<span>${file.fileRealName }</span>
												</a>
											</c:if>
										</td>
										<td><span id="fileFancy">${file.fileFancy }</span></td>
										<td><span>${file.fileExtns }</span></td>
										<td class="text-center">${file.fileDate }</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty deleteFileBoxList }">
								<tr>
									<td colspan="5" class="text-center">파일이 존재하지않습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<form id="searchForm">
						<input type="hidden" name="page" />
					</form>
					<br/>
					<div id="pagingArea">${pagingVO.pagingHTML }</div>
				</div>	
			</div>
		</div>
	</div>
</div>

<!-- 파일 완전 삭제 확인  modal-->
<div class="modal fade bd-example-modal-sm" id="fileRealDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="fileRealDeleteModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">파일을 완전 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="fileDeleteBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#allFileChk").on("click", function(){
		if($("#allFileChk").prop("checked")){
			$("input[name='fileNo']").prop("checked", true);
		}else{
			$("input[name='fileNo']").prop("checked", false);
		}
	});
	
	$("#backUpFileBtn").on("click", function(){
		let deleteFileNo = [];
		$("input[name=fileNo]:checked").each(function(i){
			deleteFileNo.push($(this).val())
		});
		location.href = $.getContextPath() + "/fileBox/backUp/" + deleteFileNo;
	});
	
	let fileRealDeleteModal = $("#fileRealDeleteModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body");
	});
	
	$("#realDeleteBtn").on("click", function(){
		let deleteFileNo = [];
		$("input[name=fileNo]:checked").each(function(i){
			deleteFileNo.push($(this).val())
		});
		fileRealDeleteModal.modal();
		$("#fileDeleteBtn").on("click", function(){
			location.href = $.getContextPath() + "/fileBox/realDeleteFile/" + deleteFileNo;
		});
		
	});
</script>