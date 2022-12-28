/**
 * @author 서대철
 * @since 2021. 2. 2.
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 *  수정일         수정자       		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.       서대철       		최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */ 
$(document).ready(function() {
	let thisUrl = window.location.href;
	let Url = thisUrl.split('/');
	let currentUrl = Url[Url.length-1]
	
	$('.addtype-btn').each(function(){
		if(currentUrl == this.dataset.url){
			$(this).addClass('addtype-btn__active');
			$(this).children('input').prop('checked',true);
		}
		else if(currentUrl == this.dataset.url){
			$(this).addClass('addtype-btn__active');
			$(this).children('input').prop('checked',true);
		}
	})
	
	$('.add__personal').click(function() {
		if ($('.add__public').hasClass('addtype-btn__active')) {
			$('.add__public').removeClass('addtype-btn__active');
		}
		$('.add__personal').addClass('addtype-btn__active');
	})
	$('.add__public').on('click', function() {
		if ($('.add__personal').hasClass('addtype-btn__active')) {
			$('.add__personal').removeClass('addtype-btn__active');
		}
		$('.add__public').addClass('addtype-btn__active');
	})
});