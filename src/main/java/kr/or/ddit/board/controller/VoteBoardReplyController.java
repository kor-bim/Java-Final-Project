package kr.or.ddit.board.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.service.VoteBoardReplyService;
import kr.or.ddit.board.vo.VoteBoardReplyVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * 투표게시판 댓글 관리 컨트롤러
 * 
 * @author 이운주
 * @since 2021. 2. 4.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 4.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class VoteBoardReplyController {

	@Inject
	private VoteBoardReplyService voteReplyService;
	
	/**
	 * 투표 한건에 대한 댓글 리스트 조회
	 * 
	 * @author 이운주
	 * @since 2021. 2. 5.
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voteBoard/reply")
	@ResponseBody
	public PagingVO<VoteBoardReplyVO> voteReplyList(
			@RequestParam(value = "vbNo", required = true) int vbNo,
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage) {
		
		// ====검색, 특정글의 댓글만 조회
		VoteBoardReplyVO searchDetail = new VoteBoardReplyVO();
		searchDetail.setVbNo(vbNo);
		
		PagingVO<VoteBoardReplyVO> pagingVO = new PagingVO<VoteBoardReplyVO>();
		pagingVO.setSearchDetail(searchDetail);
		// ========

		int totalRecord = voteReplyService.voteReplyCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord); // totalPage
		pagingVO.setCurrentPage(currentPage); // startRow, endRow, startPage, endPage

		List<VoteBoardReplyVO> ReplyList = voteReplyService.selectVoteReplyList(pagingVO);
		pagingVO.setDataList(ReplyList);

		return pagingVO;
	}


	/**
	 * 댓글 등록
	 * 
	 * @author 이운주
	 * @since 2021. 2. 5.
	 * @param voteBoardReplyVO
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/voteBoard/reply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insert(
			@ModelAttribute("reply") VoteBoardReplyVO voteBoardReplyVO
			, RedirectAttributes redirectAttributes, Model model) {
		ServiceResult result = voteReplyService.createVoteReply(voteBoardReplyVO);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	/**
	 * 댓글 수정
	 * 
	 * @author 이운주
	 * @since 2021. 2. 8.
	 * @param voteBoardReplyVO
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/voteBoard/reply/update.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> update(
			@ModelAttribute("reply") VoteBoardReplyVO voteBoardReplyVO
			, RedirectAttributes redirectAttributes, Model model) {
		ServiceResult result = voteReplyService.updateVoteReply(voteBoardReplyVO);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	@RequestMapping(value = "/voteBoard/reply/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(
			@ModelAttribute("reply") VoteBoardReplyVO voteBoardReplyVO
			, RedirectAttributes redirectAttributes, Model model) {
		ServiceResult result = voteReplyService.deleteVoteReply(voteBoardReplyVO);
		Map<String, Object> resultMap = Collections.singletonMap("result", result);
		return resultMap;
	}
	
	
	
}
