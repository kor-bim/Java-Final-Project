/**
 * <pre>
 * 
 * </pre>
 * @author 이운주
 * @since 2021. 2. 8.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 8.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 

const VACATYPEFORM = `
					<tr>
						<td>
							<input type="text"/>
							<input type="hidden"/>
						</td>
						<td>
							<input type="checkbox" id="temp" value="Y">연차에서 차감
						</td>
					</tr>
					`

let listBody = $("#listBody");

function getListAjax(){
	
	$.ajax({
		url : $.getContextPath() + "/admin/vacatype/list.do",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length>0){ // 검색결과가 있으면 
				$(resp).each(function(idx, vacaType){
					
					// 연차 차감 여부 
					let checked = vacaType.vtDdct=="Y"?true:false;
					
					trTags.push(
							$("<tr>").addClass("vacatypeCnt "+idx).append(
								 $("<td>").text(vacaType.vtName).append(
										 			$("<input>").attr({type:"button", id:"deleteBtn", value:"  X"}).css("float","right").addClass("btn hiddenBtn")
										 			, $("<input>").attr({type:"hidden", name:"vacatypeList["+idx+"].vtCode", value:vacaType.vtCode}))
								, $("<td>").text("   연차에서 차감").prepend($("<input>").attr({type:"checkbox", value:"Y", name:"vacatypeList["+idx+"].vtDdct", value:"Y"}).prop("checked", checked))
							).data("vacaType", vacaType)
					);
				});
			}else{
				trTags.push(
					$("<tr>").html($("<td colspan='2'>").text("검색 결과 없음"))
				);
			}
			listBody.html(trTags);
		}
	}); // ajax end
}

/** 마우스오버/마우스아웃 할때마다 삭제버튼(X) 보였다안보였다 */
$(document).on("mouseover", "tr", function(){
	$(this).find("input[type='button']").removeClass("hiddenBtn").addClass("showBtn");
})
$(document).on("mouseout", "tr", function(){
	$(this).find("input[type='button']").removeClass("showBtn").addClass("hiddenBtn");
})

/** 휴가종류 추가+ 클릭 */
$("#vacaTypeFormBtn").on("click", function(){
	// index 
	let idx = document.getElementsByClassName("vacatypeCnt").length;
	
	let formSTR = $(VACATYPEFORM.trim().replaceAll(/\s{2,}/igm, " ")).appendTo(listBody);
	formSTR.find("input[type='text']").attr("name","vacatypeList["+idx+"].vtName");
	formSTR.find("input[type='hidden']").attr({name:"vacatypeList["+idx+"].vtCode"});
	formSTR.find("input[type='checkbox']").attr({name:"vacatypeList["+idx+"].vtDdct"});
	formSTR.closest("tr").addClass("vacatypeCnt "+idx);
})

/** 휴가종류 삭제 */
$(document).on("click","#deleteBtn", function(){
	let vtCode = $(this).closest("td").find("input[type='hidden']").attr("value"); 
	console.log(vtCode);
	if(vtCode<=7){
		alert("삭제할 수 없는 항목입니다");
		return;
	}
	if(!confirm("정말 삭제하시겠습니까?")){
		return;
	};
	
	$.ajax({
		url : contextPath + "/admin/vacatype/delete.do",
		data : {vtCode : vtCode},
		method : "post",
		dataType : "json",
		success : function(resp) {
			let message = resp.message;
			new Noty({
				 text: message.text , 
				 layout: message.layout ,
				 type: message.type,
				 timeout: message.timeout ,
				 progressBar: true
			}).show();
			getListAjax();
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
})

$("#vacaTypeForm").validate({
		rules :{
			vtName : {
				required : true
				, minlength : 1
				, maxlength : 7
			}
		},
		messages :{
			vtName : {
				required : "필수 입력값입니다."
				, minlength : "1자이상 입력하세요."
				, maxlength : "7자리 이하로 입력하세요."
			}
		}
	});
