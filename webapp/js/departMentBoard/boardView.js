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
let boardDeleteForm = $("#deleteForm");
let removeBtn = $("#deleteBtn").on("click", function() {
	if (confirm("정말 삭제할텨?")) {
		boardDeleteForm.submit();
	}
});