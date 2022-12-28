<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 1. 28.     길영주      최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table class="table table-bordered border-t0 key-buttons text-nowrap w-100">
	<tr>
		<td>게시글번호</td>
		<td id="nbNo">
			<c:out value="${noticeVO.nbNo }" />
		</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>
			<c:out value="${noticeVO.nbTitle }" />
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>
			<c:if test="${not empty noticeVO.nbAttatchList }">
				<c:forEach items="${noticeVO.nbAttatchList }" var="nbAttatch" varStatus="vs">
					<a href="${cPath}/noticeBoard/download/${nbAttatch.nbaNo }">
						<span>${nbAttatch.nbaName }</span>
						${not vs.last?"|":"" }
					</a>
				</c:forEach>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<c:out value="${noticeVO.nbContent }" escapeXml="false" />
		</td>
	</tr>
	<tr>
		<td>작성자</td>
		<td>
			<c:out value="${noticeVO.memberVO.memName }" />
		</td>
	</tr>
	<tr>
		<td>날짜</td>
		<td>
			<c:out value="${noticeVO.nbDate }" />
		</td>
	</tr>
</table>
