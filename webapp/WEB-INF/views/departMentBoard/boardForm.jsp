<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 3.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">부서게시판 글쓰기</h2>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="">커뮤니티</a></li>
			<li class="breadcrumb-item active" aria-current="page">부서게시판</li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<div class="row row-sm">
	<div class="col-lg-2"></div>
	<div class="col-lg-8">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<div>
					<form class="row g-3" method="post" enctype="multipart/form-data" id="boardForm">
						<input type="hidden" name="memId" value="${member.memId }" />
						<input type="hidden" name="deptCode" value="${member.deptCode }" />
						<input type="hidden" name="dbParent" value="${board.dbParent}" />
						<div class="col-md-12">
							<input type="text" class="form-control" name="dbTitle" placeholder="제목을 입력해주세요" value="${board.dbTitle}" />
						</div>
						<div class="col-12 mt-4">
							<textarea id="editor4" name="dbContent">
							${board.dbContent}
							</textarea>
						</div>
						<div class="col-12 mt-1">
							<input type="file" class="multi form-control" id="boardFiles" name="dbFiles" />

							<c:if test="${not empty board.attatchList }">
								<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
									<span title="다운로드" class="attatchSpan">
										<span class="delAtt" class="delAtt" data-att-no="${attatch.dbaNo }">x</span>
										<span class="MultiFile-title">${attatch.dbaRealname }</span>
									</span>
								</c:forEach>
							</c:if>
						</div>
						<div class="col-12 mt-1 d-flex justify-content-end">
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</form>
					<hr>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-2"></div>
</div>
<script type="text/javascript">
	CKEDITOR.replace("editor4",{
						filebrowserImageUploadUrl : '${cPath }/board/imageUpload?command=QuickUpload&type=Images'
					});
	$('#boardFiles').MultiFile({
		STRING : { //Multi-lingual support : 메시지 수정 가능
			duplicate : "$file 은 이미 선택된 파일입니다.",
			selected : '$file 을 선택했습니다.',
		}
	});

	let boardForm = $("#boardForm");
	$(".delAtt").on("click", function() {
		let att_no = $(this).data("attNo");
		boardForm.append($("<input>").attr({
			"type" : "hidden",
			"name" : "delAttNos"
		}).val(att_no));
		$(this).parent("span:first").hide();
	});
</script>