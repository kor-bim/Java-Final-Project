	$("#pagingArea").on("click", "a", function(event) {
		event.preventDefault();
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		return false;
	});
	
	var searchForm = $("#searchForm");
	$("#searchBtn").on("click", function() {
		let inputs = $(this).parents("div#inputUI").find(":input[name]");
		$(inputs).each(function(index, input) {
			let name = $(this).attr("name");
			let value = $(this).val();
			let hidden = searchForm.find("[name='" + name + "']");
			hidden.val(value);
		});
		searchForm.submit();
	});


//== 파일 업로드 모달 
	
	$("#fileUploadBtn").on("click",	function() {
		$("#fileUploadModal").modal();
	});

	$("#allFileChk").on("click", function(){
		if($("#allFileChk").prop("checked")){
			$("input[name='fileNo']").prop("checked", true);
			
		}else{
			$("input[name='fileNo']").prop("checked", false);
		}
	});
	var $fType = $("#fileType").val() == "PRIVATE" ? "PRIVATE" : "PUBLIC";
	//업로드 버튼 클릭 
	$("#uploadBtn").on("click", function(){
		$fileParent = $("#fileParent option:checked").val();
		
		var formData = new FormData();
		formData.append("memId", myInfo.memId);
		formData.append("fileType", $fType);
		
		if($fileParent){
			formData.append("fileParent", $fileParent);
		}
		for(var i = 0; i < $fileListArr.length ; i++){
			formData.append("uploadFile" , $fileListArr[i]);
		}
		
		$.ajax({
			url: $.getContextPath() + "/fileBox/fileUploadInsert.do",
			processData : false,
			contentType : false,
			data: formData ,
			type : 'POST',
			success : function(res){
				location.href = $.getContextPath() + '/fileBox/myFile';
			},
			error: function(){
				alert('에러');
			}
		});
	});


//== 파일 삭제 확인 modal==========================================================================
	
	$("#deleteFileBtn").on("click", function(){
		let fileNo = $("input[name='fileNo']:checked").val();
		$.ajax({
			url : $.getContextPath() + "/fileBox/fileCount/" + fileNo,
			data : {fileNo : fileNo},
			dataType : "json",
			success : function(resp) {
				if(resp.cnt > 0){
					let message = resp.message;
					new Noty({
						 text: message.text, 
						 layout: message.layout,
						 type: message.type,
						 timeout: message.timeout,
						 progressBar: true
					}).show();
				}else {
					$("#fileDeleteModal").modal();
				}
			}
		});
		$("#fileDeleteBtn").on("click", function(){
			let deleteFileNo = [];
			$("input[name=fileNo]:checked").each(function(i){
				deleteFileNo.push($(this).val())
			});
			if($fType == 'PRIVATE'){
				location.href = $.getContextPath() + "/fileBox/delete/" + deleteFileNo + "/" + $fType;
			}else {
				location.href = $.getContextPath() + "/fileBox/publicDelete/"+ deleteFileNo + "/" + $fType;
			}
		});
	});
	
//==새폴더===================================================================================================
	$("#createFolderBtn").on("click", function(){
		$(".newFolder.hide").removeClass("hide").addClass("show");
	})
	$("#formCancleBtn").on("click", function(){
		$("#fNameInput").val('');
		$(".newFolder.show").removeClass("show").addClass("hide");
	})

//==다중 파일 다운로드 (체크박스로 선택후 상단 다운로드 버튼 클릭시 zip파일 다운로드 ===============================================
	
	$("#downloadBtn").on("click", function(){
		
		let chkFiles = $("#fileBoxTable input[type=checkbox]:checked").prop("checked", true);
		let downloadList = [];
		$(chkFiles).each(function(idx, file){
			if($(file).data("dir") == "N"){    // data-dir = "N" 인 경우(파일인경우)에만 다운로드 리스트에 추가 
				let fileNo = file.value; 
				downloadList.push(fileNo);
			}
		})
		if(downloadList.length == 0){
			alert("파일만 선택해주세요");
			return;
		}
		$("#fileNos").val(downloadList);
		$("#downloadForm").submit();
	})

//==단일 파일 다운로드 (파일 이름 옆 다운로드 버튼 클릭)=====================================================================
		
	$(document).on("click", "[id^='onedown']", function(){
		let fileNo = $(this).closest("tr").find("input").val();
		location.href = $.getContextPath() + "/fileBox/download/" + fileNo;
	})

//== 중복 submit 방지 	
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}

//==폴더명 수정=====================================================================
$(document).on("click", "[id^='folderBtn']", function(event){
	event.stopPropagation();
	$(this).parent("div").find("[id^='folderBtn_']").css("display", "none");   // 폴더수정 사라져 
	$(this).parent("div").find("[id^='folderName_']").css("display", "none");   // span 사라져 
	$(this).parent("div").find("[id^='fileRealName_']").css("display", "block")  // input 나타나
	$(this).parent("div").find("[id^='updateBtn_']").css("display", "block");    // 수정 버튼 나타나
	
});

$(document).on("click", "[id^='updateBtn_']", function(){
	let fileNo = $(this).closest("tr").find("input").val();
	let fileRealName = $(this).parent("div").find("[id^='fileRealName_']").val();
	let uriStr = fileNo+"/"+fileRealName+"/" + $fType ;
	if($fType == 'PRIVATE'){
		location.href = $.getContextPath() + "/fileBox/folderNameUpdate/" + uriStr;
	}else{
		location.href = $.getContextPath() + "/fileBox/publicFolderUpdate/" + uriStr;
	}
});


//== 메일보내기 =========================================================================
var $mailAttList = new Array();
$("#mailFormOpen").on("click", function(){
	let selectAttList = [];
	let chkFiles = $("#fileBoxTable input[type=checkbox][name=fileNo]:checked").prop("checked", true).closest("tr");
	let inputSTR = [];
	
	$(chkFiles).each(function(idx, file){
//		mailAttVO = new Object();
		let data = $(file).data("file");
		let isDir = data.fileDir; 
		if(isDir == "N"){    // data-dir = "N" 인 경우(파일인경우)에만 메일 첨부파일 리스트에 추가
			if(selectAttList.indexOf(data.fileNo) > 0){
				return;
			}
			selectAttList.push(data.fileNo);

			let mailAttatchVO = {
				maName : data.fileName
				, maRealName : data.fileRealName
			}
//			$mailAttList.push(mailAttVO);
			inputSTR.push(
				$("<div class='MultiFile-label'>").append(
					$("<a class='MultiFile-remove' href='#mailFiles'>").text("x ")
					, $("<span>").append(
						$("<span class='MultiFile-label'>")
						, $("<span class='MultiFile-title'>").text(data.fileRealName)
						, $("<input type='hidden' name='attatchList["+(selectAttList.length-1)+"].maName'>").attr("value", mailAttatchVO.maName )
						, $("<input type='hidden' name='attatchList["+(selectAttList.length-1)+"].maRealname'>").attr("value", mailAttatchVO.maRealName )
					)
				)	
			)
		}
	})
	$("#mailFiles_list").append(inputSTR)
	$('.main-mail-compose').show();
})










