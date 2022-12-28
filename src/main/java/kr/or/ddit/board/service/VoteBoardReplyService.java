package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.vo.VoteBoardReplyVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

public interface VoteBoardReplyService {
	
	/**
	 * 댓글 총 갯수 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 6.
	 * @param pagingVO
	 * @return
	 */
	public int voteReplyCount(PagingVO<VoteBoardReplyVO> pagingVO);
	
	/**
	 * 댓글 리스트 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 4.
	 * @param voteBoardReplyVO
	 * @return
	 */
	public List<VoteBoardReplyVO> selectVoteReplyList(PagingVO<VoteBoardReplyVO> pagingVO);

	/**
	 * 투표 게시글 댓글 생성 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 4.
	 * @param voteBoardReplyVO
	 * @return
	 */
	public ServiceResult createVoteReply(VoteBoardReplyVO voteBoardReplyVO);
	/**
	 * 댓글 수정 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 4.
	 * @param voteBoardReplyVO
	 * @return
	 */
	public ServiceResult updateVoteReply(VoteBoardReplyVO voteBoardReplyVO);
	/**
	 * 댓글 삭제 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 4.
	 * @param voteBoardReplyVO
	 * @return
	 */
	public ServiceResult deleteVoteReply(VoteBoardReplyVO voteBoardReplyVO);
	

}
