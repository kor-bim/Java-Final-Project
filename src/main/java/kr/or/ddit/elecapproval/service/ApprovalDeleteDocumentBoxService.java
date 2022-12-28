package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

/**
 * @author 서대철
 * @since 2021. 2. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 23.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface ApprovalDeleteDocumentBoxService {

	/**
	 * 삭제 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalDocVO> selectApprovalDeleteDocumentBoxList(ApprovalDocVO approvalDocVO);

	/**
	 * 선택된 문서 백업
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult backUpDocumentList(ApprovalDocVO approvalDocVO);

	/**
	 * 선택된 문서 완전 삭제
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult realDeleteDocumentList(ApprovalDocVO approvalDocVO);

	/**
	 * 삭제 문서 상세조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param adNo
	 * @return
	 */
	ApprovalDocVO selectDocument(String adNo);

	/**
	 * 상세 페이지에서 문서 백업 처리
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult backUpDocument(ApprovalDocVO approvalDocVO);

	/**
	 * 상세 페이지에서 문서 완전삭제 처리
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param approvalDocVO
	 * @return
	 */
	ServiceResult realDeleteDocument(ApprovalDocVO approvalDocVO);

	/**
	 * 상세 페이지에서 의견 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalVO> selectCommentList(ApprovalDocVO approvalDocVO);

}
