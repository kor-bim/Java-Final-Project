<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${cPath}/css/e_approvel/approvalDetail.css">
<link rel="stylesheet" type="text/css" href="${cPath}/css/e_approvel/approvalDetail2.css">
<div >
			<form autocomplete="off" onsubmit="return false;">
<input type="submit" style="display:none;" onclick="return false;">
<div class="content_title">
	<fieldset style="max-width:969px;">
				<span class="detail_select" id="btnWriteSaveDocument">
			<a href="javascript:void(0);" onclick="ApprovalDocument.saveDocument('WRITE');">기안하기</a>
		</span>
		<span class="detail_select hide" id="btnTempSaveDocument">
			<a href="javascript:void(0);" onclick="ApprovalDocument.saveDocument('TEMP');">임시저장</a>
		</span>
		<span class="detail_select hide" id="btnPreviewDocument">
			<a href="javascript:void(0);" onclick="ApprovalDocument.previewDocument();">인쇄미리보기</a>
		</span>
			</fieldset>
</div>
<div class="content_inbox">
	<div class="cont_box write">
		<div class="approval-wrap write">
			<h4  style="display:inline-block">
				기본 설정				<a href="javascript:void(0);" class="mgl_20 weakblue hide" id="approvalFormRule" onclick="ApprovalDocument.showApprovalFormRule('');">사내전자결재규정</a>
			</h4>
			<span class="gt-float-right gt-mt-5">
				<a href="/gabia.biz/approval/document/write_test" style="color:#2985db;">시험 사용 양식을 이용해보세요.</a>
			</span>
			<table class="tableType02">
				<caption>전자결재 기본 설정</caption>
				<colgroup>
					<col style="width:12.15%;">
					<col style="width:44.94%">
					<col style="width:12.15%">
					<col style="width:30.76%">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">문서 종류</th>
						<td>
														<select name="approval_category_no" class="write-select" autocomplete="off">
																<option value="1" selected>공통</option>
																<option value="312">휴가</option>
																<option value="325">테스트</option>
															</select>
							<input type="hidden" id="prevApprovalCategoryNo" value="1">
							<select name="approval_form_no" class="write-select" autocomplete="off">
								<option value="">선택</option>
																<option value="1456">지출결의서</option>
																<option value="1447">출장여비 지출품의서</option>
																<option value="1446">경조비 지급 신청서</option>
																<option value="1439">휴가계</option>
																<option value="1451">접대비 사용승인 신청서</option>
																<option value="1449">교통비 지출결의서</option>
																<option value="1444">휴직원</option>
																<option value="1443">휴직 사유서</option>
																<option value="1442">육아휴직 신청서</option>
																<option value="1441">미사용 연차휴가 사용계획서</option>
																<option value="1440">학자금 신청서</option>
																<option value="1438">출산 휴가 신청서</option>
																<option value="1437">퇴직금 명세서</option>
																<option value="1454">교통비/차량유지비 지출내역</option>
																<option value="3079">외부 공문</option>
															</select>
							<button class="weakblue" onclick="ApprovalDocument.getSelectApprovalForm();">문서보기</button>
							<input type="hidden" id="prevApprovalFormNo" value="">
							<input type="hidden" id="prevApprovalFormTitle" value="">
													</td>
						<th scope="row">작성자</th>
						<td>
														<input type="hidden" name="node_id" value="819">
							대표이사														<span>기관장 서동운</span>
						</td>
					</tr>
					<tr>
						<th scope="row">보존 연한</th>
						<td>
							<select name="preserved_term" class="fl write-select" id="set_preserved_term_y">
								<option value="">보존 연한</option>
																<option value="1">1년</option>
																<option value="3">3년</option>
																<option value="5">5년</option>
																<option value="10">10년</option>
																<option value="0">영구</option>
															</select>
							<span class="fl hide" id="set_preserved_term_n">년</span>
							<a href="javascript:void(0)" class="icon question tipsIcon" style="position: relative;top:4px;margin-left:10px"><span class="blind">세부 설명</span></a>
							<div class="tooltip hide" style="left:0;top:0">
								<div class="tooltip-box" style="width:570px;">
									<p>ㆍ1년: 경미한 연결 문서 및 일시적인 사용 또는 처리에 그치는 문서</p>
									<p>ㆍ3년: 사무의 수행상 1년 이상에 걸쳐 참고 또는 이용해야 할 문서 및 법률상 3년간 보존을 요하는 문서</p>
									<p>ㆍ5년: 사무의 수행상 3년 이상에 걸쳐 참고 또는 이용해야 할 문서 및 법률상 5년간 보존을 요하는 문서</p>
									<p>ㆍ10년: 사무의 수행상 장기간 참고 또는 이용해야 할 문서 및 법률상 10년간 보존을 요하는 문서</p>
									<p>ㆍ영구: 회사의 존속에 필요 불가결한 문서 및 역사적 또는 기타 사유로 중요한 문서</p>
								</div>
							</div>
						</td>
						<th scope="row">보안 등급</th>
						<td>
							<select name="security_level" class="fl write-select" id="set_security_level_y">
								<option value="">보안 등급</option>
																<option value="S">S등급</option>
																<option value="A">A등급</option>
																<option value="B">B등급</option>
																<option value="C">C등급</option>
															</select>
							<span class="fl hide" id="set_security_level_n">등급</span>
							<a href="javascript:void(0)" class="icon question tipsIcon" style="position: relative;top:4px;margin-left:10px"><span class="blind">세부 설명</span></a>
							<div class="tooltip hide" style="right:150px;top:0;">
								<div class="tooltip-box" style="width:450px;">
									<p>ㆍS등급: 기안 상에 설정된 관련자들만 문서를 볼 수 있으며, 결재자와 합의자만 참조자를 추가할 수 있음.</p>
									<p>ㆍA등급: 기안 상에 설정된 관련자들과 관리자가 설정한 1등급(기관장)등급 이상인 사람이 문서를 볼 수 있음. 결재자와 합의자는 참조자를 추가할 수 있음.</p>
									<p>ㆍB등급: 기안 상에 설정된 관련자들과 관리자가 설정한 3등급(조사관)등급 이상인 사람이 문서를 볼 수 있음. 결재자, 합의자, 참조자 모두 참조자를 추가할 수 있음.</p>
									<p>ㆍC등급: 모든 임직원이 문서를 열람할 수 있지만, 기안 상에 설정된 관련자들만 참조자를 추가할 수 있음.</p>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="after">
				<h4 class="fl mgr_20">결재선</h4>
				<button type="button" class="weakblue vt hide" id="btnApprovalAdressbookSelect" onclick="ApprovalDocument.getApprovalLineSelect();">결재선설정</button>
			</div>
			<div id="approvalDocumentLine">
				문서 종류 선택 시 결재선이 노출됩니다.			</div>
			
			<div class="write_input js-approval-input hide mgt_50">
				<h4 class="fl">제목</h4>
				<div class="txt title">
					<div class="position">
						<input type="text" name="approval_document_title" id="approval_document_title" value="">
					</div>
				</div>
			</div>

			<h4 class="mgt_50">상세 입력</h4>

			<div class="write_input js-approval-input-guide">
				문서 종류 선택 시 상세 입력이 노출됩니다.			</div>

						<div class="write_input hide gt-pl-25" id="approvalEditorContent">
				<div class="txt file">
					<div id="dexteditor_holder"></div>
					<textarea style="display:none" id="compose_contents"></textarea>
                </div>
			</div>
			
			<div class="js-approval-input hide gt-pl-25" id="approvalDbContent"></div>

			<div class="write_input js-approval-input hide">
				<label for="write_txt3" class="blind">파일 첨부</label>
				<div class="txt file">
					<div class="position">
						<div class="file-list" id="dragZone" style="min-height: 66px;">
							<div class="top">
								<p class="left">
									<span class="body-color mgr_20 viewAttachedFileArea">별첨</span>
									<a href="javascript:void(0);" class="addfile viewAttachedFileArea" onclick="$j('#fileApprovalAttach').click();">파일 첨부</a>
									<input type="file" style="overflow: hidden; width:0px; height:0px;" name="approval_attach" id="fileApprovalAttach" multiple>
									<span class="weakgray viewAttachedFileArea">|</span>
									<a href="javascript:void(0);" class="addfile" onclick="ApprovalDocument.getRelatedDocumentLayer();">관련문서 첨부</a>
								</p>
								<p class="right viewAttachedFileArea hide" id="approvalAttachSize"><span id="attachAttachSum"></span> / 50MB</p>
							</div>
							
							<div class="center viewAttachedFileArea show" id="approvalAttachText">
								여기로 파일을 끌어놓으세요							</div>	
							<div class="list hide" id="approvalAttachList">
								<table id="tableApprovalRelated">
									<caption></caption>
									<colgroup>
										<col width="">
									</colgroup>
									<tbody>
																		</tbody>
								</table>
								<table id="tableApprovalAttach">
									<caption></caption>
									<colgroup>
										<col width="">
									</colgroup>
									<tbody>
																		</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="approval_document_no" value="">
