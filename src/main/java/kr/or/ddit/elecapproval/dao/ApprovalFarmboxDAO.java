package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public interface ApprovalFarmboxDAO {

	/**
	 * 양식함 목록 조회
	 * @param documentsVO
	 * @return
	 */
	List<DocumentsVO> selectApprvalFarmboxList(DocumentsVO documentsVO);

	/**
	 * 양식 등록
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	int insertFarmbox(DocumentsVO documentsVO);

	/**
	 * 양식 상세 조회
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param dfNo
	 * @return
	 */
	DocumentsVO selectFarm(int dfNo);

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
	int insertDocType(DocumentsVO documentsVO);

	/**
	 * 분류 수정
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @return
	 */
	int updateDocType(DocumentsVO documentsVO);

	/**
	 * 분류 삭제
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @return
	 */
	int deleteDocType(DocumentsVO documentsVO);

	/**
	 * 양식 수정
	 * @param documentsVO
	 * @return
	 */
	int updateFarmbox(DocumentsVO documentsVO);

	/**
	 * 양식 삭제
	 * @param documentsVO
	 * @return
	 */
	int deleteFarmbox(DocumentsVO documentsVO);

	/**
	 * 분류 사용 여부 확인
	 * @author 서대철
	 * @since 2021. 3. 11.
	 * @param documentsVO
	 * @return
	 */
	int approvalFarmCheck(DocumentsVO documentsVO);

}
