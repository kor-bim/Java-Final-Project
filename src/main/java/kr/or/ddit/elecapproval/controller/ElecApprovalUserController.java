package kr.or.ddit.elecapproval.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.elecapproval.service.ElecApprovalUserService;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.LineDetailVO;
import kr.or.ddit.hr.member.service.MemberAdminService;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * 전자결재 사용자 설정 컨트롤러
 * 
 * @author 윤한빈
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class ElecApprovalUserController {

	@Inject
	private MemberService memService;

	@Inject
	MemberAdminService adminService;

	@Inject
	ElecApprovalUserService approvalUserService;

	@RequestMapping("approval/userSetting")
	public String userSetting(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model) {

		PagingVO<ApprovalLineVO> pagingVO = new PagingVO<>(5, 5);

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);
		pagingVO.setUserId(authMember.getMemId());

		int totalRecord = approvalUserService.retrieveAprvlLineListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<ApprovalLineVO> aprvlLineList = approvalUserService.retrieveAprvlLineList(pagingVO);
		pagingVO.setDataList(aprvlLineList);

		model.addAttribute("pagingVO", pagingVO);

		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);
		return "approval/userApprovalSetting";
	}

	@PostMapping("/approval/upateSignImg")
	@ResponseBody
	public String updateMemberImg(@ModelAttribute("member") MemberVO memberVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		memberVO.setMemId(authMember.getMemId());
		ServiceResult result = memService.modifyMemberSignImg(memberVO);
		if (result == ServiceResult.OK) {
			authMember.setMemSignImg(memberVO.getMemSignImg());
		}
		return authMember.getMemSignImg();
	}

	@PostMapping("/approval/insertAprvlLine")
	public String insertAprvlLine(@ModelAttribute("approvalLineVO") ApprovalLineVO approvalLineVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			RedirectAttributes redirectAttributes) {
		LineDetailVO lineDetailVO = new LineDetailVO();
		lineDetailVO.setMemId(authMember.getMemId());
		lineDetailVO.setAldtCode("DRAFT");
		approvalLineVO.getLineDetailList().add(0, lineDetailVO);

		ServiceResult result = approvalUserService.createApprovalLine(approvalLineVO);
		switch (result) {
		case FAILED:
			redirectAttributes.addFlashAttribute("message", NotyMessageVO.builder("등록 실패").build());
			break;
		case OK:
			redirectAttributes.addFlashAttribute("message", NotyMessageVO.builder("등록 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/approval/userSetting";
	}

	@PostMapping("/approval/removeAprvlLine")
	public String removeAprvlLine(@ModelAttribute("aprvlVO") ApprovalLineVO aprvlVO,
			RedirectAttributes redirectAttributes) {
		ServiceResult result = approvalUserService.removeAprvlLine(aprvlVO);
		switch (result) {
		case FAILED:
			redirectAttributes.addFlashAttribute("message", NotyMessageVO.builder("삭제 실패").build());
			break;
		case OK:
			redirectAttributes.addFlashAttribute("message", NotyMessageVO.builder("삭제 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/approval/userSetting";
	}
	
	
	
}