<input type="hidden" name="approval_first_line" value="20627|819">
<input type="hidden" name="approval_second_line" value="">
<input type="hidden" name="approval_third_line" value="">
<input type="hidden" name="approval_fourth_line" value="">
</form>
<script>
ApprovalDocument._documentNo = '';
ApprovalDocument._firstLine = '20627|819';
ApprovalDocument._secondLine = '';
ApprovalDocument._thirdLine = '';
ApprovalDocument._fourthLine = '';
ApprovalDocument._approvalMethod = '';
ApprovalDocument._formType = '';
ApprovalDocument._registerNo = '20627';

Approval._documentMode = "WRITE";
Approval._writeType = 'write';
Approval._defaultFontFamily = '맑은 고딕';
Approval._defaultFontSize = '16px';
Approval._defaultLineHeight = '1.6';

var approvalRelatedDocumentTable = new approvalTableClass( {table:"tableRelatedDocument", row : [["action", ""], ["star", ""], ["document_code", "docu-num"], ["none_link_title", "title"], ["register", "docu-register"]]} );

$j(document).ready(function(){
	autoComplete();
	Approval.settingDblClickForDeleteApprovalSelectUser();
		
			ApprovalDocument._editFlag = 'N';
		var approval_editor = makeEditor_dext();
	
	$j(document).on("click", ".js-approval-form-line-delete", function(){
		if( $j(this).parent().length > 0 ){
			ApprovalForm.deleteApprovalLine($j(this).parent().attr('line'), $j(this).parent().attr('user_no'));
			$j(this).parent().remove();
		}
	});

	$j(document).on("change", "select[name='approval_category_no']", function(){
		ApprovalDocument.getApprovalFormByCategory($j(this).val());
	});


	$j(document).on("change", "select[name='approval_form_no']", function(){
		ApprovalDocument.changeApprovalForm($j(this).val());
	});

		
	$j(document).on("change", "select[name='node_id']", function(){
		ApprovalDocument.changeNodeId($j(this).val());
		if(typeof dbUnitWrite === "object")
		{
			dbUnitWrite.setInsaOrgBySelectedNodeId();
		}
	});
	
	$j(document).on("click", '.js-approval-line-delete', function(){
		if($j(this).parent().parent().attr("id") === undefined){
			alert("다시 시도해주시기 바랍니다.");
			return;
		}

		var approval_line_id_name = {'approvalFirstLine':'approval_first_line', 'approvalSecondLine':'approval_second_line', 'approvalThirdLine':'approval_third_line', 'approvalFourthLine':'approval_fourth_line'};
		var approval_line_id = $j(this).parent().parent().attr("id");
		var approval_line_name = approval_line_id_name[approval_line_id];

		$j(this).parent().remove();
		ApprovalDocument.saveApprovalLine(approval_line_id, approval_line_name);

		if(ApprovalDocument._universalSetting === 'Y'){
			ApprovalUniversal.deleteUser($j(this).parent().attr('user_no'));
		}
	});

	$j('#fileApprovalAttach').fileupload({
		url : '/gabia.biz/approval/upload_file/attach',
		sequentialUploads : true,
		dataType: 'json',
		paramName: 'approval_attach',
		add: function(e, data){
			console.log("start");
			var isValid = true;

			var total_size = get_attached_file_sum();
			for(var i=0; i<data.originalFiles.length; i++)
			{
				total_size += data.originalFiles[i].size;
			}
			
			var uploadFile = data.files[0];
			if(uploadFile.size > 50 * 1024 * 1024 || total_size > 50 * 1024 * 1024){
				alert('파일 첨부는 50MB까지입니다.');
				isValid = false;
			}
			if($j('.js-approval-attach').length >= 100){
				alert('파일 첨부는 100개까지입니다.');
				isValid = false;
			}
			if($j('.viewAttachedFileArea:visible').length === 0){
				isValid = false;
			}

			if(isValid){
				data.submit();
			}else{
				return false;
			}
		}

		,progressall: function(e,data) {
			console.log('progressall');
			Common._createProgress();
		}

		,done: function (e, data) {
			Common._closeProgress();

			if($j('.js-approval-attach').length >= 100){
				alert('파일 첨부는 100개까지입니다.');
				return;
			}

			$j('#approvalAttachText').hide();
			$j('#approvalAttachSize').show();
			$j('#approvalAttachList').show();

			if(data.result.resultCode === RT_OK){
				$j("#tableApprovalAttach").append($j("<tr><td>" + data.result.text + "</td></tr>"));
			}

			set_attached_file_sum();
		}

		,fail: function(e, data){
			Common._closeProgress();
			alert('서버와 통신 중 문제가 발생했습니다. 다시 시도해주시기 바랍니다.');
		}

		,drop: function(e, data)
		{

		}

		,dropZone: $j("#dragZone")
	});

	$j("#dragZone")
		.on('drag dragstart dragend dragover dragenter dragleave drop', function(e)
		{
			e.preventDefault();
			e.stopPropagation();
		})
		.on('dragover dragenter', function(){
			if($j('.viewAttachedFileArea:visible').length){
				$j("#dragZone").addClass("drag");
			}
		})
		.on('dragleave dragend drop', function(){
			if($j('.viewAttachedFileArea:visible').length){
				$j("#dragZone").removeClass("drag");
			}
		});
});

