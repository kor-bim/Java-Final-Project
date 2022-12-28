package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

@Repository
public interface ApprovalDetailDAO {
	/**
	 * 전자결재 상세보기 
	 * @param approvalDocVO
	 * @return
	 */
	public ApprovalDocVO selectApprovalDetail(String adNo);

	public List<ApprovalVO> retrieveApprovalComment(ApprovalDocVO approvalDocVO);
}
