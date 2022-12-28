var mypageLang = new validateMessage('mypage');

var popupWindow = Array();

jQuery.fn.extend({
    showPopup : function()
    {
		if(jQuery("#alphaDiv"))	jQuery("#alphaDiv").hide();
        if( this.length ){
            this.css({'marginLeft': this.outerWidth() / 2 * -1});
            this.css({'marginTop': this.outerHeight() / 2 * -1});
            jQuery('#dimmed').show();
            this.insertAfter(jQuery("#wrap"));
            this.show();

			popupWindow.push(this);
            return this;
        }
    }
});

function showPopup(el, closeflag) 
{
	$j(el).showPopup();
}

function hidePopup(flag)
{
	var pop = popupWindow.pop();
	if(pop) {
		pop.hidePopup(false);
		if(undefined != flag) {
			popupWindow = Array();
		}
		if(pop = popupWindow.pop()) {
			pop.showPopup();
		}
	}
}

var myinfo = {
	confirmMyinfo: function()
	{
		var f = document.frmMyinfo;

		// 핸드폰번호 체크
		var r2 = RegExp(/^\+[0-9](-?[0-9]){3,14}$/);

		if(f.cell.value.length > 0){
			var check_cell = myinfo.checkCellPhone(f.cell.value);
			if(check_cell === false){
				window.alert(mypageLang.getMessage('INSA_INVALID_CELL_FORMAT'));
				f.cell.focus();
				return;
			}
		}

		if (f.phone.value != "")
		{
			if ( !validCheck.phone_no_style( f.phone.value) )
			{
				alert(mypageLang.getMessage("INVALID_PHONE"));
				f.phone.focus();
				return;
			}
		}

		// 우편번호 숫자체크
		if(f.zipcode1.value !== "" && isNaN(f.zipcode1.value)){
			window.alert(mypageLang.getMessage('ZIPCODE_NUMBER'));
			f.zipcode1.focus();
			return;
		}

		//메일
		if(f.email.value != '' && !Common.funcCheckEmail(f.email.value)) {
			window.alert(mypageLang.getMessage('JPEMailChk'));
			f.email.focus();
			return;
		}

		// 생년월일 체크
		if(f.birth_year.value.length > 0 || f.birth_month.value.length > 0  || f.birth_day.value.length > 0 ){


			if(!jQuery.isNumeric(f.birth_year.value)){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_year.focus();
				return;
			}

			if(!jQuery.isNumeric(f.birth_month.value)){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_month.focus();
				return;
			}

			if(!jQuery.isNumeric(f.birth_day.value)){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_day.focus();
				return;
			}

			var today = new Date();

			if(f.birth_year.value.length < 4 || parseInt(f.birth_year.value) < 1900 || parseInt(f.birth_year.value) > today.getFullYear()){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_year.focus();
				return;
			}else if(f.birth_month.value.length < 1 || f.birth_month.value == 0 || parseInt(f.birth_month.value) > 12 ){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_month.focus();
				return;
			}else if(f.birth_day.value.length < 1 || f.birth_day.value == 0 || parseInt(f.birth_day.value) > 31){
				window.alert(mypageLang.getMessage('JPBirthdayChk'));
				f.birth_day.focus();
				return;
			}
		}

		f.birth_month.value = f.birth_month.value.length == 1 ? "0" + f.birth_month.value : f.birth_month.value;
		f.birth_day.value = f.birth_day.value.length == 1 ? "0" + f.birth_day.value : f.birth_day.value;

		if($j('input[name=company_flag]').length == 0){
			$j('#frmMyinfo').append('<input type="hidden" name="company_flag" value="N"/>');
		}
		
        f.action = Common.getRoot() + "insa/info/myinfo/saveUserInfo";
        f.submit();
	},

	changeAddressType: function(type)
	{
		if('jibun' == type) {
			$j("#type_01").show();
			$j("#type_02").hide();
		}else {
			$j("#type_01").hide();
			$j("#type_02").show();

		}
	},

	clearfindAddress: function()
	{
		$j("#mypage_layer_address").find("input").val("");
		$j("#mypage_layer_address").find("select").empty().append("<option value=''>선택</option>");
		$j("#mypage_layer_address").find("#address_detail").hide();
	},

	showfindAddress: function()
	{
		showPopup($j("#mypage_layer_address"));
		this.clearfindAddress();
		var URL = "https://biz.gabia.com/common/addr_ajax/gabia_address_api_json?jsoncallback=jsonp";

		$j.ajax({
			type: 'get',
			url: URL,
			data: {'flag':'sido'},
			dataType: "jsonp",
			jsonp : "callback",
			success: function(data) {
				if("SUCCESS" == data.resultCode) {
					myinfo.setAddressSido(data.result);
				}
			}
		});
	},

	setAddressSido: function(data)
	{
		var select = $j("#address_sido");
		select.empty();
		select.append("<option value=''>선택</option>");
		for(var i=0; i<data.length; i++) {
			var item = data[i];
			var option = document.createElement('option');
			option.innerHTML = item.sido;
			option.value = item.sido_code;
			select.append(option);
		}
	},

	getAddressGugun: function()
	{
		var sido_no = $j("#address_sido").val();
		if(!validCheck.numeric(sido_no)){
			return;
		}
		if(sido_no > 0) {
			var URL = "https://biz.gabia.com/common/addr_ajax/gabia_address_api_json?jsoncallback=jsonp";

			$j.ajax({
				type: 'get',
				url: URL,
				dataType: "jsonp",
				data: {
					'flag':'gugun',
					'gugun_no':sido_no
				},
				jsonp : "callback",
				success: function(data) {
					if("SUCCESS" == data.resultCode) {
						myinfo.setAddressGugun(data.result);
					}
				}
			});
		}else {
			var select = $j("#address_gugun");
			select.empty();
			select.append("<option value=''>선택</option>");
		}
	},

	setAddressGugun: function(data)
	{
		var select = $j("#address_gugun");
		select.empty();
		select.append("<option value=''>선택</option>");
		for(var i=0; i<data.length; i++) {
			var item = data[i];
			var option = document.createElement('option');
			option.innerHTML = item.gugun;
			option.value = item.gugun_code;
			select.append(option);
		}
	},

	getAddressDong: function()
	{
		var gugun_no = $j("#address_gugun").val();
		if(!validCheck.numeric(gugun_no)){
			alert('유효하지 않은 값입니다.');
			return;
		}
		if(gugun_no > 0) {
			var URL = "https://biz.gabia.com/common/addr_ajax/gabia_address_api_json?jsoncallback=jsonp";

			$j.ajax({
				type: 'get',
				url: URL,
				dataType: "jsonp",
				data: {
					'flag':'dong',
					'dong_no':gugun_no
				},
				success: function(data) {
					if("SUCCESS" == data.resultCode) {
						myinfo.setAddressDong(data.result);
					}
				}
			});
		}else {
			var select = $j("#address_dong");
			select.empty();
			select.append("<option value=''>선택</option>");
		}
	},

	setAddressDong: function(data)
	{
		var select = $j("#address_dong");
		select.empty();
		select.append("<option value=''>선택</option>");
		for(var i=0; i<data.length; i++) {
			var item = data[i];
			var option = document.createElement('option');
			option.innerHTML = item.dong;
			option.value = item.dong_code;
			select.append(option);
		}
	},
	searchRoad: function()
	{
		if($j("#address_sido").val() == ""){
			alert("찾고자 하는 주소의 시도를 선택하세요.");
			$j("#address_sido").focus();
			return false;
		}
		if($j("#address_gugun").val() == ""){
			alert("찾고자 하는 주소의 시구군를 선택하세요.");
			$j("#address_gugun").focus();
			return false;
		}
		if($j("#address_road").val() == ""){
			alert("찾고자 하는 주소의 도로명를 입력하세요.");
			$j("#address_road").focus();
			return false;
		}
		
		if(!validCheck.numeric($j('#address_sido').val()) || !validCheck.numeric($j('#address_gugun').val())){
			alert("올바르지 않은 요청입니다.");
			return false;
		}
		
		var address_road = $j('#address_road').val().replace(/ /g, '');
		if(!/^[가-힣]/.test(address_road) || /[^가-힣0-9\s]/.test(address_road)){
			alert("도로명를 올바르게 입력해주세요.");
			$j("#address_road").focus();
			return false;
		}
		
		var build_no = $j('#address_build').val().replace(/ /g, '');
		
		if(build_no != "" && (!/^[0-9]+\-?\d+$/.test(build_no))){
			alert("건물 번호를 올바르게 입력해주세요.");
			$j("#address_build").focus();
			return false;
		}

		var arr = [];
		arr.push( $j("#address_sido").val());
		arr.push( $j("#address_gugun").val());

		var URL = "https://biz.gabia.com/common/addr_ajax/gabia_address_api_json?jsoncallback=jsonp";

		$j.ajax({
			type: 'get',
			url: URL,
			dataType: "jsonp",
			data: {
				'type':'road_name',
				'sido_code':arr[0],
				'gugun_code':arr[1],
				'word':address_road,
				'extra_no': build_no,
				'flag':'getDetailAddList'
			},
			success: function(data) {
				if("SUCCESS" == data.resultCode) {
					myinfo.searchRoadResult(data.result);
				}
			}
		});
	},
	searchRoadResult: function(data)
	{
		var tbl = $j("#address_detail");
		tbl.find("tbody").empty();
		tbl.show();
		
		if(data.length > 0) {
			for(var i=0; i<data.length; i++)
			{
				var item = data[i];
				var r = document.createElement('tr');
				tbl.find("tbody").append(r);

				var d = document.createElement('td');
				d.className = 'center';
				d.innerHTML = item.new_zipcode;
				$j(r).append(d);

				var d = document.createElement('td');
				d.innerHTML = item.new_addr_kor;
				$j(r).append(d);

				var d = document.createElement('td');
				d.innerHTML = "<button class='weakblue' onclick='myinfo.closeApply("+i+");'>선택</button>";
				$j(r).attr("tno", i);
				$j(r).append(d);
			}
		}else {
			var r = document.createElement('tr');
			tbl.find("tbody").append(r);

			var d = document.createElement('td');
			d.className = 'center';
			d.colSpan = 3;
			d.innerHTML = "결과 없음";
			$j(r).append(d);
		}

		var top = ($j("#mypage_layer_address").outerHeight() / 2) * -1;
		
		$j("#mypage_layer_address").css({"margin-top":(top)+"px"});
	},
	searchJibun: function()
	{
		var sido = $j("#address_sido").val();
		var gugun = $j("#address_gugun").val();
		var dong = $j("#address_dong").val();
		if(sido == ""){
			alert("찾고자 하는 주소의 시도를 선택하세요.");
			$j("#address_sido").focus();
			return false;
		}
		if(gugun == ""){
			alert("찾고자 하는 주소의 시구군를 선택하세요.");
			$j("#address_gugun").focus();
			return false;
		}
		if(dong == ""){
			alert("찾고자 하는 주소의 동/읍/면 이름을 입력하세요.");
			$j("#address_dong").focus();
			return false;
		}
		
		if(!validCheck.numeric(sido) || !validCheck.numeric(gugun) || !validCheck.numeric(dong)){
			alert("올바르지 않은 요청입니다.");
			$j("#address_dong").focus();
			return false;
		}
		
		var jibun = $j('#address_jibun').val().replace(/ /g, '');
		
		if(jibun != "" && (!/^[0-9]+\-?\d+$/.test(jibun))){
			alert("지번을 올바르게 입력해주세요.");
			$j("#address_jibun").focus();
			return false;
		}

		var arr = [];
		arr.push( sido );
		arr.push( gugun );
		arr.push( dong );

		var URL = "https://biz.gabia.com/common/addr_ajax/gabia_address_api_json?jsoncallback=jsonp";

		$j.ajax({
			type: 'get',
			url: URL,
			dataType: "jsonp",
			data: {
				'type':'ji_num',
				'sido_code':arr[0],
				'gugun_code':arr[1],
				'word':arr[2],
				'extra_no': jibun,
				'flag':'getDetailAddList'
			},
			success: function(data) {

				if("SUCCESS" == data.resultCode) {
					myinfo.searchJibunResult(data.result);
				}
			}
		});
	},

	searchJibunResult: function(data)
	{
		var tbl = $j("#address_detail");
		tbl.find("tbody").empty();
		tbl.show();
		
		if(data.length > 0) {
			for(var i=0; i<data.length; i++)
			{
				var item = data[i];
				var r = document.createElement('tr');
				tbl.find("tbody").append(r);

				var d = document.createElement('td');
				d.className = 'center';
				d.innerHTML = item.new_zipcode;
				$j(r).append(d);

				var d = document.createElement('td');
				d.innerHTML = item.old_addr_kor;
				$j(r).append(d);

				var d = document.createElement('td');
				d.innerHTML = "<button class='weakblue' onclick='myinfo.closeApply("+i+");'>선택</button>";
				$j(r).attr("tno", i);
				$j(r).append(d);
			}
		}else {
			var r = document.createElement('tr');
			tbl.find("tbody").append(r);

			var d = document.createElement('td');
			d.className = 'center';
			d.colSpan = 3;
			d.innerHTML = "결과 없음";
			$j(r).append(d);
		}
		
		var top = ($j("#mypage_layer_address").outerHeight() / 2) * -1;
		
		$j("#mypage_layer_address").css({"margin-top":(top)+"px"});
	},

	closeApply: function(tno)
	{
		var tbl = $j("#address_detail");
		var zipcode = tbl.find("tr[tno="+tno+"]").find("td").eq(0).html();
		var address = tbl.find("tr[tno="+tno+"]").find("td").eq(1).html();
		
		$j("#zipcode1").val(zipcode);
		if( $j('#extra_zipcode').length ) $j('#extra_zipcode').hide();
		$j("#address").val(address);
		hidePopup();
	}
	
	// 휴대전화 형식 체크
	,checkCellPhone : function(pCell){
		var pattern1 = /^\+[0-9](-?[0-9]){3,19}$/;	// 국제번호
		
		if(pCell.length > 20) return false;
		
		if(pattern1.test(pCell)){
			return pCell;
		}else{
			if(validCheck.cellphone(pCell)){
				return myinfo.changeCellStyle(pCell);
			}
		}
		
		return false;
	}
	
	// 휴대폰번호 서식 적용
	,changeCellStyle : function(cell_num){
		var cell = cell_num.replace(/[^0-9]/gi, '');
		var len = cell.length;
		if(len == 10){
			return cell.replace(/([0-9]{3})([0-9]{3})([0-9]{4})/, '$1-$2-$3');
		}else if(len == 11){
			return cell.replace(/([0-9]{3})([0-9]{4})([0-9]{4})/, '$1-$2-$3');
		}else{
			return cell;
		}
	}
};

$j(document).ready(function(){
	$j(document).on('click', 'input[name=cell_flag], input[name=email_flag], input[name=birth_flag], input[name=company_flag], input[name=address_flag], input[name=intro_flag]', function(){
		if($j(this).prop('checked')){
			$j(this).val('N');
		}else{
			$j(this).val('Y');
		}
	});
	
	$j('#cellPhone').on('blur', function(){
		if(validCheck.cellphone($j(this).val())){
			$j(this).val( myinfo.changeCellStyle($j(this).val()) );
		}
	});
});
