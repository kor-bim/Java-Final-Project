package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

/**
 * @author 서대철
 * @since 2021. 2. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 22.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface ApprovalAllDocumentBoxService {
	/**
	 * 전체 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 22.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalDocVO> selectApprovalAllDocumentBoxList(ApprovalDocVO approvalDocVO);

	/**
	 * 선택된 문서 삭제
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult deleteDocumentList(ApprovalDocVO approvalDocVO);

	/**
	 * 문서 상세 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param adNo
	 * @return
	 */
	ApprovalDocVO selectDocument(String adNo);

	/**
	 * 상세조회 페이지에서 삭제 처리
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult deleteDocument(ApprovalDocVO approvalDocVO);

	/**
	 * 결재 상태가 반려인 것만 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 9.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalDocVO> selectDocumentCode(ApprovalDocVO approvalDocVO);

	/**
	 * 상세조회 페이지에서 의견 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param approvalDocVO
	 * @return
	 */
//	List<ApprovalVO> selectCommentList(ApprovalDocVO approvalDocVO);

}
