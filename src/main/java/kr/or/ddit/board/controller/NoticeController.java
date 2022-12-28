package kr.or.ddit.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.NoticeVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.vo.MemberVO;

@Controller
public class NoticeController {

	@Inject
	private NoticeService service;

	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	/*
	 * 공지사항 목록
	 */

	@RequestMapping("/noticeBoard/list.do")
	@ResponseBody
	public List<NoticeVO> noticeBoardList() {
		List<NoticeVO> noticeBoardList = service.retrieveNoticeBoardList();
		return noticeBoardList;
	}

	@RequestMapping("/noticeBoard/{seq}")
	public String noticeBoardDetail(@PathVariable(value = "seq", required = true) int nbNo, Model model) {
		NoticeVO notice = service.retrieveNoticeBoard(nbNo);
		model.addAttribute("noticeVO", notice);

		return "noticeBoard/modalNoticeView/noticeView";
	}

	/*
	 * 공지사항 등록
	 */

	@RequestMapping(value = "/noticeBoard/noticeForm.do")
	public String noticeInsertForm(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model, MemberVO memberVO) {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setMemId(authMember.getMemId());
		noticeVO.setMemName(authMember.getMemName());
		model.addAttribute("noticeVO", noticeVO);
		return "noticeBoard/modalNoticeView/noticeForm";
	}

	@RequestMapping("/noticeBoard/noticeInsert.do")
	public String noticeBoardInsert(@ModelAttribute("noticeVO") NoticeVO noticeVO, BindingResult errors, Model model,
			RedirectAttributes redirectAttributes ) {

		String goPage = null;
		NotyMessageVO message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.createNoticeBoard(noticeVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("공지사항이 등록되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/noticeBoard";
				break;
			default:
				model.addAttribute("message", NotyMessageVO.builder("등록이 실패하였습니다.").type(NotyType.error).build());
				goPage = "noticeBoard/noticeForm";
				break;
			}
		} else {
			goPage = "noticeBoard/noticelist";
		}
		return goPage;
	}

	/*
	 * 공지사항 업데이트
	 */
	@RequestMapping({ "/noticeBoard/noticeUpdateForm.do/{nbNo}" })
	public String noticeUpdateform(NoticeVO noticeVO, Model model, @PathVariable("nbNo") int nbNo) {
		addCommandAttribute(model);
		noticeVO.setNbNo(nbNo);
		noticeVO = service.retrieveNoticeBoard(noticeVO.getNbNo());

		model.addAttribute("noticeVO", noticeVO);

		return "noticeBoard/modalNoticeView/noticeForm";
	}

	@RequestMapping("/noticeBoard/noticeUpdate.do")
	public String noticeBoardUpdate(@ModelAttribute("noticeVO") NoticeVO noticeVO, BindingResult errors, Model model,
			RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = service.modifyNoticeBoard(noticeVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("공지사항이 수정되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/noticeBoard";
				break;
			default:
				model.addAttribute("message", NotyMessageVO.builder("수정 실패하였습니다.").build());
				goPage = "noticeBoard/noticeForm";
				break;
			}
		} else {
			goPage = "noticeBoard/noticeForm";
		}
		return goPage;
	}

	/*
	 * 공지사항 삭제
	 */

	@RequestMapping({ "/noticeBoard/noticeDeleteForm.do/{nbNo}" })
	public String noticeBoardDelete(NoticeVO noticeVO, Errors errors, @PathVariable("nbNo") int nbNo, Model model,
			RedirectAttributes redirectAttributes) {
		noticeVO.setNbNo(nbNo);

		NotyMessageVO message = null;

		ServiceResult result = service.removeNoticeBoard(noticeVO);

		switch (result) {
		case OK:
			message = NotyMessageVO.builder("삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;

		default:
			message = NotyMessageVO.builder("삭제를 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/noticeBoard";
	}
}