$j(document).on('click', '.js-approval-attach-delete', function(){
	$j(this).parent().parent().parent().remove();

	if($j('.js-approval-attach-delete').length === 0 && $j('.js-approval-related').length === 0){
		$j('#approvalAttachText').show();
		$j('#approvalAttachSize').hide();
		$j('#approvalAttachList').hide();
	}

	set_attached_file_sum();
});

$j(document).on("click", '.js-approval-btn-box-mode', function(){
	if($j(this).parent().children("ul").hasClass("dropdown-menu")){
		$j(this).parent().children("ul").toggleClass("show");
		$j(".js-approval-li-types").removeClass("active");
	}
});

$j(document).on("blur", '.js-approval-box-type', function(){
	if( $j('#menuApprovalTypeMode').hasClass("show") ){
		$j('#menuApprovalTypeMode').toggleClass("show");
	}
});

$j(document).on("mousedown", '.js-approval-li-types', function(event){
	event.preventDefault();
	$j('.js-approval-li-forms').removeClass("active");
	$j(this).addClass("active");
	$j('#anchorApprovalType').html("보기: " +$j(this).text());
	$j("#menuApprovalTypeMode").toggleClass("show");
	ApprovalDocument._searchRelatedDocument['type'] = $j(this).attr("value");
	ApprovalDocument._searchRelatedDocument['page'] = '1';
	ApprovalDocument.getRelatedDocumentList();
});

