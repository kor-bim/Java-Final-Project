/**
 * <pre>
 * voteList관련 
 * </pre>
 * 
 * @author 이운주
 * @since 2021. 1. 26.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
const VOTELISTTABLE = $('#voteListTable'); 
const VOTEVIEWTABLE = $('#voteViewTable'); 

const listBody = $("#listBody");

/** 현재 날짜 구하기 : 투표종료여부 따지기 위해 */
let today = $.simpleToday(); // date-util.js

/** 투표글 리스트 테이블 바디 */
$.voteList=function(){
	$.ajax({
		url : $.getContextPath() + "/voteBoard/list.do",
		dataType : "json",
		success : function(resp) {
			let trTags = [];
			if(resp.length>0){ // 검색결과가 있으면 
				$(resp).each(function(idx, vote){
					let isEnd = vote.vbEnd < today; // 종료일자가 오늘보다 작으면 : true (투표종료)  
					trTags.push(
						$("<tr>").append(
							  $("<td>").text(vote.vbNo)
							, $("<td>").text(vote.vbTitle)
							, $("<td>").text(vote.memId)
							, $("<td>").text(vote.vbDate)
							, $("<td>").text(vote.vbEnd)
							, $("<td>").text(isEnd?"종료":"진행중")
						).data("vote", vote).data("isEnd", isEnd)
					);
				});
			}else{
				trTags.push(
					$("<tr>").html($("<td colspan='6'>").text("검색 결과 없음"))
				);
			}
			listBody.html(trTags);
			
			let table = VOTELISTTABLE.DataTable({
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
			.buttons().container().appendTo("#voteListTable_wrapper .col-md-6:first");
		}
	}); // ajax end

	$("#voteFormModalBtn").on('click', function(){
		window.location=$.getContextPath() + "/voteBoard/voteForm.do"
	}); // click event end

	/** 목록에서 게시글 한건 상세 조회 = 투표화면 */
	listBody.on("click", "tr", function(event){
		let vbNo = $(this).data("vote").vbNo;
		console.log($(this).data("isEnd"));
		 //종료된 투표인 경우 
//		if($(this).data("isEnd")){
//			window.location=$.getContextPath() + "/voteBoard/" + vbNo ;  // 차트 예정
//		}else{
			window.location=$.getContextPath() + "/voteBoard/" + vbNo
//		}
	}); // click event end
} // votelist end

