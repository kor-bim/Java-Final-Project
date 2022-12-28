package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalDeleteDocumentBoxDAO;
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
@Service
public class ApprovalDeleteDocumentBoxServiceImpl implements ApprovalDeleteDocumentBoxService {
	
	@Inject
	private ApprovalDeleteDocumentBoxDAO approvalDeleteDocumentBoxDAO;

	@Override
	public List<ApprovalDocVO> selectApprovalDeleteDocumentBoxList(ApprovalDocVO approvalDocVO) {
		return approvalDeleteDocumentBoxDAO.selectApprovalDocumentBoxList(approvalDocVO);
	}

	@Override
	public ServiceResult backUpDocumentList(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalDeleteDocumentBoxDAO.backUpDocumentList(approvalDocVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult realDeleteDocumentList(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalDeleteDocumentBoxDAO.realDeleteApprovalDoc(approvalDocVO);
		cnt = approvalDeleteDocumentBoxDAO.realDeleteDocumentList(approvalDocVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ApprovalDocVO selectDocument(String adNo) {
		ApprovalDocVO approvalDocVO = approvalDeleteDocumentBoxDAO.selectDocument(adNo);
		if(adNo == null) throw new CustomException(adNo + "의 문서가 존재하지 않습니다.");
		return approvalDocVO;
	}

	@Override
	public ServiceResult backUpDocument(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalDeleteDocumentBoxDAO.backUpDocument(approvalDocVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult realDeleteDocument(ApprovalDocVO approvalDocVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalDeleteDocumentBoxDAO.realDeleteDocument(approvalDocVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public List<ApprovalVO> selectCommentList(ApprovalDocVO approvalDocVO) {
		return approvalDeleteDocumentBoxDAO.selectCommentList(approvalDocVO);
	}
}
