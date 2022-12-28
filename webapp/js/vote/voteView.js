/**
 * <pre>
 * voteView관련
 * </pre>
 * 
 * @author 이운주
 * @since 2021. 1. 29.
 * @version 1.0
 * 
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 1. 29.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */

const VOTINGFORM = $("#votingForm");
const VIEWBODY = $("#viewBody");

const VOTINGBTN = $("#votingBtn");
const UPDATEBTN = $("#updateBtn");
const DELETEBTN = $("#deleteBtn");
const REPLYINSERTBTN = $("#replyInsertBtn");

/** 투표 버튼 클릭 */
VOTINGBTN.on("click", function(e) {
	var select_obj = '';
	$(".vcNo:input[type='checkbox']:checked").each(function(index) {
		if (index != 0) {
			select_obj += ', ';
		}
		select_obj += $(this).val();
	})
	console.log(select_obj.length);
	if (select_obj.length == 0) {
		alert("아무것도 선택하지 않았습니다!");
	} else {
		VOTINGFORM.attr("action", 'voting.do');
		VOTINGFORM.submit();
	}
})
/** 행 선택시 체크 박스 선택 */
VIEWBODY.on("click", "tr", function(e) {
	if (event.target.type == 'checkbox') {
		console.log(event.target.id);
		let dup = $("#vbDup").val();
		if (dup == 'N') {
			NoMultiChk(event.target.id);
		}
		return;
	}

	var tr = $(this);
	var td = tr.children();
	var check = td.eq(0).children(); // input checkbox

	voteChk(check);
})

/** 중복 투표 여부에 따라 체크박스 체크 제한 */
function voteChk(chk) {
	var flag = chk.prop("checked");
	console.log(flag);
	console.log(!flag);

	chk.prop("checked", !flag);

	let dup = $("#vbDup").val();
	if (dup == 'N') {
		var id = chk.prop("id");
		NoMultiChk(id);
	}
}
/** 중복 선택 불가 */
function NoMultiChk(paramId) {
	var obj = $(".vcNo:input[type='checkbox']:checked");

	for (var i = 0; i < obj.length; i++) {
		var objId = obj[i].id;
		console.log(objId);
		console.log(obj[i].id);
		if (objId != paramId) {
			obj[i].checked = false;
		}
	}
}

/** 수정 버튼 클릭 */
UPDATEBTN.on("click", function(e) {
	VOTINGFORM.attr("action", 'voteForm.do');
	VOTINGFORM.submit();

})
/** 삭제 버튼 클릭 */
DELETEBTN.on("click", function(e) {
	VOTINGFORM.attr("action", 'delete.do');
	VOTINGFORM.submit();
})

/** 투표 완료 chart */
var ctx = document.getElementById('voteCompleteChart').getContext('2d');
var voteCompleteChart = new Chart(ctx, {
	type : 'pie',
	data : {
		datasets : [ {
			data : data,
			backgroundColor : [ 'rgba(051,051,051,.3)',
					'rgba(38, 222, 129,1.0)', 'rgba(69, 170, 242,1.0)',
					'rgba(153, 102, 255,1.0)', 'rgba(255, 153, 153,1.0)',
					'rgba(255, 153, 051,1.0)', 'rgba(051, 153, 255,1.0)' ],
			borderColor : [ 'rgba(051,051,051,.3)', 'rgba(38, 222, 129,1.0)',
					'rgba(69, 170, 242,1.0)', 'rgba(153, 102, 255,1.0)',
					'rgba(255, 153, 153,1.0)', 'rgba(255, 153, 051,1.0)',
					'rgba(051, 153, 255,1.0)' ]
		} ],
		labels : labels
	},
	options : {
		legend : {
			display : true,
			position : 'bottom',
			labels : {
				boxWidth : 50,
				fontColor : '#111',
				padding : 30,
				fontSize : 30,
				fontStyle : "bold"
			}
		},
		tooltips : {
			titleFontSize : 16,
			titleFontColor : '#0066ff',
			bodyFontColor : '#FFF',
			bodyFontSize : 24,
			displayColors : false
		}
	}
});

//

/** 현재 날짜 구하기 : 투표종료여부 따지기 위해 */
let today = $.simpleToday(); // date-util.js

let isEnd = today > end;
console.log(isEnd);
if (isEnd) {
	$("#voteCompleteChart").show();
} else {
	$("#voteCompleteChart").hide();
}
