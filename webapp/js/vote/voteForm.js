/**
 * <pre>
 * 
 * </pre>
 * @author 이운주
 * @since 2021. 1. 30.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 30.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 

const ANSWERS = `
			<div class="answers answer">
				<div class="mdl-textfield mdl-js-textfield">
					<input class="mdl-textfield__input answerInput options" /><label
						class="mdl-textfield__label" for="option1"></label>
				</div>
			</div>`

function getSelect(e){
	"type_single"==e ?
			($("#"+e).removeClass("getUnselected").removeClass("btn-outline-success").removeClass("btn-success")  
					,$("#"+e).addClass("getSelected").addClass("btn-success")    
					,$("#type_multi").removeClass("btn-success").removeClass("getSelected")
					,$("#type_multi").addClass("getUnselected").addClass("btn-outline-success")
					,$("#multiBtn").prop("checked", false)
					,$("#singleBtn").prop("checked", true))
			:($("#type_multi").removeClass("getUnselected").removeClass("btn-outline-success")
					,$("#type_multi").addClass("getSelected").addClass("btn-success")
					,$("#type_single").removeClass("btn-success").removeClass("getSelected")
					,$("#type_single").addClass("getUnselected").addClass("btn-outline-success")
					,$("#multiBtn").prop("checked", true)
					,$("#singleBtn").prop("checked", false))
}

function noSubmit(){var e=document.getElementsByClassName("getUnselected");return 2==e.length?(console.log("hi"),!1):void console.log(e.length)}
/** 선택지 추가하면 자동 스크롤 */
function navigate2(e){$("main").animate({scrollTop:e},100)}

/** 선택지 최소 2개 ~ 최대 20개 */
function addOption(){
	var e=document.getElementsByClassName("answer"); // 현재 선택지의 갯수 (20개를 넘을수 없음)
	if(20==e.length){
		alert("20개 이상 선택지를 추가할 수 없습니다.");
		return;
	}else{
		// 새로 추가될 answer의 name 과 id 결정
		var name = "voteCateVO.vcNames["+ e.length +"]";  
		var id = "option"+(e.length+1);
		var holder = (e.length+1) + ". 선택지 내용을 입력해주세요...";
		
		var answerSTR = $(ANSWERS.trim().replaceAll(/\s{2,}/igm, " "));
		var answer = $(ANSWERS.trim().replaceAll(/\s{2,}/igm, " ")).children().children("input")
					.attr("id", id)
					.attr("name", name )
					.attr("placeholder", holder);
		
		answerSTR.children().html(answer);
		$("#answerDIV").append(answerSTR);
	}
}
function delOption(){
	var e=document.getElementsByClassName("answer")
	2==e.length?alert("선택지는 최소 2개 필요합니다."):$(".answer:last").remove();
}

/** validate */ 
function validate_form(){
	let flag = true;
	if(!$("#title").val()) return alert("제목을 입력해 주세요!"), navigate2(0),!1;
	if(!$("#vbEnd").val()) return alert("투표 종료일을 입력해 주세요!"), navigate2(0),!1;
	
	if(!$("#question").val()) return alert("질문을 입력해 주세요!"), navigate2(0),!1;
	var e=$("#question").val(),
		t=document.getElementsByClassName("getSelected");
	if(0==t.length)return alert("복수응답 가능 여부를 선택해주세요!"),navigate2(100),!1;
	for(var n=document.getElementsByClassName("answer").length,l=0,a=new Array,s=1;n>=s;s++)
		$("#option"+s).val()||(l=1),a[s-1]=$("#option"+s).val();
	if(l)return alert("빈칸으로 남아있는 선택지가 있습니다!"),!1;
	
	return flag;
}

$("#voteInsertBtn").on("click", function(){
	if(validate_form()){
		for(var n=20-document.getElementsByClassName("hidden").length,s=20;n<s;s--){
			$("#option"+s).attr("disabled", true);
		}
		$("#voteBoardForm").submit();
	}
});

$(function(){
	let command = $("#command").val();
	if(command == "MODIFY"){
//		makeUpdateBody();
		console.log("수정");
	}else{
		addOption();
		addOption();
	}
})
