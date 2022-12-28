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
const APPROVALAWAITLISTTABLE = $('#approvalAwaitListTable'); 

const listBody = $("#listBody");


$.approvalAwaitList=function(){
	$.ajax({
		url : $.getContextPath() + "/approval/await.do",
		data:{memId : memId},
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length>0){ // 검색결과가 있으면
				$(resp).each(function(idx, approval){
					let dsCode = approval.dsCode='APPROVING'?'결재중':'';
					if(approval.aprvlStateCode=="WAIT"){
						aprvlStateCode="대기"
					}else{
						aprvlStateCode="확인"
					}
					trTags.push(
						$("<tr>").append(
							  $("<td>").text(approval.adNo).append($("<input>").attr({type:"checkbox", name:"checkRow",id:"inputCheck"}).css("float","left"))
							, $("<td>").text(approval.adTitle)
							, $("<td>").text(approval.memId)
							, $("<td>").text(approval.adDate)
							, $("<td>").text(aprvlStateCode)
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
			
			let table = APPROVALAWAITLISTTABLE.DataTable({
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
			.buttons().container().appendTo("#approvalAwaitListTable_wrapper .col-md-6:first");
		}
	}); // ajax end
	

	$("#approvalDiv").hide();
	
    $("#inputCheck").change(function(){
        if($("#inputCheck").is(":checked")){
        	$("#approvalDiv").show();
        }
        else{
        	$("#approvalDiv").hide();
        }
        
    });
}
