package kr.or.ddit.elecapproval.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

@Repository
public interface InBoxDetailDAO {
	
	/**
	 * 문서함 상세보기
	 * @param adNo
	 * @return
	 */
	public ApprovalDocVO selectInBoxDetail(String adNo);
	
//	public List<ApprovalVO> retrieveInBoxComment(ApprovalDocVO approvalDocVO); 운주
}
