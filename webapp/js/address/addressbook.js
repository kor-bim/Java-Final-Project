/**
 * @author 서대철
 * @since 2021. 1. 28.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.       서대철       		최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

const listBody = $("#listBody");
const ADDRESSTABLE = $('#addressTable'); 

$.addressList = function() {
	let url = document.location.href;
	let adbkType = $.lastStrOfUrl(url);

	$.ajax({
		url : $.getContextPath() + "/addressbook/list.do",
		data : {"memId" : memId, "adbkType" : adbkType},
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length>0){ // 검색결과가 있으면 
				$(resp).each(function(idx, addr){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(addr.adbkName)
							, $("<td>").text(addr.adbkMail)
							, $("<td>").text(addr.adbkHp)
							, $("<td>").text(addr.adbkAdd)
						).data("addr", addr)
					);
				});
			}else{
				trTags.push(
					$("<tr>").html($("<td colspan='4'>").text("검색 결과 없음"))
				);
			}
			listBody.html(trTags);
			
			let table = ADDRESSTABLE.DataTable({
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
	}); // ajax end
	 
	// 주소록 상세조회 Modal
	let addressViewModal = $("#addressViewModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});

	listBody.on('click','tr',function(){
	    let adbkCode = $(this).data("addr").adbkCode;
	    addressViewModal.find(".modal-body").load($.getContextPath() + "/addressbook/" + adbkType + "/" + adbkCode, function() {
	    	addressViewModal.modal("show");
		});
	});
	
	// 주소 등록 Model
	let addressInsertModal = $("#addressInsertModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
	
	$("#insertBtn").on("click", function(){
		addressInsertModal.find(".modal-body").load($.getContextPath() + "/addressbook/insertAddressForm.do?memId="+memId, function() {
			addressInsertModal.modal("show");
		});
	});
	
	// 주소 수정 Modal
	let addressUpdateModal = $("#addressUpdateModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	
	$("#modifyBtn").on("click", function(){
		addressViewModal.modal("hide");
	    let adbkCode = $("#adbkCode").text();
		addressUpdateModal.find(".modal-body").load($.getContextPath() + "/addressbook/updateAddressForm/" + adbkCode, function(){
			addressUpdateModal.modal("show");
		});
	});
	
	// 주소 삭제 modal
	let addressDeleteModal = $("#addressDeleteModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body").empty();
	});
	
	$("#removeBtn").on("click", function(){
		addressViewModal.modal("hide");
		addressDeleteModal.modal("show");
		let adbkCode = $("#adbkCode").text();
		$("#deleteBtn").on("click", function(){
			location.href = $.getContextPath() + "/addressbook/delete.do/" + adbkCode;
		});
	});
}
