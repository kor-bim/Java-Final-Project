/**
 * @author 서대철
 * @since 2021. 2. 22.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 22.       서대철       		최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

const listBody = $("#listBody");
const ALLDOCUMENTTABLE = $("#allDocumentTable");

$.approvalAllDocumnetBoxList = function(){
	$.ajax({
		url : $.getContextPath() + "/admin/approvalAllDocumentBoxList",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length > 0){
				$(resp).each(function(idx, all){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(all.adNo).prepend(
								$("<input type='checkbox'>").attr("name", "adNo")
															.attr("value", all.adNo)
															.attr("onclick", "approvalCheck(this)")
															.css({"margin-right":"10px", width:"17px", height:"17px"})
							)
							, $("<td>").addClass("text-left").text(all.adTitle)
							, $("<td>").text(all.memberVO.memName)
							, $("<td>").text(all.adDate)
							, $("<td>").text(all.adFinishDate)
							, $("<td>").text(all.dsName)
						).data("all", all)
					);
				});
				
				
			} else {
				trTags.push(
					$("<tr>").html($("<td colspan='6'>").text("검색 결과가 존재하지 않습니다."))
				);
			}
			
			if($.fn.DataTable.isDataTable(ALLDOCUMENTTABLE)){
				ALLDOCUMENTTABLE.DataTable().destroy();
			}
			listBody.html(trTags);
			
			ALLDOCUMENTTABLE.DataTable({
				paging : true,
				search : true,
				dom : "<'row'<'col-md-6'B><'col-md-6'f>>tp",
				buttons : ['copy', 'excel', 'pdf'],
				pageLength: 10,
				lengthChange: false,
				ordering : true,
				order : {[0] : 'desc'},
				columnDefs : [
					{
					      targets : [0,3,4], 
					      className: "dtCenter"
					    }
					],
					 language: {
						    search: "검색 "
						   },
			})
			.buttons().container().appendTo("#allDocumentTable_wrapper .col-md-6:first");
		}
	});
}