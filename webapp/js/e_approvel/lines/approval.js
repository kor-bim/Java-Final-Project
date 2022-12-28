/**
 * 
 */

// 초기 셋팅
	
	var APPROVALTABLEBODY =`
		<tr class="nameZone firstTR" style="height:30px;">
		<th class="position firstTH"></th>
		<th class="position"></th>
		<th class="position"></th>
		<th class="position"></th>
		<th class="position"></th>
		<th class="position"></th>
		<th class="position"></th>
		<th class="position lastTD"></th>
		</tr>
		<tr class="signZone" style="height:80px;">
		<td class="sign firstTD"></td>
		<td class="sign"></td>
		<td class="sign"></td>
		<td class="sign"></td>
		<td class="sign"></td>
		<td class="sign"></td>
		<td class="sign"></td>
		<td class="sign lastTD"></td>
		</tr>
		<tr class="nameZone lastTR" style="height:30px;">
		<td class="name firstTD"></td>
		<td class="name"></td>
		<td class="name"></td>
		<td class="name"></td>
		<td class="name"></td>
		<td class="name"></td>
		<td class="name"></td>
		<td class="name lastTD"></td>
		</tr>`
	var LISTBODY = $("#approvalBody");
	var REFERBODY = $("#referenceBody");
	var bodyContent = $(APPROVALTABLEBODY.trim().replaceAll(/\s{2,}/igm, " "));
	
function makeLineInit(){
	LISTBODY.html(bodyContent);
	
	var psnamezone = LISTBODY.find("tr.firstTR th.firstTH");
	var signzone = LISTBODY.find("tr.signZone td.firstTD");
	var namezone = LISTBODY.find("tr.lastTR td.firstTD");
	
	let inputTags = [];
	inputTags.push(
			$("<input type='hidden'>").attr({name:"approvalMember", value : myInfo.memId })
			, myInfo.psName
	);
	psnamezone.append(inputTags);
	namezone.text(myInfo.memName);
}

// 빈값 체크 함수
var isEmpty = function(value){ 
		if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){ 
			return true 
		}else{ 
			return false 
		} 
	};

