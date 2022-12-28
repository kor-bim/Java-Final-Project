/**
 * @author 서대철
 * @since 2021. 2. 19.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.       서대철       		최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 

const listBody = $("#listBody");
const FARMBOXTABLE = $("#farmboxTable");

$.approvalFarmboxList = function(){
	$.ajax({
		url : $.getContextPath() + "/admin/approvalFarmList",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length>0){ // 검색결과가 있으면 
				$(resp).each(function(idx, farm){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(farm.docTypeName)
							, $("<td>").text(farm.dfUseYn == "Y" ? "사용" : "사용 안함")
							, $("<td>").addClass("text-left").text(farm.dfName)
							, $("<td>").addClass("text-left").text(farm.dfDescription)
						).data("farm", farm)
					);
				});
			}else{
				trTags.push(
					$("<tr>").html($("<td colspan='4'>").text("검색 결과 없음"))
				);
			}
			listBody.html(trTags);
			
			let table = FARMBOXTABLE.DataTable({
				paging : true,
				search : true,
				dom : "<'row'<'col-md-6'B><'col-md-6'f>>tp",
				buttons : ['copy', 'excel', 'pdf'],
				lengthChange: false,
				ordering : true,
				order : {[0] : 'desc'},
				columnDefs : [{
				      targets : "_all", 
				      orderable : false
				    }]
			})
			.buttons().container().appendTo("#addressTable_wrapper .col-md-6:first");
		}
	});
}