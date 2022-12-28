<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.newFolder {
	border-top: 1px dotted black;
	border-bottom: 1px dotted black;
}
.hide {
	display: none !important;
}
.show {
	display: block !important;
}
#pagingArea {
	float: right;
}
#fileFancy {
	float: right;
}
.fileChk{
	width: 20px;
	height: 20px;
}
</style>
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5" id="pageName">개인 파일보관함</h2>
		<ol class="breadcrumb">
		</ol>
	</div>
</div>
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<!-- 상단 메뉴바 START -->
				<div class="card custom-card mr-2" style="display: inline;">
					<input style="font-size:1.2rem;" class="btn btn-outline-success" type="button" id="createFolderBtn" value="새폴더" />
					<input style="font-size:1.2rem;" class="btn btn-outline-success" type="button" id="downloadBtn" value="다운로드" />
					<input style="font-size:1.2rem;" type="button" id="mailFormOpen" class="btn btn-outline-primary" value="메일보내기">
					<input style="font-size:1.2rem;" class="btn btn-outline-secondary" type="button" id="deleteFileBtn" value="삭제" />
				</div>
				<!-- 상단 메뉴바 END -->
				<br/>
				<br/>
				<!-- 새폴더 생성 폼 START -->
				<div id="createFolderDiv" class="d-flex p-1 mb-2 newFolder hide">
					<form:form commandName="fileBoxVO" id="createForlderForm" action="${cPath }/fileBox/folderInsert">
						<input type="hidden" id="fileType" value="PRIVATE"/>
						<table class="table table-hover mb-0">
							<colgroup>
								<col width="8%">
								<col width="32%">
								<col width="10%">
								<col width="50%">
							</colgroup>
							<tr>
								<th>
				                    <div class="">새 폴더명</div>
								</th>
								<th style="height:100%;">
				                    <div class="col-auto">
				                    	<input class="" id="fNameInput" type="text" name="fileRealName" style="width:100%;" placeholder="폴더 이름을 입력하세요.">
				                    	<input class="" id="fileParent" type="hidden" name="fileParent">
				                    </div>
								</th>
								<th>
				                    <div class="d-flex">
				                    	<input id="" class="btn btn-outline-success mr-1" type="submit" value="확인"/>
				                    	<input id="formCancleBtn" class="btn btn-outline-secondary" type="button" value="취소"/>
				                    </div>
								</th>
								<th>
				                   	<div id="folderNameError" class="error_msg" style="display: none;">
				       	            	<i class="fas fa-exclamation-circle"></i>
				   	                    <span class="" style="font-size:1rem;">지정한 이름을 가진 폴더가 이미 있습니다. 다시 입력해주세요.</span>          
				                    </div>
								</th>
							</tr>
						</table>
					</form:form>				
				</div>
				<!-- 새폴더 생성 폼 END-->

				<div>
					<table class="table text-nowrap text-md-nowrap table-bordered mg-b-0 table-hover" id="fileBoxTable">
						<colgroup>
							<col width="4%">
							<col width="45%">
							<col width="17%">
							<col width="17%">
							<col width="17%">
						</colgroup>
						<thead class="text-center">
							<tr>
								<th>
									<input type="checkbox" class="fileChk" id="allFileChk" value=""/>
								</th>
								<th>이름</th>
								<th>크기</th>
								<th>확장자</th>
								<th>등록날짜</th>
							</tr>
						</thead>
						<tbody id="fileListBody">
							<c:set var="fileList" value="${pagingVO.dataList }" />
							<c:if test="${not empty fileList }">
								<script type="text/javascript">
									let $filedata = null;
									let $data = new Object();
								</script>
								<c:forEach items="${fileList }" var="file">
									<tr id="fileListTr_${file.fileNo}">
										<td class="text-center">
											<c:if test="${file.fileDir eq 'N' }">
												<input type="checkbox" name="fileNo" class="fileChk" value="${file.fileNo }" data-dir="N"/>
											</c:if>
											<c:if test="${file.fileDir eq 'Y' }">
												<input type="checkbox" name="fileNo" class="fileChk" value="${file.fileNo }" data-dir="Y"/>
											</c:if>
										</td>
										<td>
										<div class="d-flex">
											<c:if test="${file.fileDir eq 'N' }">
												<span>${file.fileRealName }</span>
											</c:if>
											<c:if test="${file.fileDir eq 'Y' }">
												<a href="javascript:void(0);" class="infolder" 
													 data-fileno="${file.fileNo }" data-parentno="${file.fileParent ? file.fileParent : 0 }" data-fname="${file.fileRealName}">
													<i class="fa fa-folder-open" aria-hidden="true"></i>
													<span id="folderName_${file.fileNo }">${file.fileRealName }</span>
												</a>
												<input type="text" id="fileRealName_${file.fileNo }" style="display: none;" value="${file.fileRealName}" />
											</c:if>
											<c:if test="${file.fileDir eq 'N' }">
												<button type="button" class="btn btn-outline-light btn-sm ml-3" id="onedown_${file.fileNo}"> 다운로드</button>
											</c:if>
											<c:if test="${file.fileDir eq 'Y' }">
												<button type="button" class="btn btn-outline-light btn-sm ml-3" id="folderBtn_${file.fileNo }" data-fname="${file.fileRealName}"> 폴더수정</button>
												<button type="button" class="btn btn-outline-light btn-sm ml-3" id="updateBtn_${file.fileNo }" style="display:none;"> 수정</button>
											</c:if>
											</div>
										</td>
										<td><span id="fileFancy">${file.fileFancy }</span></td>
										<td><span>${fn:split(file.fileRealName,'.')[1]}</span></td>
										<td class="text-center">${file.fileDate }</td>
									</tr>
									<script type="text/javascript">
										$data = {
											fileNo : "${file.fileNo}"
											, fileDir : "${file.fileDir}" 	
											, fileName : "${file.fileName}"
											, fileRealName : "${file.fileRealName}"
										}
										$("#fileListTr_${file.fileNo}").data("file", $data );
									</script>
								</c:forEach>
							</c:if>
							<c:if test="${empty fileList }">
								<tr>
									<td colspan="5" >파일이 존재하지않습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<form id="searchForm">
						<input type="hidden" name="page" />
					</form>
					<br/>
					<div id="pagingArea">${pagingVO.pagingHTML }</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 파일 업로드 폼 -->
