<%--
* 사용자 관리 페이지 : 구성원 리스트 
*
* [[개정이력(Modification Information)]]
*   수정일                  수정자               수정내용
* -----------   ---------  -----------------
* 2021. 1. 25.   이재형                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<script src="${cPath }/js/org/memList.js" type="text/javascript"></script>
<security:authentication property="principal" var="principal"/>
<c:set var="authMember" value="${principal.realMember }"/>

<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">인사정보</h2>
	</div>
</div>
<!-- End Page Header -->

<style>
.flex-container {
/* 	display: inline-flex; */
	display: flex;
 	flex-direction: column; 
 	flex-wrap: wrap; 
 	justify-content: flex-start; 
 	align-content: stretch; 
 	flex: 0; 
}

.container {
 	display: flex;
 	box-sizing:border-box;
}

.deptName {
	font-size: x-large;
}
.memberBox{
	display:inline-flex;
	width: 200px;
	margin-top: 20px;
	margin-right: 20px;
	padding-left: 15px;
	padding-top: 15px; 
}
.memberBox:hover{
	border:4px solid #BEEFFF;
	margin-top: 17.5px;
	margin-right: 18px;
	margin-left: 2px;
	margin-bottom: -3px;
	
}

.memberBox dd{
	display: flex;
	margin: 0;
}
.deptBox{
	margin-bottom: 30px;
}

.modal-body span.sub{
}
.modal-body{
	display:inline-block;
}

.modal-body td.left{
	height: 43px;
	width: 150px;
}

.modal-body .box{
	display:block;
	width : 100px;
	justify-content: center;
	margin : 0;
	align-self: center;
	
}
.memberBoxes {
	dispaly: inline-flex;
}
#toMemberInfo{
	display:none;
}

.imgBox{
	width: 200px;
    height: 200px; 
    border-radius: 70%;
    overflow: hidden;
    display: inline-block;
}

.memImg{
	width: 100%;
    height: 100%;
    object-fit: cover;
}

.box .value{
	display:block;
}


.custom-menu-name:hover{
	background-color: #BEEFFF; 
}
.custom-menu-name{
	font: bolder;
	text-align: center;
}
.label1{
	display:none;
}
.label2{
	display:none;
}

</style>
<%-- <link rel="stylesheet" type="text/css" href="${cPath }/css/org/zTreeStyle.css" /> --%>


<script>
// var myModal = document.getElementById('myModal')
// var myInput = document.getElementById('myInput')

// myModal.addEventListener('shown.bs.modal', function () {
//   myInput.focus()
// })

// var profileModal = new bootstrap.Modal(document.getElementById('profileModal'), {
// 	keyboard: false
// });
$(function(){
	$('#profileModal').modal({keyboard: true});
	$('#profileModal').modal('hide');
	$('#deptModal').modal({keyboard: true});
	$("#deptModal").modal('hide');


// 권한 확인
	var arr = new Array();
	<c:forEach items="${authMember.roleInfoList }" var="roleInfo">
		arr.push("${roleInfo.roleCode}");
	</c:forEach>
	if(arr.includes("ROLE_HRADMIN") || arr.includes("ROLE_ADMIN")){
		$("#btnUpdateDept").show();
		$("#btnDeleteDept").show();
		$("#btnChangeDept").show();
	}
	
});

// 부서 추가 버튼 클릭시 
$(document).on("click", "#btnUpdateDept", function(){
	$("#deptModalLabel").text("부서 추가");
	$("#btnInsertDept").show();
	$("#btnChangeDeptName").hide();
	$(".label1").show();
	$(".label2").hide();
	let thisDept = $(".curSelectedNode").children(".deptSpan").attr("value");
// 	console.log(thisDept)
	if(thisDept === undefined){
		alert("부서를 선택해주세요.");
		$("#deptModal").modal('hide');
	}else{
		$("#deptModal").modal("show");
		$("#deptModal").find("input").reset();
		let thisLevel = $(".curSelectedNode").attr("class").split(/\s+/);
		let level = thisLevel[0].substring(5);
		$("#deptCode").val(thisDept);
		$("#deptLevel").val(Number(level)+1);
// 		console.log($("#deptCode").val());
// 		console.log($("#deptLevel").val());
	}
});

