/**
 * <pre>
 * 
 * </pre>
 * @author 서대철
 * @since 2021. 2. 28.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        	  수정자       	수정내용
 * --------     --------    ----------------------
 * 2021. 2. 28.        서대철       	최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

const CRELIST = $("#creList");

const APPLIST = $("#appList");
const APPTABLE = $("#appTable")

const ALLLISTBODY = $("#allListBody");

$.creList = function(){
	$.ajax({
		url : $.getContextPath() + "/vacation/vacationStatusList",
		data : {"memId" : memId},
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length > 0){
				$(resp).each(function(idx, cre){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(cre.vacaYear+"년").attr("class", "text-center")
							, $("<td>").text(cre.vacaInday+"일").attr("class", "text-center")
							, $("<td>").text(cre.vacaRmday+"일").attr("class", "text-center")
							, $("<td>").text(cre.vtName).attr("class", "text-center")
						)
					)
				});
			} else {
				trTags.push(
					$("<tr>").html($("<td colspan='5'>").text("휴가 생성 내역이 존재하지 않습니다."))
				);
			}
			CRELIST.html(trTags);
		}
	});
}

$.appList = function(){
	$.ajax({
		url : $.getContextPath() + "/vacation/vacationList",
		data : {"memId" : memId},
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length > 0){
				$(resp).each(function(idx, vaca){
					trTags.push(
						$("<tr>").append(
							$("<input type='hidden'>").text(vaca.adNo)
							, $("<td>").text(vaca.vtName).attr("class", "text-center")
							, $("<td>").text(vaca.days+"일").attr("class", "text-center")
							, $("<td>").text(vaca.vacaBegin + "~" +vaca.vacaEnd).attr("class", "text-center")
							, $("<td>").text(vaca.dsName).attr("class", "text-center")
							, $("<td>").append(
								$("<input type='button'>").attr("class", "btn btn-outline-light detailBtn").attr("value", "상세")
							).attr("class", "text-center")
						).data("vaca", vaca)
					);
				});
			} else {
				trTags.push(
					$("<tr>").html($("<td colspan='5'>").text("휴가 신청 내역이 존재하지 않습니다."))
				);
			}
			APPLIST.html(trTags);
			
			let table = APPTABLE.DataTable({
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
			.buttons().container().appendTo("#appTable_wrapper .col-md-6:first");
		}
	});
}
	
$.allList = function(){
	$.ajax({
		url : $.getContextPath() + "/admin/vacationAllList",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length > 0){
				$(resp).each(function(idx, vc){
					trTags.push(
						$("<tr>").append(
							$("<td>").text(vc.memName).attr("class", "text-center")
							, $("<td>").text(vc.deptName).attr("class", "text-center")
							, $("<td>").text(vc.vtName).attr("class", "text-center")
							, $("<td>").text(vc.vacaBegin + "~" + vc.vacaEnd).attr("class", "text-center")
							, $("<td>").text(vc.days + "일").attr("class", "text-center")
							, $("<td>").text(vc.dsName).attr("class", "text-center")
							, $("<td>").append(
								$("<input type='button'>").attr("class", "btn btn-outline-light allDetailBtn").attr("value", "상세")
							).attr("class", "text-center")
							, $("<td>").append(
								$("<input type='button'>").attr("class", "btn btn-outline-light vcReturn").attr("value", "휴가신청취소")
							).attr("class", "text-center")
						).data("vc", vc)
					);
				});
			} else {
				trTags.push(
					$("<tr>").html($("<td colspan='8'>").text("휴가 신청 내역이 존재하지 않습니다."))
				);
			}
			ALLLISTBODY.html(trTags);
		}
	});
}