<jsp:include page="/WEB-INF/views/fileBox/fileUploadModal/fileUploadForm.jsp"></jsp:include>

<!-- 파일 삭제 확인  modal-->
<div class="modal fade bd-example-modal-sm" id="fileDeleteModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="fileDeleteModalLabel">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<table class="table">
					<tr>
						<td align="center">파일을 삭제하시겠습니까?</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<input type="button" value="삭제" class="btn btn-outline-warning" id="fileDeleteBtn" />
				<button type="button" class="btn btn-outline-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<form id="downloadForm" action="${cPath }/fileBox/download" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="fileNos" id="fileNos"/>
</form>

<form id="folderList" action="${cPath }/fileBox/folderList" method="get">
	<input type="hidden" name="fileNo" />
</form>

<div class="main-mail-compose">
	<form action="${cPath}/mail/sendMail" method="post" enctype="multipart/form-data" id="mailSendForm">
		<div class="container">
			<div class="main-mail-compose-box">
				<div class="main-mail-compose-header">
					<span>새 메일 작성하기</span>
					<nav class="nav">
						<a class="nav-link" href="">
							<i class="fas fa-minus"></i>
						</a>
						<a class="nav-link" href="">
							<i class="fas fa-compress"></i>
						</a>
						<a class="nav-link" href="">
							<i class="fas fa-times"></i>
						</a>
					</nav>
				</div>
				<div class="main-mail-compose-body">
					<div class="form-group mb-0">
						<label class="form-label">받는 사람</label>
						<select id="receiverId" name="receiverId" class="form-control text-black" multiple="multiple" style="width: 1000px; border: none;">
							<c:if test="${not empty memberList }">
								<c:forEach items="${memberList }" var="member">
									<option value="${member.memId}">${member.memName}(${member.memId})-${member.psName}/${member.deptName}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="form-group mb-0">
						<label class="form-label">제목</label>
						<div>
							<input name="mailTitle" class="form-control" type="text">
						</div>
					</div>
					<div class="form-group">
						<textarea id="editor4" name="mailContent" class="form-control" placeholder="" rows="12"></textarea>
					</div>
					<div class="form-group mg-b-0">
						<nav class="nav">
							<input type="file" class="multi form-control" id="mailFiles" name="mailFiles" />
						</nav>
						<button id="formSubmit" type="submit" class="btn ripple btn-primary">전송</button>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>
<script src="${cPath}/js/mail/mailForm.js"></script>
<script type="text/javascript" src="${cPath }/js/file/fileBox.js?v=1"></script>

<!-- 운주 스크립트 -->
<script type="text/javascript">

