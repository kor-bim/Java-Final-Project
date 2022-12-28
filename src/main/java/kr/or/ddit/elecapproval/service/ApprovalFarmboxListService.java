package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * @author 서대철
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface ApprovalFarmboxListService {

	/**
	 * 양식함 목록 조회
	 * @param documentsVO
	 * @return
	 */
	List<DocumentsVO> selectApproalFarmboxList(DocumentsVO documentsVO);

	/**
	 * 양식 등록
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	ServiceResult insertFarmbox(DocumentsVO documentsVO);

	/**
	 * 양식 상세조회
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param dfNo
	 * @return
	 */
	DocumentsVO selectApproalFarm(int dfNo);

	/**
	 * 분류 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	List<DocumentsVO> selectApprovalDocTypeList(DocumentsVO documentsVO);

	/**
	 * 분류 등록
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @return
	 */
	ServiceResult insertDocType(DocumentsVO documentsVO);

	/**
	 * 분류 수정
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @return
	 */
	ServiceResult updateDocType(DocumentsVO documentsVO);

	/**
	 * 분류 삭제
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @return
	 */
	ServiceResult deleteDocType(DocumentsVO documentsVO);

	/**
	 * 양식 수정
	 * @param documentsVO
	 * @return
	 */
	ServiceResult UpdateFarmbox(DocumentsVO documentsVO);

	/**
	 * 양식 삭제
	 * @param documentsVO
	 * @return
	 */
	ServiceResult deleteFarmbox(DocumentsVO documentsVO);

	/**
	 * 분류 사용 여부 확인
	 * @author 서대철
	 * @since 2021. 3. 11.
	 * @param documentsVO
	 * @return
	 */
	ServiceResult approvalFarmCheck(DocumentsVO documentsVO);

}
