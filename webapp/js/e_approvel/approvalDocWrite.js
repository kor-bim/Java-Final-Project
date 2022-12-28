/**
 * <pre>
 * 결재 문서 작성 
 * </pre>
 * @author 이운주
 * @since 2021. 2. 19.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 




//=== 새로 작성하는 경우 =================================================================================================
CKEDITOR.replace("adContent", {height:500});

const DOCTYPESELECT = $("#docTypeSelect");
const DOCFORMSELECT = $("#docFormSelect");

// docForm 리스트 조회 
function getDocFormList(){
	let docTypeCode = DOCTYPESELECT.val();
	let optArray = [];
	optArray.push($("<option>").attr("value","").text("선택"));

	$.ajax({
		url : $.getContextPath() + "/approval/docFormList",
		data : {docTypeCode : docTypeCode},
		dataType : "JSON",
		success : function(resp) {
			$(resp).each(function(idx, form){
				optArray.push(
						$("<option>").data("form",form).attr("value",form.dfNo).text(form.dfName)
				);
			})
			DOCFORMSELECT.html(optArray);
		}
	}); // ajax end
}

const PRESDIV = $("#presName");
const SLEVELDIV = $("#slevel");
const LINEFORMDIV = $("#lineFormDIV"); // 결재선 나오는 곳 

var alCode = '';

// docForm 선택 후 
DOCFORMSELECT.on("change", function(){
	// 보안등급, 보존연한 셋팅
	let data = $(this).find("option:selected").data("form");
	let slevel = data.scuCode + "등급";
	let presName = data.presName;
	alCode = data.alCode;
	
	PRESDIV.html(presName);
	SLEVELDIV.html(slevel);
	
	// 결재선 등장
	LINEFORMDIV.empty();
	LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function(){
		makeLineInit();
	});
	
	// 작성창 등장 
	$(".beforeLoad").addClass("hide");
	$(".afterLoad").removeClass("hide");
	CKEDITOR.instances.adContent.setData(data.dfContent);
})

// 기안하기 
const APPROVALDOCFORM = $("#approvalDocForm");
function saveDocument(command){
	if(command == 'DRAFT'){
		console.log("기안클릭");
		APPROVALDOCFORM.submit();
	}
}

// 결재선 설정 버튼 클릭 
function getApprovalLineSelect(){
	const DEPT_TREE_DIV = $("#deptTreeDIV"); 
	DEPT_TREE_DIV.load($.getContextPath() + "/ztree/deptMem");
}

$(document).ready(function(){
	getDocFormList();
	
	let docFormValue = $("#docFormSelect").find("option:selected").val();
	let writingDoc = docFormValue.length > 0 ? true : false;
});

//=== 결재선 설정 모달 =================================================================================================
const SAVED_LINE_SELECT = $("#savedLineSelect");
const lineSettingModal = $("#lineSettingModal");
function getApprovalLineSelectModal(){
	$("#deptTreeDIV").load($.getContextPath() + "/ztree/deptMem", function(){
		lineSettingModal.modal();
	});
	
	// 자주 쓰는 결재선 리스트 불러오기
	SAVED_LINE_SELECT.html('');
	
	let alineList = [];
	alineList.push($("<option>").attr({value:''}).text("자주 쓰는 결재선"));

	let myId = myInfo.memId;
	let selectAlCode = alCode;
	$.ajax({
		url : $.getContextPath() + "/approval/get/savedLineList",
		data : {
			memId : myId
			, alCode : selectAlCode
		},
		dataType : "JSON",
		success : function(resp) {
			$(resp).each(function(idx, aline){
				alineList.push(
					$("<option>").data("aline", aline).attr({value:aline.alNo}).text(aline.alName == null ? '이름없음' : aline.alName)
				)
			})
			SAVED_LINE_SELECT.html(alineList);
		}
	});
}

const APPROVAL_AREA = $("#approvalArea");
const REFERENCE_AREA = $("#referenceArea");
//자주 쓰는 결재선 중에 선택 
SAVED_LINE_SELECT.on("change", function(){
	let alNo = $(this).find("option:selected").data("aline").alNo;
	
	$.ajax({
		url : $.getContextPath() + "/approval/get/savedLine",
		data : {alNo : alNo},
		dataType : "JSON",
		success : function(resp) {
			// 기존에 있던 내용 지운다.
			APPROVAL_AREA.empty();
			REFERENCE_AREA.empty();
			$("#alNo").val(resp.alNo);
			
			let detailList = resp.lineDetailList;
			$(detailList).each(function(idx, detail){
				if(detail.aldtCode == 'REFERENCE'){
					REFERENCE_AREA.append(
							$("<div>").addClass("list-group-item border-secondary rounded m-1")
								.text(detail.memName)
								.attr("value", detail.memId).css({width:"100px;", float:"left", display:"inline-block"})
									.append($("<span>").addClass("pull-right deleteMe").text("X").css("cursor","pointer")).data("memInfo", detail)
									)
					options.push(detail.memId);
					
				}else{
	    			APPROVAL_AREA.append(
	    					$("<div>").addClass("list-group-item border-secondary rounded m-1")
		    					.text(detail.memName + "( "+ detail.deptName + " / " + detail.psName+" )")
		    					.attr("value",detail.memId)
			    					.append($("<span>").addClass("pull-right deleteMe").text("X")).data("memInfo", detail)
			    					)
			    	options.push(detail.memId);
				}
			}) //each end
		}
	}); // ajax end 
})

// 트리에서 멤버 선택
zTree_Menu = $.fn.zTree.getZTreeObj("deptMemTree");
var options = [];
function myOnCheck(event, treeId, treeNode) {
	$("#alNo").val('');
	
    $("#approvalBtn").on("click", function(){
    	if(treeNode.value == myInfo.memId){
    		return;
    	}
    	if(treeNode.checked==true){
	    	if(options.indexOf(treeNode.value)==-1){
	    		let memInfo = JSON.parse(treeNode.dataSet);
	    		
		    	options.push(treeNode.value);
		    	APPROVAL_AREA.append(
	    			$("<div>").addClass("list-group-item border-secondary rounded m-1")
			    			.text(treeNode.name + "( "+ memInfo.deptName +" / "+ memInfo.psName+" )")
			    			.attr("value",treeNode.value)
			    			.append($("<span>").addClass("pull-right deleteMe").text("X")).data("memInfo", memInfo)
		    	);
	    	}else{
	    		options.splice(treeNode.value,1);
	    	}
    	}
    });
    $("#referenceBtn").on("click", function(){
    	if(treeNode.value == myInfo.memId){
    		return;
    	}
    	if(treeNode.checked==true){
	    	if(options.indexOf(treeNode.value)==-1){
	    		let memInfo = JSON.parse(treeNode.dataSet);
	    		
		    	options.push(treeNode.value);
		    	REFERENCE_AREA.append(
	    			$("<div>").addClass("list-group-item border-secondary rounded m-1")
			    			.text(treeNode.name)
			    			.attr("value",treeNode.value).css({width:"100px;", float:"left", display:"inline-block"})
			    			.append($("<span>").addClass("pull-right deleteMe").text("X").css("cursor","pointer")).data("memInfo", memInfo)
		    	);
	    	}else{
	    		options.splice(treeNode.value,1);
	    	}
    	}
    });
    
    $(document).on("click", ".deleteMe", function() {
		for (let i = 0; i < options.length; i++) {
			if (options[i] === $(this).closest("div").attr("value")) {
				options.splice(i, 1);
				i--;
			}
		}
		for(let i = 0; i < $(".chkbutton").length; i++){
			if($($(".chkbutton ")[i]).attr("value")===$(this).closest("div").attr("value")){
				$($(".chkbutton ")[i]).attr('class','button chkbutton chk checkbox_false_full');
			}
		}
		treeNode.checked=false;
		$(this).closest("div").remove();
	});
    
} // myOnCheck end 


//const APPROVAL_AREA = $("#approvalArea");
//const REFERENCE_AREA = $("#referenceArea");
// 결재선 설정모달에서 등록 버튼 클릭
// 결재문서 작성 화면의 결재라인에 뿌려줘야됨
$("#approvalLineMakeBtn").on("click", function(){
	let drafterInfo = {
		memName : myInfo.memName,
		deptName : myInfo.deptName,
		psName : myInfo.psName,
	}
	$("#drafter").data("memInfo", drafterInfo);
	
	$("#approvalBody").empty();
	let approvalMember = [];
	let referenceMember = [];
	
	// 결재자 
	let approval = APPROVAL_AREA.find("div");
	for(let i = 0 ; i < approval.length ; i++){
		let approvalTemp = {
			memId : $(approval[i]).attr("value")
			, memName : $(approval[i]).data("memInfo").memName
			, deptName : $(approval[i]).data("memInfo").deptName
			, psName : $(approval[i]).data("memInfo").psName
			, aldtCode : i==0?"DRAFT":"APPROVAL"
		}
		approvalMember.push(approvalTemp);
	}
	
	// 참조자
	let reference = REFERENCE_AREA.find("div")
	for(let i = 0 ; i < reference.length ; i++){
		let referTemp = {
				memId : $(reference[i]).attr("value")
				, memName : $(reference[i]).data("memInfo").memName
				, deptName : $(reference[i]).data("memInfo").deptName
				, psName : $(reference[i]).data("memInfo").psName
				, aldtCode : "REFERENCE"
		}
		referenceMember.push(referTemp);
	}
	
	let lineData = {
		approval : approvalMember
		, reference : referenceMember
		, needBtns : false
		, needSigns : false
	}
	
	LINEFORMDIV.empty();
	LINEFORMDIV.load($.getContextPath() + "/getLine/aproval", function(){
		lineSettingModal.modal('hide');
		makeApprovalLineForm(lineData);
	});
})

