/**
 * <pre>
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


/** 파라미터로 받은 url의 "/"로 구분된 문자 중 마지막 문자열 반환
 * 
 *  사용방법 : 복붙하면 됨
 *  	var url = document.location.href;   
 *      var lastStr = lastStrOfUrl(url);
 *   -------------------------------------------   
 *   // ex. http://localhost/Forest/voteBoard
 *   // voteBoard
 */
$.lastStrOfUrl=function(url){
	var link = url.split("/");
	var lastStr = link.slice(-1)[0];
	return lastStr;
}	
