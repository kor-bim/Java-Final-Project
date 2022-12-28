<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 10.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<style>
.btnForm {
	margin: 5px;
	float: right;
}

.button:focus {
	display: inline-block;
}

.on {
	border : 2px solid black;
}
</style>

<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />

<c:set var="action" />
<c:choose>
	<c:when test="${command != 'MODIFY' }">
		<c:url value="/schedule/calendarInsert.do" var="action" />
	</c:when>
	<c:otherwise>
		<c:url value="/schedule/calendarUpdate.do" var="action" />
	</c:otherwise>
</c:choose>

<form:form method="post" id="calendarForm" commandName="calendarVO" action="${action }">
	<input type="hidden" name="calNo" value="${calendarVO.calNo }" />
	<input type="hidden" name="memId" value="${member.memId }" />
	<input type="hidden" name="calCode" value="MYCAL" />
	<div class="row">
		<div class="col-4">
			<span>캘린더 이름</span>
		</div>
		<div class="col-8">
			<input type="text" name="calName" class="form-control" value="${calendarVO.calName }" placeholder="캘린더 이름을 입력하세요"/>
		</div>
	</div>
	<div class="row">
		<div class="col-4">
			<span>색깔</span>
		</div>
		<div class="col-8">
			<button type="button" class="label" value="c1">
				<span class="c1"></span>
			</button>
			<button type="button" class="label" value="c2">
				<span class="c2"></span>
			</button>
			<button type="button" class="label" value="c3">
				<span class="c3"></span>
			</button>
			<button type="button" class="label" value="c4">
				<span class="c4"></span>
			</button>
			<button type="button" class="label" value="c5">
				<span class="c5"></span>
			</button>
			<button type="button" class="label" value="c6">
				<span class="c6"></span>
			</button>
			<button type="button" class="label" value="c7">
				<span class="c7"></span>
			</button>
			<button type="button" class="label" value="c8">
				<span class="c8"></span>
			</button>
			<button type="button" class="label" value="c9">
				<span class="c9"></span>
			</button>
			<button type="button" class="label" value="c10">
				<span class="c10"></span>
			</button>
			<input type="hidden" id="calColor" name="calColor" value="" />
		</div>
	</div>
	<div class="btnForm">
		<c:if test="${command != 'MODIFY' }">
			<input type="submit" value="등록" class="btn btn-outline-primary" />
			<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		</c:if>
		<c:if test="${command == 'MODIFY' }">
			<input type="submit" value="수정" class="btn btn-outline-info" id="calendarModify" />
			<button type="button" class="btn btn-outline-warning" value="${calendarVO.calNo }" id="myCalendarDeleteBtn">삭제</button>
			<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		</c:if>
	</div>
</form:form>

<!-- 내 캘린더 삭제 modal-->
<div class="modal fade bd-example-modal-sm" id="myCalendarDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="calendarDeleteModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">캘린더를 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="calendarDelete" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var calcol = "${calendarVO.calColor }";
	$("#calendarForm button[value='"+calcol+"']").addClass(" on");
	$("#calColor").attr("value",calcol);
	

	$("button").on("click", function(){
		let val = $(this).val();
		$("#calColor").attr("value",val);
		if($("button").hasClass('on')){
			$("button").removeClass('on');
		}
		$(this).addClass("on");
	});
	
	// ================= 내 캘린더 삭제 modal ===========================================================
	myCalendarDelete = $("#myCalendarDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
	
	$("#myCalendarDeleteBtn").on("click", function(){
		let calNo = this.value;
		
		let message = '';
		
		$.ajax({
			url : $.getContextPath() + "/schdule/sharedCalendarCount/" + calNo,
			data : {calNo:calNo},
			dataType : "json",
			traditional : true,
			success : function(resp) {
				if(resp.cnt > 0){
					message = resp.message;
					new Noty({
						 text: message.text , 
						 layout: message.layout ,
						 type: message.type,
						 timeout: message.timeout ,
						 progressBar: true
					}).show();
				}else{
					myCalendarDelete.modal();
				}
			}
		});
		$("#calendarDelete").on("click", function(){
			location.href = $.getContextPath() + "/schedule/calendarDelete.do/" + calNo;
		});
	});
</script>

<script type="text/javascript">
	$("#calendarForm").validate({
		ignore : [], 
		rules : {
			calName : {
				required : true
				, minlength : 2
				, maxlength : 20
			}
			, calColor : {
				required : true
			}
			, calendarMember : {
				required : true
			}
		},
		messages : {
			calName : {
				required : "캘린더명은 필수 입력값입니다"
				, minlength : "2자이상 입력하세요"
				, maxlength : "20자 이하로 입력하세요"
			},
			calColor : {
				required : "색깔은 필수 선택값입니다."
			},
			calendarMember : {
				required : "필수 입력값입니다."
			}
		}
	});
</script>
