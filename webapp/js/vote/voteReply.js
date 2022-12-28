/**
 * <pre>
 * 투표게시판댓글관련 js 
 * 
 * </pre>
 * 
 * @author 이운주
 * @since 2021. 2. 5.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.    이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

let options = {
	dataType : "json",
	success : function(resp) {
		if (resp.result == "OK") {
			replyInsertForm.get(0).reset();
			searchForm.submit();
		} else if (resp.message) {
			alert(resp.message);
		}
	}
}

let repList = $("#reply-list");
let pagingArea = $("#pagingArea");
let pagingA = pagingArea.on('click', "a", function() {
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	searchForm.find("[name='page']").val(1);
	return false;
});

let searchForm = $("#searchForm").ajaxForm({
	dataType : "json",
	success : function(resp) {
		repList.empty();
		pagingArea.empty();
		let replyList = resp.dataList;
		let divTags = [];
		if(replyList){
			$(replyList).each(function(idx, reply){
				let div = $("<div>").attr("id","replyDIV");
				let memImg = reply.memImg;
				
				// 내가 쓴 댓글이 아닌 경우 수정,삭제버튼 숨김
				let myReply = "";
				if(reply.memId != myId){
					myReply = "none";
				}else{
					myReply = "visible";
				}
				
				div.append(
						$("<div>").addClass("row mb-2 orgnReply ").attr("id", "replyDIV"+reply.vbrNo).append(  
								 $("<div>").addClass("col-10 pl-0").append(
										$("<div>").append($("<b>").text(reply.name+"/"+reply.deptName))
										, $("<div>").text(reply.vbrContent)
										, $("<div style='color: grey'>").addClass("orgnContent").append(
												$("<span>").text((reply.vbrDate).substring(0,(reply.vbrDate).length-2))
										)
								)
								// 수정, 삭제 버튼
								, $("<div>").css("text-align", "right").css("display", myReply).append(
										$("<input>").addClass("btn ripple btn-warning replyUpdateFormBtn")
													.attr({
														type : "button"
														, value : "수정"
													})
										, $("<input>").addClass("btn ripple btn-danger ml-1 replyDeleteBtn")
													.attr({
														type : "button"
														, value : "삭제"
													}).data("reply", reply)
								)
						)
						// 수정 form 
						, $("<div>").addClass("row mb-2 replyUpdateDIV").attr("id", "replyUpdateDIV"+reply.vbrNo).append(
								$("<div>").addClass("col-10 pl-0").append(
										$("<div>").append($("<b>").text(reply.name+"/"+reply.deptName))
								)
								, $("<form>").addClass("col-12 replyUpdateForm")
											.attr({action : contextPath + "/voteBoard/reply/update.do" , method : "POST"})
											.append(
										$("<input>").attr({type : "hidden" , name : "vbrNo" , value : reply.vbrNo})
										, $("<div>").addClass("d-flex").append(
												 $("<textarea>").addClass("voteReply__input").text(reply.vbrContent).css({height:"100%", width:"90%"})
																.attr({ name : "vbrContent" , rows : "3"}).prop("required", true)
												, $("<input>").attr({type:"submit", value:"저장", id:"replyUpdateBtn"}).addClass("btn btn-primary")
												, $("<input>").attr({type:"button", value:"취소", id:"replyCancleBtn"}).addClass("btn btn-danger")
										)
								)
						).css("display","none")	
				).data("reply", reply);
				divTags.push(div);
				});
		}
		repList.html(divTags);
		if(replyList.length>0){
			pagingArea.html(resp.pagingHTML);
		}
	},
	error : function(errResp) {
		console.log(errResp);
	}
}).submit();

/** 댓글 등록 */
var replyInsertForm = $("#replyInsertForm");
$("#replyInsertBtn").on("click", function(event){
	event.stopPropagation();
	replyInsertForm.ajaxForm(options);
})

/** 댓글 수정 폼 */ 
$(document).on("click", ".replyUpdateFormBtn", function(){
	let replyDiv = $(this).parents("#replyDIV");
	replyDiv.find(".orgnReply").css("display","none");
	replyDiv.find(".replyUpdateDIV").css("display","block");
})
/** 댓글 수정 */
$(document).on("click", "#replyUpdateBtn",function(){
	let updateForm = $(this).parents("form");
	updateForm.ajaxForm(options);
})
/** 댓글 수정 취소 */
$(document).on("click", "#replyCancleBtn",function(){
	let replyDiv = $(this).parents("#replyDIV");
	replyDiv.find(".orgnReply").css("display","");
	replyDiv.find(".replyUpdateDIV").css("display","none");
})
/** 댓글 삭제 */
$(document).on("click", ".replyDeleteBtn",function(){
	if(!confirm("댓글을 삭제하시겠습니까?")){
		return;
	}
	let vbrNo = $(this).data("reply").vbrNo;
	$.ajax({
		url : $.getContextPath()+"/voteBoard/reply/delete.do",
		data : {vbrNo : vbrNo},
		method : "post",
		dataType : "json",
		success : function(resp) {
			searchForm.submit();
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
})