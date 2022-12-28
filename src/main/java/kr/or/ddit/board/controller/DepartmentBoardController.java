package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.board.service.DepartMentBoardService;
import kr.or.ddit.board.vo.DepartMentBoardVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.vo.MemberVO;

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
 * 2021. 2. 2.      이운주             boardView() 요청 url 변경 {what} -> /departMentBoard/{what}
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class DepartmentBoardController {

	@Inject
	DepartMentBoardService service;

	/**
	 * 부서게시판 리스트 조회 및 리스트 페이지 이동
	 * 
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/departMentBoard")
	public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<DepartMentBoardVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setDeptCode(authMember.getDeptCode());

		int totalRecord = service.retrieveBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<DepartMentBoardVO> DepartMentBoardList = service.retrieveBoardList(pagingVO);
		pagingVO.setDataList(DepartMentBoardList);

		model.addAttribute("pagingVO", pagingVO);

		return "departMentBoard/list";
	}

	/**
	 * 부서게시판 상세페이지 조회 맟 상세페이지 화면 이동
	 * 
	 * @param dbNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/departMentBoard/{what}")
	public String boardView(@PathVariable(value = "what", required = true) int dbNo, Model model) {
		DepartMentBoardVO board = new DepartMentBoardVO();
		board.setDbNo(dbNo);
		board = service.retrieveBoard(board);
		model.addAttribute("board", board);
		return "departMentBoard/boardView";
	}

	/**
	 * 부서게시글 등록 화면
	 * 
	 * @return
	 */
	@GetMapping("/departMentBoard/insert")
	public String insertForm() {
		return "departMentBoard/boardForm";
	}

	/**
	 * 부서게시글 등록
	 * 
	 * @param board
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping("/departMentBoard/insert")
	public String insert(@Validated(InsertGroup.class) @ModelAttribute("board") DepartMentBoardVO board,
			RedirectAttributes redirectAttributes, BindingResult errors, Model model) {
		String goPage = null;
		NotyMessageVO message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createBoard(board);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("글이 등록되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/departMentBoard/" + board.getDbNo();
				break;
			default:
				model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
				goPage = "departMentBoard/boardForm";
				break;
			}
		} else {
			model.addAttribute("message", NotyMessageVO.builder("등록에 실패하였습니다").type(NotyType.error).build());
			goPage = "departMentBoard/boardForm";
		}
		return goPage;
	}

	/**
	 * 부서게시글 업데이트 화면
	 * 
	 * @param dbNo
	 * @param model
	 * @return
	 */
	@GetMapping({ "/departMentBoard/update/{dbNo}" })
	public String updateForm(@PathVariable("dbNo") int dbNo, Model model) {
		DepartMentBoardVO board = new DepartMentBoardVO();
		board.setDbNo(dbNo);
		board = service.retrieveBoard(board);
		model.addAttribute("board", board);
		return "departMentBoard/boardForm";
	}

	/**
	 * 부서게시글 업데이트
	 * 
	 * @param board
	 * @param errors
	 * @param model
	 * @return
	 */
	@PostMapping("/departMentBoard/update/{dbNo}")
	public String update(@Validated(UpdateGroup.class) @ModelAttribute("board") DepartMentBoardVO board,
			BindingResult errors, Model model) {
		String goPage = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyBoard(board);
			switch (result) {
			case OK:
				goPage = "redirect:/departMentBoard/" + board.getDbNo();
				break;
			case INVALIDPASSWORD:
				model.addAttribute("message", NotyMessageVO.builder("비밀번호 오류").build());
				goPage = "departMentBoard/boardForm";
				break;
			default:
				model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
				goPage = "departMentBoard/boardForm";
				break;
			}
		} else {
			goPage = "departMentBoard/boardForm";
		}
		return goPage;
	}

	/**
	 * 부서게시글 삭제
	 * 
	 * @param board
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/departMentBoard/delete")
	public String delete(@Validated(DeleteGroup.class) @ModelAttribute("board") DepartMentBoardVO board, Errors errors,
			RedirectAttributes redirectAttributes) {

		// 글번호, 비밀번호에 대한 검증(삭제 힌트 적용)
		String goPage = "redirect:/departMentBoard/" + board.getDbNo();
		NotyMessageVO message = null;
		// 각 케이스별 메시지와 이동 위치 필요
		if (!errors.hasErrors()) { // 검증 통과
			ServiceResult result = service.removeBoard(board);
			switch (result) {
			case INVALIDPASSWORD:
				message = NotyMessageVO.builder("비밀번호 오류").type(NotyType.error).layout(NotyLayout.topCenter)
						.timeout(3000).build();
				break;
			case FAILED:
				message = NotyMessageVO.builder("서버 오류").type(NotyType.error).layout(NotyLayout.topCenter).timeout(3000)
						.build();
				break;
			default: // OK , 삭제 성공시 게시글 목록으로 이동
				goPage = "redirect:/departMentBoard";
				break;
			}

		} else { // 불통
			message = NotyMessageVO.builder("글번호나 비밀번호 누락").type(NotyType.error).layout(NotyLayout.topCenter)
					.timeout(3000).build();
		}
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);

		return goPage;
	}

}