//=============================================================================================================================
function makeApprovalLineForm(lineData){
	// lineData : 가져온 자료
	// approval(결재자), reference(참조자), needBtns(버튼필요여부), needSigns(사인필요여부)
	let approval = lineData.approval;   // 결재자 arr
	let reference = lineData.reference; // 참조자 arr
	let needBtns = lineData.needBtns;   // 버튼 필요 여부 : true(필요. 진행중인문서), false(불필요. 기안작성. 문서함. 관리자의 목록조회)
	let needSigns = lineData.needSigns; // 서명 필요 여부 : true(필요. 진행중인문서. 문서함. 관리자의 목록조회), false(불필요. 기안작성)

	LISTBODY.html(bodyContent);
	var psnamezone = LISTBODY.find("tr.firstTR");
	var signzone = LISTBODY.find("tr.signZone");
	var namezone = LISTBODY.find("tr.lastTR");
	
	var waitCnt = 0;
	// waitCnt == 1 이고 myInfo.memId 가 내 아이디일때 버튼 생기게 하려고. 

	// 결재자
	if(approval.length <= 8){
		for(let i = 0 ; i < 8 ; i++){
			let memId    = ''
				, memName  = ''
			    , deptName = ''
			    , psName   = ''
			    , aldtCode = ''
			    , memSignImg = ''
			    , SignDate = ''
			    , stateCode = ''  // 대기 혹은 완료
			    , resultCode = ''  // 각자 결재한 결과 (승인, 협의요청, 반려, 전결,  ) 
			    , signDate = '';

			let targetTH_ps = psnamezone.find("th:eq("+i+")");
			let targetTD_img = signzone.find("td:eq("+i+")");
			let targetTD_name = namezone.find("td:eq("+i+")");
			
			if(i < approval.length){
				memId = approval[i].memId;
				memName = approval[i].memName;
				deptName = approval[i].deptName;
				psName = approval[i].psName;
				aldtCode = approval[i].aldtCode;
				memImg = approval[i].memSignImg;
				resultCode = approval[i].aprvlTypeCode;
				stateCode = approval[i].aprvlStateCode;
				if(stateCode == "WAIT"){
					waitCnt++;
				}
				signDate = approval[i].adAd;
				

				// 직위
				targetTH_ps.append(
						$("<input type='hidden'>").attr({name:"approvalMember", value : memId})
						, psName
				)
				
				// 서명 or 결재버튼
				let memSignImg = '<img width="80%" class="stamp ok" src="'+$.getContextPath() +'/profileImages/'+ memImg+'" alt="img">';
				let okImg = '<img width="80%;" class="stamp return" src="'+ $.getContextPath() +'/images/approval/stamp/ok.jpg" alt="승인">';
				let returnImg = '<img width="80%;" class="stamp return" src="'+ $.getContextPath() +'/images/approval/stamp/return.jpg" alt="반려">';
				let discussImg = '<img width="80%;" class="stamp return" src="'+ $.getContextPath() +'/images/approval/stamp/discuss.jpg" alt="협의요청중">';
				let allOk = '<div>전결</div>';
				
				let stampBtn = '<button type="button" style="width:80%; height:50px;" class="btn btn-outline-success appBtns" id="stampBtn" data-toggle="modal" data-target="#myModal"><span>결  재</span></button>';
				let stamRequestBtn = '<button type="button"  style="width:80%; height:50px;" class="btn btn-outline-success appBtns" id="stampRequestBtn" data-toggle="modal" data-target="#myModal">협의중</button>';
				let checkBtn = '<button type="button"  style="width:80%; height:50px;" class="btn btn-outline-success appBtns" id="checkBtn" data-toggle="modal" data-target="#myModal">확인</button>';
				
				if(resultCode == 'OK'){
					if(memImg == null || memImg.length == 0){
						needSigns == true ? targetTD_img.append(okImg) : targetTD_img.append('') ;
					}else{
						needSigns == true ? targetTD_img.append(memSignImg) : targetTD_img.append('');
					}
				}else if(resultCode == "RETURN"){
					needSigns == true ? targetTD_img.append(returnImg) : targetTD_img.append('');
				}else if(resultCode == "REQUEST"){
					if(memId == myInfo.memId){
						needBtns == true ? targetTD_img.append(stamRequestBtn) : targetTD_img.append('');
					}else{
						needSigns == true ? targetTD_img.append(discussImg) : targetTD_img.append('');
					}
				}else if(resultCode == 'ALLOK'){
					needSigns == true ? targetTD_img.addClass("slash").append("<div class='text-left mb-4'>전결</div>") : targetTD_img.append('');
				}else{
					if(waitCnt == 1){
						if(memId == myInfo.memId){
							needBtns == true ? targetTD_img.append(stampBtn) : targetTD_img.append('');
						}
					}
				}
				// 결재일
				if(!isEmpty(signDate)){
					signDate = signDate.substr(0,10);
					targetTD_img.append("<div>",signDate);
				}
				
				
				// 이름
				targetTD_name.append(memName);

			}
		}
	} // 결재자 end

	// 참조자
	let cnt = reference.length;
	for(let i = 0 ; i < cnt ; i++){
		let memId    = '';
		let memName  = '';
		let deptName = '';
		let psName   = '';
		let aldtCode = '';
		let stateCode = '';
		
		memId = reference[i].memId;
		memName = reference[i].memName;
		deptName = reference[i].deptName;
		psName = reference[i].psName;
		aldtCode = reference[i].aldtCode;
		stateCode = reference[i].aprvlStateCode;
		
		let btnTag = null;
		if(stateCode == "WAIT"){
			if(memId == myInfo.memId){ // 내 아이디이고 대기중이면 
				btnTag = $("<input type='button'>").attr({id:"confirmBtn_"+memId, value:"확인", onclick:"$('#referenceForm').submit();"}).addClass("btn btn-info ml-2")
			}
		}else{ // 확인했으면
			btnTag = $("<span>").append(
				$("<i>").addClass("fas fa-check").css("color", "green")
			)
		}
		
		REFERBODY.append(
			$("<div class='col-auto d-flex'>").append(
				$("<div class='pt-2'>").append(
					$("<input type='hidden'>").attr({name:"referenceMember", value : memId})
					, memName
					, btnTag
				)		
			)
		)
			
	}
}
//==========================================================================================================================================

// JSON으로 받아온 lineDetail 값을 결재폼을 위한 데이터로 변환 
function makeLineDataByJSON(jsonData){
	let approvalMember = [];
	let referenceMember = [];
	
	let lines = jsonData.lineDetailList;
	for(i = 0 ; i < lines.length ; i++ ){
		let line = lines[i];
		if(line.aldtCode == 'DRAFT' || line.aldtCode == 'APPROVAL'){
			approvalMember.push(line);
		}else if(line.aldtCode == 'REFERENCE'){
			referenceMember.push(line);
		}
	}
	let lineData = {
			approval : approvalMember
			, reference : referenceMember
		}
	return lineData;
}

