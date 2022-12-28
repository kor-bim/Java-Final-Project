<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
#fileload {
	display: none;
}
</style>

<!-- 파일 업로드 Modal -->
<div class="modal fade" id="fileUploadModal" data-backdrop="static"
	tabindex="-1" role="dialog" aria-labelledby="fileUploadModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="fileUploadModalLabel">파일 업로드</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div>
					<h5>대상 폴더 선택</h5>
					<select style="width: 380px; color: black;" id="fileParent" class="form-control">
						<option value=''>개인파일보관함</option>

						<c:set var="folderList" value="${selectFolderList }"/>
						<c:if test="${not empty folderList }">
							<c:forEach items="${folderList }" var="fList">
								<option value='${fList.fileNo }'>${fList.fileRealName }</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<hr>
				<div>
					<h5>파일 첨부</h5>
					<button type="button" id="btn-upload"
						class="btn btn-outline-success">파일선택</button>
					<input type="file" id="fileload" name="selectFile"
						multiple="multiple" />
					<button type="button" class="btn btn-outline-success"
						onclick="removeFiles()">삭제</button>
				</div>
				<hr>
				<div>
					<table class="table table-hover mb-0" id="displayFileTable"
						style="font-size: 1.0rem;">
						<colgroup>
							<col width="5%">
							<col width="55%">
							<col width="20%">
							<col width="20%">
						</colgroup>
						<thead style="background-color: #f7f7f7">
							<tr>
								<td style="font-size: 1.2rem;"><input type="checkbox"
									id="allFileCheck"></td>
								<td style="font-size: 1.2rem;">파일명</td>
								<td style="font-size: 1.2rem;">확장자</td>
								<td style="font-size: 1.2rem;">파일크기</td>
							</tr>
						</thead>
						</tbody>
					</table>
					<div style="height: 300px; overflow: auto;">
						<table class="table table-hover" id="fileListTable"
							style="font-size: 1.0rem;">
							<colgroup>
								<col width="5%">
								<col width="55%">
								<col width="20%">
								<col width="20%">
							</colgroup>
						</table>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-outline-primary" id="uploadBtn">업로드</button>
				<button type="button" class="btn btn-outline-secondary"
					data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	$(function() {
		$('#btn-upload').click(function(e) {
			e.preventDefault();
			$('#fileload').click();
		});
	});
	
	// 파일 업로드 폼 
	var $fileListArr = new Array();
	var $totSize = 0;
	var $keyNum = 0;
	var $limit = 0;
	
	let uploadNames = [];
    $('#fileload').change(function(evt) {
    	var files = $(this)[0].files;
    	var fileArr = new Array();
    	
    	fileArr = $fileListArr;
    	$limit = $totSize;
    	for(var i = 0 ; i < files.length; i++){
    		$limit = $limit + files[i].size;
    		if($limit > 20000000){
    			alert("첨부파일 용량은 20MB를 넘길수 없습니다.");
    			return false;
    		}
    	}
    	
    	for(var i = 0 ; i < files.length; i++){
    		var file = files[i];
    		var fileSize = (Math.floor(file.size/1000) > 1000) ? ((Math.floor(file.size/1000))/1000 + " MB"): (Math.floor(file.size/1000) +" KB");
			// 중복으로 들어가지 않게 함. 
    		if(uploadNames.length > 0){
    			console.log()
    			if(uploadNames.indexOf(file.name)>=0){
    				continue;
    			}
    		}
    		uploadNames.push(file.name)
    		
    		$("#fileListTable").append(
    			"<tr id='file'"+$keyNum+">"
    			+ "<td><input type='checkbox' name='filechk'/></td>"
    			+ "<td>"+file.name+"</td>"		
    			+ "<td>"+file.name.split(".")[1]+"</td>"	
    			+ "<td id='fileSize'"+$keyNum+"><p class='file"+$keyNum+"'>"+fileSize+"</p></td>"		
    			+ "</tr>"
    		);
    		$keyNum++;
    		fileArr.push(file);
    		$totSize = $totSize + file.size;
    	}
    	$fileListArr = new Array();
    	$fileListArr = fileArr;
    	$("#totSize").text("");
    	$("#totSize").text(Math.floor($totSize/1000000));
    });		
	
// 전체 선택, 전체 해제
    $("#allFileCheck").on("click", function(){
    	if($("#allFileCheck").prop("checked")){
    		$("input[name=filechk]").prop("checked", true);
    	}else{
    		$("input[name=filechk]").prop("checked", false);
    	}
    })

// 업로드할 목록에서 삭제 
    function removeFiles(){
    	$("#fileListTable input[type=checkbox]:checked").closest("tr").remove();
    }

    
</script>