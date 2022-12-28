package kr.or.ddit.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.board.vo.NoticeVO;

/**
 * @author 길영주
 * @since 2021. 1. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 29.      길영주       최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Repository
public interface NoticeBoardDAO {
	/**
	 * @param notice
	 * @return
	 */
	public int insertNoticeBoard(NoticeVO notice);

	/**
	 * @return
	 */
	public List<NoticeVO> selectNoticeBoardList();

	/**
	 * @param nbNo
	 * @return
	 */
	public NoticeVO selectNoticeBoard(int nbNo);

	/**
	 * @param nbNo
	 * @return
	 */
	public int noticeincrementHit(int nbNo);

	/**
	 * @param notice
	 * @return
	 */
	public int updateNoticeBoard(NoticeVO notice);

	/**
	 * @param nbNo
	 * @return
	 */
	public int deleteNoticeBoard(int nbNo);
}
