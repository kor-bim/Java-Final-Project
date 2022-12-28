package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.elecapproval.dao.ApprovalDetailDAO;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

@Service
public class ApprovalDetailServiceImpl implements ApprovalDetailService {
	
	@Inject
	public ApprovalDetailDAO approvalDetailDAO;
	

	@Override
	public ApprovalDocVO retrieveApprovalDetail(ApprovalDocVO approvalDocVO) {
		ApprovalDocVO approvalDetail = approvalDetailDAO.selectApprovalDetail(approvalDocVO.getAdNo());
		if(approvalDetail == null) {
			throw new CustomException(approvalDocVO.getAdNo() + "번 글이 없음.");
		}
		return approvalDetail;
	}

	@Override
	public List<ApprovalVO> retrieveApprovalComment(ApprovalDocVO approvalDocVO) {
		List<ApprovalVO> comment = approvalDetailDAO.retrieveApprovalComment(approvalDocVO);
		return comment;
	}
	
	
}
