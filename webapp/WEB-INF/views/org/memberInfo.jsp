<%--
* 사용자 관리 페이지 : 구성원 리스트 
*
* [[개정이력(Modification Information)]]
*   수정일                  수정자               수정내용
* -----------   ---------  -----------------
* 2021. 1. 28.   이재형                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script src="/Forest/WEB-INF/views/org/jorgtree.js" type="text/javascript"></script> -->
<!-- <script src="/Forest/WEB-INF/views/org/insa_myinfo.js" type="text/javascript"></script> -->
<script type="text/javascript" src="${cPath}/js/org/jusoPopup.js"></script>
<input type="hidden" value="${pageContext.request.contextPath }" id="contextPath" name="contextPath">
<script type="text/javascript" src="${cPath}/plugins/jquery-validation-1.19.2/jquery.validate.min.js"></script>
<script type="text/javascript" src="${cPath}/plugins/jquery-validation-1.19.2/additional-methods.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var str = "${memFormVO.deptLine.line}";
		var deptLine;
		if("${memFormVO.deptLine.deptLevel}" == 1){
			deptLine = str.substr(2, str.length);
		}else{
			deptLine = str.substr(10, str.length);
		}
		
		$("#deptLine").append(deptLine);
	});
</script>



<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">내 정보 관리</h2>
	</div>
</div>
<!-- End Page Header -->
<!--Row-->
<div class="row row-sm">
	<div class="col-lg-12 col-md-12">
		<div class="card custom-card">
			<div class="card-body mg-l-50">
				<%-- 				<form:form modelAttribute="MemFormVO"> --%>
				<%-- 					<form:hidden path="" value=""/> --%>
				<%-- 					<form:input path="jobVO" value="${memFormVO.memberVO.memId}"/> --%>
				<!-- 					<input type="submit" value="전송"/>				 -->
				<%-- 				</form:form> --%>
				<form:form modelAttribute="memFormVO" action="${cPath}/org/updateMember" class="row g-3" name="form" id="form">
					<div class="col-md-4">
						<label for="inputName" class="form-label">이름</label>
						<span>${memFormVO.memberVO.memName }</span>
						<form:hidden path="memberVO.memId" id="memId" name="memId" />
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="inputMemId" class="form-label">사번</label>
						<span>${memFormVO.memberVO.memId}</span>
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="" class="form-label">소속</label>
						<p id="deptLine"></p>
					</div>
					<div class="col-12 mt-4">
						<label for="inputPosition" class="form-label">직위</label>
						<span>${memFormVO.memberVO.psName}</span>
					</div>
					<div class="col-md-4 mt-4" data-select2-id="72">
						<label for="memberVO.jobCode" class="form-label">직무</label>
						<form:select path="memberVO.jobCode" cssClass="form-control">
							<form:options items="${memFormVO.jobVOList }" itemLabel="jobName" itemValue="jobCode" />
						</form:select>
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="memComtel" class="form-label">사내전화번호</label>
						<form:input path="memberVO.memComtel" cssClass="form-control" id="memComtel" name="memComtel" />
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="memHp" class="form-label">휴대전화번호</label>
						<form:input path="memberVO.memHp" cssClass="form-control" />
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="inputEmail" class="form-label">사내 이메일</label>
						<span id="inputEmail">${memFormVO.memberVO.memId}@ddit.or.kr</span>
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="memMail" class="form-label">개인 이메일</label>
<%-- 						<span id="inputEmail">${memFormVO.memberVO.memMail}</span> --%>
						<form:input path="memberVO.memMail" cssClass="form-control" id="memMail" />
					</div>
					<div class="col-md-6"></div>
					<div class="col-md-4 mt-4">
						<label for="inputHiredDate" class="form-label">입사일</label>
						<span id="inputHiredDate">${memFormVO.memberVO.memHdate }</span>
					</div>
					<div class="col-md-6"></div>

					<div class="col-md-4 mt-4">
						<label for="memBirth" class="form-label">생년월일</label>
						<form:input path="memberVO.memBirth" cssClass="form-control" id="memBirth" placeholder="생년월일을 입력하세요. ex)19920518" />
					</div>
					<div class="col-md-12"></div>
					<div class="col-12 mt-4">
						<label for="inputZipcode" class="form-label">자택 주소</label>
					</div>
					<div class="col-sm-1">
						<label for="btnZipcode" class="form-label">우편번호</label>
<%-- 						<input type="text" class="form-control" id="memZip" name="memberVO.memZip" value="${memFormVO.memberVO.memZip }" readonly> --%>
						<form:input path="memberVO.memZip" cssClass="form-control" id="memZip" readonly="true"/>
						<input id="btnZipcode" type="button" class="btn ripple btn-light" value="우편번호 검색" onClick="goPopup();">
					</div>
					<div class="col-md-1 mt-4">
					</div>
					<div class="col-md-12"></div>
					<div class="col-md-8 mt-2">
						<label for="btnZipcode" class="form-label">주소</label>
<%-- 						<input type="text" class="form-control" id="memAdd" name="memberVO.memAdd" value="${memFormVO.memberVO.memAdd }" readonly> --%>
						<form:input path="memberVO.memAdd" cssClass="form-control" id="memAdd" readonly="true"/>
					</div>
					<div class="col-md-8 mt-4">
						<label for="memEtc" class="form-label">기타 정보</label>
						<form:textarea path="memberVO.memEtc" cssClass="form-control" rows="5" cols="144" id="memEtc" />
					</div>
					<div class="col-12 mt-4">
						<button type="submit" class="btn btn-primary">저장</button>
						<button class="btn btn-outline-danger" type="reset">취소</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
$(function(){
	$("#form").validate({
		rules :{
			memComtel : {
				pattern : /^\d{2,3}\d{3,4}\d{4}$/
				, maxlength : 11 
			}
			, memHpd : {
				pattern : /^\d{2,3}\d{3,4}\d{4}$/
				, maxlength : 11 
			}
			, memBirth : {
				dateISO : true
				, maxlength : 8
			}
			, memMail : {
				email : true
				, maxlength : 70
			}
		},
		messages :{
			memComtel : {
				pattern : "-를 빼고 입력해주세요."
				, maxlength : "-를 빼고 입력해주세요."
			}
			, memHpd : {
				pattern : "-를 빼고 입력해주세요.",
				maxlength : "-를 빼고 입력해주세요." 
			}
			, memBirth : {
				dateISO : "-없이 숫자만 입력해주세요.",
				maxlength : "숫자만 8자 입력해주세요. ex)20210208"
			}
			, memMail : {
				email : "이메일 형식으로 입력해주세요."
			}
		}
	});
});
</script>
