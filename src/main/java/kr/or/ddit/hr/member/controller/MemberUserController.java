package kr.or.ddit.hr.member.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemFormVO;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.service.OrgMapService;
import kr.or.ddit.hr.organization.vo.DeptVO;

@Controller
public class MemberUserController {

	@Inject
	private MemberService memService;

	@Inject
	private OrgMapService orgService;

	@RequestMapping("/org/memberInfo")
	public String memberInfo(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model) {
		List<JobVO> jobVOList = memService.retrieveJobList();
		MemFormVO memFormVO = new MemFormVO();
		DeptVO deptLine = orgService.retrieveDeptLine(authMember.getDeptCode());
		memFormVO.setJobVOList(jobVOList);
		memFormVO.setMemberVO(authMember);
		memFormVO.setDeptLine(deptLine);

		model.addAttribute("memFormVO", memFormVO);

		return "org/memberInfo";
	}

	@PostMapping("/org/updateMember")
	public String updateMember(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			MemFormVO memFormVO, BindingResult errors, Model model) {
		if (!errors.hasErrors()) {
			ServiceResult result = memService.modifyMember(memFormVO.getMemberVO());
			switch (result) {
			case OK:
				authMember.setJobCode(memFormVO.getMemberVO().getJobCode());
				authMember.setMemComtel(memFormVO.getMemberVO().getMemComtel());
				authMember.setMemHp(memFormVO.getMemberVO().getMemHp());
				authMember.setMemMail(memFormVO.getMemberVO().getMemMail());
				authMember.setMemBirth(memFormVO.getMemberVO().getMemBirth());
				authMember.setMemZip(memFormVO.getMemberVO().getMemZip());
				authMember.setMemAdd(memFormVO.getMemberVO().getMemAdd());
				authMember.setMemEtc(memFormVO.getMemberVO().getMemEtc());
				model.addAttribute("message", NotyMessageVO.builder("수정 성공했습니다.").build());
				break;

			default:
				model.addAttribute("message", NotyMessageVO.builder("수정 실패했습니다.").build());
				break;
			}
		}

		return "redirect:/org/memberInfo";
	}

}