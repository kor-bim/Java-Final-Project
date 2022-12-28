/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 3. 2.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 3. 2.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
CKEDITOR.replace("editor4",{
	filebrowserImageUploadUrl : '${cPath }/board/imageUpload?command=QuickUpload&type=Images'
});
$('#mailFiles').MultiFile({
	STRING : { // Multi-lingual support : 메시지 수정 가능
		duplicate : "$file 은 이미 선택된 파일입니다.",
		selected : '$file 을 선택했습니다.',
	}
});
$('#receiverId').select2({
	tags : true
});
$('#formSubmit').keydown(function() {
	if (event.keyCode === 13) {
		event.preventDefault();
	}
});