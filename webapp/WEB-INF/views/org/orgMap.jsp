<%--
* 사용자 관리 페이지 : 구성원 리스트 
*
* [[개정이력(Modification Information)]]
*   수정일                  수정자               수정내용
* -----------   ---------  -----------------
* 2021. 1. 27.   이재형                최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="/Forest/WEB-INF/views/org/jorgtree.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/Forest/css/org/insa.css" />


<!-- Page Header -->
<div class="page-header">
	<div>
		<h2 class="main-content-title tx-24 mg-b-5">조직도</h2>
	</div>
</div>
<!-- End Page Header -->

<!-- Row -->
<div id="contents">
	<!-- insa.office.hiworks.com/hiworks/views/dev/org.php -->
	<div class="setting_title after">
		<h3 class="fl">조직도</h3>
	</div>
	<div class="row row-sm">
		<div class="col-lg-12 col-md-12">
			<div class="card custom-card">
				<div class="card-body ml-3">
					<div class="content_inbox">
						<div class="org-wrap">
							<table>
								<colgroup>
									<col style="width: 290px;">
									<col>
								</colgroup>
								<tbody id="org-map">
									<tr class="level-1">
										<th scope="row">회사 (8)</th>
										<td>
											<span class="leader" onclick="OrgMember.getOrgMemberInfo(97);">P_관리자(변경금지)</span>
										</td>
									</tr>
									<tr class="level-2">
										<th scope="row">경영 (3)</th>
										<td>
											<span onclick="OrgMember.getOrgMemberInfo(20629);">사용자테스트2(변경금지)</span>
											,
											<span onclick="OrgMember.getOrgMemberInfo(20630);">사용자테스트3(변경금지)</span>
											,
											<span onclick="OrgMember.getOrgMemberInfo(97);">P_관리자(변경금지)</span>
										</td>
									</tr>
									<tr class="level-2">
										<th scope="row">인사 (4)</th>
										<td>
											<span onclick="OrgMember.getOrgMemberInfo(20498);">P_사용자계정1(변경금지)</span>
											,
											<span onclick="OrgMember.getOrgMemberInfo(20627);">관리자(변경금지)</span>
											,
											<span onclick="OrgMember.getOrgMemberInfo(20500);">P_사용자계정3(변경금지)</span>
											,
											<span onclick="OrgMember.getOrgMemberInfo(20499);">P_사용자계정2(변경금지)</span>
										</td>
									</tr>
									<tr class="level-3">
										<th scope="row">
											<div class="level-show">하위조직1 (0)</div>
										</th>
										<td></td>
									</tr>
									<tr class="level-4">
										<th scope="row">
											<div class="level-show">하위조직1 (0)</div>
										</th>
										<td></td>
									</tr>
									<tr class="level-3">
										<th scope="row">
											<div class="level-show">하위조직2 (0)</div>
										</th>
										<td></td>
									</tr>
									<tr class="level-2">
										<th scope="row">하위조직3 (0)</th>
										<td></td>
									</tr>
									<tr class="level-2">
										<th scope="row">하위조직4 (1)</th>
										<td>
											<span onclick="OrgMember.getOrgMemberInfo(20628);">사용자테스트1(변경금지)</span>
										</td>
									</tr>
									<tr class="level-3">
										<th scope="row">
											<div class="level-show">하위조직1 (0)</div>
										</th>
										<td></td>
									</tr>
									<tr class="level-4">
										<th scope="row">
											<div class="level-show">하위조직1 (0)</div>
										</th>
										<td></td>
									</tr>
								</tbody>
							</table>


						</div>

					</div>

					<div class="layer_box middle hide" id="apply_download" style="width: 358px">
						<div class="title_layer text_variables">조직원 정보 다운로드</div>
						<p>공개된 조직원 정보를 이름/소속/직위/내선/휴대전화 정보를 엑셀파일로 다운로드할 수 있습니다.</p>
						<p class="pdb_20">조직원의 정보를 다운로드할 조직을 선택하세요.</p>
						<p class="pdb_10">
							<b>조직 선택</b>
						</p>
						<select id="select_box_excel" style="width: 100%">
							<option value="ALL">모든 조직</option>
						</select>
						<div class="dropdown hide" id="excel_node_list" style="width: 100%; display: none;">
							<div class="dropdown-menu block" style="width: 100%; overflow-y: auto;">
								<div id="excelNode" class="filter_orgtree" style="max-height: 150px; width: 100%;">
									<ul class="node">
										<li no="ALL">
											<div class="container">
												<strong onclick="OrgMember.selectedNode(this, event)">모든 조직</strong>
											</div>
										</li>
										<li class="last" no="819">
											<div class="container">
												<strong onclick="OrgMember.selectedNode(this, event);">회사</strong>
												<img onclick="orgtree.toggleTree(this, event);" src="/static/ui/images/icon_minus.png" class="plus">
											</div>
											<ul class="node">
												<li class="" no="6709">
													<div class="container">
														<strong onclick="OrgMember.selectedNode(this, event);">경영</strong>
													</div>
												</li>
												<li class="" no="6710">
													<div class="container">
														<strong onclick="OrgMember.selectedNode(this, event);">인사</strong>
														<img onclick="orgtree.toggleTree(this, event);" src="https://office.hiworks.com/static/ui/images/icon_plus.png" class="plus">
													</div>
													<ul class="node" style="display: none;">
														<li class="" no="6716">
															<div class="container">
																<strong onclick="OrgMember.selectedNode(this, event);">하위조직1</strong>
																<img onclick="orgtree.toggleTree(this, event);" src="https://office.hiworks.com/static/ui/images/icon_plus.png" class="plus">
															</div>
															<ul class="node" style="display: none;">
																<li class="last" no="6718">
																	<div class="container">
																		<strong onclick="OrgMember.selectedNode(this, event);">하위조직1</strong>
																	</div>
																</li>
															</ul>
														</li>
														<li class="last" no="6719">
															<div class="container">
																<strong onclick="OrgMember.selectedNode(this, event);">하위조직2</strong>
															</div>
														</li>
													</ul>
												</li>
												<li class="" no="6711">
													<div class="container">
														<strong onclick="OrgMember.selectedNode(this, event);">하위조직3</strong>
													</div>
												</li>
												<li class="last" no="6712">
													<div class="container">
														<strong onclick="OrgMember.selectedNode(this, event);">하위조직4</strong>
														<img onclick="orgtree.toggleTree(this, event);" src="https://office.hiworks.com/static/ui/images/icon_plus.png" class="plus">
													</div>
													<ul class="node" style="display: none;">
														<li class="last" no="6715">
															<div class="container">
																<strong onclick="OrgMember.selectedNode(this, event);">하위조직1</strong>
																<img onclick="orgtree.toggleTree(this, event);" src="https://office.hiworks.com/static/ui/images/icon_plus.png" class="plus">
															</div>
															<ul class="node" style="display: none;">
																<li class="last" no="6717">
																	<div class="container">
																		<strong onclick="OrgMember.selectedNode(this, event);">하위조직1</strong>
																	</div>
																</li>
															</ul>
														</li>
													</ul>
												</li>
											</ul>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
						</div>
						<div class="layer_button">
							<button type="button" class="btn_variables" onclick="OrgMember.downloadExcel();">다운로드</button>
							<button type="button" onclick="$j('#apply_download').hidePopup(this);">취소</button>
						</div>
						<a href="javascript:void(0)" class="icon btn_closelayer" onclick="$j('#apply_download').hidePopup(this);" title="레이어 닫기"><span class="blind">레이어 닫기</span></a>
					</div>

					<div class="layer_box mid_large hide" id="insa_info_layer"></div>

					<script type="text/javascript">
						$j(document)
								.ready(
										function() {
											OrgMember.initOrgMapData();

											$j('.content_inbox')
													.on(
															'scroll',
															function() {
																var wintop = $j(
																		'.content_inbox')
																		.scrollTop();
																var docheight = $j(
																		'.content_inbox')
																		.prop(
																				"scrollHeight");
																var winheight = $j(
																		window)
																		.outerHeight();
																var scrolltrigger = 0.90;
																if ((wintop / (docheight - winheight)) > scrolltrigger) {
																	OrgMember
																			.drawOrgMap();
																}
															});

											$j(document).on(
													'click',
													'#select_box_excel',
													function(e) {
														e.preventDefault();
														e.stopPropagation();
														$j(this).blur();
														$j('#excel_node_list')
																.show();
													});

											$j(document).on('click',
													function() {
														$j('.dropdown').hide();
													});
										});
					</script>
				</div>
				<!-- End Row -->