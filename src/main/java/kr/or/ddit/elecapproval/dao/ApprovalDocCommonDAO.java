package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * 전자 결재 관련해서 공통적으로 사용되는 DB
 * 
 * @author 이운주
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface ApprovalDocCommonDAO {
	
	/**
	 * 문서 양식 분류 코드 리스트 조회 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	public List<DocumentsVO> selectDocTypeList(DocumentsVO documentsVO);

	/**
	 * 문서 양식 분류 코드에 해당하는 문서 양식 리스트 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	public List<DocumentsVO> selectDocFormList(DocumentsVO documentsVO);
	
	/**
	 * 문서 양식 한건 조회
	 * @author 서대철
	 * @since 2021. 2. 26.
	 * @param documentsVO
	 * @return
	 */
	public DocumentsVO selectDocForm(DocumentsVO documentsVO);

	/**
	 * 자주 쓰는 결재선 리스트 조회 
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @param approvalLineVO - 기안자 memId
	 * @return
	 */
	public List<ApprovalLineVO> selectApprovalLineList(ApprovalLineVO approvalLineVO);

	/**
	 * 자주 쓰는 결재선 상세 보기 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @param approvalLineVO
	 * @return
	 */
	public ApprovalLineVO selectApprovalLine(ApprovalLineVO approvalLineVO);

	/** 
	 * 결재문서 한건의 결재라인
	 * @author 이운주
	 * @since 2021. 2. 24.
	 * @param approvalLineVO
	 * @return
	 */
	public ApprovalLineVO getApprovalLine(ApprovalDocVO approvalDocVO);

	/**
	 * 결재하기 : 승인, (협의요청, 반려, 전결, 대결, 후결 )
	 * @param approvalVO
	 * @authoe 이운주
	 * @date 2021. 2. 25.
	 */
	public int signApprovalDoc(ApprovalVO approvalVO);
	
	/**
	 * 다음 결재자 아이디
	 * @author 이운주
	 * @since 2021. 2. 25.
	 * @param approvalVO
	 * @return
	 */
	public String selectNextMemId(ApprovalVO approvalVO);

	/**
	 * 다음 결재자가 없는 경우 : 결재문서 상태 변경 (dsCode)
	 * @param approvalVO
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 25.
	 */
	public int updateApprovalDocStatus(ApprovalDocVO approvalDocVO);

	/**
	 * 전결, 대결시 최종결재자까지 모두 결재완료 처리하는 메서드
	 * @param approvalVO
	 * @authoe 이운주
	 * @date 2021. 2. 27.
	 */
	public void approvalAllComplete(ApprovalVO approvalVO);


}
