/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 2. 5.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.      윤한빈       최초작성
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
				if(reply.memId != myId){
					let div = $("<div>").addClass("row mt-4").append(
						$("<div>").addClass("col-12").append(
							$("<div>").addClass("bg-light rounded").append(
								$("<span>").addClass("m-1").text(reply.memId),
								$("<span>").addClass("pull-right mr-2").css("color","#8784a7").text(reply.dbrDate)
							)
						),
						$("<div>").addClass("col-12").append(
							$("<p>").addClass("m-2").text(reply.dbrContent)
						)
					).data("reply",reply);
					divTags.push(div);
				} else {
					let myDiv = $("<div>").addClass("row mt-4").attr("id","myReplyDiv").append(
							
						$("<div>").addClass("col-12 replycmt").append(
							$("<div>").addClass("rounded").append(
								$("<span>").addClass("m-1").text(reply.memId),
								$("<span>").addClass("pull-right mr-2").css("color","#8784a7").text(reply.dbrDate)
							).css("background-color","#deeeff")
						),
						$("<div>").addClass("col-12 replycmt").append(
							$("<p>").addClass("m-2").text(reply.dbrContent),
							$("<span>").addClass("pull-right mr-2").append(
								$("<i>").addClass("fas fa-trash mr-2").css("color","#8784a7").attr("id","replyDelete").text("삭제"),
								$("<i>").addClass("fas fa-pencil-alt").css("color","#8784a7").attr("id","replyUpdate").text("수정")
							)
						),
						$("<div>").addClass("col-12 p-0").append(
							$("<form>").addClass("col-12 row m-0 p-0 replyUpdateForm").attr("action",contextPath+"/departMentBoard/reply/update").attr("method","post").css("display","none").append(
								$("<input>").attr({type:"hidden", name:"dbrNo", value:reply.dbrNo}).prop("required",true),
								$("<input>").attr({type:"hidden", name:"dbNo", value:reply.dbNo}).prop("required",true),
								$("<div>").addClass("col-12 input-group").append(
									$("<input>").addClass("cancleBtn").attr({type:"button", value:"x"}).addClass("btn btn-warning"),
									$("<textarea>").addClass("form-control").attr("name","dbrContent").text(reply.dbrContent),
									$("<input>").attr({type:"submit", value:"등록"}).addClass("btn btn-primary updateReplyBtn")
								)
							)
						)
					).data("reply",reply);
					divTags.push(myDiv);
				}
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

let replyInsertForm = $("#replyInsertForm").ajaxForm(options);

$(document).on("click","#replyUpdate",function(){
	let myReplyDiv = $(this).parents('#myReplyDiv');
	let replyUpdateForm = myReplyDiv.children().find(".replyUpdateForm");
	let replycmt = myReplyDiv.children(".replycmt")
	replycmt.css("display","none");
	replyUpdateForm.css("display","block");
});

$(document).on("click",".cancleBtn",function(){
	let myReplyDiv = $(this).parents('#myReplyDiv');
	let replyUpdateForm = myReplyDiv.children().find(".replyUpdateForm");
	let replycmt = myReplyDiv.children(".replycmt")
	replycmt.css("display","block");
	replyUpdateForm.css("display","none");
});

$(document).on("click",".updateReplyBtn",function(){
	let myReplyDiv = $(this).parents('#myReplyDiv');
	let replyUpdateForm = myReplyDiv.children().find(".replyUpdateForm");
	replyUpdateForm.ajaxForm(options);
})

$(document).on("click","#replyDelete",function(){
	if(confirm("댓글을 삭제하시겠습니까?")){
		let reply = $(this).parents('#myReplyDiv').data("reply");
		$.ajax({
			url : contextPath+"/departMentBoard/reply/delete",
			data : {
				dbrNo : reply.dbrNo,
				dbNo : reply.dbNo
			},
			method : "post",
			dataType : "json",
			success : function(resp) {
				searchForm.submit();
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
	}
});