$j(document).on("keydown", '#textSearchRelatedDocument', function(e){
	if(e.keyCode == 13){ // enter
		e.preventDefault();
		e.stopPropagation();

		ApprovalDocument.searchRelatedDocument();
	}
});

$j(document).on("click", '#anchorApprovalUserOrder', function(){
	var options = $j('#orderApprovalDropdown').find('a');
	var orderKey = getCookie('orderKey');

	if(!orderKey) {
		orderKey = 'name';
	}

	for(var i=0; i < options.length; i++) {
		var option = options[i];

		if(option.onclick.toString().includes(orderKey)) {
			option.style.fontWeight = 'bold';
		} else {
			option.style.fontWeight = '';
		}
	}
	$j('#orderApprovalDropdown').toggleClass("show");
});

$j(document).on("click", '.js-approval-node-order', function(){
	// $j("#treeDiv .selectedNode").  click();
});

var set_attached_file_sum = function()
{
	var sum = 0;
	$j('.js-approval-attach').each(function(index, value){
		if($j(value).attr('deleted') && $j(value).attr('deleted') === 'Y'){
			return true;
		}
		if($j(value).attr("size")){
			sum += parseInt($j(value).attr("size"));
		}

		if($j(value).hasClass('hide')){
			$j(value).closest('tr').hide();
		}
	});

	if(sum <= 0){
		$j('#approvalAttachSize').hide();
	}else{
		$j('#approvalAttachSize').show();
	}
	$j("#attachAttachSum").html(Approval.formatFileSize(sum));
}

