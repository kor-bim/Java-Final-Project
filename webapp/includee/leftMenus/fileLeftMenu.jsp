<%--

* 파일 leftMenu  
*
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 2. 09.      길영주      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<ul class="nav">
	<li class="nav-header">
		<span class="nav-label">자료실</span>
	</li>
	<li class="nav-item">
		<input type="button" class="btn btn-secondary col-10 ml-3" id="fileUploadBtn" value="파일 업로드">
	</li>
	<li class="nav-item">
		<a class="nav-link" href="javascript:void(0)">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-settings sidemenu-icon"></i>
			<span class="sidemenu-label">개인자료실 용량</span>
		</a>
		<div id="myProgressbar" style="width: 187px; margin-left: 20px;" class="progress ml-3 ">
			<div id="gauge" class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="10">
				<span class="sr-only"></span>
			</div>
		</div>
		<div class="num mr-4 d-flex" style="font-size: 10px; float: right;">
			사용량&nbsp;
			<strong><span id="fileSizeView"></span>MB</strong>
			/10.0MB
		</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="${cPath}/fileBox/myFile" onClick="$('#myFile').click()">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-user sidemenu-icon"></i>
			<span class="sidemenu-label">개인보관함</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item" style="display:none !important;" id="myFile">
				<a class="nav-sub-link" href="${cPath}/fileBox/myFile">개인파일보관함</a>
			</li>
			<c:if test="${not empty selectFolderList }">
				<c:forEach items="${selectFolderList }" var="folder">
					<li class="nav-sub-item">
						<a class="nav-sub-link inFolder leftInFolder" href="#" data-fileNo="${folder.fileNo }" data-folderName="${folder.fileRealName }" data-parentNo="${folder.fileParent }">${fn:replace(folder.fileRealName, "-", "")}</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</li>	
	<li class="nav-item">
		<a class="nav-link" href="${cPath }/fileBox/public" onClick="$('#public').click()">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="ti-user sidemenu-icon"></i>
			<span class="sidemenu-label">전사 파일보관함</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
		<ul class="nav-sub">
			<li class="nav-sub-item" style="display:none !important;" id="public">
				<a class="nav-sub-link" href="${cPath }/fileBox/public">전사파일 보관함</a>
			</li>
			<c:if test="${not empty selectFolderList }">
				<c:forEach items="${selectFolderList }" var="folder">
					<li class="nav-sub-item">
						<a class="nav-sub-link inFolder" href="#">${fn:replace(folder.fileRealName, "-", "")}</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</li>	

	<li class="nav-item">
		<a class="nav-link" href="${cPath }/fileBox/deleteFileBox">
			<span class="shape1"></span>
			<span class="shape2"></span>
			<i class="fa fa-trash sidemenu-icon"></i>
			<span class="sidemenu-label">휴지통</span>
			<i class="angle fe fe-chevron-right"></i>
		</a>
	</li>
</ul>
<script src="${cPath}/js/commons/progressbar.js"></script>
<script src="${cPath}/js/file/fileBox.js"></script>
<script>
	$.ajax({
		url:"${cPath}/fileBox/fileSize.do",
		datatype : "json",
		success:function(res) {
			var gauge = res.sizetotal;
			console.log(gauge);
			$("#gauge").css("width",gauge*10+"%");
			$("#gauge").prop("aria-valuenow", gauge);
			$("#fileSizeView").text(gauge);
		},
		error:function(xhr){
			console.log("오류")
		}
	})
	
	
</script>