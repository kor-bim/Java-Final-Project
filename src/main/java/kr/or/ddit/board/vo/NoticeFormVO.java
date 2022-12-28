package kr.or.ddit.board.vo;

import lombok.Data;
/**
 * @author 길영주
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.        길영주       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Data
public class NoticeFormVO {
	private NoticeVO noticeVO;

	private NoticeVO searchNoticeVO;

	public NoticeFormVO() {
		this.noticeVO = new NoticeVO();
		this.searchNoticeVO = new NoticeVO();
	}
}
