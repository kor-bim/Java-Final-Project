package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

public interface ApprovalDetailService {
	
	/**
	 * 전자결재 상세보기 조회
	 * @author 길영주
	 * @since 2021.02.20
	 * @param approvalDocVO
	 * @return
	 */
	public ApprovalDocVO retrieveApprovalDetail(ApprovalDocVO approvalDocVO);

	public List<ApprovalVO> retrieveApprovalComment(ApprovalDocVO approvalDocVO);
}
