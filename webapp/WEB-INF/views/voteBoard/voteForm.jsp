<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* ${date}      이운주     최초작성
* Copyright (c) ${year} by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="${cPath }/css/vote/vote.css" rel="stylesheet">
<style>
.answers, .questions {
	margin: 16px auto;
	padding: 0 16px;
	background-color: #fff
}

.questions {
	border: 1px solid #aaa;
	border-radius: 8px;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px
}

.answers {
	border: 1px solid #ccc;
	border-radius: 8px;
	-moz-border-radius: 8px;
	-webkit-border-radius: 8px
}

.getSelected {
	color: #000
}

.getUnselected {
	color: #aaa
}

.hidden {
	display: none
}

.mdl-layout__drawer {
	border-right: 0 !important
}

.divBody {
	width : 70%;
	margin : 0 auto;
}

.col-form-label{
	font-size: 1em;
}
</style>
<security:authentication property="principal" var="principal" />
<c:set var="authMember" value="${principal.realMember }" />

<!-- 오늘 날짜 -->
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate value="${now }" pattern="yyyy-MM-dd" var="today" />

<!-- =================================================================================== -->
<!-- Page Header -->
<c:set var="isMODIFY" value="${COMMAND =='MODIFY'}" />
<c:url value="/voteBoard/${isMODIFY ? 'update' : 'insert' }.do" var="commandURL" />
<form:form action="${commandURL }" commandName="voteFormVO" id="voteBoardForm">

	<form:hidden path="voteBoardVO.memId" value="${authMember.memId }" />
	<form:hidden path="voteBoardVO.vbNo" />
	<input type="hidden" id="command" value="${COMMAND }" />
	
	<div class="page-header">
		<div>
			<h2 class="main-content-title tx-24 mg-b-5">${isMODIFY ? "투표 수정하기" : "투표만들기" }</h2>
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="">커뮤니티</a>
				</li>
				<li class="breadcrumb-item active" aria-current="page">투표게시판</li>
			</ol>
		</div>
	</div>
	<!-- End Page Header -->

	<div class="card">
		<div class="divBody">
			<div class="card-body">
				<div class="row">
					<div class="row ml-1">
						<div class="col-3">
							<label class="col-form-label"><b>제목</b></label>
						</div>
						<div class="col-9">
							<form:input path="voteBoardVO.vbTitle" id="title" cssClass="form-control" placeholder="제목을 입력하세요" />
						</div>
						<div class="col-3 mt-3">
							<label class="col-form-label"><b>투표 종료일</b></label>
						</div>
						<div class="col-9 mt-3">
							<form:input type="date" path="voteBoardVO.vbEnd" cssClass="form-control" id="vbEnd" min="${today }" />
						</div>
						<div class="col-3 mt-3">
							<label class="col-form-label"><b>복수 응답 가능 여부</b></label>
						</div>
						<div class="col-9 mt-3">
							<div id="type_single" class="btn btn-outline-success ${voteFormVO.voteBoardVO.vbDup == 'N' ?'getSelected':'getUnselected' }" onclick="getSelect ('type_single');" style="height:15px;">
								<i class="fas fa-check"></i>&nbsp;하나만 선택가능
								<input type="radio" name="voteBoardVO.vbDup" id="singleBtn" class="" value="N" style="display:none;" />
							</div>
							<div id="type_multi" class="btn btn-outline-success ${voteFormVO.voteBoardVO.vbDup == 'Y' ?'getSelected':'getUnselected' }" onclick="getSelect ('type_multi');" style="height:15px;">
								<i class="fas fa-check-double"></i>&nbsp;복수응답 가능
								<input type="radio" name="voteBoardVO.vbDup" id="multiBtn" class="" value="Y"  style="display:none;" />
							</div>
						</div>
					</div>
					<div class="col-12 mt-5">
						<form:textarea path="voteBoardVO.vbContent" cssClass="form-control" rows="5" id="question" placeholder="질문을 여기에 작성해주세요" />
					</div>
				</div>
				<div id="answerDIV">
					<c:if test="${isMODIFY }">
						<c:forEach items="${voteFormVO.voteBoardVO.voteCateList}" var="vote">
							<c:set var="vcno" value="${vote.vcNo }"></c:set>
							<input type="hidden" name="voteCateVO.vcNos[${vcno-1}]" />
							<div class="answers answer">
								<div class="mdl-textfield mdl-js-textfield">
									<input class="mdl-textfield__input answerInput options" id="option${vcno}" name="voteCateVO.vcNames[${vcno-1}]" value="${vote.vcName }" />
									<label class="mdl-textfield__label" for="option${vcno}"></label>
								</div>
							</div>
						</c:forEach>
					</c:if>

				</div>
				<!-- end answerDIV -->
				<div class="answers addOption">
					<form:button class="mdl-button mdl-js-button mdl-js-ripple-effect" type="button" onclick="delOption()">- 선택지 줄이기</form:button>
					<form:button class="mdl-button mdl-js-button mdl-js-ripple-effect" type="button" onclick="addOption()">+ 선택지 추가</form:button>
				</div>
				<br />
				<div class="modal-footer">
					<form:button class="btn ripple btn-primary" type="button" id="voteInsertBtn">${isMODIFY?'수정하기': '등록하기' }</form:button>
					<form:button class="btn ripple btn-secondary returnBtn" type="button">뒤로가기</form:button>
					<form:button class="btn btn-secondary" type="reset" onclick="navigate2(0)">초기화</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>

<!-- =================================================================================== -->

<script src="${cPath}/js/vote/voteForm.js"></script>
<script type="text/javascript">
	$(".returnBtn").on("click", function() {
		location.href = $.getContextPath() + "/voteBoard";
	});
</script>
