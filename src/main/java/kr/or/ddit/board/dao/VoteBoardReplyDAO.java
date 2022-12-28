package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.vo.VoteBoardReplyVO;

@Repository
public interface VoteBoardReplyDAO {
	// 댓글 갯수 조회
	public int selectVoteReplyCount(PagingVO<VoteBoardReplyVO> pagingVO);
	
	// 조회
	public List<VoteBoardReplyVO> selectVoteReplyList(PagingVO<VoteBoardReplyVO> pagingVO);
	
	// 등록 : insert 
	public int insertVoteReply(VoteBoardReplyVO voteBoardReplyVO);

	// 수정 : update
	public int updateVoteReply(VoteBoardReplyVO voteBoardReplyVO);
	
	// 삭제 : delete 
	public int deleteVoteReply(VoteBoardReplyVO voteBoardReplyVO);
	
}
