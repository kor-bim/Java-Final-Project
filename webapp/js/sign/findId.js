/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 1. 27.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 27.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

var password;
var memId;
$('#sendMailBtn').on('click', function() {
	var memHp = $('#username').val();
	var email = $("#email").val();
	findIdCheck(memHp,email);
	$('#memberHp').val(memHp);
	$('#memberEmail').val(email);
});
$('#sendMailModalBtn').on('click', function() {
	$('#findIdModal').modal('hide');
	findIdCheck($('#memberHp').val(),$('#memberEmail').val());
});
$(document).on('click', '#checkAuthBtn', function() {
	if (password == $('#authNumber').val()) {
		$('#sendMailModalBtn').css('display', 'none');
		$('#goSignForm').css('display','block');
		$('#findMemId').text(memId);
	} else {
		$('#findMemId').text("조회된 아이디가 없습니다");
		$('#goSignForm').css('display','none');
		$('#sendMailModalBtn').css('display', 'block');
	}
	$('#findIdModal').modal('show');
});

function findIdCheck(hp,mail) {
	$.ajax({
		url : contextPath + "/sign/findIdCheck.do",
		type : "post",
		data : {
			username : hp,
			email : mail
		},
		success : function(resp) {
			$('#usernameDiv').remove();
			$('#emailDiv').remove();
			$('#sendMailBtn').remove();
			$('#authNumberDiv').css('display', 'block');
			$('#checkAuthBtn').css('display', 'block');
			password = resp.password;
			memId = resp.memId;
		},
		error : function(xhr) {
			alert("인증번호 발급 실패");
		}
	});
}
