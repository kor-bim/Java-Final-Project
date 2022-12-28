/**
 * <pre>
 * 
 * </pre>
 * @author 윤한빈
 * @since 2021. 2. 23.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 23.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
var attdDate;
var attdh;
var attdm;
 function showTime() {
	var date = new Date();
	var year = date.getFullYear();
	var month = (date.getMonth()) + 1;
	var day = date.getDate();
	var week = new Array('일','월','화','수','목','금','토');
	
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	var session = "AM";
	var hour = date.getHours();
	var min = date.getMinutes();
	
	month = (month < 10) ? "0" + month : month;
	hour = (hour < 10) ? "0" + hour : hour;
	min = (min < 10) ? "0" + min : min;
	if (h == 0) {
		h = 12;
	}

	if (h > 12) {
		h = h - 12;
		session = "PM";
	}

	h = (h < 10) ? "0" + h : h;
	m = (m < 10) ? "0" + m : m;
	s = (s < 10) ? "0" + s : s;
	
	var d = year + "년 " + month + "월 " + day + "일 " + week[date.getDay()] + "요일";
	var time = session+ " " + h + ":" + m + ":" + s;
	document.getElementById("clock").innerText = time;
	document.getElementById("clock").textContent = time;

	document.getElementById("currentDate").innerText = d;
	document.getElementById("currentDate").textContent = d;
	
	attdDate = year+"-"+month+"-"+day;
	attdh = hour;
	attdm = min;
	setTimeout(showTime, 1000);
}


showTime();