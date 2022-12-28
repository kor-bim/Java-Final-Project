package kr.or.ddit.elecapproval.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.elecapproval.service.ApprovalDeleteDocumentBoxService;
import kr.or.ddit.elecapproval.service.ApprovalDetailService;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

/**
 * @author 서대철
 * @since 2021. 2. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 23.   서대철           최초작성
 * 2021. 3.  1.   이운주           (수정) 페이지마다 의견 따로 가져오는 부분 수정.
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ApprovalDeleteDocumentBoxController {
	
	@Inject
	private ApprovalDeleteDocumentBoxService approvalDeleteDocumentBoxService;
	
	@Inject
	public ApprovalDocCommonService approvalDocCommonService; 
	@Inject
	public ApprovalDetailService approvalDetailService;
	
	
	/**
	 * 삭제 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalDeleteDocumentBoxList")
	@ResponseBody
	public List<ApprovalDocVO> approvalDeleteDocumentBoxList(ApprovalDocVO approvalDocVO, Model model){
		List<ApprovalDocVO> approvalDeleteDocumentBoxList =
				approvalDeleteDocumentBoxService.selectApprovalDeleteDocumentBoxList(approvalDocVO);
		model.addAttribute("approvalDeleteDocumentBoxList", approvalDeleteDocumentBoxList);
		return approvalDeleteDocumentBoxList;
	}
	
	/**
	 * 문서 백업
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/approvalDocumentBackUp", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approvalDocumentBackup(ApprovalDocVO approvalDocVO, Model model){
		
		NotyMessageVO message = null;
		
		ServiceResult result = approvalDeleteDocumentBoxService.backUpDocumentList(approvalDocVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 복원되었습니다.").type(NotyType.success).build();
			model.addAttribute("message", message);
			break;
		default :
			message = NotyMessageVO.builder("문서 복원 실패했습니다.").type(NotyType.error).build();
			model.addAttribute("message", message);
			break;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("message", message);
		return resultMap;
	}
	
	/**
	 * 문서 완전삭제 처리
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/approvalDocumentRealDelete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approvalRealDeleteDocumentList(ApprovalDocVO approvalDocVO, Model model){
		
		NotyMessageVO message = null;
		
		ServiceResult result = approvalDeleteDocumentBoxService.realDeleteDocumentList(approvalDocVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 완전 삭제되었습니다.").type(NotyType.success).build();
			model.addAttribute("message", message);
			break;
		default :
			message = NotyMessageVO.builder("문서 삭제 실패했습니다.").type(NotyType.error).build();
			model.addAttribute("message", message);
			break;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("message", message);
		return resultMap;
	}
	
	/**
	 * 삭제 문서 상세 조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param adNo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/admin/approvalDeleteDocumentDetail/{adNo}")
	public String approvalDeleteDocumentDetail(@PathVariable("adNo") String adNo, Model model) throws IOException {
		
		ApprovalDocVO approvalDocVO = approvalDeleteDocumentBoxService.selectDocument(adNo); 
		model.addAttribute("approvalDocVO", approvalDocVO);
		
		List<ApprovalVO> commentList = approvalDetailService.retrieveApprovalComment(approvalDocVO);	
		model.addAttribute("commentList", commentList);
		
		// 결재라인 작성에 필요한 detailJSON 을 모델에 담아줌  
		approvalDocCommonService.getApprovalLineByAdNo(adNo, model);
		
		return "approval/deleteDocumentBoxView";
	}
	
	/**
	 * 상세조회 페이지에서 복원 처리
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @param adNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/admin/approvalDocumentBackUpDetail/{adNum}")
	public String approvalDocumentBackUpDetail(ApprovalDocVO approvalDocVO
			, @PathVariable("adNum") String adNo
			, RedirectAttributes redirectAttributes) {
		
		approvalDocVO.setAdNo(adNo);
		approvalDocVO = approvalDeleteDocumentBoxService.selectDocument(approvalDocVO.getAdNo()); 
		
		String goPage = null;
		
		NotyMessageVO message = null;
		
		ServiceResult result = approvalDeleteDocumentBoxService.backUpDocument(approvalDocVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 복원되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/deleteDocumentBox";
			break;
		default :
			message = NotyMessageVO.builder("문서 복원 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/approvalDeleteDocumentDetail/" + adNo;
			break;
		}
		
		return goPage;
	}
	
	
	@RequestMapping("/admin/approvalDocumentRealDelete/{adNo}")
	public String approvalDocumentRealDelete(ApprovalDocVO approvalDocVO
			, @PathVariable("adNo") String adNo
			, RedirectAttributes redirectAttributes) {
		String goPage = null;
		NotyMessageVO message = null;
		
		approvalDocVO.setAdNo(adNo);
		approvalDocVO = approvalDeleteDocumentBoxService.selectDocument(adNo);
		
		ServiceResult result = approvalDeleteDocumentBoxService.realDeleteDocument(approvalDocVO);

		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 완전 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/deleteDocumentBox";
			break;
		default :
			message = NotyMessageVO.builder("문서 삭제 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/approvalDeleteDocumentDetail/" + adNo;
			break;
		}
		return goPage;
	}
	
}
