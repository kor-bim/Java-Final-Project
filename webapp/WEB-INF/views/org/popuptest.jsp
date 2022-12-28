<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<script>
	// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다.
	// (＂팝업 API 호출 소스"도 동일하게 적용시켜야 합니다.)
// 	document.domain = "localhost";
	function goPopup() {
		//경로는 시스템에 맞게 수정하여 사용
		//호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를
		//호출하게 됩니다.
// 		window.name = "jusoPopup";
		var pop = window.open("${pageContext.request.contextPath}/popup/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
		//** 2017년 5월 모바일용 팝업 API 기능 추가제공 **/
		// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서
		// 실제 주소검색 URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
		// var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes");
	}
	function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail,
			roadAddrPart2, engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,
			detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn,
			buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo) {
		// 2017년 2월 제공항목이 추가되었습니다. 원하시는 항목을 추가하여 사용하시면 됩니다.
		document.form.roadFullAddr.value = roadFullAddr;
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.roadAddrPart2.value = roadAddrPart2;
		documentform.addrDetail.value = addrDetail;
		document.form.zipNo.value = zipNo;
	}
</script>
<form name="form" id="form" method="post">
	<input type="button" onClick="goPopup();" value="팝업" />
	도로명주소 전체(포맷)
	<input type="text" id="roadFullAddr" name="roadFullAddr" />
	<br> 도로명주소
	<input type="text" id="roadAddrPart1" name="roadAddrPart1" />
	<br> 고객입력 상세주소
	<input type="text" id="addrDetail" name="addrDetail" />
	<br> 참고주소
	<input type="text" id="roadAddrPart2" name="roadAddrPart2" />
	<br> 우편번호
	<input type="text" id="zipNo" name="zipNo" />
</form>



