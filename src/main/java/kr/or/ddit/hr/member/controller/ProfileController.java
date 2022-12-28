package kr.or.ddit.hr.member.controller;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.MemberVO;

@Controller
public class ProfileController {

	@Inject
	private MemberService service;

	@Inject
	private AuthenticationManager authenticationManager;

	@RequestMapping("/profile/profileSetting")
	public String profileSetting(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model) {
		MemberVO memberVO = service.retrieveMember(authMember);
		model.addAttribute("member", memberVO);
		return "/profile/profileSetting";
	}

	@PostMapping("/profile/updateMemberPass")
	public String updateMemberPass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass) {
		service.changePassword(oldPass, newPass);
		return "redirect:/profile/profileSetting";
	}

	@PostMapping("/profile/upateImg")
	@ResponseBody
	public String updateMemberImg(@ModelAttribute("member") MemberVO memberVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		memberVO.setMemId(authMember.getMemId());
		ServiceResult result = service.modifyMemberImg(memberVO);
		if (result == ServiceResult.OK) {
			authMember.setMemImg(memberVO.getMemImg());
		}
		return authMember.getMemImg();
	}

	@PostMapping("/profile/checkMemberPass")
	@ResponseBody
	public ServiceResult checkMemberPass(@ModelAttribute("member") MemberVO memberVO, Authentication authen) {
		ServiceResult result = null;
		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(memberVO.getMemId(),
					memberVO.getMemPass());
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			result = ServiceResult.OK;
		} catch (Exception e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}
}
