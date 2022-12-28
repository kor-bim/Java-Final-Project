package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.vo.DepartMentBoardReplyVO;

/**
 * @author 윤한빈
 * @since 2021. 2. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface DepartMentBoardReplyDAO {
	/**
	 * @param reply
	 * @return
	 */
	public int insertReply(DepartMentBoardReplyVO reply);

	/**
	 * @param pagingVO
	 * @return
	 */
	public int selectReplyCount(PagingVO<DepartMentBoardReplyVO> pagingVO);

	/**
	 * @param pagingVO
	 * @return
	 */
	public List<DepartMentBoardReplyVO> selectReplyList(PagingVO<DepartMentBoardReplyVO> pagingVO);

	/**
	 * @param reply
	 * @return
	 */
	public int updateReply(DepartMentBoardReplyVO reply);

	/**
	 * @param reply
	 * @return
	 */
	public int deleteReply(DepartMentBoardReplyVO reply);
}
