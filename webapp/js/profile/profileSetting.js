/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 1. 29.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 29.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#imagePreview').css('background-image',
					'url(' + e.target.result + ')');
			$('#imagePreview').hide();
			$('#imagePreview').fadeIn(650);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
$("#imageUpload").change(function() {
	var url = this;
	readURL(url);
	
	imgForm.submit();
});

let imgForm = $("#imgForm").ajaxForm({
	dataType : "text",
	success : function(resp) {
		$("#memProfileImg").attr("src",contextPath+"/profileImages/"+resp);
	},
	error : function(xhr) {
		console.log(xhr);
	}
});

$("#checkPassBtn").on('click', function() {
	let memberPass = $("#memberPass").val();
	$.ajax({
		url : contextPath + "/profile/checkMemberPass",
		data : {
			memId : memId,
			memPass : memberPass
		},
		method : "post",
		dataType : "text",
		success : function(resp) {
			console.log(resp);
			if (resp == '"OK"') {
				$("#oldPass").val(memberPass);
				$("#memPass").css("display", "block");
				$("#updatePassBtn").css("display", "block");
				$("#changePassBtn").remove();
				$("#changePassModal").modal('hide');
			} else {
				alert("비밀번호가 틀렸습니다");
				$("#memberPass").val("");
			}
		},
		error : function(xhr) {
			alert(xhr);
		}
	});
})