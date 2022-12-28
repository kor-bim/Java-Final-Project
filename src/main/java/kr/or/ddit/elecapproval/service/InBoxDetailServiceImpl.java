package kr.or.ddit.elecapproval.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.elecapproval.dao.InBoxDetailDAO;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

@Service
public class InBoxDetailServiceImpl implements InBoxDetailService {

	@Inject
	public InBoxDetailDAO inBoxDetailDAO;

	@Override
	public ApprovalDocVO retrieveInBoxDetail(ApprovalDocVO approvalDocVO) {
		ApprovalDocVO inBoxDetail = inBoxDetailDAO.selectInBoxDetail(approvalDocVO.getAdNo());
		if(inBoxDetail == null) {
			throw new CustomException(approvalDocVO.getAdNo() + "번 글이 없음.");
		}
		return inBoxDetail;
	}

//  0301 운주	
//	@Override
//	public List<ApprovalVO> retrieveInBoxComment(ApprovalDocVO approvalDocVO) {
//		List<ApprovalVO> inBoxComment = inBoxDetailDAO.retrieveInBoxComment(approvalDocVO);
//		return inBoxComment;
//	}

}
