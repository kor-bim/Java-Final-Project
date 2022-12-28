package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.dao.ElecapprovalInBoxDAO;
import kr.or.ddit.elecapproval.vo.InBoxVO;

@Service
public class ElecapprovalInBoxServiceImpl implements ElecapprovalInBoxService {

	@Inject
	private ElecapprovalInBoxDAO dao;

	@Override
	public int retrieveInBoxAllListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxAllListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxAllList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxAllList(pagingVO);
	}

	@Override
	public int retrieveInBoxDraftListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxDraftListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxDraftList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxDraftList(pagingVO);
	}

	@Override
	public int retrieveInBoxApprovalListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxApprovalListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxApprovalList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxApprovalList(pagingVO);
	}

	@Override
	public int retrieveInBoxReceptionListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxReceptionListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxReceptionList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxReceptionList(pagingVO);
	}

	@Override
	public int retrieveInBoxPassAlongListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxPassAlongListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxPassAlongList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxPassAlongList(pagingVO);
	}

	@Override
	public int retrieveInBoxReturnListCount(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxReturnListCount(pagingVO);
	}

	@Override
	public List<InBoxVO> retrieveInBoxReturnList(PagingVO<InBoxVO> pagingVO) {
		return dao.selectInBoxReturnList(pagingVO);
	}

}
