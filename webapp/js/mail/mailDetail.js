/**
 * <pre>
 * 
 * </pre>
 * @author 윤한빈
 * @since 2021. 3. 4.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * 2021. 3. 4.      윤한빈       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
function divPrint(){
	var initBody = document.body.innerHTML;
	window.onbeforeprint = function(){
		document.body.innerHTML = document.getElementById('divID').innerHTML;
	}
	window.onafterprint = function(){
		document.body.innerHTML = initBody;
	}
	window.print();
}