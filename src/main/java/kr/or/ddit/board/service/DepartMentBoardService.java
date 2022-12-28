package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.vo.DepartMentBoardAttatchVO;
import kr.or.ddit.board.vo.DepartMentBoardVO;
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
public interface DepartMentBoardService {
	/**
	 * 게시글 생성
	 * 
	 * @param board
	 * @return OK, FAILED
	 */
	public ServiceResult createBoard(DepartMentBoardVO board);

	/**
	 * 게시글 페이지 카운트
	 * 
	 * @param paging
	 * @return
	 */
	public int retrieveBoardCount(PagingVO<DepartMentBoardVO> paging);

	/**
	 * 게시글 리스트 조회
	 * 
	 * @param paging
	 * @return 검색조건에 맞는 글이 없으면, size == 0
	 */
	public List<DepartMentBoardVO> retrieveBoardList(PagingVO<DepartMentBoardVO> paging);

	/**
	 * 게시글 상세 조회
	 * 
	 * @param board
	 * @return 존재하지 않으면, CustomException 발생
	 */
	public DepartMentBoardVO retrieveBoard(DepartMentBoardVO board);

	/**
	 * 게시글 수정
	 * 
	 * @param board
	 * @return INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult modifyBoard(DepartMentBoardVO board);

	/**
	 * 게시글 삭제
	 * 
	 * @param board
	 * @return INVALIDPASSWORD, OK, FAILED
	 */
	public ServiceResult removeBoard(DepartMentBoardVO board);

	/**
	 * 첨부파일 다운
	 * 
	 * @param att_no
	 * @return
	 */
	public DepartMentBoardAttatchVO download(int att_no);
}
