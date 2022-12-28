package kr.or.ddit.board.vo;

/**
 * VoteFormVo
 * VoteList.jsp 에서 ajax로 보낼 때
 * 
 * @author 이운주
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public class VoteFormVO {

	private VoteBoardVO voteBoardVO;
	/** 검색조건 */
	private VoteBoardVO searchVoteVO;
	/** 투표 */
	private VoteCateVO voteCateVO;
	/** 댓글 */ 
	private VoteBoardReplyVO voteBoardReplyVO;
	
	public VoteFormVO() {
		this.voteBoardVO = new VoteBoardVO();
		this.searchVoteVO = new VoteBoardVO();
		this.voteCateVO = new VoteCateVO();
		this.voteBoardReplyVO = new VoteBoardReplyVO();
	}

	public VoteBoardVO getVoteBoardVO() {
		return voteBoardVO;
	}

	public void setVoteBoardVO(VoteBoardVO voteBoardVO) {
		this.voteBoardVO = voteBoardVO;
	}

	public VoteBoardVO getSearchVoteVO() {
		return searchVoteVO;
	}

	public void setSearchVoteVO(VoteBoardVO searchVoteVO) {
		this.searchVoteVO = searchVoteVO;
	}

	public VoteCateVO getVoteCateVO() {
		return voteCateVO;
	}

	public void setVoteCateVO(VoteCateVO voteCateVO) {
		this.voteCateVO = voteCateVO;
	}

	public VoteBoardReplyVO getVoteBoardReplyVO() {
		return voteBoardReplyVO;
	}

	public void setVoteBoardReplyVO(VoteBoardReplyVO voteBoardReplyVO) {
		this.voteBoardReplyVO = voteBoardReplyVO;
	}
}
