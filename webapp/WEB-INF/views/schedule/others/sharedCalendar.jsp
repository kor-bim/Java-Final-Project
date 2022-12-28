<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 13.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

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
	margin-top: 20px;
	margin: 5px;
	float: right;
}

.button:focus {
	display: inline-block;
}

.on {
	border: 2px solid black;
}

.org {
	margin-left: 20px;
	margin-right: 20px;
	border: 1px solid black;
	width: 200px;
	height: 430px;
	overflow: -moz-scrollbars-horizontal;
	overflow-x: auto;
	overflow-y: auto;
}

.list {
	border: 1px solid black;
	width: 200px;
	height: 430px;
	overflow: -moz-scrollbars-horizontal;
	overflow-x: auto;
	overflow-y: auto;
}

.addAuto {
	border: 1px solid black;
	height: 176px;
	width: 150px;
}

.readAuto {
	border: 1px solid black;
	height: 400px;
	width: 150px;
}

select[multiple]{ 
 	border : none; 
 	overflow : hidden; 
 	height: 400px; 
 }
</style>

<security:authentication property="principal" var="principal" />
<c:set var="member" value="${principal.realMember }" />

<c:set var="action" />
<c:choose>
	<c:when test="${command != 'MODIFY' }">
		<c:url value="/calendar/sharedCalendarInsert.do" var="action" />
	</c:when>
	<c:otherwise>
		<c:url value="/calendar/sharedCalendarUpdate.do" var="action" />
	</c:otherwise>
</c:choose>

<form:form method="post" id="shareCalendarForm" commandName="calendarVO" action="${action }">
	<input type="hidden" name="calNo" value="${calendarVO.calNo }" />
	<input type="hidden" name="memId" value="${member.memId }" />
	<input type="hidden" name="calCode" value="SHARECAL" />
	<div class="row">
		<div class="col-3">
			<span>캘린더 이름</span>
		</div>
		<div class="col-9">
			<input type="text" name="calName" class="form-control" value="${calendarVO.calName }" placeholder="캘린더 이름을 입력하세요"/>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-3">
			<span>색깔</span>
		</div>
		<div class="col-9">
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
	<div style="margin-top: 20px;">
		<h6>공유대상</h6>
	</div>
		<div class="row">
		<div class="org col-3" id="deptTreeDIV"></div>
		<div class="list col-4" id="listBody">
			<c:set value="${deptMemberList }" var="deptMemberList" />
			<c:if test="${not empty deptMemberList}">
				<select multiple="multiple" class="form-control" style="height:1000px;" id="multi_d">
					<c:forEach items="${deptMemberList }" var="dept">
						<option value="${dept.memId }">${dept.memName }&lt;${dept.deptName }&gt;</option>
					</c:forEach>
				</select>
			</c:if>
		</div>
		<div class="col-2">
			<br/><br/><br/><br/><br/><br/><br/><br/>
	        <button type="button" id="multi_d_rightSelected" class="btn btn-light btn-block"><i class="fas fa-angle-right"></i></button>
	        <button type="button" id="multi_d_leftSelected" class="btn btn-light btn-block"><i class="fas fa-angle-left"></i></button>
		</div>
		<div>
		</div>
		<div>
			<b>구성원 초대</b>
			<div class="readAuto">
				<select name="calendarMember" id="multi_d_to" class="form-control" multiple="multiple" style="height:300px;">
					<c:if test="${not empty option }">
						<c:if test="${command eq 'MODIFY' }">
							<c:forEach items="${option.memberList }" var="opt">
								<option value="${opt.memId }">${opt.memName }&lt;${opt.deptName }&gt;</option>
							</c:forEach>
						</c:if>
					</c:if>
				</select>
			</div>
		</div>
	</div>
	<div class="btnForm">
		<c:if test="${command != 'MODIFY' }">
			<input type="submit" value="등록" id="insertBtn" class="btn btn-outline-primary"/>
			<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
		</c:if>
		<c:if test="${command == 'MODIFY' }">
			<input type="submit" value="수정" class="btn btn-outline-info" id="calendarModify" />
			<button type="button" class="btn btn-outline-warning" value="${calendarVO.calNo }" id="sharedCalendarDeleteBtn">삭제</button>
			<button type="button" class="btn btn-outline-secondary" id="modalCancleBtn" data-dismiss="modal">닫기</button>
		</c:if>
	</div>
</form:form>

<!-- 공유 캘린더 삭제 modal-->
<div class="modal fade bd-example-modal-sm" id="sharedCalendarDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="sharedCalendarDeleteModalLabel">
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
						<td align="center">공유 캘린더를 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="calendarDeleteBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