// 부서 생성
$(document).on("click", "#btnInsertDept", function(){
	$.ajax({
		url: "${cPath}/admin/org/insertDept",
		data : {"deptName":$("#inputDeptName").val().trim(), "deptParentCode":$("#deptCode").val().trim(), "deptLevel":$("#deptLevel").val().trim()},
		type : "post",
		success:function(resp){
			console.log(resp);
			if(resp == "OK"){
				alert("성공적으로 저장되었습니다.");
			}else if(resp == "ALREADYEXIST"){
				alert("이미 존재하는 부서명 입니다.");
			}else{
				alert("저장에 실패하였습니다.");
			}
			
			$("#deptModal").modal('hide');
			$("#deptModal").find("input").val('');
			window.location.reload(true);
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
});

// 부서 삭제
$(document).on("click", "#btnDeleteDept", function(){
	let thisDept = $(".curSelectedNode").children(".deptSpan").attr("value");
	let deptData = {"deptCode":$(".curSelectedNode").children(".deptSpan").attr("value")};
	console.log(deptData);
	if(thisDept === undefined){
		alert("부서를 선택해주세요.");
	}else{
		let answer = confirm("하위에 속한 부서까지 모두 삭제 됩니다. 삭제 하시겠습니까?");
		if(answer){
			$.ajax({
				url : "${cPath}/admin/org/deleteDept",
				data : deptData,
				type : "post",
				success : function(resp){
					if(resp == "OK"){
						alert("성공적으로 삭제되었습니다.");
					}else{
						alert("삭제에 실패하였습니다.");
					}
					window.location.reload(true);
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});
		}
	}
});

// 부서 수정 버튼 클릭시
$(document).on("click", "#btnChangeDept", function(){
	$("#deptModalLabel").text("부서 수정");
	$("#btnChangeDeptName").show();
	$("#btnInsertDept").hide();
	$(".label2").show();
	$(".label1").hide();
	let thisDept = $(".curSelectedNode").children(".deptSpan").attr("value");
// 	console.log(thisDept)
	if(thisDept === undefined){
		alert("부서를 선택해주세요.");
		$("#deptModal").modal('hide');
	}else{
		$("#deptModal").modal("show");
		let thisLevel = $(".curSelectedNode").attr("class").split(/\s+/);
		let level = thisLevel[0].substring(5);
		$("#inputDeptName").val($(".curSelectedNode").children(".deptSpan").text()).focus();
		$("#deptCode").val(thisDept);
		$("#deptLevel").val(Number(level)+1);
// 		console.log($("#inputDeptName").val());
// 		console.log($("#deptCode").val());
// 		console.log($("#deptLevel").val());
	}
});

// 부서 수정
$(document).on("click", "#btnChangeDeptName", function(){
	$.ajax({
		url: "${cPath}/admin/org/modifyDeptName",
		data : {"deptName":$("#inputDeptName").val().trim(), "deptCode":$("#deptCode").val().trim()},
		type : "post",
		success:function(resp){
			console.log(resp);
			if(resp == "OK"){
				alert("성공적으로 저장되었습니다.");
			}else if(resp == "ALREADYEXIST"){
				alert("이미 존재하는 부서명 입니다.");
			}else{
				alert("저장에 실패하였습니다.");
			}
			
			$("#deptModal").modal('hide');
			$("#deptModal").find("input").val('');
			window.location.reload(true);
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
});



// document.addEventListener('mousedown', '.node_name:parent', function() {
// 	if ((event.button == 2) || (event.which == 3)) {
		
// 	}
// });

// $(document).on("contextmenu", ":parent.node_name", function(event) { 
// 	event.preventDefault();
//     let customMenu = $("<div>",{"class":"custom-menu"}).append($("<div>").append($("<span>", {"class":"custom-menu-name", "text":"수정"})).css({top: event.pageY + "px", left: event.pageX + "px"}));
//     customMenu.appendTo("body");
// }).on("click", "body", function(event) {
//     $("div.custom-menu").hide();
// });


// 부서명 우클릭 이벤트
// $(document).on("mousedown", '.node_name', function(event){
// 	$(".inputUpdateDeptName").remove();
// 	let deptName = $(this).text();
// 	let deptCode = $(this).attr("value");
// 	console.log(deptName);
// 	console.log(deptCode);
// 	_this = $(this);
// 	$(".custom-menu").remove();
	
// 	if ((event.button == 2) || (event.which == 3)) {
// // 		$(document).on("contextmenu", ":parent.node_name", function(e) { 
// // 		 	e.preventDefault();
// // 		});
// 		var div = document.createElement('div');
// 		var id = 'contextmenu';
// 		div.className = "custom-menu";
// 		div.id = id;
// 		div.style = 'display:none;position:fixed;width:150px; background: #fff;box-shadow:1px 1px 5px 0 rgba(0, 0, 0, 0.54)';
// 		document.body.appendChild(div);
// 		$(".custom-menu").append($("<div>", {"class":"custom-menu-name"}).append($("<span>", {"class":"custom-menu-name", "text":"수정"})));
// 		var customMenu = document.getElementById(id);
// 		$(document).on("contextmenu", ".node_name", function(e) {
// 			e.preventDefault();
			
// 			var x = e.pageX + 'px';
// 			var y = e.pageY + 'px';
// 			customMenu.style.display = 'block';
// 			customMenu.style.left = x;
// 			customMenu.style.top = y;
// 			$(document).on("click", ":not(.custom-menu-name)", function(e) {
// 				div.style.display = 'none';
// 			});
// 		});
// 	}
// 		$(document).on("click", ".custom-menu-name", function(){
// 			let input = $("<input>", {"class":"inputUpdateDeptName", "type":"text", "value":deptName}).prop("autofocus", true);
// 			_this.hide();
// 			_this.parent().append(input);
// 		});
// 		$(document).on("blur", ".inputUpdateDeptName", function(){
// 			_this.text($(".inputUpdateDeptName").attr("value"));
// 			_this.show();
// 			$(".inputUpdateDeptName").remove();
			
// 		});
// });
// var Contextmenu = !function(){
// 	var id = 'contextmenu';
// 	{
// 		var div = document.createElement('div');
// 		div.id = id;
// 		div.style = 'display:none;position:fixed;width:150px; height:200px; background: #fff;box-shadow:1px 1px 5px 0 rgba(0, 0, 0, 0.54)';
// 		document.body.appendChild(div);
// 	}
// 	var div = document.getElementById(id);
// 	document.addEventListener("contextmenu",function(e) {
// 		e.preventDefault();
		
// 		var x = e.pageX + 'px';
// 		var y = e.pageY + 'px';
// 		div.style.display = 'block';
// 		div.style.left = x;
// 		div.style.top = y;
// 	})
// document.addEventListener("click", function(e) {div.style.display = 'none'})
// }();

</script>



<!-- 프로필 Modal -->
<div class="modal effect-slide-in-bottom" id="profileModal" style="display: block; padding-right: 17px;" aria-modal="true" role="dialog" aria-labelledby="profileModalLabel">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-content-demo">
			<div class="modal-header">
				<h6 class="modal-title" id="profileModalLabel">직원 정보</h6>
				<button aria-label="Close" class="close" data-dismiss="modal" type="button"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
			</div>
			<div class="modal-footer">
				<button class="btn ripple btn-primary" type="button" id="toMemberInfo" onClick="location.href='${cPath}/org/memberInfo'">내 정보 관리</button>
				<button class="btn ripple btn-secondary" data-dismiss="modal" type="button">닫기</button>
			</div>
		</div>
	</div>
</div>

<!-- 부서 추가 Modal -->
<div class="modal effect-slide-in-right" id="deptModal" style="display: block; padding-right: 17px;" aria-modal="true" role="dialog" aria-labelledby="deptModalLabel">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content modal-content-demo">
			<div class="modal-header">
				<h6 class="modal-title" id="deptModalLabel">부서 추가</h6>
				<button aria-label="Close" class="close" data-dismiss="modal" type="button"><span aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<label for="inputDeptName" class="mg-r-10 label1">하위 부서명</label>
				<label for="inputDeptName" class="mg-r-10 label2">부서명</label>
				<input type="text" id="inputDeptName">
				<input type="hidden" id="deptCode">
				<input type="hidden" id="deptLevel">
			</div>
			<div class="modal-footer">
				<button class="btn ripple btn-primary" type="button" id="btnInsertDept" style="display:none;" >하위 부서 생성</button>
				<button class="btn ripple btn-primary" type="button" id="btnChangeDeptName" style="display:none;">수정</button>
				<button class="btn ripple btn-secondary" data-dismiss="modal" type="button">닫기</button>
			</div>
		</div>
	</div>
</div>


<div id="contents">
	<div class="row row-sm container col-lg-12">
		<div class="col-lg-2">
			<div class="card custom-card col-lg-12" style="display: inline-flex">
				<div class="card-body">
					<div>
						<h5 class="main-content-label">조직도</h5>
					</div>
					<div>
						<h4 style="display:inline;">부서</h4>
						<button class="btn ripple fe fe-plus" type="button" id="btnUpdateDept" style="width:40px; height:40px; display:none;" data-toggle="tooltip" title="하위 부서 생성"></button>
						<button class="btn ripple fe fe-x" type="button" id="btnDeleteDept" style="width:40px; height:40px; display:none;" data-toggle="tooltip" title="부서 삭제"></button>
						<button class="btn ripple  px-0" type="button" id="btnChangeDept" style="height:40px;display:none;" data-toggle="tooltip" title="부서 수정">수정</button>
					<hr style="margin-top: 0;margin-bottom: 20px;">
					</div>
					<div class="content_inbox">
						<div id="deptTreeDIV">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="col-lg-9">
			<div class="card custom-card flex-container">
				<div class="card-body ml-3">
					<div>
						<h5 class="main-content-label">직원 목록</h5>
					</div>
					<div class="deptMemTree" id="deptMemTree">
					</div>
					
				</div>
			</div>
		</div>
	</div>
</div>


	<script type="text/javascript">
		//====부서 트리======================================================== 
		const DEPT_TREE_DIV = $("#deptTreeDIV");
		// DEPT_TREE_DIV.load($.getContextPath() + "/ztree/dept");

		// $(document).on("click", ".deptLayerShow", function(){
		//    DEPT_TREE_DIV.slideToggle();
		// });

		// $(document).mouseup(function (e){
		//    if( DEPT_TREE_DIV.has(e.target).length === 0)
		//       DEPT_TREE_DIV.hide();
		// });
		var memdatalist = [];
		$(function() {
			var contextPath = "${cPath}";
			$.getContextPath = function() {
				return "${cPath }";
			}

			const folderArray = [];
			const setting = {
				data : {
					simpleData : {
						enable : true
					}
				},
				view : {
					showIcon : false,
					addDiyDom : addDiyDom,
					showLine : true
				}
			}; // setting end 
			
			
			
			$.ajax({
				url : $.getContextPath() + "/deptMemTree.do",
				dataType : "JSON",
				success : function(resp) {
					console.log(resp);
					$(resp).each(function(idx, dept) {
						folderArray.push({
							id : dept.deptCode,
							pId : dept.deptParentCode,
							name : dept.deptName,
							customClass : "deptSpan",
							value : dept.deptCode
						});
						
						$(dept.deptMemList).each(function(index, mem) {
// 							console.log("deptCode:"+dept.deptCode)
							memdatalist.push({
								memName : mem.memName,
								memId : mem.memId,
								jobCode : mem.jobCode,
								jobName : mem.jobName,
								psCode : mem.psCode,
								psName : mem.psName,
								deptName : mem.deptName,
								deptCode : mem.deptCode,
								memHdate : mem.memHdate,
								memMail : mem.memMail,
								memComtel : mem.memComtel,
								memHp : mem.memHp,
								memZip : mem.Zip,
								memAdd : mem.memAdd,
								memBirth : mem.memBirth,
								memEtc : mem.memEtc,
								memImg : mem.memImg
							});
						});
// 						console.log(memdatalist);
// 						console.log(folderArray[idx].name);
// 						console.log(folderArray.length);
					});
// 					$(memdatalist).each(function(index, mem){
// 						console.log(memdatalist[index]);
// 						$("div.memberBox").
// 					});
					let treeObj = $.fn.zTree.init($("#treeDemo"), setting, folderArray);
					treeObj.expandAll(true);
					
					for(var i=0; i<folderArray.length; i++){
						var deptBox = $("<div>", {"class":"deptBox"});
						var spanDeptName = $("<span>", {"class":"deptName", "data-dept-code":folderArray[i].id}).text(folderArray[i].name);
						
						let div = $("<div>", {"class":"memberBoxes"});
						$("#deptMemTree").append(deptBox.append(spanDeptName));
						for(var j=0; j<memdatalist.length; j++){
							if(memdatalist[j].memId != null || memdatalist[j].memId!= undefined){
								let memberBox = $("<div>", {"class":"memberBox bd rounded-10 shadow-base", "data-toggle":"modal", "data-target":"#profileModal"});
								let dl = $("<dl>", {"title":memdatalist[j].memName, "data-memid":memdatalist[j].memId});
								let dt = $("<dt>", {"class":"memName", "text":memdatalist[j].memName});
								let ddPsName = $("<dd>", {"class":"psName", "text":memdatalist[j].psName});
								let ddJobName = $("<dd>", {"class":"jobName", "text":memdatalist[j].jobName});
								let previewBox = $("<div>", {"class":"imgBox mg-r-20", "style":"width:60px; height:60px;"});
	// 							console.log(folderArray[i].id);
								let previewImg;
								if(memdatalist[j].memImg == null){
									previewImg = $("<img>", {"class":"memImg m-0", "src":"${cPath}/images/pngs/noImage.png", "alt":"img"});
								}else{
									previewImg = $("<img>", {"class":"memImg m-0", "src":"${cPath}/profileImages/"+memdatalist[j].memImg, "alt":"img"});
								}
								if(folderArray[i].id == memdatalist[j].deptCode){
									$("span[data-dept-code="+folderArray[i].id+"]").parent().append(div.append(memberBox.append(previewBox.append(previewImg)).append(dl.append(dt).append(ddPsName).append(ddJobName))));	
								}
							}
						}
					}
					
				},
				error : function(xhr) {
					console.log(xhr);
				}
			});

			function addDiyDom(treeId, treeNode) {
				var spaceWidth = 5;
				var spanObj = $("#" + treeNode.tId + "_span"), icoObj = $("#"
						+ treeNode.tId + "_ico");
				spanObj.remove();
				icoObj.before(spanObj);

				if (treeNode.level >= 1) {
					var spaceStr = "<span style='display: inline-block;width:"
							+ (spaceWidth * treeNode.level) + "px'></span>";
					spanObj.before(spaceStr);
				}
			}


		});

		// 부서 이름 클릭 이벤트 
		$(document).on("click", "a:has(.node_name)", function() {
			let deptName = $(this).children(".node_name").text();
			let deptCode = $(this).children(".node_name").attr("value");

// 			$("#filtered_org_name").text(deptName);
// 			$("#searchDept").val(deptCode);
// 			if($("span.deptName").data("deptCode")==deptCode){
				$("#deptMemTree").find(".deptBox").show();
				$("#deptMemTree").find(".deptBox").not($(".deptName[data-dept-code="+deptCode+"]").parent()).hide();
// 				console.log($(this));		
// 				var treeObj = $.fn.zTree.getZTreeObj(this);
// 				var nodes = treeObj.getSelectedNodes();
// 				console.log(nodes);
// 			}
// 			$(".curSelectedNode").parent().parent().show();
		});
		
		// 멤버명 클릭 이벤트
		$(document).on("click", ".memberBox", function(){
			_this = $(this);
			$("#profileModal .modal-body").children().remove();
			$(memdatalist).each(function(index, mem){
				if(mem.memId == _this.children("dl").data("memid")){
					console.log(mem.memId);
					console.log(_this.children("dl").data("memid"));
					let memName = $("<h5>", {"text":mem.memName, "class":"value"});
					let memId = $("<input>", {"type":"hidden", "id":"memId", "value":mem.memId});
					let jobCode = $("<span>", {"text":mem.jobCode, "class":"value"});
					let jobName = $("<span>", {"text": mem.jobName, "class":"value"});
					let psCode = $("<span>", {"text": mem.psCode, "class":"value"});
					let psName = $("<span>", {"text": mem.psName, "class":"value"});
					let deptName = $("<span>", {"text" : mem.deptName, "class":"value"});
					let deptCode = $("<span>", {"text" : mem.deptCode, "class":"value"});
					let memHdate = $("<span>", {"text" : mem.memHdate, "class":"value"});
					let memMail = $("<span>", {"text" : mem.memMail, "class":"value"});
					let memComtel = $("<span>", {"text" : mem.memComtel, "class":"value"});
					let memHp = $("<span>", {"text" : mem.memHp, "class":"value"});
					let memZip = $("<span>", {"text" : mem.Zip, "class":"value"});
					let memAdd = $("<span>", {"text" : mem.memAdd, "class":"value"});
					let memBirth = $("<span>", {"text" : mem.memBirth, "class":"value"});
					let memEtc = $("<span>", {"text" : mem.memEtc, "class":"value"});
					let memImg;
					console.log(mem.memImg);
// 					let imgTag = '<img width="500px" id="memImg" src="'+$.getContextPath() +'/profileImages/'+mem.memImg+'" alt="img">';
// 					<div class="avatar-preview">
// 					<c:if test="${not empty member.memImg }">
// 						<div id="imagePreview" style="background-image: url(${cPath}/profileImages/${member.memImg});"></div>
// 					</c:if>
// 					<c:if test="${empty member.memImg }">
// 						<div id="imagePreview" style="background-image: url(${cPath}/images/pngs/noImage.png);"></div>
// 					</c:if>
// 				</div>
					
					if(mem.memImg == null){
						memImg = $("<img>", {"class":"memImg", "src":"${cPath}/images/pngs/noImage.png", "alt":"img"});
					}else{
						memImg = $("<img>", {"class":"memImg", "src":"${cPath}/profileImages/"+mem.memImg, "alt":"img"});
					}
					
					$("#profileModal .modal-body").append($("<div>", {"class":"imgBox"}).append(memImg))
									.append($("<div>", {"class":"box"}).append(memName)
																	   .append(deptName)
																	   .append(psName)
																	   .append(jobName))
									.append($("<table>").append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"입사일", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memHdate)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"이메일", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append($("<span>", {"text":mem.memId+"@ddit.or.kr"}))))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"사내전화", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memComtel)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"휴대전화", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memHp)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"개인 이메일", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memMail)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"생년월일", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memBirth)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"자택 주소", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memAdd)))
														.append($("<tr>").append($("<td>", {"class":"left"}).append($("<span>", {"text":"기타 정보", "class":"sub"})))
														.append($("<td>", {"class":"right"}).append(memEtc))));
					let myId = "${authMember.memId}";
					console.log(myId);
					if(myId == mem.memId){
						$("#toMemberInfo").show();
					}else{
						$("#toMemberInfo").hide();
					}
					
				}
			});
			
		});
		
	</script>


