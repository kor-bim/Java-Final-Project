<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 8.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="action" />
<c:choose>
	<c:when test="${command ne 'MODIFY' }">
		<c:url value="/schedule/insert.do" var="action" />
	</c:when>
	<c:otherwise>
		<c:url value="/schedule/update.do" var="action" />
	</c:otherwise>
</c:choose>

<form:form commandName="scheduleVO" id="scheduleForm" method="post" action="${action }">
	<form:hidden path="schdlNo"/>
	<form:hidden path="memId" />
	<table class="table">
		<tr>
			<td>캘린더명</td>
			<td>
				<form:select path="calNo"  cssClass="form-control">
					<form:options items="${calendarTypeList }" itemLabel="calName" itemValue="calNo"/>
				</form:select>
			</td>
		</tr>
		<tr>
			<td>일정명</td>
			<td>
				<form:input path="schdlName" cssClass="form-control" placeholder="일정명을 입력하세요" />
				<form:errors path="schdlName" element="span" cssClass="error" />
			</td>
		</tr>
		<tr>
			<td>시작일</td>
			<td>
				<div>
					<form:input path="schdlBegin" type="date" cssClass="form-control" id="dateStart" onchange="$('#dateEnd').attr('min', this.value)" />
					<form:errors path="schdlBegin" element="span" cssClass="error" />
				</div>
			</td>
		</tr>
		<tr>
			<td>종료일</td>
			<td>
				<div>
					<form:input path="schdlEnd" type="date" cssClass="form-control" id="dateEnd" />
					<form:errors path="schdlEnd" element="span" cssClass="error" />
				</div>
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<form:textarea path="schdlContent" cols="55" rows="10" />
				<form:errors path="schdlContent" element="span" cssClass="error" />
			</td>
		</tr>
	</table>
	<div class="modal-footer">
		<c:if test="${command ne 'MODIFY' }">
			<input type="submit" value="등록" class="btn btn-outline-primary" />
			<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		</c:if>
		<c:if test="${command eq 'MODIFY' }">
			<input type="submit" value="수정" class="btn btn-outline-primary" />
			<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		</c:if>
	</div>
</form:form>

<script type="text/javascript">
	$(function(){
		$("#scheduleForm").validate({
			rules : {
				schdlName : {
					required : true
					, minlength : 2
					, maxlength : 100
				}
				, schdlBegin : {
					required : true
				}
				, schdlEnd : {
					required : true
				}
			},
			messages : {
				schdlName : {
					required : "일정명은 필수 입력값입니다"
					, minlength : "2자이상 입력하세요"
					, maxlength : "100자 이하로 입력하세요"
				},
				schdlBegin : {
					required : "시작일은 필수 선택값입니다."
				},
				schdlEnd : {
					required : "종료일은 필수 선택값입니다."
				}
			}
		});
	})
</script>