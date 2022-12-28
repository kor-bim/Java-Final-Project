<%--
* [[개정이력(Modification Information)]]
*   수정일              수정자      	   수정내용
* ----------  ---------  -----------------
*  2021. 1. 25.      서대철      		   최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
table th{
	text-align: center;
}

th {
	background-color: #efefff;
}

.form {
	margin-top: 20px;
	margin-bottom: 20px;
}

.approvalInsertBtn{
	margin-bottom: 20px;
}

.form-check-input{
	margin-left : 10px;
}

.form-check-label {
	margin-left : 30px;
	margin-right: 30px;
}

#alCode {
	visibility: hidden;
	width: 1px;
}

</style>

<c:set var="action" />
<c:choose>
   <c:when test="${command != 'MODIFY' }">
      <c:url value='/admin/approvalFarmInsert' var="action"/>
   </c:when>
   <c:otherwise>
      <c:url value='/admin/approvalFarmUpdate'  var="action"/>
   </c:otherwise>
</c:choose>

<!-- Page Header -->
<div class="page-header">
	<div>
		<c:choose>
		   <c:when test="${command != 'MODIFY' }">
				<h2 class="main-content-title tx-24 mg-b-5">양식생성</h2>
		   </c:when>
		   <c:otherwise>
				<h2 class="main-content-title tx-24 mg-b-5">양식수정</h2>
		   </c:otherwise>
		</c:choose>
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${cPath }/admin/approvalFarmboxList">뒤로가기</a></li>
		</ol>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div class="row row-sm">
	<div class="col-lg-12">
		<div class="card custom-card overflow-hidden">
			<div class="card-body">
				<form:form commandName="documentsVO" id="formTable" action="${action }">
					<form:hidden path="dfNo"/>
					<c:choose>
					   <c:when test="${command != 'MODIFY' }">
							<input type="submit" value="양식등록" class="btn btn-outline-light approvalInsertBtn" />
					   </c:when>
					   <c:otherwise>
							<input type="submit" value="양식수정" class="btn btn-outline-light approvalInsertBtn" />
					   </c:otherwise>
					</c:choose>
					<fieldset>
						<strong id="inputContent">기본 설정</strong>
						<table class="table table-bordered">
							<tbody>
								<tr>
									<th>분류</th>
									<td>
										<label class="col-6">
											<form:select cssClass="form-control col" path="docTypeCode">
												<c:if test="${not empty documentsVO }">
													<c:forEach items="${documentsVO.docTypeList }" var="doc">
														<form:option value="${doc.docTypeCode }">${doc.docTypeName }</form:option>
													</c:forEach>
												</c:if>
											</form:select>
										</label>
									</td>
									<th>사용 여부</th>
									<td id="">
										<input type="radio" class="form-check-input" name="dfUseYn" id="onUse" value="Y" ${documentsVO.dfUseYn eq 'Y' ? 'checked' : '' } />
										<label class="form-check-label" for="dfUseYn"> 사용 </label>
										<input type="radio" class="form-check-input" name="dfUseYn" id="unUse" value="N" ${documentsVO.dfUseYn eq 'N' ? 'checked' : '' }/>
										<label class="form-check-label" for="dfUseYn"> 미사용 </label>
									</td>
								</tr>
								<tr>
									<th>보안 등급</th>
									<td>
										<label class="col-6">
											<form:select path="scuCode" cssClass="form-control col">
												<form:option value="S">S등급</form:option>
												<form:option value="A">A등급</form:option>
												<form:option value="B">B등급</form:option>
												<form:option value="C">C등급</form:option>
											</form:select>
										</label>
									</td>
									<th scope="row">양식명</th>
									<td>
										<label>
											<form:input path="dfName" cssClass="form-control" id="formName"/>
										</label>
									</td>
								</tr>
								<tr>
									<th scope="row">보존 연한</th>
									<td>
										<label class="col-6">
											<form:select path="presCode" cssClass="form-control">
												<form:option value="1Y">1년</form:option>
												<form:option value="3Y">3년</form:option>
												<form:option value="5Y">5년</form:option>
												<form:option value="10Y">10년</form:option>
												<form:option value="PERMANENT">영구</form:option>
											</form:select>
										</label>
									</td>
									<th scope="row">설명</th>
									<td>
										<label>
											<form:input path="dfDescription" cssClass="form-control" id="inputDescription"/>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</fieldset>
					<div class="form approvalTo">
						<strong id="inputContent2">결재양식</strong>
						<button type="button" class="btn btn-outline-link" name="approvalSetting" id="formBtn">설정</button>
						<input type="text" name="alCode" value="${documentsVO.alCode }" id="alCode"/>
						<div id="form-body">
							<c:if test="${command eq 'MODIFY' }">
								<ul>
									<li value="${documentsVO.alCode }">
										결재 포맷 : ${documentsVO.alName }
										<input type="hidden" name="alCode" value="${documentsVO.alCode }"/>
									</li>
								</ul>
							</c:if>
						</div>
					</div>
					<div class="content">
						<form:textarea path="dfContent" cssClass="form-control" id="textarea"/>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- End Row -->