// 폴더 상위-하위 이동 
	var $history = [];
	var $hisName = [];

	$(document).on("click", "a.infolder", function(){
		var fileNo = $(this).data("fileno");     // 클릭된 폴더 번호 
		var parentNo = $(this).data("parentno"); // 클릭된 폴더의 부모
		var pageName = $("#pageName").text(); // 현재페이지 이름 (돌아갈 페이지 이름)
		var folderName = $(this).data("fname");   // 클릭된 폴더 이름

		$("#fileParent").val(fileNo);

		$history.push((parentNo == null || parentNo == 'undefined' || parentNo == "" ) ? 0 : parentNo);
		$hisName.push(pageName);
		getFolder(fileNo, folderName);

	});
	
	$(document).on("click", "a.outFolder", function(){
		var parentNo = $history.pop();
		var folderName = $hisName.pop();
		getFolder(parentNo, folderName);
	})
	
	function getFolder(parentNo, folderName){
		$.ajax({
			url : $.getContextPath() + "/fileBox/folderList",
			data : {fileNo : parentNo
				, fileType : "PRIVATE"	
			},
			dataType : "json",
			success : function(resp) {

				$("#pageName").text(folderName);
				let trTags = [];
				if(resp.length > 0){
					$(resp).each(function(idx, file){
						if($history.length != 0){
							if(idx == 0){
								trTags.push(
									$("<tr>").append(
										$("<td>").append(
											$("<input>").attr({type:"hidden", name:"fileNo"})
										),
										$("<td>").append(
											$("<a href='javascript:void(0);' class='outFolder'>").text("... 상위폴더로")
										).attr("colspan", "5")
									)
								)
							}
						}
						if(file.fileDir == 'Y'){
							trTags.push(
								$("<tr>").append(
									$("<td>").attr("class", "text-center").append(
										$("<input type='checkbox' data-dir='Y'>").css({width:"20px", height:"20px"}).attr({name:"fileNo", value: file.fileNo })
									)
									, $("<td>").append(
										$("<div>").append(
											$("<a href='javascript:void(0);' class='infolder'  data-fileno='"+file.fileNo+"'data-parentno='"+(file.fileParent == null ? "0" : file.fileParent)+"' data-fname='"+file.fileRealName+"'>").append(
												$("<i>").attr({"class":"fa fa-folder-open", "aria-hidden":true})
												,$("<span>").attr("id", "folderName_"+file.fileNo).text(file.fileRealName)
											)
											, $("<input type='text'>").attr({id:"fileRealName_"+file.fileNo, name:"fileRealName", value:file.fileRealName}).css("display", "none")
											, $("<button type='button'>").attr({"class":"btn btn-outline-light btn-sm ml-3 ", id:"folderBtn_"+file.fileNo, "data-fname":file.fileRealName}).text("폴더수정")
											, $("<button type='button'>").attr({"class":"btn btn-outline-light btn-sm ml-3 ", id:"updateBtn_"+file.fileNo}).css("display", "none").text("수정")
										).attr("class", "d-flex")
									)
									, $("<td>").text(file.fileFancy)
									, $("<td>").text(file.fileExtns)
									, $("<td>").text(file.fileDate)
								).data("file", file)
							)
						} else {
							trTags.push(
								$("<tr>").append(
									$("<td>").append(
										$("<input type='checkbox' data-dir='N'>").css({width:"20px", height:"20px"}).attr({name:"fileNo", value: file.fileNo })
									).attr("class", "text-center")	
									, $("<td>").append(
										$("<span>").text(file.fileRealName)
										, $("<input type='button'>").attr({"class":"btn btn-outline-light btn-sm ml-3", value:"다운로드","id":"onedown"})
									)
									, $("<td>").append(
										$("<span>").text(file.fileFancy).css("float", "right")		
									)
									, $("<td>").text(file.fileRealName.split(".")[1])
									, $("<td>").text(file.fileDate).attr("class", "text-center")
								).data("file", file)
							)
						}
					});
				}else{
					trTags.push(
							$("<tr>").append(
								$("<td>").append(
									$("<a href='javascript:void(0);' class='outFolder'>").text("... 상위폴더로")
									, $("<input>").attr({type:"hidden", name:"fileNo", value:parentNo})
								).attr("colspan", "5")
							)
						)
				}
				$("#fileListBody").html(trTags);
			}
		});
	}
	
	$(".leftInFolder").on("click",function(){
		let parentNo = $(this).data("fileno");
		let folderName = $(this).data("filename");

		$history.push((parentNo == null || parentNo == 'undefined' || parentNo == "" ) ? 0 : parentNo);
		$hisName.push(pageName);
		
		getFolder(parentNo, folderName);
	});

</script>
