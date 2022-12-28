package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.DepartMentBoardAttatchVO;
import kr.or.ddit.board.vo.DepartMentBoardVO;

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
@Repository
public interface DepartMentBoardAttatchDAO {

	/**
	 * @param board
	 * @return
	 */
	public int insertAttaches(DepartMentBoardVO board);

	/**
	 * @param board
	 * @return
	 */
	public int deleteAttatches(DepartMentBoardVO board);

	/**
	 * @param dbaNo
	 * @return
	 */
	public DepartMentBoardAttatchVO selectAttach(int dbaNo);
}