<style type="text/css">
.ztree * {
	font-size: 10pt;
	font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Web Light",
		"Segoe UI Light", "Segoe UI Web Regular", "Segoe UI",
		"Segoe UI Symbol", "Helvetica Neue", Arial
}
/*
.ztree li ul {
	margin: 0;
	padding: 0
}

.ztree li {
	line-height: 30px;
}

.ztree li a {
	width: 200px;
	height: 30px;
	padding-top: 0px;
}

.ztree li a:hover {
	text-decoration: none;
	background-color: #E7E7E7;
}

.ztree li a span.button.switch {
	visibility: hidden
}

.ztree.showIcon li a span.button.switch {
	visibility: visible
}

.ztree li a.curSelectedNode {
	background-color: #D4D4D4;
	border: 0;
	height: 30px;
}

.ztree li span {
	line-height: 30px;
}

.ztree li span.button {
	margin-top: -7px;
}

.ztree li span.button.switch {
	width: 16px;
	height: 16px;
}
*/

.ztree li a span {
	font-size: 120%;
	font-weight: bold;
}
/*
.ztree li span.button {
	background-image: url("./left_menuForOutLook.png");
	*background-image: url("./left_menuForOutLook.gif")
}
*/
/*

.ztree li span.button.switch.level0 {
	width: 20px;
	height: 20px
}

.ztree li span.button.switch.level1 {
	width: 20px;
	height: 20px
}

.ztree li span.button.noline_open {
	background-position: 0 0;
}

.ztree li span.button.noline_close {
	background-position: -18px 0;
}

.ztree li span.button.noline_open.level0 {
	background-position: 0 -18px;
}

.ztree li span.button.noline_close.level0 {
	background-position: -18px -18px;
}
*/
</style>
<style type="text/css">
/*.ztree * {
	font-size: 1.09375rem;
	font-family: "Microsoft Yahei", Verdana, Simsun, "Segoe UI Web Light",
		"Segoe UI Light", "Segoe UI Web Regular", "Segoe UI",
		"Segoe UI Symbol", "Helvetica Neue", Arial
}

.ztree li {
	line-height: 20px;
}
*/
</style>