/**
 * <pre>
 * 
 * </pre>
 * @author 윤한빈
 * @since 2021. 2. 17.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 17.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
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

$("#detail tr").on("click", function() {
	let no = $(this).children().eq(0).text();
	let adDelYn = $(this).children().eq(6).text();
	if (adDelYn != "Y") {
		location.href = $.getContextPath() + "/approvalBox/" + no;
	}
});
