package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
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
public interface DepartmentBoardDAO {
	/**
	 * @param board
	 * @return
	 */
	public int insertBoard(DepartMentBoardVO board);

	/**
	 * @param paging
	 * @return
	 */
	public int selectBoardCount(PagingVO<DepartMentBoardVO> paging);

	/**
	 * @param paging
	 * @return
	 */
	public List<DepartMentBoardVO> selectBoardList(PagingVO<DepartMentBoardVO> paging);

	/**
	 * @param bo_no
	 * @return
	 */
	public DepartMentBoardVO selectBoard(int bo_no);

	/**
	 * @param dbNo
	 * @return
	 */
	public int incrementHit(int dbNo);

	/**
	 * @param board
	 * @return
	 */
	public int updateBoard(DepartMentBoardVO board);

	/**
	 * @param dbNo
	 * @return
	 */
	public int deleteBoard(int dbNo);
}
