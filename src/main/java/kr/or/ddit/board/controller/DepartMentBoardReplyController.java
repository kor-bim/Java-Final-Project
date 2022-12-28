package kr.or.ddit.board.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.service.DepartMentBoardReplyService;
import kr.or.ddit.board.vo.DepartMentBoardReplyVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 윤한빈
 * @since 2021. 2. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 6.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@RestController
public class DepartMentBoardReplyController {

	@Inject
	private DepartMentBoardReplyService service;

	/**
	 * @param dbNo
	 * @param currentPage
	 * @return
	 */
	@GetMapping("/departMentBoard/reply/list")
	public PagingVO<DepartMentBoardReplyVO> list(@RequestParam(value = "dbNo", required = true) int dbNo,
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage) {

		// ====검색, 특정글의 댓글만 조회
		DepartMentBoardReplyVO searchDetail = new DepartMentBoardReplyVO();
		searchDetail.setDbNo(dbNo);

		PagingVO<DepartMentBoardReplyVO> pagingVO = new PagingVO<DepartMentBoardReplyVO>();
		pagingVO.setSearchDetail(searchDetail);
		// ========

		int totalRecord = service.readReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage

		List<DepartMentBoardReplyVO> ReplyList = service.readReplyList(pagingVO);
		pagingVO.setDataList(ReplyList);

		return pagingVO;
	}

	/**
	 * @param reply
	 * @return
	 */
	@PostMapping("/departMentBoard/reply/insert")
	public Map<String, Object> insert(@ModelAttribute("reply") DepartMentBoardReplyVO reply) {
		ServiceResult result = service.createReply(reply);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}

	/**
	 * @param reply
	 * @return
	 */
	@PostMapping("/departMentBoard/reply/update")
	public Map<String, Object> update(@ModelAttribute("reply") DepartMentBoardReplyVO reply) {
		ServiceResult result = service.modifyReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}

	/**
	 * @param reply
	 * @return
	 */
	@PostMapping("/departMentBoard/reply/delete")
	public Map<String, Object> delete(@ModelAttribute("reply") DepartMentBoardReplyVO reply) {
		ServiceResult result = service.removeReply(reply);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}

}
