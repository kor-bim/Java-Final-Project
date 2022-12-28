package kr.or.ddit.elecapproval.service;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

public interface InBoxDetailService {
	
	/**
	 * 문서함 상세보기 조회
	 * @author 길영주
	 * @since 2021.02.20
	 * @param approvalDocVO
	 * @return
	 */
	public ApprovalDocVO retrieveInBoxDetail(ApprovalDocVO approvalDocVO);

//	public List<ApprovalVO> retrieveInBoxComment(ApprovalDocVO approvalDocVO); 운주 
}
