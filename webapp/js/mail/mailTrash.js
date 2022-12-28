/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 3. 3.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 3. 3.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
$(".realDeleteBtn").on("click", function() {
	if (confirm("완전히 삭제하시겠습니까?")) {
		let deleteMailNo = [];
		$("input[name=mailNo]:checked").each(function(i) {
			deleteMailNo.push($(this).val())
		});
		location.href = contextPath + "/mail/realDeleteMail/" + deleteMailNo;
	}
});
$(".restoreBtn").on("click", function() {
	if (confirm("복구 하시겠습니까?")) {
		let restoreMailNo = [];
		$("input[name=mailNo]:checked").each(function(i) {
			restoreMailNo.push($(this).val())
		});
		location.href = contextPath + "/mail/restoreMail/" + restoreMailNo;
	}
});