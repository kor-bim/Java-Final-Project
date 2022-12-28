<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 18.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
  
<div>
    <div>
    	<div>
    		<form:form commandName="documentsVO" action="${cPath }/admin/approvalFarmForm">
	    		<div class="row">
					<div class="form-check col-6">
						<p>
							<input class="form-check-input" type="radio" name="alCode" id="APPROVAL" value="결재" checked>
							<label class="form-check-label" for="approval"> 결재 </label>
						</p>
						<img src="${cPath }/images/approval/approval.png" alt="결재 포맷">
					</div>
					<div class="form-check col-6">
						<p>
							<input class="form-check-input" type="radio" name="alCode" id="APPLY" value="신청">
							<label class="form-check-label" for="apply"> 신청 </label>
						<p>
						<img src="${cPath }/images/approval/apply.png" alt="신청 포맷">
					</div>
	    		</div>
	    		<div class="row">
					<div class="form-check col-6">
						<p>
							<input class="form-check-input" type="radio" name="alCode" id="TRANSMIT" value="결재 후 송신">
							<label class="form-check-label" for="transmit"> 결재 후 송신 </label>
						<p>
						<img src="${cPath }/images/approval/transmit.png" alt="결재 후 송신">
					</div>
					<div class="form-check col-6">
						<p>
							<input class="form-check-input" type="radio" name="alCode" id="PASSALONG" value="회람">
							<label class="form-check-label" for="passAlong"> 회람 </label>
						<p>
						<img src="${cPath }/images/approval/passAlong.png" alt="회람">
					</div>
	    		</div>
    		</form:form>
		</div>
	</div>
</div>