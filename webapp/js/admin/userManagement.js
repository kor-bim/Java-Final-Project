/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 2. 8.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 8.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
$("#insertMemberBtn").on("click", function() {
	location.href = contextPath + "/admin/memberForm";
});
$("#pagingArea").on("click", "a", function(event) {
	event.preventDefault();
	let page = $(this).data("page");
	searchForm.find("[name='page']").val(page);
	searchForm.submit();
	return false;
});
let searchForm = $("#searchForm");
$("#searchBtn").on("click", function() {
	let inputs = $(this).parents("div#inputUI").find(":input[name]");
	$(inputs).each(function(index, input) {
		let name = $(this).attr("name");
		let value = $(this).val();
		let hidden = searchForm.find("[name='" + name + "']");
		hidden.val(value);
	});
	searchForm.submit();
});

$('.updateMemberBtn').on("click",function(){
	location.href = contextPath+"/admin/modifyMember/"+$(this).data("memid");
})

let removeMemberForm = $("#removeMemberForm");
$('.deleteMemberBtn').on("click", function() {
	let asCode = $(this).data("ascode");
	if (asCode == "DELETE") {
		alert("이미 탈퇴처리 되어있습니다");
		return;
	}

	let memId = $(this).data("memid");
	if (confirm("정말 탈퇴처리하시겠습니까?")) {
		removeMemberForm.find("[name='memId']").val(memId);
		removeMemberForm.submit();
	}
})

function all(){
	$("input[name=searchType]").val("userAsCode");
	$("input[name=searchWord]").val("");
	searchForm.submit();
}

function asOn(){
	$("input[name=searchType]").val("userAsCode");
	$("input[name=searchWord]").val("ON");
	searchForm.submit();
}

function asDelete(){
	$("input[name=searchType]").val("userAsCode");
	$("input[name=searchWord]").val("DELETE");
	searchForm.submit();
}

function asDormant(){
	$("input[name=searchType]").val("userAsCode");
	$("input[name=searchWord]").val("DORMANT");
	searchForm.submit();
}

function select(e){
	let selectName = $(e).text();
	$("#dropdown").text(selectName+ " ").append($("<i>", {"class":"fe fe-chevron-down"}));
}