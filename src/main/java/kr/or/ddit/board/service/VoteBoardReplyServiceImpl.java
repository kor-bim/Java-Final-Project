package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.service.BaseService;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.dao.VoteBoardReplyDAO;
import kr.or.ddit.board.vo.VoteBoardReplyVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

@Service
public class VoteBoardReplyServiceImpl extends BaseService implements VoteBoardReplyService {

	@Inject
	private VoteBoardReplyDAO voteReplyDAO;

	@Override
	public int voteReplyCount(PagingVO<VoteBoardReplyVO> pagingVO) {
		return voteReplyDAO.selectVoteReplyCount(pagingVO);
	}

	@Override
	public ServiceResult createVoteReply(VoteBoardReplyVO voteBoardReplyVO) {
		// 글 조회
		
		ServiceResult result = ServiceResult.FAILED;
		int cnt = voteReplyDAO.insertVoteReply(voteBoardReplyVO);
		if(cnt > 0) {
			return ServiceResult.OK;
		}
		return result;
	}

	@Override
	public List<VoteBoardReplyVO> selectVoteReplyList(PagingVO<VoteBoardReplyVO> pagingVO) {
		List<VoteBoardReplyVO> voteReplyList = voteReplyDAO.selectVoteReplyList(pagingVO);
		return voteReplyList ;
	}

	@Override
	public ServiceResult updateVoteReply(VoteBoardReplyVO voteBoardReplyVO) {
		ServiceResult result = null;
		int cnt = voteReplyDAO.updateVoteReply(voteBoardReplyVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult deleteVoteReply(VoteBoardReplyVO voteBoardReplyVO) {
		ServiceResult result = null;
		int cnt = voteReplyDAO.deleteVoteReply(voteBoardReplyVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}
}
