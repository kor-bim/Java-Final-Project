package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.NBAttatchVO;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

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
public interface NoticeService {
	/**
	 * @param noticeVO
	 * @return OK, FAILED
	 */
	public ServiceResult createNoticeBoard(NoticeVO noticeVO);
	
	/**
	 * @return 검색조건에 맞는 글이 없으면, size == 0 이다. 
	 */
	public List<NoticeVO> retrieveNoticeBoardList();
	/**
	 * @param nbNo
	 * @return 존재하지 않으면, CustomException 발생함
	 */
	public NoticeVO retrieveNoticeBoard(int nbNo);
	
	/**
	 * @param noticeVO
	 * @return  OK, FAILED
	 */
	public ServiceResult modifyNoticeBoard(NoticeVO noticeVO);
	/**
	 * @param noticeVO
	 * @return OK, FAILED
	 */
	public ServiceResult removeNoticeBoard(NoticeVO noticeVO);
	
	/**
	 *  파일 다운로드
	 * @param nbaNo
	 * @return
	 */
	public NBAttatchVO download(int nbaNo);
}