var get_attached_file_sum = function()
{
	var sum = 0;
	$j('.js-approval-attach').each(function(index, value){
		if($j(value).attr("size")){
			sum += parseInt($j(value).attr("size"));
		}
	});

	return sum;
}

var set_attached_file = function(pText)
{
	if($j('.js-approval-attach').length >= 100){
		alert('파일 첨부는 100개까지입니다.');
		return;
	}

	$j('#approvalAttachText').hide();
	$j('#approvalAttachSize').show();
	$j('#approvalAttachList').show();

	$j("#tableApprovalAttach").append($j("<tr><td>" + pText + "</td></tr>"));

	set_attached_file_sum();
}

window.onbeforeunload = function() {
	if(ApprovalDocument._documentSubmit === false){
		return "작성중인 내용은 저장되지 않습니다.";
	}
};

$j(document).on('click', 'a[href^="javascript:void"]', function(e){
	e.preventDefault();
});

// 기안 복사 삭제된 계정 안내

function dext_editor_before_insert_url_event(editorId, url) {
	var pValue = JSON.parse(url);

	if(pValue.resultCode === 'SUCCESS'){
		set_attached_file(pValue.text);
		return pValue.image;
	}else{
		alert(pValue.message);
		return '';
	}

	return '';
}

// 본문 내 용량 변경 시
function dext_editor_contentsize_change_event(editorID, sizeInfoObj) {
	console.log('content_change');
	var dom = DEXT5.getD5Dom(editorID);
	var cDomImages = dom.body.getElementsByTagName('img');
	var cImages = [];
	
	for(var i=0; i<cDomImages.length; i++){
		var imgSrc = cDomImages[i].src;
		if(imgSrc.indexOf('/approval/image/view/') !== -1){
			var findFileNo = imgSrc.split('/');
			cImages.push(findFileNo.pop());
		}
	}
	
	$j('.js-approval-attach:hidden').each(function(k, v){
		if($j.inArray($j(v).attr('file'), cImages) === -1){
			$j(v).attr('deleted', 'Y');
		}else{
			$j(v).removeAttr('deleted');
		}
	});

	set_attached_file_sum();
}

var ApprovalUniversal = new ApprovalUniversalClass('ApprovalUniversal', 'ApprovalDocument.saveApprovalLineSelectUniversal');
ApprovalUniversal.setConfig({showAutoLine : false});
</script>
		</div>
			</div>
	<div id="dimmed"></div>
	<div id="dimmed2"></div>
	<style>
	#dimmed2 {display: none;position: fixed;width: 100%;height: 100%;z-index: 2000;background-color: #000;opacity: .3;top: 0;left: 0;margin: 0;padding: 0;}
 	.layer_box.mid_large.auto-logout {width: 480px;z-index: 2010;}
	</style>
</div>
<div id='main_layer_div'>
	
</div>

<div class="layer_box small alarm hide popup1 " style="" id='HWA_MAIN'>
	<p class="text" id='HWA_MSG'></p>
	<div class="layer_button">
		<button class="btn_variables" type="button" onclick="hidePopup();" id='HWA_MAIN_OK'>확인</button>
	</div>
	<a href="javascript:void(0)" class="icon btn_closelayer" onclick="hidePopup();" title="레이어 닫기"><span class="blind">레이어 닫기</span></a>
</div>

<div class="layer_box large hide dns_intro_layer" style="width:550px;">
	<div class="title_layer text_variables">메일 서비스 이용 안내</div>
	<p class="bold body_bold">메일 서비스를 이용하시려면 하이웍스용 DNS 정보 설정이 필요합니다.</p>
	<p class="mgt_15"><span style="display:inline-block;width:3px;height:3px;vertical-align:middle;background:#676767;"></span> 가비아 등록 도메인은 자동 설정을 지원합니다.</p>
	<div class="layer_button mgt_10 ta_l">
		<button type="button" class="btn_variables" onclick="location:href=window.open('https://domain.gabia.com/api/hiworksdns/domainsetting')">가비아 도메인 자동 설정하기</button>
	</div>
	<p class="pdt_20"><span style="display:inline-block;width:3px;height:3px;vertical-align:middle;background:#676767;"></span> 타사 등록 도메인은 DNS 정보를 직접 설정해야 합니다.</p>
	<div class="layer_button mgt_10 ta_l">
		<button type="button" class="btn_variables" onclick="location:href=window.open('https://customer.gabia.com/manuals_pop/manual_set.php?service=webmail_hosting&amp;fact=mailzine&amp;seq_no=2221')">설정 방법 보기</button>
	</div>
	<p class="bold body_bold mgt_50">※ 하이웍스용 DNS 정보 설정을 완료하셨다면 메일 용량을 할당한 후 메일 서비스를<br>
	이용할 수 있습니다. ‘오피스 관리’에서 계정별 메일 용량을 설정해주세요.</p>
	<div class="layer_button mgt_10 ta_l">
		<button type="button" class="btn_variables" onclick="location:href=window.open('/gabia.biz/admin/Orgmain')">오피스관리 페이지 가기</button>
	</div>
	<a href="javascript:void(0)" class="icon btn_closelayer" onclick="hidePopup();" title="레이어 닫기"><span class="blind">레이어 닫기</span></a>
