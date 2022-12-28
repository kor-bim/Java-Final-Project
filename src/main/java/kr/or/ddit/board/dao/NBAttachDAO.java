package kr.or.ddit.board.dao;


import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.NBAttatchVO;
import kr.or.ddit.board.vo.NoticeVO;

@Repository
public interface NBAttachDAO {
	
	public int insertNBAttaches(NoticeVO noticeVO);
	public int deleteNBAttaches(NoticeVO noticeVO);
	public NBAttatchVO selectNBAttach(int nbaNo);
}
