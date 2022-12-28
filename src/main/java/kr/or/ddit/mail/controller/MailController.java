package kr.or.ddit.mail.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyLayout;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.service.MemberAdminService;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.mail.service.MailService;
import kr.or.ddit.mail.vo.MailVO;

/**
 * 메일 관련 컨트롤러
 * 
 * @author 윤한빈
 * @since 2021. 2. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 27.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class MailController {

	@Inject
	MemberAdminService adminService;

	@Inject
	MailService service;

	@RequestMapping("/mail/inbox")
	public String mailInboxlist(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<MailVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = service.retrieveInboxCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MailVO> mailList = service.retrieveInboxList(pagingVO);
		pagingVO.setDataList(mailList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);

		return "mail/mailInbox";
	}

	@RequestMapping("/mail/sentbox")
	public String mailReceivelist(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<MailVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = service.retrieveSentBoxCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MailVO> mailList = service.retrieveSentBoxList(pagingVO);
		pagingVO.setDataList(mailList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);

		return "mail/mailSentBox";
	}

	@RequestMapping("/mail/starred")
	public String mailStarredlist(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<MailVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = service.retrieveStarredCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MailVO> mailList = service.retrieveStarredList(pagingVO);
		pagingVO.setDataList(mailList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);

		return "mail/mailStarred";
	}

	@RequestMapping("/mail/important")
	public String mailImportantlist(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<MailVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = service.retrieveImportantCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MailVO> mailList = service.retrieveImportantList(pagingVO);
		pagingVO.setDataList(mailList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);

		return "mail/mailImportant";
	}

	@RequestMapping("/mail/trash")
	public String mailTrashlist(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<MailVO> pagingVO = new PagingVO<>(10, 5);
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = service.retrieveTrashCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MailVO> mailList = service.retrieveTrashList(pagingVO);
		pagingVO.setDataList(mailList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);

		return "mail/mailTrash";
	}

	@RequestMapping("/mail/mailDetail/{mailNo}")
	public String mailDetail(@PathVariable(value = "mailNo", required = true) int mailNo,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model) {
		MailVO mailVO = new MailVO();
		mailVO.setMailNo(mailNo);
		service.modifyRead(mailNo, authMember.getMemId());
		mailVO = service.retrieveMail(mailVO);
		model.addAttribute("mail", mailVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);
		return "mail/mailDetail";
	}

	@PostMapping("/mail/updateBookmark")
	@ResponseBody
	public String mailUpdateBookmark(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			@ModelAttribute("mailVO") MailVO mailVO) {
		ServiceResult result = service.modifyBookmark(mailVO, authMember);
		String message = "";
		if (result == ServiceResult.OK) {
			message = "OK";
		} else {
			message = "FAILED";
		}
		return message;
	}

	@PostMapping("/mail/updateStarred")
	@ResponseBody
	public String mailUpdateStarred(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			@ModelAttribute("mailVO") MailVO mailVO) {
		ServiceResult result = service.modifyStarred(mailVO, authMember);
		String message = "";
		if (result == ServiceResult.OK) {
			message = "OK";
		} else {
			message = "FAILED";
		}
		return message;
	}

	@PostMapping("/mail/sendMail")
	public String sendMail(@ModelAttribute("mailVO") MailVO mailVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model) {
		NotyMessageVO message = null;
		mailVO.setSenderId(authMember.getMemId());
		ServiceResult result = service.createMail(mailVO);
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("메일 전송이 성공되었습니다").type(NotyType.success).build();
			model.addAttribute("message", message);
			break;
		default:
			model.addAttribute("message", NotyMessageVO.builder("메일 전송 실패").build());
			break;
		}
		return "redirect:/mail/sentbox";
	}

	@GetMapping("/mail/moveToTrash/{mailNo}")
	public String moveToTrash(@PathVariable(value = "mailNo", required = true) int mailNo,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			RedirectAttributes redirectAttributes) {
		String goPage = "redirect:/mail/mailDetail/" + mailNo;
		NotyMessageVO message = null;
		ServiceResult result = service.moveToTrash(mailNo, authMember.getMemId());
		switch (result) {
		case FAILED:
			message = NotyMessageVO.builder("서버 오류").type(NotyType.error).layout(NotyLayout.topCenter).timeout(3000)
					.build();
			break;
		default: // OK , 삭제 성공시 게시글 목록으로 이동
			message = NotyMessageVO.builder("휴지통으로 이동되었습니다").type(NotyType.success).layout(NotyLayout.topCenter)
					.timeout(3000).build();
			goPage = "redirect:/mail/trash";
			break;
		}
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return goPage;
	}
	
	@GetMapping("/mail/mailsToTrash/{trashMailNo}")
	public String mailsToTrash(@PathVariable(value = "trashMailNo", required = true) List<Integer> trashMailNo,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			RedirectAttributes redirectAttributes) {
		NotyMessageVO message = null;
		MailVO mailVO = new MailVO();
		mailVO.setTrashMailNo(trashMailNo);
		ServiceResult result = service.mailsToTrash(mailVO, authMember.getMemId());
		switch (result) {
		case FAILED:
			message = NotyMessageVO.builder("서버 오류").type(NotyType.error).layout(NotyLayout.topCenter).timeout(3000)
					.build();
			break;
		default: // OK , 삭제 성공시 게시글 목록으로 이동
			message = NotyMessageVO.builder("완전 삭제되었습니다").type(NotyType.success).layout(NotyLayout.topCenter)
					.timeout(3000).build();
			break;
		}
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/mail/trash";
	}

	@GetMapping("/mail/restoreMail/{restoreMailNo}")
	public String restoreMail(@PathVariable(value = "restoreMailNo", required = true) List<Integer> restoreMailNo,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			RedirectAttributes redirectAttributes) {
		NotyMessageVO message = null;
		MailVO mailVO = new MailVO();
		mailVO.setRestoreMailNo(restoreMailNo);
		ServiceResult result = service.restoreMail(mailVO, authMember.getMemId());
		switch (result) {
		case FAILED:
			message = NotyMessageVO.builder("서버 오류").type(NotyType.error).layout(NotyLayout.topCenter).timeout(3000)
					.build();
			break;
		default: // OK , 삭제 성공시 게시글 목록으로 이동
			message = NotyMessageVO.builder("복구 되었습니다").type(NotyType.success).layout(NotyLayout.topCenter)
					.timeout(3000).build();
			break;
		}
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/mail/trash";
	}

	@GetMapping("/mail/realDeleteMail/{deleteMailNo}")
	public String realDeleteMail(@PathVariable(value = "deleteMailNo", required = true) List<Integer> deleteMailNo,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			RedirectAttributes redirectAttributes) {
		NotyMessageVO message = null;
		MailVO mailVO = new MailVO();
		mailVO.setDeleteMailNo(deleteMailNo);
		ServiceResult result = service.removeMail(mailVO, authMember.getMemId());
		switch (result) {
		case FAILED:
			message = NotyMessageVO.builder("서버 오류").type(NotyType.error).layout(NotyLayout.topCenter).timeout(3000)
					.build();
			break;
		default: // OK , 삭제 성공시 게시글 목록으로 이동
			message = NotyMessageVO.builder("완전 삭제되었습니다").type(NotyType.success).layout(NotyLayout.topCenter)
					.timeout(3000).build();
			break;
		}
		if (message != null)
			redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/mail/trash";
	}
}
