package kr.or.ddit.hr.vacation.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.controller.BaseController;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.service.ApprovalDocWriteService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.service.VacaTypeService;
import kr.or.ddit.hr.vacation.service.VacationStatusService;
import kr.or.ddit.hr.vacation.service.VacationWriteService;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;
import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

/**
 * @author 서대철
 * @since 2021. 2. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 24.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class VacationWriteController extends BaseController {
	
	@Inject
	private VacationWriteService vacationWriteService;
	
	@Inject
	private VacationStatusService vacaStatService;
	
	@Inject
	private VacaTypeService vacaTypeService;
	
	@Inject
	public ApprovalDocWriteService approvalDocWriteService;
	@Inject
	public ApprovalDocCommonService approvalDocComnService;
	
	/**
	 * 휴가 신청 폼
	 * @author 서대철
	 * @since 2021. 2. 24.
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/vacation/vacationWrite")
	public String vacationWriteForm(@AuthenticationPrincipal(expression="realMember") MemberVO authMember, Model model) {
		
		String memId = authMember.getMemId();
		
		VacaStatusVO vacaStatusVO = new VacaStatusVO();
		vacaStatusVO.setMemId(memId);
		
		DocumentsVO documentsVO = new DocumentsVO();
		documentsVO.setDfNo(16); // 휴가 dfNo = 16
		
		documentsVO = approvalDocComnService.selectDocForm(documentsVO);
		VacaRecVO vacaRecVO = new VacaRecVO();
		ApprovalDocVO approvalDocVO = new ApprovalDocVO();
		approvalDocVO.setDocumentsVO(documentsVO);
		vacaRecVO.setApprovalDocVO(approvalDocVO);
		
		String searchYear = vacaStatusVO.getSearchYear();
		if(StringUtils.isBlank(searchYear)) {
			Calendar cal = Calendar.getInstance();
			searchYear = cal.get(Calendar.YEAR)+"";
			vacaStatusVO.setSearchYear(searchYear);
		}
		
		vacaStatusVO = vacaStatService.selectVacaStat(vacaStatusVO);
		model.addAttribute("vacaStatusVO", vacaStatusVO);
		
		List<VacaTypeVO> vacaTypeList = vacaTypeService.selectVacaTypeList();
		model.addAttribute("vacaTypeList", vacaTypeList);
		
		List<VacaRecVO> vacaRecList = vacationWriteService.selectVacaRecList(vacaStatusVO);
		model.addAttribute("vacaRecList", vacaRecList);
		
		model.addAttribute("vacaRecVO", vacaRecVO);
		return "vacation/vacationForm";
	}
	
	/**
	 * 휴가 신청
	 * @author 서대철
	 * @since 2021. 2. 26.
	 * @param vacaRecVO
	 * @param errors
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/vacation/vacationInsert")
	public String vacationWrite(VacaRecVO vacaRecVO
			, Errors errors
			, RedirectAttributes redirectAttributes
			, HttpServletRequest req) {
		String goPage = null;
		NotyMessageVO message = null;
		String[] approvalMember = req.getParameterValues("approvalDocVO.approvalMember");
		String[] referenceMember = req.getParameterValues("approvalDocVO.referenceMember");
		  
		vacaRecVO.getApprovalDocVO().setLineDetailList(approvalMember, referenceMember);
	
		boolean valid = !errors.hasErrors();
		if(valid) {
			
			ServiceResult result = approvalDocWriteService.createApprovalDocument(vacaRecVO.getApprovalDocVO());
			result = vacationWriteService.insertVacation(vacaRecVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("휴가가 신청되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/vacation/vacationStatus";
				break;
				
			default:
				message = NotyMessageVO.builder("휴가 신청 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/vacation/vacationWrite";
				break;
			}
		}
		return goPage;
	}
}
