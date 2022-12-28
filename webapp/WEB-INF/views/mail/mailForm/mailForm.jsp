<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 3. 2.      윤한빈         최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<div class="main-mail-compose">
	<form action="${cPath}/mail/sendMail" method="post" enctype="multipart/form-data">
		<div class="container">
			<div class="main-mail-compose-box">
				<div class="main-mail-compose-header">
					<span>새 메일 작성하기</span>
					<nav class="nav">
						<a class="nav-link" href="">
							<i class="fas fa-minus"></i>
						</a>
						<a class="nav-link" href="">
							<i class="fas fa-compress"></i>
						</a>
						<a class="nav-link" href="">
							<i class="fas fa-times"></i>
						</a>
					</nav>
				</div>
				<div class="main-mail-compose-body">
					<div class="form-group mb-0">
						<label class="form-label">받는 사람</label>
						<select id="receiverId" name="receiverId" class="form-control text-black" multiple="multiple" style="width: 1000px; border: none;">
							<c:if test="${not empty memberList }">
								<c:forEach items="${memberList }" var="member">
									<option value="${member.memId}">${member.memName}(${member.memId})-${member.psName}/${member.deptName}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="form-group mb-0">
						<label class="form-label" id="inputContent">제목</label>
						<div>
							<input name="mailTitle" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<textarea id="editor4" name="mailContent" class="form-control" placeholder="" rows="12"></textarea>
					</div>
					<div class="form-group mg-b-0">
						<nav class="nav">
							<input type="file" class="multi form-control" id="mailFiles" name="mailFiles" />
						</nav>
						<button id="formSubmit" type="submit" class="btn ripple btn-primary">전송</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<script src="${cPath}/js/mail/mailForm.js"></script>

<script>
	$(document).on("click", "#inputContent", function(){
		$("input[name=mailTitle]").val("▶세금계산서 송부드립니다.◀");
		CKEDITOR.instances.editor4.setData("▶세금계산서 송부드립니다.◀");
	});
</script>