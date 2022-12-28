<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 19.      이운주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
	.slash {
	  background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg"><line x1="0" y1="100%" x2="100%" y2="0" stroke="gray" /></svg>');
	}
</style>
<div id="">
	<table class="table table-bordered p-0 m-0 text-center">
		<colgroup>
			<col style="width: 12.09%;">
			<col style="width: 87.91%;">
		</colgroup>
		<tbody>
			<tr>
				<th>결재</th>
				<td class="p-0" id="approvalTable">
					<table class="table">
						<colgroup>
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
							<col style="width: 12.5%;">
						</colgroup>
						<tbody id="approvalBody">
							
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<th>참조</th>
				<td class="">
					<div class="row " id="referenceBody">
					</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>

<script src="${cPath}/js/e_approvel/lines/approval.js?v=1"></script>
