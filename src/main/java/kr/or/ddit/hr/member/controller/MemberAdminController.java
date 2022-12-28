package kr.or.ddit.hr.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.hr.member.service.MemberAdminService;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemFormVO;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.service.OrgMapService;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

/**
 * 
 * @author 이운주
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.       이운주              최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class MemberAdminController {

	@Inject
	MemberService memService;

	@Inject
	MemberAdminService adminService;

	@Inject
	OrgMapService orgService;

	@RequestMapping("/admin/setManager")
	public String setManger(Model model) {
		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		List<MemberVO> superAdminList = adminService.retrievesuperAdminList();
		List<MemberVO> hrAdminList = adminService.retrieveHrAdminList();
		List<MemberVO> eaAdminList = adminService.retrieveEaAdminList();
		List<MemberVO> cmAdminList = adminService.retrieveCmAdminList();
		if (memberList == null) {
			model.addAttribute("message", NotyMessageVO.builder("조회된 회원정보가 없습니다").build());
		} else {
			model.addAttribute("memberList", memberList);
			model.addAttribute("superAdminList", superAdminList);
			model.addAttribute("hrAdminList", hrAdminList);
			model.addAttribute("eaAdminList", eaAdminList);
			model.addAttribute("cmAdminList", cmAdminList);
		}
		return "admin/setManager";
	}

	@RequestMapping("/admin/setHrManager")
	public String setHrManager(Model model) {
		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		List<MemberVO> superAdminList = adminService.retrievesuperAdminList();
		List<MemberVO> hrAdminList = adminService.retrieveHrAdminList();
		List<MemberVO> eaAdminList = adminService.retrieveEaAdminList();
		List<MemberVO> cmAdminList = adminService.retrieveCmAdminList();
		if (memberList == null) {
			model.addAttribute("message", NotyMessageVO.builder("조회된 회원정보가 없습니다").build());
		} else {
			model.addAttribute("memberList", memberList);
			model.addAttribute("superAdminList", superAdminList);
			model.addAttribute("hrAdminList", hrAdminList);
			model.addAttribute("eaAdminList", eaAdminList);
			model.addAttribute("cmAdminList", cmAdminList);
		}
		return "hrAdmin/setHrManager";
	}

	@RequestMapping("/admin/setEaManager")
	public String setEaManger(Model model) {
		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		List<MemberVO> superAdminList = adminService.retrievesuperAdminList();
		List<MemberVO> hrAdminList = adminService.retrieveHrAdminList();
		List<MemberVO> eaAdminList = adminService.retrieveEaAdminList();
		List<MemberVO> cmAdminList = adminService.retrieveCmAdminList();
		if (memberList == null) {
			model.addAttribute("message", NotyMessageVO.builder("조회된 회원정보가 없습니다").build());
		} else {
			model.addAttribute("memberList", memberList);
			model.addAttribute("superAdminList", superAdminList);
			model.addAttribute("hrAdminList", hrAdminList);
			model.addAttribute("eaAdminList", eaAdminList);
			model.addAttribute("cmAdminList", cmAdminList);
		}
		return "approval/setEaManager";
	}

	@RequestMapping("/admin/setCmManager")
	public String setCmManger(Model model) {
		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		List<MemberVO> superAdminList = adminService.retrievesuperAdminList();
		List<MemberVO> hrAdminList = adminService.retrieveHrAdminList();
		List<MemberVO> eaAdminList = adminService.retrieveEaAdminList();
		List<MemberVO> cmAdminList = adminService.retrieveCmAdminList();
		if (memberList == null) {
			model.addAttribute("message", NotyMessageVO.builder("조회된 회원정보가 없습니다").build());
		} else {
			model.addAttribute("memberList", memberList);
			model.addAttribute("superAdminList", superAdminList);
			model.addAttribute("hrAdminList", hrAdminList);
			model.addAttribute("eaAdminList", eaAdminList);
			model.addAttribute("cmAdminList", cmAdminList);
		}
		return "departMentBoard/setCmManager";
	}

	@RequestMapping("/admin/updateCmAdmin")
	public String updateCmAdmin(@RequestParam("notAdminMemId") List<String> notAdminMemId,
			@RequestParam("adminMemId") List<String> adminMemId, Model model) {
		ServiceResult result = adminService.modifyCmAdmin(notAdminMemId, adminMemId);
		switch (result) {
		case FAILED:
			model.addAttribute("message", NotyMessageVO.builder("등록 실패").build());
			break;
		case OK:
			model.addAttribute("message", NotyMessageVO.builder("등록 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/admin/setCmManager";
	}

	@RequestMapping("/admin/updateEaAdmin")
	public String updateEaAdmin(@RequestParam("notAdminMemId") List<String> notAdminMemId,
			@RequestParam("adminMemId") List<String> adminMemId, Model model) {
		ServiceResult result = adminService.modifyEaAdmin(notAdminMemId, adminMemId);
		switch (result) {
		case FAILED:
			model.addAttribute("message", NotyMessageVO.builder("등록 실패").build());
			break;
		case OK:
			model.addAttribute("message", NotyMessageVO.builder("등록 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/admin/setEaManager";
	}

	@RequestMapping("/admin/updateHrAdmin")
	public String updateHrAdmin(@RequestParam("notAdminMemId") List<String> notAdminMemId,
			@RequestParam("adminMemId") List<String> adminMemId, Model model) {
		ServiceResult result = adminService.modifyHrAdmin(notAdminMemId, adminMemId);
		switch (result) {
		case FAILED:
			model.addAttribute("message", NotyMessageVO.builder("등록 실패").build());
			break;
		case OK:
			model.addAttribute("message", NotyMessageVO.builder("등록 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/admin/setHrManager";
	}

	@RequestMapping("/admin/updateSuperAdmin")
	public String updateSuperAdmin(@RequestParam("notAdminMemId") List<String> notAdminMemId,
			@RequestParam("adminMemId") List<String> adminMemId, Model model) {
		ServiceResult result = adminService.modifySuperAdmin(notAdminMemId, adminMemId);
		switch (result) {
		case FAILED:
			model.addAttribute("message", NotyMessageVO.builder("등록 실패").build());
			break;
		case OK:
			model.addAttribute("message", NotyMessageVO.builder("등록 성공").build());
			break;
		default:
			break;
		}
		return "redirect:/admin/setManager";
	}

	@RequestMapping("/admin/userManagement")
	public String memberList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model) {

		PagingVO<MemberVO> pagingVO = new PagingVO<>(10, 5);

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = memService.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MemberVO> memberList = memService.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
		return "hrAdmin/userManagement";
	}

	@RequestMapping("/admin/memberForm")
	public String goMemberForm(MemFormVO memFormVO) {
		List<JobVO> jobVOList = memService.retrieveJobList();
		List<PositionVO> psVOList = orgService.retrievePsList();
		List<DeptVO> deptList = orgService.retrieveDeptList();
		memFormVO.setJobVOList(jobVOList);
		memFormVO.setPsVOList(psVOList);
		memFormVO.setDeptList(deptList);
		return "hrAdmin/memberForm";
	}

	@PostMapping("/admin/memberForm")
	public String insertMember(MemFormVO memFormVO, Model model) {
		String goPage = null;
		List<JobVO> jobVOList = memService.retrieveJobList();
		List<PositionVO> psVOList = orgService.retrievePsList();
		List<DeptVO> deptList = orgService.retrieveDeptList();
		memFormVO.setJobVOList(jobVOList);
		memFormVO.setPsVOList(psVOList);
		memFormVO.setDeptList(deptList);

		ServiceResult result = memService.registMember(memFormVO.getMemberVO());

		switch (result) {
		case PKDUPLICATED:
			goPage = "hrAdmin/memberForm";
			model.addAttribute("message", NotyMessageVO.builder("아이디 중복").build());
			break;
		case FAILED:
			goPage = "hrAdmin/memberForm";
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			break;
		case OK:
			goPage = "redirect:/admin/userManagement";
			break;
		default:
			break;
		}
		return goPage;
	}

	@GetMapping("/admin/modifyMember/{memId}")
	public String goUpdateMember(@PathVariable("memId") String memId, Model model) {
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member = memService.retrieveMember(member);
		MemFormVO memFormVO = new MemFormVO();
		memFormVO.setMemberVO(member);
		List<JobVO> jobVOList = memService.retrieveJobList();
		List<PositionVO> psVOList = orgService.retrievePsList();
		List<DeptVO> deptList = orgService.retrieveDeptList();
		memFormVO.setJobVOList(jobVOList);
		memFormVO.setPsVOList(psVOList);
		memFormVO.setDeptList(deptList);
		model.addAttribute("modify","1");
		model.addAttribute("memFormVO", memFormVO);
		return "hrAdmin/memberForm";
	}

	@PostMapping("/admin/modifyMember/{memId}")
	public String updateMember(@PathVariable("memId") String memId, MemFormVO memFormVO, Model model) {
		String goPage = null;
		ServiceResult result = memService.modifyMember(memFormVO.getMemberVO());
		switch (result) {
		case OK:
			goPage = "redirect:/admin/userManagement";
			break;
		case INVALIDPASSWORD:
			model.addAttribute("message", NotyMessageVO.builder("비밀번호 오류").build());
			goPage = "hrAdmin/memberForm";
			break;
		default:
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			goPage = "hrAdmin/memberForm";
			break;
		}
		return goPage;
	}

	@PostMapping("/admin/removeMember")
	public String removeMember(@ModelAttribute("memberVO") MemberVO memberVO, Model model) {
		ServiceResult result = memService.modifyMember(memberVO);
		switch (result) {
		case OK:
			model.addAttribute("message", NotyMessageVO.builder("탈퇴처리 성공").build());
			break;
		case INVALIDPASSWORD:
			model.addAttribute("message", NotyMessageVO.builder("비밀번호 오류").build());
			break;
		default:
			model.addAttribute("message", NotyMessageVO.builder("서버 오류").build());
			break;
		}
		return "redirect:/admin/userManagement";
	}
}
