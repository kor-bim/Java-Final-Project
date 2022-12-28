package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.base.service.BaseService;
import kr.or.ddit.board.dao.VoteBoardDAO;
import kr.or.ddit.board.vo.VoteBoardVO;
import kr.or.ddit.board.vo.VoteCateVO;
import kr.or.ddit.board.vo.VoteFormVO;
import kr.or.ddit.board.vo.VotePrtcpVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

@Service
public class VoteBoardServiceImpl extends BaseService implements VoteBoardService {
	
	@Inject
	private VoteBoardDAO voteDAO;
	
	@Transactional
	@Override
	public ServiceResult createVoteBoard(VoteFormVO voteFormVO) {
		
		VoteBoardVO voteBoardVO = voteFormVO.getVoteBoardVO();
		VoteCateVO voteCateVO = voteFormVO.getVoteCateVO();
		try {
			int cnt = voteDAO.insertVoteBoard(voteBoardVO);
			if(cnt > 0) {
				// voteBoard 등록에 성공하면, voteCate(투표항목) 등록
				voteCateVO.setVbNo(voteBoardVO.getVbNo());
				cnt += createVoteCate(voteCateVO); 
			}
			ServiceResult result = null;
			if(cnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAILED;
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	// 투표항목(선택지) 등록
	private int createVoteCate(VoteCateVO voteCateVO)  {
		int cnt = 0;
		if(voteCateVO != null ) {
			cnt += voteDAO.insertVoteCate(voteCateVO);
		}
		return cnt;
	}

	@Override
	public List<VoteBoardVO> selectVoteBoardList() {
		List<VoteBoardVO> voteList = voteDAO.selectVoteList();
		return voteList; 
	}

	@Transactional
	@Override
	public VoteBoardVO selectVoteBoard(int vbNo) {
		VoteBoardVO voteBoardVO = voteDAO.selectVoteBoardView(vbNo);
		if(voteBoardVO == null) { 
			throw new CustomException(vbNo + "번 글이 없습니다.");
		}else {
			voteDAO.incrementHit(vbNo); // 조회수 +1
			List<VotePrtcpVO> voterList = voteDAO.selectVoters(vbNo);
			voteBoardVO.setVoterList(voterList); // 투표 참여자 리스트 
			return voteBoardVO;
		}
	}

	@Transactional
	@Override
	public int voting(VoteBoardVO voteBoardVO){
		// 1. vote_prtcp insert
		VotePrtcpVO votePrtcpVO = voteBoardVO.getVotePrtcpVO();
		int cnt = voteDAO.insertVotePrtcpVO(votePrtcpVO);
		voteDAO.incrementVoteCnt(voteBoardVO.getVbNo()); // 투표 참여수 +1 
		return cnt;
	}
	
	@Transactional
	@Override
	public int updateVoteBoard(VoteFormVO voteFormVO) {
		// 투표 수정 순서 
		// 0. VOTE_BOARD 조회 
		// 1. update VOTE_BOARD : 투표정보 변경
		// 2. delete VOTE_PRTCP : 응답결과 초기화
		// 3. delete VOTE_CATE  : 선택지 변경 ( 있는 건 update, 없는 건 insert )
		int vbNo = voteFormVO.getVoteBoardVO().getVbNo();
		VoteBoardVO savedVoteBoard = voteDAO.selectVoteBoardView(vbNo);
		if(savedVoteBoard == null) {
			throw new CustomException(vbNo + "번 글이 없음");
		}
		
		VoteBoardVO voteBoardVo = voteFormVO.getVoteBoardVO();
		
//		encodePassword(board);
//		String inputPass = board.getBo_pass();
//		String savedPass = savedBoard.getBo_pass();
//		if(savedPass.equals(inputPass)) {
			
			int cnt = voteDAO.updateVoteBoard(voteBoardVo); 
			if(cnt > 0) {
				resetVotePrtcpVO(voteFormVO);
				updateVoteCate(voteFormVO);
			}
//		}
		return cnt;
	}
	private int resetVotePrtcpVO(VoteFormVO voteFormVO) {
		VoteBoardVO voteBoardVo = voteFormVO.getVoteBoardVO();
		int cnt = voteDAO.resetVotePrtcpVO(voteBoardVo);
		return cnt;
	}
	private int updateVoteCate(VoteFormVO voteFormVO) {
		int cnt = voteDAO.updateVoteCate(voteFormVO);
		return cnt;
	}
	
	@Transactional
	@Override
	public int deleteVoteBoard(VoteFormVO voteFormVO) {
		// 투표게시판 삭제 순서 
		// 0. VOTE_BOARD 조회          : 존재여부 확인
		// 1. delete VOTE_PRTCP : 응답결과 삭제
		// 2. delete VOTE_CATE  : 선택지 삭제
		// 3. update VOTE_BOARD : 삭제여부(Y), 제목(삭제된 게시글), 내용(삭제된 게시글입니다.) 
		
		int vbNo = voteFormVO.getVoteBoardVO().getVbNo();
		VoteBoardVO savedVoteBoard = voteDAO.selectVoteBoardView(vbNo);
		if(savedVoteBoard == null) {
			throw new CustomException(vbNo + "번 글이 없음");
		}
		
//		encodePassword(board);
//		String inputPass = board.getBo_pass();
//		String savedPass = savedBoard.getBo_pass();
//		if(savedPass.equals(inputPass)) {
		
			resetVotePrtcpVO(voteFormVO); // 응답결과 삭제 
			int cnt = deleteVoteCate(voteFormVO); // 선택지 삭제
			if(cnt > 0) {
				cnt += deleteVoteBoardVO(voteFormVO);
			}
//		}
		return cnt;
	}
	private int deleteVoteCate(VoteFormVO voteFormVO) {
		return voteDAO.deleteVoteCate(voteFormVO);
	}
	private int deleteVoteBoardVO(VoteFormVO voteFormVO) {
		int cnt = voteDAO.deleteVoteBoard(voteFormVO);
		if(cnt > 0) {
			voteDAO.selectVoteBoardView(voteFormVO.getVoteBoardVO().getVbNo());
		}
		return cnt;
	}
	
	
	@Override
	public VoteBoardVO retrieveVoteComplete(int vbNo) {
		return voteDAO.selectVoteComplete(vbNo);
	}
	
}
