package kr.or.ddit.hr.vacation.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.controller.BaseController;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.vacation.service.VacaTypeService;
import kr.or.ddit.hr.vacation.service.VacationStatusService;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;
import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

/**
 * 직원 휴가(포상휴가)의  생성/사용 현황 관리
 *  
 * @author 이운주
 * @since 2021. 2. 11.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 11.       이운주              최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class VacationStatusController extends BaseController {
	@Inject
	private VacaTypeService vacaTypeService;
	@Inject
	private VacationStatusService vacaStatService;
	@Inject
	public ApprovalDocCommonService docComnService;
	
	/**
	 * 직원 휴가 현황 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 17.
	 * @param vacaStatusVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/vacaStatus")
	public String selectVacaStatList(VacaStatusVO vacaStatusVO, Model model){
		String searchYear = vacaStatusVO.getSearchYear();
		if(StringUtils.isBlank(searchYear)) {
			Calendar cal = Calendar.getInstance();
			searchYear = cal.get(Calendar.YEAR)+"";
			vacaStatusVO.setSearchYear(searchYear);
		}
		
		List<VacaStatusVO> vacaStatList = vacaStatService.vacaStatusList(vacaStatusVO);
		
		List<VacaTypeVO> vacaTypeList = vacaTypeService.selectVacaTypeList();
		List<DeptVO> deptList= orgMapService.retrieveDeptList();
		
		model.addAttribute("vacaMemberList", vacaStatList);   
		model.addAttribute("vacaTypeList", vacaTypeList);     // 휴가 타입 리스트
		model.addAttribute("deptList", deptList);             // 부서 리스트 (셀렉트)
		return "vacation/vacaStatus";
	}
	
	@RequestMapping("/admin/vacaStatForm.do/{memId}/{searchYear}")
	public String selectVacaStat(VacaStatusVO vacaStatusVO, Model model) {
		
		vacaStatusVO = vacaStatService.selectVacaStat(vacaStatusVO);
		model.addAttribute("vacaStat", vacaStatusVO);
		return "vacation/others/vacaStatusUpdateForm";
	}
	
	@RequestMapping(value="/admin/vacaStat/update.do", method=RequestMethod.POST)
	public String updateVacaStat(VacaStatusVO vacaStatusVO, Model model, RedirectAttributes redirectAttributes) {
		ServiceResult result = vacaStatService.updateVacaStat(vacaStatusVO);
		NotyMessageVO message = null;
		
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("저장되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("다시 시도해주세요").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		
		return "redirect:/admin/vacaStatus";
	}
	
	/**
	 * 내 휴가 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 26.
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/vacation/vacationStatus")
	public String myVacationStatusList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, Model model) {
		String memId = authMember.getMemId();
		
		VacaStatusVO vacaStatusVO = new VacaStatusVO();
		vacaStatusVO.setMemId(memId);
		
		String searchYear = vacaStatusVO.getSearchYear();
		if(StringUtils.isBlank(searchYear)) {
			Calendar cal = Calendar.getInstance();
			searchYear = cal.get(Calendar.YEAR)+"";
			vacaStatusVO.setSearchYear(searchYear);
		}
		
		vacaStatusVO = vacaStatService.selectVacaStat(vacaStatusVO);
		model.addAttribute("vacaStatusVO", vacaStatusVO);
		
		VacaRecVO vacaRecVO = new VacaRecVO();
		vacaRecVO.setMemId(memId);
		
		List<VacaRecVO> vacaList = vacaStatService.selectVacationList(vacaRecVO);
		model.addAttribute("vacaList", vacaList);
		
		return "vacation/myVacationStatus";
	}
	
	/**
	 * 휴가 생성 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 1.
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/vacation/vacationStatusList")
	@ResponseBody
	public List<VacaStatusVO> vacationStatusList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, Model model) {
		String memId = authMember.getMemId();
		
		VacaStatusVO vacaStatusVO = new VacaStatusVO();
		vacaStatusVO.setMemId(memId);
		
		List<VacaStatusVO> vacationStatusList = vacaStatService.selectStatusList(vacaStatusVO);
		
		return vacationStatusList;
	}
	
	/**
	 * 휴가 신청 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 1.
	 * @param authMember
	 * @return
	 */
	@RequestMapping("/vacation/vacationList")
	@ResponseBody
	public List<VacaRecVO> myVacationList(@AuthenticationPrincipal(expression="realMember") MemberVO authMember) {
		VacaRecVO vacaRecVO = new VacaRecVO();
		vacaRecVO.setMemId(authMember.getMemId());
		
		List<VacaRecVO> vacationList = vacaStatService.selectVacationList(vacaRecVO);
		
		return vacationList;
	}
	
	/**
	 * 관리자가 휴가 신청 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 1.
	 * @param vacaRecVO
	 * @return
	 */
	@RequestMapping("/admin/vacationAllList")
	@ResponseBody
	public List<VacaRecVO> vacationAllList(VacaRecVO vacaRecVO){
		List<VacaRecVO> vacaAllList = vacaStatService.selectVacaAllList(vacaRecVO);
		
		return vacaAllList;
	}
	
	/**
	 * 관리자가 휴가 신청 취소 처리
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param adNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/vacation/vacationReturn/{adNo}/{memId}")
	public String vacationCancel(
			@PathVariable("memId") String memId
			,@PathVariable("adNo") String adNo
			, RedirectAttributes redirectAttributes) {
		ApprovalVO approvalVO = new ApprovalVO();
		approvalVO.setAdNo(adNo);
		approvalVO.setMemId(memId);
		approvalVO.setAprvlTypeCode("RETURN");
		approvalVO.setAdComment("취소 요청으로 인한 관리자가 반려처리");
		approvalVO.setAprvlStateCode("COMPLETE");
		
		ServiceResult result = docComnService.approvalProcess(approvalVO);
		
		NotyMessageVO message = null;
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("휴가 신청이 취소되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case FAILED:
			message = NotyMessageVO.builder("다시 시도해주세요").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			break;
		}
		
		return "redirect:/vacation/vacationStatus";
	}
	
	
	
	
}
