/**
 * <pre>
 * 
 * </pre>
 * @author 윤한빈
 * @since 2021. 1. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
var password;
var memId;
$('#checkIdBtn').on('click', function() {
	var sucessHtml = '<div class="page main-signin-wrapper">                                                    '+
						'	<div class="row signpages text-center">                                                '+
						'		<div class="col-md-3">                                                             '+
						'		</div>                                                                             '+
						'		<div class="col-md-6">                                                             '+
						'			<div class="card custom-card  bg-primary tx-white">                            '+
						'				<div class="card-body">                                                    '+
						'					<h1 class=" tx-white tx-medium mg-b-10">메세지 전송 성공</h1>          '+
						'					<p class="card-text">이메일에서 임시 비밀번호를 확인하세요</p>         '+
						'					<a class="card-link tx-white-7 hover-white" href="'+contextPath+'/sign/signForm">로그인하러 가기</a>'+ 
						'				</div>                                                                     '+
						'			</div>                                                                         '+
						'		</div>                                                                             '+
						'		<div class="col-md-3">                                                             '+
						'		</div>                                                                             '+
						'	</div>                                                                                 '+
						'</div>                                                                                    ';
	$.ajax({
		url : contextPath + "/sign/findPassCheck.do",
		type : "post",
		data : {
			username : $('#username').val(),
			email : $("#email").val()
		},
		success : function(resp) {
			console.log(resp);
			if(resp=="OK"){
				$('body').html(sucessHtml);
			}else{
				alert('이메일이 존재하지 않습니다');
			}
		},
		error : function(xhr) {
			alert("아이디가 존재하지 않습니다");
		}
	});
});


