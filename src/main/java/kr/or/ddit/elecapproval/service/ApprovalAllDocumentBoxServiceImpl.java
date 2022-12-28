package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalAllDocumentBoxDAO;
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
@Service
public class ApprovalAllDocumentBoxServiceImpl implements ApprovalAllDocumentBoxService {
	
	@Inject
	private ApprovalAllDocumentBoxDAO approvalAllDocumentBoxDAO;

	@Override
	public List<ApprovalDocVO> selectApprovalAllDocumentBoxList(ApprovalDocVO approvalDocVO) {
		return approvalAllDocumentBoxDAO.selectApprovalDocumentBoxList(approvalDocVO);
	}

	@Override
	public ServiceResult deleteDocumentList(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalAllDocumentBoxDAO.deleteDocumentList(approvalDocVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ApprovalDocVO selectDocument(String adNo) {
		ApprovalDocVO approvalDocVO = approvalAllDocumentBoxDAO.selectDocument(adNo);
		if(adNo == null) throw new CustomException(adNo + "의 문서가 존재하지 않습니다.");
		return approvalDocVO;
	}

	@Override
	public ServiceResult deleteDocument(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalAllDocumentBoxDAO.deleteDocument(approvalDocVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public List<ApprovalDocVO> selectDocumentCode(ApprovalDocVO approvalDocVO) {
		List<ApprovalDocVO> resultList = approvalAllDocumentBoxDAO.selectDocumentCode(approvalDocVO);
		return resultList;
	}

//  20210301 운주 : 중복	
//	@Override
//	public List<ApprovalVO> selectCommentList(ApprovalDocVO approvalDocVO) {
//		return approvalAllDocumentBoxDAO.selectCommentList(approvalDocVO);
//	}
	
}
