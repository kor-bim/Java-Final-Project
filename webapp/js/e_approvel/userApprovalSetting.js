/**
 * <pre>
 * </pre>
 * 
 * @author 윤한빈
 * @since 2021. 2. 19.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
$("#aprvlLineTable tr").on("click", function() {
	let alNo = $(this).children().eq(0).text();
	$.ajax({
		url : $.getContextPath() + "/approval/get/savedLine",
		data : {alNo : alNo},
		dataType : "JSON",
		success : function(resp) {
			// 기존에 있던 내용 지운다.
			$("#approvalMemberList").empty();
			$("#referenceMemberList").empty();
			$("#alNo").val(resp.alNo);
			$("#alNameSpan").text(resp.alName);
			
			let detailList = resp.lineDetailList;
			$(detailList).each(function(idx, detail){
				
				if(detail.aldtCode == 'REFERENCE'){
					$("#referenceMemberList").append(
							$("<div>").addClass("list-group-item border-secondary rounded m-1")
								.text(detail.memName)
								.data("val",detail.memId).css({width:"100px;", float:"left", display:"inline-block"})
									)
					
					
				}else{
					$("#approvalMemberList").append(
	    					$("<div>").addClass("list-group-item border-secondary rounded m-1")
		    					.text(detail.memName)
		    					.data("val",detail.memId)
			    					)
				}
				
			}) //each end 
		}
	}); // ajax end
	$("#modal2").modal("show");
});

$("#signImageUpload").on("change",function() {
	signImgForm.submit();
});

let signImgForm = $("#signImgForm").ajaxForm({
	dataType : "text",
	success : function(resp) {
		console.log(resp);
		$("#memSignImg").attr("src",contextPath+"/profileImages/"+resp);
	},
	error : function(xhr) {
		console.log("error:"+xhr);
	}
});


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

let removeAprvlLineForm = $("#removeAprvlLineForm");
$('.deleteApvlLineBtn').on("click", function() {
	let alNo = $(this).data("alno");
	if (confirm("정말 삭제하시겠습니까?")) {
		removeAprvlLineForm.find("[name='alNo']").val(alNo);
		removeAprvlLineForm.submit();
	}
});

$("input[id=signImageUpload]").change(function(){
    
    var ext = $(this).val().split(".").pop().toLowerCase();
    
    if($.inArray(ext,["gif","jpg","jpeg","png","bmp"]) == -1) {
        alert("gif, jpg, jpeg, png, bmp 파일만 업로드 해주세요.");
        $("input[id=signImageUpload]").val("");
        return;
    }
    
    var fileSize = this.files[0].size;
    var maxSize = 1024 * 1024;
    if(fileSize > maxSize) {
        alert("파일용량을 초과하였습니다.");
        return;
    }
    
    var file  = this.files[0];
    var _URL = window.URL || window.webkitURL;
    var img = new Image();
    
    img.src = _URL.createObjectURL(file);
    img.onload = function() {
        
        if(img.width != 180 || img.height != 100) {
            alert("이미지 가로 180px, 세로 100px로 맞춰서 올려주세요.");
            $("input[id=signImageUpload]").val("");
        } 
    }
});


var treeObj = $("#deptMemTree");

var curMenu = null, zTree_Menu = null;
var setting = {
	view: {
		showLine: false,
		showIcon: false,
		selectedMulti: true,
		dblClickExpand: true,
		addDiyDom: addDiyDom
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	check : {
	    	enable: true,
	    	chkboxType : {
		    	Y : "s"
		    	, N : "s" 
	    	}
	},
	callback: {
		onCheck: myOnCheck
	}
};

var deptMemArray =[];
	$.ajax({
		url : $.getContextPath() + "/deptMemTree.do",
		dataType : "JSON",
		success : function(resp) {
			$(resp).each(function(idx, dept){
				let mems = dept.deptMemList;
				let childrens = [];
				$(mems).each(function(index, mem){
					childrens.push({
						name : mem.memName
						, value : mem.memId
					});
				})
		
				let temp = {
					id : dept.deptCode
					, pId : dept.deptParentCode
					, name : dept.deptName
					, value : dept.deptCode
					, customClass   : 'deptSpan'
					, nocheck : true
					, children : childrens
					, open : idx == 0 ? true : false
				}
				deptMemArray.push(temp);
			})
			$.fn.zTree.init(treeObj, setting, deptMemArray);
			
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});

function addDiyDom(treeId, treeNode) {
	var spaceWidth = 10;
	var switchObj = $("#" + treeNode.tId + "_switch")
		, icoObj = $("#" + treeNode.tId + "_ico")
		, spanObj = $("#" + treeNode.tId + "_span")
		, checkObj = $("#" + treeNode.tId + "_check").attr("value", spanObj.attr("value"))
	switchObj.remove();
	icoObj.before(switchObj);
	icoObj.after(checkObj);
	if (treeNode.level > 1) {
		var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
		switchObj.before(spaceStr);
	}
}

var options = [];
function myOnCheck(event, treeId, treeNode) {
	
    $("#approvalBtn").on("click", function(){
    	console.log(treeNode.checked);
    	if(treeNode.checked==true){
	    	if(options.indexOf(treeNode.value)==-1){
		    	options.push(treeNode.value);
		    	$("#approvalMemList").append(
	    			$("<li>").addClass("list-group-item border-secondary rounded m-1")
	    			.text(treeNode.name)
	    			.data("val",treeNode.value)
	    			.append($("<span>").addClass("pull-right deleteMe").text("X")
	    				, $("<input>").attr({type:"hidden", name:"approvalMember", value:treeNode.value})
	    			)
		    	);
	    	}else{
	    		options.splice(treeNode.value,1);
	    	}
    	}
    });
    $("#referenceBtn").on("click", function(){
    	if(treeNode.checked==true){
	    	if(options.indexOf(treeNode.value)==-1){
		    	options.push(treeNode.value);
		    	$("#referenceMemList").append(
	    			$("<li>").addClass("list-group-item border-secondary rounded m-1")
	    			.text(treeNode.name)
	    			.data("val",treeNode.value)
	    			.append($("<span>").addClass("pull-right deleteMe").text("X")
	 					, $("<input>").attr({type:"hidden", name:"referenceMember", value:treeNode.value})	
	    			)
		    	); 
	    	}else{
	    		options.splice(treeNode.value,1);
	    	}
    	}
    });
    
    $(document).on("click", ".deleteMe", function() {
		for (let i = 0; i < options.length; i++) {
			if (options[i] === $(this).closest("li").data("val")) {
				options.splice(i, 1);
				i--;
			}
		}
		for(let i = 0; i < $(".chkbutton").length; i++){
			if($($(".chkbutton ")[i]).attr("value")===$(this).closest("li").data("val")){
				$($(".chkbutton ")[i]).attr('class','button chkbutton chk checkbox_false_full');
			}
		}
		treeNode.checked=false;
		$(this).closest("li").remove();
	});
};

