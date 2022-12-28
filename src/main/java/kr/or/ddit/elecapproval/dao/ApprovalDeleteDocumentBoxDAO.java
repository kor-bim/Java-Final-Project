package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface ApprovalDeleteDocumentBoxDAO {

	/**
	 * 삭제 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalDocVO> selectApprovalDocumentBoxList(ApprovalDocVO approvalDocVO);

	/**
	 * 선택된 문서 복원
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	int backUpDocumentList(ApprovalDocVO approvalDocVO);

	/**
	 * 선택된 문서 완전 삭제
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	int realDeleteDocumentList(ApprovalDocVO approvalDocVO);

	/**
	 * 삭제 문서 상세 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param adNo
	 * @return
	 */
	ApprovalDocVO selectDocument(String adNo);

	/**
	 * 상세조회 페이지에서 문서 복원
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @return
	 */
	int backUpDocument(ApprovalDocVO approvalDocVO);

	/**
	 * 상세조회 페이지에서 문서 완전 삭제 처리
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param approvalDocVO
	 * @return
	 */
	int realDeleteDocument(ApprovalDocVO approvalDocVO);

	/**
	 * 상세조회 페이지에서 의견 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param approvalDocVO
	 * @return
	 */
	List<ApprovalVO> selectCommentList(ApprovalDocVO approvalDocVO);

	/**
	 * 기안문서 삭제
	 * @author 서대철
	 * @since 2021. 2. 27.
	 * @param approvalDocVO
	 * @return
	 */
	int realDeleteApprovalDoc(ApprovalDocVO approvalDocVO);
	
}