<!-- 결재양식 설정 modal-->
<div class="modal fade" id="approvalSettingModal" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="approvalSettingModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title h4" id="approvalSettingModalLabel">결재 양식</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				
			</div>
			<div class="modal-footer">
				<input type="button" class="btn btn-outline-primary" value="저장" id="approvalSettingBtn"/>
				<input type="button" class="btn btn-outline-secondary" value="취소" data-dismiss="modal"/>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("#formTable").validate({
			rules : {
				dfName : {
					required : true
					, minlength : 2
				}
				, dfDescription : {
					required : true
				}
				, dfUseYn : {
					required : true
				}
				, alCode : {
					required : true
				}
			},
			messages : {
				dfName : {
					required : "양식명은 필수 입력값입니다"
					, minlength : "2자이상 입력하세요"
				}
				, dfDescription : {
					required : "설명은 필수 입력값입니다."
				}
				, dfUseYn : {
					required : "사용여부는  필수 입력값입니다."
				}
				, alCode : {
					required : "결재포맷은  필수 입력값입니다."
				}
			},
		    errorPlacement: function(error, element) {
				element.tooltip({
				title: error.text()
		             , placement: "right"
		             , trigger: "manual"
		             , delay: { show: 500, hid: 100 }
				}).on("shown.bs.tooltip", function() {
					let tag = $(this);
		            setTimeout(() => {
						tag.tooltip("hide");
		            }, 2000)
				}).tooltip('show');
		    }
		});
	})

	CKEDITOR.replace("dfContent", {
		filebrowserImageUploadUrl: '${cPath }/board/imageUpload.do?command=QuickUpload&type=Images'
	});
	
	
	// ================ 결재 포맷 modal ==============================================================================
	let approvalSettingModal = $("#approvalSettingModal").on("hidden.bs.modal", function() {
		$(this).find(".modal-body").empty();
	});
 	
	$("#formBtn").on("click", function() {
		approvalSettingModal.find(".modal-body").load($.getContextPath() + "/admin/approvalForm", function() {
			approvalSettingModal.modal();
		});
	});
	
	const formBody = $("#form-body")
	
	$("#approvalSettingBtn").on("click", function(){
		approvalSettingModal.modal('hide');
		let id =$("input[name='alCode']:checked").attr("id");
		let value = $("input[name='alCode']:checked").val();
		let formArr = [];
		formArr.push(
			$("<ul>").append(
				$("<li>").text("결재 포맷 : " + value)
				, $("<input type='hidden'>").val(id).attr("name", "alCode")
			)
		);
		formBody.html(formArr);
		$("#alCode").prop("disabled", true);
	});
</script>

<script>
	$(document).on("click", "#inputContent", function(){
		$("#onUse").prop("checked", true);
		$("option[value=A]").prop("selected", true);
		$("#formName").val("인사발령양식");
		$("option[value=5Y]").prop("selected", true);
		$("#inputDescription").val("인사발령양식");
		CKEDITOR.instances.textarea.setData(
				'<div>                                                                                                                                                                                            '
				+'<div style="margin: 0px; padding: 0px; font-family: &quot;맑은 고딕&quot;; font-size: 16px; line-height: 1.8;">                                                                                 '
				+'<h1>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;인 사 발 령</h1>'
                +'                                                                                                                                                                                                '
				+'<table border="1" cellspacing="0" style="border-collapse:collapse; width:100%">                                                                                                                 '
				+'	<tbody>                                                                                                                                                                                       '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>번호</p>                                                                                                                                                                           '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>성명</p>                                                                                                                                                                           '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>발령내용</p>                                                                                                                                                                       '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:150px">                                                                               '
				+'			<p>부서</p>                                                                                                                                                                           '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:150px">                                                                               '
				+'			<p>직위</p>                                                                                                                                                                           '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>발령일자</p>                                                                                                                                                                       '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>1</p>                                                                                                                                                                              '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:100px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>2</p>                                                                                                                                                                              '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:100px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>3</p>                                                                                                                                                                              '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:100px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>4</p>                                                                                                                                                                              '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:100px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'		<tr>                                                                                                                                                                                      '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>5</p>                                                                                                                                                                              '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:500px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:190px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:100px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'			<td style="border-color:#cdcdcd; border-style:solid; border-width:1px; text-align:center; width:200px">                                                                               '
				+'			<p>&nbsp;</p>                                                                                                                                                                         '
				+'			</td>                                                                                                                                                                                 '
				+'		</tr>                                                                                                                                                                                     '
				+'	</tbody>                                                                                                                                                                                      '
				+'</table>                                                                                                                                                                                        '
				+'</div>                                                                                                                                                                                          '
				+'</div>	                                                                                                                                                                                      '
		
		);                                                                                                                                                                                          
	});
	
// 	$(document).on("click", "#inputContent2", function(){
// 		$("#textarea").val("asdf");
// 	});
	
</script>