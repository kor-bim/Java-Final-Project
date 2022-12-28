<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 1. 30.     길영주      최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="action" />
<c:choose>
	<c:when test="${command != MODIFY }">
		<c:url value='/noticeBoard/noticeUpdate.do' var="action" />
	</c:when>
	<c:otherwise>
		<c:url value='/noticeBoard/noticeInsert.do' var="action" />
	</c:otherwise>
</c:choose>
<form:form commandName="noticeVO" modelAttribute="noticeVO" id="noticeForm" method="post" enctype="multipart/form-data" action="${action }">
<%-- 	<input type="hidden" class="form-control" name="nbNo" value="${noticeVO.nbNo }" /> --%>
	<form:hidden path="nbNo"/>
	<table class="table table-bordered">
		<tr>
			<td class="text-center">제목</td>
			<td class="pb-1">
				<form:input path="nbTitle" cssClass="form-control" />
			</td>
		</tr>
		<tr>
			<td class="text-center">작성자</td>
			<td class="pb-1">
				<form:input type="text" path="memName" class="form-control" readonly="true" />
				<form:hidden path="memId"/>
			</td>
		</tr>
		<tr>
			<th class="text-center">첨부파일</th>
			<td class="pb-1" id="fileArea">
				<div>
					<c:if test="${not empty noticeVO.nbAttatchList }">
						<c:forEach items="${noticeVO.nbAttatchList }" var="attatch" varStatus="vs">
							<span class="nbAttatchSpan">
								<img src="${cPath }/images/pngs/delete2.png" style="width: 20px; height: 20px;" class="delAtt" data-att-no="${attatch.nbaNo }" />
								${attatch.nbaName } &nbsp; ${not vs.last?"|":"" }
							</span>
						</c:forEach>		
					</c:if>
				</div>
				<div class="input-group">
					<input type="file" class="form-control" name="nbFiles" />
					<span class="btn btn-primary plusBtn">+</span>
				</div>
				<span class="error">${errors.nbFiles }</span>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td class="text-center">첨부파일</td> -->
<!-- 			<td class="pb-1" id="fileArea"> -->
<!-- 				<div> -->
<%-- 					<c:if test="${not empty noticeVO.nbAttatchList }"> --%>
<%-- 						<c:forEach items="${noticeVO.nbAttatchList }" var="nbAttatch" varStatus="vs"> --%>
<%-- 							<span class="nbAttatchSpan"> <img src="${cPath }/images/delete.png" class="delAtt" data-att-no="${nbAttatch.nbaNo }" /> ${nbAttatch.realFile } &nbsp; ${not vs.last?"|":"" } --%>
<!-- 							</span> -->
<%-- 						</c:forEach> --%>
<%-- 					</c:if> --%>
<!-- 				</div> -->
<!-- 				<div class="input-group"> -->
<%-- 					<form:input type="file" cssClass="form-control" path="nbFiles" /> --%>
<!-- 					<span class="btn btn-primary plusBtn">+</span> -->
<%-- 					<form:errors path="nbFiles" element="span" cssClass="error"/> --%>
<!-- 				</div> -->
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td class="text-center">내용</td>
			<td class="pb-1">
				<form:textarea path="nbContent" id="nbContent" cssClass="form-control" />
				<form:errors path="nbContent" element="span" cssClass="error"/>
				
			</td>
		</tr>
	</table>
	<div align="center" class="modal-footer">
		<input type="submit" class="btn btn-outline-primary" value="저장"/>
		<input type="button" class="btn btn-outline-secondary" value="취소" data-dismiss="modal"/>
	</div>
</form:form>
<script type="text/javascript">
	
	CKEDITOR
			.replace(
					"nbContent",
					{
						filebrowserImageUploadUrl : '${cPath }/board/imageUpload.do?command=QuickUpload&type=Images'
					});

	$("#fileArea").on("click", ".plusBtn", function() {
		let clickDiv = $(this).parents("div.input-group");
		let newDiv = clickDiv.clone();
		let fileTag = newDiv.find("input[type='file']");
		fileTag.val("");
		clickDiv.after(newDiv);
	});

	let nbBoardForm = $("#noticeForm");
	$(".delAtt").on("click", function() {
		let nbAttNo = $(this).data("attNo");
		nbBoardForm.append($("<input>").attr({
			"type" : "hidden",
			"name" : "delNbAttNos"
		}).val(nbAttNo));
		$(this).parent("span:first").hide();
		
	});
	$("#noticeForm").validate({
		rules :{
			nbTitle : {
				required : true
				, minlength : 2
				, maxlength : 100
			}
			, nbContent : {
				required : true
			}
		},
		message :{
			nbTitle : {
				required : "필수 입력값입니다."
				, minlength : "2자이상 입력하세요."
				, maxlength : "50자 이하로 입력하세요."
			}
			, nbContent :{
				required : "필수 입력값입니다."
			}
		}
	})
	function save() {
		alert("등록되었습니다.")
	}
</script>
