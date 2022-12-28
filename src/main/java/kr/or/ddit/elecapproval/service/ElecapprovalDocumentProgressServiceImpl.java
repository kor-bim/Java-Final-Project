package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.dao.ElecapprovalDocumentProgressDAO;
import kr.or.ddit.elecapproval.vo.DocumentProgressVO;

@Service
public class ElecapprovalDocumentProgressServiceImpl implements ElecapprovalDocumentProgressService {

	@Inject
	private ElecapprovalDocumentProgressDAO dao;

	@Override
	public int retrieveApprovalAllListCount(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalAllListCount(pagingVO);
	}

	@Override
	public List<DocumentProgressVO> retrieveApprovalAllList(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalAllList(pagingVO);
	}

	@Override
	public int retrieveApprovalAwaitListCount(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalAwaitListCount(pagingVO);
	}

	@Override
	public List<DocumentProgressVO> retrieveApprovalAwaitList(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalAwaitList(pagingVO);
	}

	@Override
	public int retrieveApprovalProgressListCount(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalProgressListCount(pagingVO);
	}

	@Override
	public List<DocumentProgressVO> retrieveApprovalProgressList(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalProgressList(pagingVO);
	}

	@Override
	public int retrieveApprovalConfirmListCount(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalConfirmListCount(pagingVO);
	}

	@Override
	public List<DocumentProgressVO> retrieveApprovalConfirmList(PagingVO<DocumentProgressVO> pagingVO) {
		return dao.selectApprovalConfirmList(pagingVO);
	}

}
