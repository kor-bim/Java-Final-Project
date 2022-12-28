package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.VoteBoardVO;
import kr.or.ddit.board.vo.VoteFormVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
/**
 * @author 이운주
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 * 
 * 투표게시판 관련 service
 */
public interface VoteBoardService {
	
	/** 투표게시판 새글 등록
	 * @param voteBoardVo
	 * @return ServiceResult  OK, FAILED
	 * @authoe 이운주
	 * @date 2021. 1. 29.
	 */
	public ServiceResult createVoteBoard(VoteFormVO voteFormVO);

	/**
	 * 투표게시판 게시글 목록 조회
	 * 
	 * @author 이운주
	 * @since 2021. 1. 28.
	 * @param searchVO
	 * @return
	 */
	public List<VoteBoardVO> selectVoteBoardList();
	
	/**
	 * 투표 게시글 1건 조회
	 * @author 이운주
	 * @since 2021. 1. 28.
	 * @param vb_no
	 * @return
	 */
	public VoteBoardVO selectVoteBoard(int vbNo);
	
	/**
	 * 투표 
	 * @author 이운주
	 * @since 2021. 1. 29.
	 * @return
	 */
	public int voting(VoteBoardVO voteBoardVO);

	/**
	 * 투표게시판 수정
	 * 
	 * @author 이운주
	 * @since 2021. 2. 3.
	 * @param voteFormVO
	 * @return
	 */
	public int updateVoteBoard(VoteFormVO voteFormVO);
	
	/**
	 * 투표 게시판 삭제 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 4.
	 * @param voteFormVO
	 * @return
	 */
	public int deleteVoteBoard(VoteFormVO voteFormVO);
	
	/**
	 * 투표 완료 게시판 chart
	 * @param vbNo
	 * @return
	 */
	public VoteBoardVO retrieveVoteComplete(int vbNo);
}
