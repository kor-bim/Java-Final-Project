/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 2. 25.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 25.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

let searchForm = $("#searchForm");
$("#pagingArea").on("click", "a", function(event) {
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});

$("#mailTable tr td.view-message").on("click", function() {
	let mailNo = $(this).siblings(".mailNo").text();
	location.href = contextPath + "/mail/mailDetail/" + mailNo;
});

$(".mailsToTrashBtn").on("click", function() {
	if (confirm("삭제하시겠습니까?")) {
		let trashMailNo = [];
		$("input[name=trashMailNo]:checked").each(function(i) {
			trashMailNo.push($(this).val())
		});
		location.href = contextPath + "/mail/mailsToTrash/" + trashMailNo;
	}
});

// 북마크 클릭
$('.fa-bookmark').on('click', function() {
	_this = $(this);
	let mail = _this.parent().siblings(".mailNo").data();
	$.ajax({
		url : $.getContextPath() + "/mail/updateBookmark",
		data : {
			"mailNo" : mail.mailNo
		},
		type : "post",
		dataType : "text",
		success : function(resp) {
			_this.toggleClass('text-danger');
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
});

// 별표 클릭
$('.fa-star').on('click', function() {
	_this = $(this);
	let mail = _this.parent().siblings(".mailNo").data();
	$.ajax({
		url : $.getContextPath() + "/mail/updateStarred",
		data : {
			"mailNo" : mail.mailNo
		},
		type : "post",
		dataType : "text",
		success : function(resp) {
			_this.toggleClass('text-warning');
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
});

function all(){
	$(".read").show();
	$(".unRead").show();
}
function read(){
	$(".unRead").hide();
	$(".read").show();
}
function unRead(){
	$(".unRead").show();
	$(".read").hide();
}

function select(e){
	let selectName = $(e).text();
	$("#dropdown").text(selectName+ " ").append($("<i>", {"class":"fe fe-chevron-down"}));
}
