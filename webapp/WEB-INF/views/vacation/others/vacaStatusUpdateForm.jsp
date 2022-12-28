<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 17.      이운주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form commandName="vacaStatusVO" id="vacaStatUpdateForm" method="post" action="${cPath }/admin/vacaStat/update.do">
	<form:hidden path="memId"/>
	<form:hidden path="searchYear"/>

	<div class="title_layer text_variables">직원 휴가일 수정</div>
	<p>
		<b>${vacaStat.memName }(${vacaStat.memId }) 휴가 생성 내역  [${vacaStat.vacaYear }년]</b>
	</p>
	<p class="gt-pv-10">입사일: ${vacaStat.memHdate }</p>
	<p class="pdt_10">※ 정기 연차와 포상 휴가 일수는 ‘정수’ 또는 ‘0.5’ 단위로 입력할 수 있습니다.</p>
	<div class="pdt_30">
		<table class="table table-bordered border-t0 key-buttons text-nowrap w-100 text-center">
			<colgroup>
				<col>
				<col>
				<col>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">종류</th>
					<th scope="col">현재</th>
					<th scope="col">변경 후</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${vacaStat.vacaStatList }" var="vaca">
					<c:if test="${vaca.vtCode eq 1 }">
						<c:set var="regularVaca" value="${vaca.vacaInday }"/>
					</c:if>
					<c:if test="${vaca.vtCode eq 2 }">
						<c:set var="rewardVaca" value="${vaca.vacaInday }"/>
					</c:if>
				</c:forEach>
				<tr>
					<td>정기 연차</td>
					<td>${not empty regularVaca ? regularVaca : '0' }</td>
					<td>
						<input type="hidden" name="vacaStatList[0].vtCode" value="1"/>
						<input type="text"   name="vacaStatList[0].vacaInday" class="hw-input" id="modify_regular" placeholder="일수" style="width:50px" value="${not empty regularVaca ? regularVaca : '0' }"/>
					</td>
				</tr>
				<tr>
					<td>포상</td>
					<td>${not empty rewardVaca ?rewardVaca:'0' }</td>
					<td>
						<input type="hidden" name="vacaStatList[1].vtCode" value="2"/>
						<input type="text"   name="vacaStatList[1].vacaInday"  class="hw-input" id="modify_reward" placeholder="일수" style="width:50px" value="${not empty rewardVaca ?rewardVaca:'0' }">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</form:form>

