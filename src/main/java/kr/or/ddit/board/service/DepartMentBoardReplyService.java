package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.vo.DepartMentBoardReplyVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 윤한빈
 * @since 2021. 2. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */

public interface DepartMentBoardReplyService {
	/**
	 * @param reply
	 * @return
	 */
	public ServiceResult createReply(DepartMentBoardReplyVO reply);

	/**
	 * @param pagingVO
	 * @return
	 */
	public int readReplyCount(PagingVO<DepartMentBoardReplyVO> pagingVO);

	/**
	 * @param pagingVO
	 * @return
	 */
	public List<DepartMentBoardReplyVO> readReplyList(PagingVO<DepartMentBoardReplyVO> pagingVO);

	/**
	 * @param reply
	 * @return
	 */
	public ServiceResult modifyReply(DepartMentBoardReplyVO reply);

	/**
	 * @param reply
	 * @return
	 */
	public ServiceResult removeReply(DepartMentBoardReplyVO reply);
}
