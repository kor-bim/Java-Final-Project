package kr.or.ddit.elecapproval.service;

import java.util.List;

import org.springframework.ui.Model;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * 전자결재 관련 공통으로 사용하는 비즈니스 로직 
 * ex. 문서양식 분류 코드 조회 
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
public interface ApprovalDocCommonService {
	
	/**
	 * 문서 양식 분류 코드, 이름 조회 
	 * 
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
	 * 
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @param approvalLineVO
	 * @return
	 */
	public List<ApprovalLineVO> selectApprovalLineList(ApprovalLineVO approvalLineVO);

	/**
	 * 자주 쓰는 결재선 한건 조회. 결재라인 상세 + 결재자 정보를 포함
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @param approvalLineVO
	 * @return
	 */
	public ApprovalLineVO selectApprovalLine(ApprovalLineVO approvalLineVO);

	/**결재 문서 한건에 대한 결재라인 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 24.
	 * @param approvalLineVO
	 * @return
	 */
	public void getApprovalLineByAdNo(String adNo, Model model);	// adNo 를 받아 detailJSON을 모델에 담아줌.
	public ApprovalLineVO getApprovalLine(ApprovalDocVO approvalDocVO);

	/**
	 * 결재 프로세스
	 * @author 이운주
	 * @since 2021. 2. 24.
	 * @param approvalVO
	 * @return
	 */
	public ServiceResult approvalProcess(ApprovalVO approvalVO);



}
