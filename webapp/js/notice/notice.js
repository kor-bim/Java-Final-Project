0/**
 * @author 길영주
 * @since 2021. 1. 28.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.       길영주       		최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

$.noticeList = function() {
	let table = $('#noticeTable').DataTable({
		paging : true,
		search : true,
		dom : "<'row'<'col-md-6'B><'col-md-6'f>>tp",
		ajax : {
			url : $.getContextPath() + "/noticeBoard/list.do",
			dataType : "JSON",
			dataSrc : ""
		},
		columns : [ 
//			{
//			data : "rnum"
//		}, 
		{
			data : "nbNo"
		}, {
			data : "nbTitle"
		}, {
			data : "nbDate"
		}, {
			data : "memId"
		} ],
		columnDefs : [ {
			targets : [ 0 ],
			visible : true,
			className : "alCenter"
		},
		{
			targets : [ 2 ],
			className : "alCenter"
		},
		{
			targets : [ 3 ],
			className : "alCenter"
		}
		],
		buttons : [ 'copy', 'excel', 'pdf' ],
		lengthChange : false
	});
	table.buttons().container()
			.appendTo("#noticeTable_wrapper .col-md-6:first");

	// 공지사항 상세조회
	let noticeViewModal = $("#noticeViewModal").on("hidden.bs.modal",
			function() {
				$(this).find(".modal-body").empty();
			});

	// 공지사항 등록
	let noticeInsertModal = $("#noticeBoardInsertModal").on("hidden.bs.modal",
			function() {
				$(this).find(".modal-body").empty();
			});

	$("#NBInsertBtn").on(
			"click",
			function() {
				noticeInsertModal.find(".modal-body").load(
						$.getContextPath() + "/noticeBoard/noticeForm.do",
						function() {
							noticeInsertModal.modal("show");
						});
			})

	// 공지사항 수정
	let noticeUpdateModal = $("#noticeBoardUpdateModal").on("hidden.bs.modal",
			function() {
				$(this).find(".modal-body").empty();
			});

	$("#updateBtn").on("click",function() {
				noticeViewModal.modal("hide");
				let nbNo = $("#nbNo").text();
				noticeUpdateModal.find(".modal-body").load(
						$.getContextPath()+ "/noticeBoard/noticeUpdateForm.do/" + nbNo,function() {
							noticeUpdateModal.modal("show");
						});
			})


	// 게시글 삭제
	let noticeDeleteModal = $("#noticeBoardDeleteModal").on("hidden.bs.modal", function(){
		$(this).find(".modal-body");
	});
	
	$("#removeBtn").on("click", function(){
		noticeViewModal.modal("hide");
		noticeDeleteModal.modal("show");
		let nbNo = $("#nbNo").text();
		$("#deleteBtn").on("click", function(){
			location.href = $.getContextPath() + "/noticeBoard/noticeDeleteForm.do/" + nbNo;
		});
	});
	
	$(document).on(
			'click',
			'#noticeTable td',
			function() {
				var tr = $(this).closest('tr');
				var td = tr.children();
				var seq = td.eq(0).text();
				console.log(seq);
				noticeViewModal.find(".modal-body").load(
						$.getContextPath() + "/noticeBoard/" + seq, function() {
							noticeViewModal.modal("show");
						});

			});
	
	
}