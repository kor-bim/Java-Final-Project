package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalDocCommonDAO;
import kr.or.ddit.elecapproval.dao.ElecApprovalUserDAO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;

@Service
public class ElecApprovalUserServiceImpl implements ElecApprovalUserService {

	@Inject
	private ElecApprovalUserDAO dao;

	@Inject
	private ApprovalDocCommonDAO docComnDAO;

	@Override
	public int retrieveAprvlLineListCount(PagingVO<ApprovalLineVO> pagingVO) {
		return dao.selectAprvlLineListCount(pagingVO);
	}

	@Override
	public List<ApprovalLineVO> retrieveAprvlLineList(PagingVO<ApprovalLineVO> pagingVO) {
		return dao.selectAprvlLineList(pagingVO);
	}

	@Override
	public ApprovalLineVO retrieveAprvlLine(ApprovalLineVO aprvlVO) {
		return docComnDAO.selectApprovalLine(aprvlVO);
	}

	@Transactional
	@Override
	public ServiceResult createApprovalLine(ApprovalLineVO approvalLineVO) {
		ServiceResult result = ServiceResult.FAILED;

		try {
			Integer lineNo = approvalLineVO.getAlNo();
			int cnt = 0;
			if (lineNo == null) {
				cnt = dao.insertApprovalLine(approvalLineVO);
				if (cnt <= 0) {
					return result;
				}
			}
			if (cnt > 0) {
				dao.insertApprovalLineDetail(approvalLineVO);
				result = ServiceResult.OK;
			}
		} catch (Exception e) {
			return result;
		}
		return result;
	}

	@Override
	public ServiceResult removeAprvlLine(ApprovalLineVO aprvlVO) {
		ServiceResult result = ServiceResult.FAILED;
		dao.deleteApprovalLine(aprvlVO);
		ApprovalLineVO savedaprvlVO = docComnDAO.selectApprovalLine(aprvlVO);
		if (savedaprvlVO == null) {
			result = ServiceResult.OK;
		}
		return result;
	}

}