jQuery(document).ready(function($) {
    $('#multi_d').multiselect({
		right: '#multi_d_to, #multi_d_to_2',
		rightSelected: '#multi_d_rightSelected, #multi_d_rightSelected_2',
		leftSelected: '#multi_d_leftSelected, #multi_d_leftSelected_2',
 
        moveToRight: function(Multiselect, $options, event, silent) {
            var button = $(event.currentTarget).attr('id');
 
            if (button == 'multi_d_rightSelected') {
                var $left_options = Multiselect.$left.find('> option:selected');
                Multiselect.$right.eq(0).append($left_options);
 
                if ( typeof Multiselect.callbacks.sort == 'function' && !silent ) {
                    Multiselect.$right.eq(0).find('> option').sort(Multiselect.callbacks.sort).appendTo(Multiselect.$right.eq(0));
                }
            } else if (button == 'multi_d_rightSelected_2') {
                var $left_options = Multiselect.$left.find('> option:selected');
                Multiselect.$right.eq(1).append($left_options);
 
                if ( typeof Multiselect.callbacks.sort == 'function' && !silent ) {
                    Multiselect.$right.eq(1).find('> option').sort(Multiselect.callbacks.sort).appendTo(Multiselect.$right.eq(1));
                }
            }
        },
 		
        moveToLeft: function(Multiselect, $options, event, silent, skipStack) {
            var button = $(event.currentTarget).attr('id');
 		
            if (button == 'multi_d_leftSelected') {
                var $right_options = Multiselect.$right.eq(0).find('> option:selected');
                Multiselect.$left.append($right_options);
 
                if ( typeof Multiselect.callbacks.sort == 'function' && !silent ) {
                    Multiselect.$left.find('> option').sort(Multiselect.callbacks.sort).appendTo(Multiselect.$left);
                }
            } else if (button == 'multi_d_leftSelected_2') {
                var $right_options = Multiselect.$right.eq(1).find('> option:selected');
                Multiselect.$left.append($right_options);
 
                if ( typeof Multiselect.callbacks.sort == 'function' && !silent ) {
                    Multiselect.$left.find('> option').sort(Multiselect.callbacks.sort).appendTo(Multiselect.$left);
                }
            }
        }
    });
    $("#multi_d").multiselect("clearSelection");
    $("#multi_d").multiselect("refresh");
});
</script>

<script type="text/javascript">
	var calcol = "${calendarVO.calColor }";
	$("#shareCalendarForm button[value='"+calcol+"']").addClass(" on");
	$("#calColor").attr("value",calcol);

	$(".label").on("click", function(){
		let val = $(this).val();
		$("#calColor").attr("value",val);
		if($("button").hasClass('on')){
			$("button").removeClass('on');
		}
		$(this).addClass("on");
	});
	
	$("#insertBtn").on("click", function(){
		let opts = $("#multi_d_to").find("option");
		opts.each(function(idx, opt){
			opt.selected = true;
		})
	});
	$("#calendarModify").on("click", function(){
		let opts = $("#multi_d_to").find("option");
		opts.each(function(idx, opt){
			opt.selected = true;
		})
	});
	
	// ================= 공유 캘린더 삭제 modal ===========================================================
	sharedCalendarDelete = $("#sharedCalendarDeleteModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
	
	$("#sharedCalendarDeleteBtn").on("click", function(){
		let calNo = this.value;
		$.ajax({
			url : $.getContextPath() + "/schdule/sharedCalendarCount/" + calNo
			, data : {calNo:calNo}
			, dataType : "json"
			, success : function(resp){
				if(resp.cnt > 0){
					let message = resp.message;
					new Noty({
						 text: message.text, 
						 layout: message.layout,
						 type: message.type,
						 timeout: message.timeout,
						 progressBar: true
					}).show();
				} else {
					sharedCalendarDelete.modal();
				}
			}
		});
		$("#calendarDeleteBtn").on("click", function(){
			location.href = $.getContextPath() + "/schedule/sharedCalendarDelete.do/" + calNo;
		});
	});
	$("#deptTreeDIV").load($.getContextPath() + "/ztree/dept");
	$("#shareCalendarForm").validate({
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
				required : "캘린더명은 필수입력값입니다"
				, minlength : "2자이상 입력하세요"
				, maxlength : "20자 이하로 입력하세요"
			},
			calColor : {
				required : "색깔은 필수 선택값입니다."
			},
			calendarMember : {
				required : "구성원 초대는 필수 선택값입니다."
			}
		}
	});

	// 부서 이름 클릭 이벤트 
	$(document).on("click", ".node_name", function(){
	    let deptName = $(this).text();
	    let deptCode = $(this).attr("value");
		
		$.ajax({
			url : $.getContextPath() + "/calendar/deptMemberList.do/" + deptName,
			data : {deptName : deptName},
			method : "post",
			dataType : "json",
			success : function(resp) {
				let selectTags = [];
				if(resp.deptMemberList.length > 0){
					let options = [];
					$(resp.deptMemberList).each(function(idx, dept){
						options.push(
							$("<option>").attr("value", dept.memId).text(dept.memName + "<" + dept.deptName + ">")
						)
					});
					selectTags.push(
						$("<select>").attr("multiple", "multiple").attr("id", "multi_d").attr("class", "form-control").css("heigth", "400px").append(options)
					)
				}
				$("#listBody").html(selectTags);
				$('#multi_d').multiselect('refresh');
			}
		});
	})

</script>


