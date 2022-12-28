package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.VoteBoardVO;
import kr.or.ddit.board.vo.VoteCateVO;
import kr.or.ddit.board.vo.VoteFormVO;
import kr.or.ddit.board.vo.VotePrtcpVO;

@Repository
public interface VoteBoardDAO {

	// 전체 조회 : retrieve voteBoard List
	public List<VoteBoardVO> selectVoteList();
	
	// 조회 : retrieve voteBoard and voters(vote_prtcp)
	public VoteBoardVO selectVoteBoardView(int vbNo);
	public List<VotePrtcpVO> selectVoters(int vbNo);
	/**
	 * 완료된 투표 정보 조회~
	 * @param vbNo
	 * @return
	 */
	VoteBoardVO selectVoteComplete(int vbNo);
	// 조회수 + 1
	public void incrementHit(int vbNo);
	
	// 등록 : insert voteBoard and voteCate 
	public int insertVoteBoard(VoteBoardVO voteBoardVo);
	public int insertVoteCate(VoteCateVO voteCateVO);

	// 투표 : voting  
	public int insertVotePrtcpVO(VotePrtcpVO votePrtcpVO);
	// 투표참여수 +1
	public void incrementVoteCnt(Integer vbNo);
	
	// 수정 : update
	public int updateVoteBoard(VoteBoardVO voteBoardVO);  // voteBoard update
	public int resetVotePrtcpVO(VoteBoardVO voteBoardVO); // 응답결과 초기화
	public int updateVoteCate(VoteFormVO voteFormVO);     // voteCate update 
	
	// 삭제 : delete 
	public int deleteVoteCate(VoteFormVO voteFormVO);
	public int deleteVoteBoard(VoteFormVO voteFormVO);



}