</div>

<script type="text/javascript">
jQuery( "#topMenuBtn" ).click(function() {
	if(jQuery("#gnb_layer").html().trim() == ""){
		jQuery.ajax({
			url : Common.getRoot() + "menu/manage_ajax/showMenu",
			data : {},
			type : 'post',
			dataType : 'json',
			async :	true,
			success : function(data){
				if(data.resultCode !== undefined && data.resultCode === RT_LOGOUT){
					console.log("logout");
					Common.showLogout(data);
					return;
				}

				jQuery("#gnb_layer").html(data.result);
				recentCount();
			},
			error : function(){
				return;
			}
		});	
	}else{
		recentCount();
	}
});

recentCount = function()
{
	jQuery.ajax({
		url : Common.getRoot() + "common/menu/recent_count",
		data : {},
		type : 'post',
		dataType : 'json',
		async :	true,
		success : function(data){
			if(data.resultCode !== undefined && data.resultCode === RT_LOGOUT){
				console.log("logout");
				Common.showLogout(data);
				return;
			}

			if(data.mail !== undefined && jQuery('#CNT_BADGE_MAIL_LINK').length > 0){
				jQuery('#CNT_BADGE_MAIL_LINK').css('display', 'block');
				jQuery("#CNT_BADGE_MAIL_LINK").html(data.mail);
				if(data.mail == 0) jQuery("#CNT_BADGE_MAIL_LINK").css("display", "none");
			}

			if(data.ea !== undefined && jQuery('#CNT_BADGE_EA_LINK').length > 0){
				jQuery('#CNT_BADGE_EA_LINK').css('display', 'block');
				jQuery("#CNT_BADGE_EA_LINK").html(data.ea);
				if(data.ea == 0) jQuery("#CNT_BADGE_EA_LINK").css("display", "none");
			}
		},
		error : function(){
			return;
		}
	});	
}


// web_alarm 추가 
jQuery( "#bell_btn" ).click(function() {

	if(Common.getDomain() == 'gabia.com'){
		var url = Common.getRoot() + "common/notification_ajax/get_list";
	}else{
		var url =Common.getRoot() + "common/menu/web_alarm";
	}

	if(jQuery("#_alarm_div").html().trim() == ""){
		jQuery.ajax({
			url : url,
			data : {},
			type : 'post',
			dataType : 'json',
			async :	true,
			success : function(data){
				if(data.resultCode !== undefined && data.resultCode === RT_LOGOUT){
					console.log("logout");
					Common.showLogout(data);
					return;
				}

				jQuery("#_alarm_div").html(data.result);
			}
		});
	}else{
		jQuery("#notify_div").remove()
	}
});

recentAlarmCount = function()
{
	if(Common.getDomain() == 'gabia.com'){
		var url = Common.getRoot() + "common/notification_ajax/get_count";
	}else{
		var url = Common.getRoot() + "common/menu/web_alarm_cnt";
	}

	jQuery('.notification').show();
	jQuery.ajax({
		url : url,
		data : {},
		type : 'post',
		dataType : 'json',
		async :	true,
		success : function(data){
			if(data.resultCode !== undefined && data.resultCode === RT_LOGOUT){
				console.log("logout");
				Common.showLogout(data);
				return;
			}

			if(data.result > 0 ){
				jQuery('#bell_btn').addClass('alram')
				jQuery('.wa_count_class').show()
				jQuery('.wa_count_class').text(data.result)
			}
		}
	});	
}

jQuery( document ).ready(function() {
	recentAlarmCount();
});
var CHECK_TIME_OUT = window.setInterval(Common.showTimeout, 6*60*60*1000);
</script>