package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.base.controller.BaseController;
import kr.or.ddit.board.service.VoteBoardService;
import kr.or.ddit.board.vo.VoteBoardVO;
import kr.or.ddit.board.vo.VoteFormVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;

/**
 * 투표게시판 관리 컨트롤러 
 * (투표 등록, 조회, 투표, 수정, 삭제)
 * 
 * @author 이운주
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class VoteBoardController extends BaseController {
	@Inject
	private VoteBoardService voteService;
	
	/**
	 * voteForm.jsp 로 이동
	 * 
	 * @param voteFormVO
	 * @return
	 * @authoe 이운주
	 * @date 2021. 1. 31.
	 */
	@RequestMapping(value = "/voteBoard/voteForm.do")
	public String voteForm(VoteFormVO voteFormVO, Model model) {
		// voteFormVo 에 vbNo가 있음 = 수정 
		if(voteFormVO.getVoteBoardVO().getVbNo()!=null) {
			int vbNo = voteFormVO.getVoteBoardVO().getVbNo();
			try {
				VoteBoardVO voteBoardVO = voteFormVO.getVoteBoardVO(); 
				voteBoardVO = voteService.selectVoteBoard(vbNo);
				voteFormVO.setVoteBoardVO(voteBoardVO);
				model.addAttribute("COMMAND", "MODIFY");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "voteBoard/voteForm";
	}

	/**
	 * 투표게시글 등록하기
	 * 
	 * @author 이운주
	 * @since 2021. 1. 29.
	 * @param voteBoardVo
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/voteBoard/insert.do", method = RequestMethod.POST)
	public String voteInsert(VoteFormVO voteFormVO, BindingResult errors, Model model) {
		String goPage = "voteBoard/voteForm";
		NotyMessageVO message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = voteService.createVoteBoard(voteFormVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("게시물 등록에 성공하였습니다.")
				.type(NotyType.success)
				.build();
				model.addAttribute("message", message);
				goPage = "redirect:/voteBoard";
				break;

			default:
				message = NotyMessageVO.builder("게시물 등록에 실패하였습니다.")
				.type(NotyType.error)
				.build();
				model.addAttribute("message", message);
				model.addAttribute("voteFormVO", voteFormVO);
				break;
			}
		}
		return goPage;
	}

	/**
	 * 투표 게시글 리스트 조회 
	 * 
	 * @param voteFormVO
	 * @return 투표게시판 게시글 리스트
	 * @throws Exception
	 * @authoe 이운주
	 * @date 2021. 1. 28.
	 */
	@RequestMapping("/voteBoard/list.do")
	@ResponseBody
	public List<VoteBoardVO> voteList() {
		return voteService.selectVoteBoardList();
	}

	/**
	 * 투표게시글 한 건 상세조회
	 * 
	 * @param vbNo 투표게시글 번호
	 * @return 투표게시글 상세화면
	 * @throws Exception
	 * @authoe 이운주
	 * @date 2021. 1. 28.
	 */
	@RequestMapping(value = "/voteBoard/{vbNo}")
	public String voteView(@PathVariable(value = "vbNo", required = true) int vbNo
				, VoteFormVO voteFormVO
				, Model model) {
		try {
			// 투표글 한건에 대한 정보
			VoteBoardVO voteBoardVO = voteFormVO.getVoteBoardVO();
			voteBoardVO = voteService.selectVoteBoard(vbNo);
			
			//============
			VoteBoardVO voteCList = voteService.retrieveVoteComplete(vbNo);
			
			ObjectMapper mapper = new ObjectMapper();
			String voteBoardList = mapper.writeValueAsString(voteCList);
			
			model.addAttribute("voteBoardList", voteBoardList);
			//============
			
			model.addAttribute("voteBoardVO", voteBoardVO);
			
			
			
			return "voteBoard/voteView";
		} catch (Exception e) {
			e.printStackTrace();
			return "voteBoard/voteList";
		}
	}
	/**
	 * 
	 * @param vbNo
	 * @param voteFormVO
	 * @param model
	 * @return
	 * @authoe 길영주
	 * @throws JsonProcessingException
	 */
//	@RequestMapping("/voteBoard/voteComplete/{vbNo}")
//	public String voteComplete(@PathVariable(value = "vbNo", required = true) int vbNo
//			, VoteFormVO voteFormVO,Model model)
//			throws JsonProcessingException {
//		VoteBoardVO voteCList = voteService.retrieveVoteComplete(vbNo);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		String voteBoardList = mapper.writeValueAsString(voteCList);
//		
//		model.addAttribute("voteBoardList", voteBoardList);
//		
//		return "/voteBoard/voteView";
//	}
	
	
	

	/**
	 * 투표하기
	 * 
	 * @author 이운주
	 * @since 2021. 1. 29.
	 * @param voteBoardVO 투표한 게시글과 투표자에 대한 정보(memberVO) 포함
	 * @param model
	 */
	@RequestMapping(value = "/voteBoard/voting.do", method = RequestMethod.POST)
	public String voting(VoteFormVO voteFormVO, Model model) {
		int vbNo = voteFormVO.getVoteBoardVO().getVbNo();
		voteFormVO.getVoteBoardVO().getVotePrtcpVO().setVbNo(vbNo); // vbNo 를 votePrtcpVO 에 넣어줌 
		try {
			voteService.voting(voteFormVO.getVoteBoardVO());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/voteBoard/" + vbNo;
	}

	/**
	 * 투표게시글 업데이트 
	 * 
	 * @param voteFormVO
	 * @param errors
	 * @param model
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 3.
	 */
	@RequestMapping(value = "/voteBoard/update.do",method = RequestMethod.POST)
	public String voteUpdate(VoteFormVO voteFormVO, BindingResult errors, Model model){
		String goPage = "voteBoard/voteForm";
		if (!errors.hasErrors()) {
			try {
				int cnt = voteService.updateVoteBoard(voteFormVO);
				if (cnt > 0) {
					goPage = "redirect:/voteBoard/"+voteFormVO.getVoteBoardVO().getVbNo();
				}
			} catch (Exception e) {
				model.addAttribute("message", "게시글 수정에 실패했습니다. 다시 시도해주세요");
				model.addAttribute("voteFormVO", voteFormVO);
			}
		}
		return goPage;
	}
	
	@RequestMapping(value = "/voteBoard/delete.do", method = RequestMethod.POST)
	public String voteDelete(VoteFormVO voteFormVO, RedirectAttributes redirectAttributes , Model model){
		String goPage = "voteBoard/voteView";
		NotyMessageVO message = null;
		try {
			int cnt = voteService.deleteVoteBoard(voteFormVO);
			if (cnt > 0) {
				message = NotyMessageVO.builder("게시글 삭제에 성공했습니다.")
						.type(NotyType.success)
						.build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/voteBoard/"+voteFormVO.getVoteBoardVO().getVbNo();
			}
		} catch (Exception e) {
			message = NotyMessageVO.builder("실패하였습니다.")
					.type(NotyType.error)
					.build();
			model.addAttribute("message", message);
			model.addAttribute("voteFormVO", voteFormVO);
		}
		return goPage;
	}
	
}
