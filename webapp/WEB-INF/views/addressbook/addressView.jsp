<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 1. 28.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form>
	<input type="hidden" value="${address.adbkCode }" />
	<input type="hidden" value="${address.adbkType }" />
</form:form>
<h5><b>${address.adbkName }</b>님의 주소록</h5>
<hr>
<table class="table">
	<tr>
		<td>주소록번호</td>
		<td id="adbkCode">${address.adbkCode }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${address.adbkName }</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${address.adbkMail }</td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td>${address.adbkHp }</td>
	</tr>
	<tr>
		<td>회사주소</td>
		<td>${address.adbkAdd }</td>
	</tr>
	<tr>
		<td>홈페이지</td>
		<td>${address.adbkUrl }</td>
	</tr>
	<tr>
		<td>생년월일</td>
		<td>${address.adbkBirth }</td>
	</tr>
	<tr>
		<td>메모</td>
		<td>${address.adbkNote }</td>
	</tr>
</table>
