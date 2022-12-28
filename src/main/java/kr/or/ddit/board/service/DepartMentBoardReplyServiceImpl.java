package kr.or.ddit.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.dao.DepartMentBoardReplyDAO;
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
@Service
public class DepartMentBoardReplyServiceImpl implements DepartMentBoardReplyService {

	@Inject
	private DepartMentBoardReplyDAO dao;

	@Override
	public ServiceResult createReply(DepartMentBoardReplyVO reply) {
		int rowcnt = dao.insertReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if (rowcnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public int readReplyCount(PagingVO<DepartMentBoardReplyVO> pagingVO) {
		return dao.selectReplyCount(pagingVO);
	}

	@Override
	public List<DepartMentBoardReplyVO> readReplyList(PagingVO<DepartMentBoardReplyVO> pagingVO) {
		return dao.selectReplyList(pagingVO);
	}

	@Override
	public ServiceResult modifyReply(DepartMentBoardReplyVO reply) {
		int rowcnt = dao.updateReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if (rowcnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public ServiceResult removeReply(DepartMentBoardReplyVO reply) {
		int rowcnt = dao.deleteReply(reply);
		ServiceResult result = ServiceResult.FAILED;
		if (rowcnt > 0)
			result = ServiceResult.OK;
		return result;
	}

}
