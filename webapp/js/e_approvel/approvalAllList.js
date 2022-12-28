/**
 * <pre>
 * approvalAllList관련
 * </pre>
 * 
 * @author 길영주
 * @since 2021. 2. 08.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 08.      길영주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
const APPROVALALLLISTTABLE = $('#approvalAllListTable'); 

const listBody = $("#listBody");


$.approvalallList=function(){
	$.ajax({
		url : $.getContextPath() + "/approval/list.do",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			let aprlStateCode ;
			
			if(resp.length>0){ // 검색결과가 있으면
				$(resp).each(function(idx, approval){
					let dsCode = approval.dsCode='APPROVING'?'결재중': 'DISCUSS';
					trTags.push(
						$("<tr>").append(
							  $("<td>").text(approval.adNo).append($("<input>").attr({type:"checkbox", name:"checkRow",id:"inputCheck"}).css("float","left"))
							, $("<td>").text(approval.adTitle)
							, $("<td>").text(approval.memId)
							, $("<td>").text(approval.adDate)
							, $("<td>").text(approval.aldtName)
							, $("<td>").text(dsCode)
						).data("approval", approval)
					);
				});
			}else{
				trTags.push(
					$("<tr>").html($("<td colspan='6'>").text("검색 결과 없음"))
				);
			}
			listBody.html(trTags);
			
			let table = APPROVALALLLISTTABLE.DataTable({
				paging : true,
				search : true,
				dom : "<'row'<'col-md-6'B><'col-md-6'f>>tp",
				buttons : ['copy', 'excel', 'pdf'],
				lengthChange: false,
				ordering : true,
				order : {[0] : 'desc'},
				columnDefs : [{
				      targets : [0,2,3,4,5], 
				      className: "dtCenter"
				    },
				    {
				    	target : [1],
				    	className : "dtLeft"
				    }]
			})
			.buttons().container().appendTo("#approvalAllListTable_wrapper .col-md-6:first");
		}
	}); // ajax end
	

	$(function(){
		$("#approvalDiv").hide();
		
	    $("#inputCheck").change(function(){
	        if($("#inputCheck").is(":checked")){
	        	$("#approvalDiv").show();
	        }
	        else{
	        	$("#approvalDiv").hide();
	        }
	        
	    });
	});
}
$("#detail tr").on("click", function() {
	let adNo = $(this).children().eq(0).text();
	let adDelYn = $(this).children().eq(6).text();
	if (adDelYn != "Y") {
		location.href = $.getContextPath() + "/approval/" + adNo;
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



