/**
 * <pre>
 * 
 * 날짜 관련 처리 함수 모음
 * 
 * </pre>
 * @author 이운주
 * @since 2021. 2. 2.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.      이운주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 

/** 오늘 날짜를 0000-00-00 형식으로 반환 */
$.simpleToday=function(){
	var today = new Date();
	var simpleToday = getSimple(today);
	return simpleToday;
}

function getSimple(paramDate){
	var year = paramDate.getFullYear();
	var month = (paramDate.getMonth()+1) > 9 ?  (paramDate.getMonth()+1) : "0" + (paramDate.getMonth()+1);
	var date = (paramDate.getDate()) > 9 ?  (paramDate.getDate()) : "0" + (paramDate.getDate());
		
	var simpleDateFormat = year +"-"+ month + "-" + date;
	return simpleDateFormat;
}

