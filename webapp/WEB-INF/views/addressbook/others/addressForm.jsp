<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 1. 28.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="action" />
<c:choose>
   <c:when test="${command != 'MODIFY' }">
      <c:url value='/addressbook/insert.do' var="action"/>
   </c:when>
   <c:otherwise>
      <c:url value='/addressbook/update.do'  var="action"/>
   </c:otherwise>
</c:choose>

<form:form commandName="addressBookVO" id="addressForm" method="post" action="${action }">
	<form:hidden path="adbkCode" />
	<c:if test="${not empty memId }">
		<form:hidden path="memId" value="${memId }" />
	</c:if>
	<div class="flex-container">
		<div class="addtype-btn add__personal" data-url="private">
			<input type="radio" id="PRIVATE" name="adbkType" value="private" style="display:none">
			<label for="PRIVATE">개인주소록</label>
		</div>
		<div class="addtype-btn add__public" data-url="public">
			<input type="radio" id="PUBLIC" name="adbkType" value="public" style="display:none">
			<label for="PUBLIC">공유주소록</label>
		</div>
	</div>
	<table class="table">
		<tr>
			<td>이름</td>
			<td>
				<form:input path="adbkName" cssClass="form-control" placeholder="이름을 입력하세요" />
				<form:errors path="adbkName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
				<form:input path="adbkMail" cssClass="form-control" placeholder="이메일을 입력하세요" />
				<form:errors path="adbkMail" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
				<form:input path="adbkHp" cssClass="form-control" placeholder="000-0000-0000" />
				<form:errors path="adbkHp" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>회사주소</td>
			<td>
				<form:input path="adbkAdd" cssClass="form-control" placeholder="회사주소를 입력하세요" />
				<form:errors path="adbkAdd" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>홈페이지</td>
			<td>
				<form:input path="adbkUrl" cssClass="form-control" placeholder="홈페이지 주소를 입력하세요" />
				<form:errors path="adbkUrl" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td>
				<form:input path="adbkBirth" cssClass="form-control" placeholder="YYYY-MM-DD" />
				<form:errors path="adbkBirth" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>메모</td>
			<td>
				<form:input path="adbkNote" cssClass="form-control" placeholder="메모를 입력하세요" />
				<form:errors path="adbkNote" element="span" cssClass="error" />
			</td>
		</tr>
	</table>
	<div class="modal-footer">
		<input type="submit" class="btn btn-outline-primary" value="저장"/>
		<input type="button" class="btn btn-outline-secondary" value="취소" data-dismiss="modal"/>
	</div>
</form:form>

<script src="${cPath }/js/address/addressBtn.js"></script>

<script type="text/javascript">
	$("#addressForm").validate({
		rules :{
			adbkName : {
				required : true
				, minlength : 2
				, maxlength : 10
			}
			, adbkMail : {
				email : true
				, maxlength : 70
			}
			, adbkHp : {
				pattern : /^\d{3}-\d{3,4}-\d{4}$/
				, maxlength : 13
			}
			, adbkBirth : {
				date : true
			}
		},
		messages :{
			adbkName : {
				required : "필수 입력값입니다."
				, minlength : "2자이상 입력하세요."
				, maxlength : "10자리 이하로 입력하세요."
			}
			, adbkMail : {
				email : "유효한 이메일 주소를 입력하세요."
			}
			, adbkHp : {
				pattern : "유효한 전화번호를 입력하세요."
				, maxlength : "전화번호 자릿수를 확인하세요."
			}
			, adbkBirth : {
				date : "날짜 형식에 맞게 입력하세요." 
			}
		}
	});
</script>