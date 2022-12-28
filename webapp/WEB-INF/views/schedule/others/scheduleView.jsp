<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 9.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form commandName="scheduleVO">
	<form:hidden path="schdlNo"/>
</form:form>

<form:form commandName="calendarVO">
	<form:hidden path="calNo"/>
</form:form>

<table class="table">
	<tr>
		<td id="schdlNo"><input type="hidden" name="schdlNo" value="${scheduleVO.schdlNo }" /></td>
	</tr>
	<tr>
		<td>캘린더 분류</td>
		<td>${calendarVO.calCode }</td>
	</tr>
	<tr>
		<td>캘린더명</td>
		<td>${calendarVO.calName }</td>
	</tr>
	<tr>
		<td>일정명</td>
		<td>${scheduleVO.schdlName }</td>
	</tr>
	<tr>
		<td>시작일</td>
		<td>${scheduleVO.schdlBegin }</td>
	</tr>
	<tr>
		<td>종료일</td>
		<td>${scheduleVO.schdlEnd }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>${scheduleVO.schdlContent }</td>
	</tr>
</table>