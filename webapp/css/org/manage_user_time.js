var mUserTime = {
	// 근태 초기화
	initTimeData : function(){
		var msg = '전 직원 근태 현황을 초기화합니다. 근태 현황 초기화 시, 모든 근태 기록은 삭제되며 복원할 수 없습니다. 근태 기록 백업은 리스트 상단 엑셀 다운로드로 할 수 있습니다. 근태 현황을 초기화하시겠습니까?';
		if(confirm(msg)){
			var url = Common.getRoot() + 'insa/manage/time_manage/init_time';
			getAjaxData(url, null, function(data){
				if(data.resultCode == 'SUCCESS'){
					alert(data.message);
				}
			});
		}
	},
	pYear : new Date().getFullYear(),
	pMonth : new Date().getMonth() + 1,
	pType : 'M',
	pOrder : 'org',
	pOrderType : 'asc',
	pSearchValue : '',
	pPage : 1,
	getManageTimeList : function(page){
		Common._createBackGround();
		Common._createProgress();
		mUserTime.pPage = page;
		mUserTime.pSearchValue = $j('#search_txt').val();
		var url = Common.getRoot() + 'insa/manage/time_manage/manage_time_list';
		var params = {
				'pPage':page, 'pYear':mUserTime.pYear, 'pMonth':mUserTime.pMonth, 'pType':mUserTime.pType, 
				'pOrder':mUserTime.pOrder, 'pOrderType':mUserTime.pOrderType,
				'pValue': mUserTime.pSearchValue
		};
		getAjaxData(url, params, function(data){
			if(data.resultCode == 'SUCCESS'){
				$j('#user_time_month_table').html(data.result);
				$j('#user_time_count').html(data.totalCount);
				$j('#user_time_paginate').html(data.paging);
			}
			Common._closeBackGround();
			Common._closeProgress();
		});
	},
	
	// 월단위, 연단위 보기
	filterMonthYear : function(type){
		if(type == 'M'){
			mUserTime.pType = 'M';
			$j('#filter_type_year').removeClass('active');
			$j('#filter_type_month').addClass('active');
			$j('#filter_type_name').html('월 단위');
		}else if(type == 'Y'){
			mUserTime.pType = 'Y';
			$j('#filter_type_month').removeClass('active');
			$j('#filter_type_year').addClass('active');
			$j('#filter_type_name').html('연 단위');
		}
		mUserTime.getManageTimeList(mUserTime.pPage);
	},
	
	// 페이징
	setUserTimePage : function(page){
		mUserTime.getManageTimeList(page);
	},
	
	// 월 단위 정렬
	setOrderTimeList : function(ele, field){
		
		if(field == 'name'){
			mUserTime.pOrder = 'name';
		}else{
			mUserTime.pOrder = 'org';
		}
		
		var updown = $j(ele).find('span');
		
		if(updown.length){
			if(updown.hasClass('up')){
				mUserTime.pOrderType = 'desc';
				updown.addClass('down').removeClass('up');
			}else{
				mUserTime.pOrderType = 'asc';
				updown.addClass('up').removeClass('down');
			}
		}else{
			$j(ele).closest('tr').find('a.updown').find('span').remove();
			$j(ele).append('<span class="up"></span>');
		}
		
		mUserTime.getManageTimeList(mUserTime.pPage);
	},
	
	// 검색 : 검색어 엔터
	searchKeyDown : function(e){
		if($j('#search_cancel_btn').hasClass('hide')){
			$j('#search_cancel_btn').removeClass('hide');
		}
		if($j('#search_txt').val() == ''){
			$j('#search_cancel_btn').addClass('hide');
		}
		if(e.keyCode == 13){
			mUserTime.searchUserTime();
		}
	},
	searchUserTime : function(){
		mUserTime.getManageTimeList(1);
	},
	// 검색취소
	cancelSearchUserTime : function(){
		$j('#search_cancel_btn').addClass('hide');
		$j('#search_txt').val('');
		mUserTime.searchUserTime();
	},
	
	// 월 이동
	moveMonth : function(move){
		if(move == 'left'){
			mUserTime.pMonth--;
			if(mUserTime.pMonth < 1){
				mUserTime.pYear--;
				mUserTime.pMonth = 12;
			}
		}else if(move == 'right'){
			mUserTime.pMonth++;
			if(mUserTime.pMonth > 12){
				mUserTime.pYear++;
				mUserTime.pMonth = 1;
			}
		}
		var month = mUserTime.pMonth;
		var year = mUserTime.pYear;
		$j('#today_date').html(year + '. '+ (month < 10 ? ('0'+month) : month));
		mUserTime.getManageTimeList(mUserTime.pPage);
	},
	
	// 연 이동
	moveYear : function(move){
		if(move == 'left'){
			mUserTime.pYear--;
			if(mUserTime.pYear < 1){
				mUserTime.pYear = new Date().getFullYear();
			}
		}else if(move == 'right'){
			mUserTime.pYear++;
		}
		$j('#today_year').html(mUserTime.pYear);
		mUserTime.getManageTimeList(mUserTime.pPage);
	},
	
	// 월단위 15일 이동
	moveTimeDisplay : function(ele){
		var idx = $j(ele).closest('th').index();

		if(idx == 15){
			$j(ele).closest('tr').find('th:lt(16)').hide();
			$j(ele).closest('tr').find('th:gt(15)').show();
			$j(ele).closest('thead').find('th.btnfield').attr('colspan', $j(ele).closest('tr').find('th:gt(15)').length);

			$j(ele).closest('table').find('tbody').find('tr:even').each(function(key, val){
				var index = $j(this).find('td:gt(3).bdr_0', 0).index();

				$j(this).find('td:lt('+ (index + 1) +')').not('td:lt(4)').hide();
				$j(this).find('td:gt(' + index + ')').show();
			});

			$j(ele).closest('table').find('tbody').find('tr:odd').each(function(key, val){
				var index = $j(this).find('td.bdr_0', 0).index();

				$j(this).find('td:lt(' + (index + 1) + ')').hide();
				$j(this).find('td:gt(' + index + ')').show();
			});
		}else{
			$j(ele).closest('tr').find('th:lt(16)').show();
			$j(ele).closest('tr').find('th:gt(15)').hide();
			$j(ele).closest('thead').find('th.btnfield').attr('colspan', 16);

			$j(ele).closest('table').find('tbody').find('tr:even').each(function(key, val){
				var index = $j(this).find('td:gt(3).bdr_0', 0).index();

				$j(this).find('td:lt('+ (index + 1) +')').not('td:lt(4)').show();
				$j(this).find('td:gt(' + index + ')').hide();
			});

			$j(ele).closest('table').find('tbody').find('tr:odd').each(function(key, val){
				var index = $j(this).find('td.bdr_0', 0).index();

				$j(this).find('td:lt(' + (index + 1) + ')').show();
				$j(this).find('td:gt(' + index + ')').hide();
			});
		}
	},
	
	// 엑셀 다운로드
	excelDownload : function(){
		$j('#excel_year').val(mUserTime.pYear);
		$j('#excel_month').val(mUserTime.pMonth);
		$j('#excel_type').val(mUserTime.pType);
		$j('#excel_order').val(mUserTime.pOrder);
		$j('#excel_order_type').val(mUserTime.pOrderType);
		$j('#excel_search_value').val(mUserTime.pSearchValue);
		$j('#user_time_excel').submit();
	},
	
	// 상세보기 레이어
	showUserTimeDetail : function(year, month, num){	
		var params = {
				'pMenu' : 'get_user_time_detail_layer',
				'pNo' : num,
				'pYear' : year,
				'pMonth' : month,
				'pCallback' : 'mUserTime.resultShowUserTimeDetail'
		};
		mUserTime.Ajax(params);
	},
	
	resultShowUserTimeDetail : function(pValue)
	{
		if($j('#insa_time_detail').length){
			$j('#insa_time_detail').remove();
		}
		
		$j('#wrap').append(pValue.result);
		$j('#insa_time_detail').showPopup();
		mUserTime.getUserTimeDetailList();
	},
	
	getUserTimeDetailList : function()
	{
		var no = $j('#insa_time_detail #user_no').val();
		var year = $j('#insa_time_detail #time_filter_year').val();
		var month = $j('#insa_time_detail #time_filter_month').val();
		var type = $j('#insa_time_detail #time_filter_type').val();
		
		var params = {
				'pMenu' : 'get_user_time_detail_list',
				'pNo' : no,
				'pYear' : year,
				'pMonth' : month,
				'pType' : type,
				'pCallback' : 'mUserTime.resultGetUserTimeDetailList'
		};
		
		mUserTime.Ajax(params);
	},
	
	resultGetUserTimeDetailList : function(pValue)
	{
		userTimeDetailTable.insertRow(pValue);
		$j('#insa_time_detail #time_detail_count').html(pValue.totalCount);
	},
	
	// 근태 수정 요청
	modifyTime : function(pNo, pType){
		location.href = Common.getRoot() + 'insa/vacation_time/time/view/'+pNo + '/' + pType;
	},

	Ajax : function(pParam, pSync, pFlag)
	{
		if(pFlag === undefined){
			Common._createBackGround();
			Common._createProgress();
		}
		if(pSync == undefined) pSync = true;
		getAjaxData(Common.getRoot() + 'insa/manage/time_manage_ajax/', pParam, mUserTime.AjaxCallback, pSync);
	},
	
	AjaxCallback : function(pValue)
	{
		Common._closeProgress();
		Common._closeBackGround();
		if (pValue.resultCode == RT_OK)
		{
			var methods = (new Function('return ' + pValue.layerID))();
			try
			{
				methods.call(this, pValue);
			} catch(E)
			{
				alert("callback : " + E);
				console.log(E);
			}
		}
		else
		{
			if(pValue.message){
				alert(pValue.message);
			}
			return;
		}
	}
};

$j(document).ready(function(){
	mUserTime.getManageTimeList(1);
	
	$j(document).on('click', '.layerShow', function(e){
		e.stopPropagation();
		$j('.dropdown').show();
	});
	$j(document).on('click', function(){
		$j('.dropdown').hide();
	});
});