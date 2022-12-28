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
import kr.or.ddit.elecapproval.service.ApprovalAllDocumentBoxService;
import kr.or.ddit.elecapproval.service.ApprovalDetailService;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;

/**
 * @author 서대철
 * @since 2021. 2. 22.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 22.   서대철            최초작성
 * 2021. 3.  1.   이운주            (수정) 페이지마다 의견 따로 가져오는 부분 수정
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ApprovalAllDocumentBoxController {
	
	@Inject
	private ApprovalAllDocumentBoxService approvalAllDocumentBoxService;
	
	@Inject
	public ApprovalDocCommonService approvalDocCommonService; 
	@Inject
	public ApprovalDetailService approvalDetailService; // 20210301 운주 : 의견 목록 조회
	
	/**
	 * 전체 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 22.
	 * @param documentsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalAllDocumentBoxList")
	@ResponseBody
	public List<ApprovalDocVO> approvalAllDocumentBoxList(ApprovalDocVO approvalDocVO, Model model){
		List<ApprovalDocVO> approvalAllDocumentBoxList = 
				approvalAllDocumentBoxService.selectApprovalAllDocumentBoxList(approvalDocVO);
		model.addAttribute("approvalAllDocumentBoxList", approvalAllDocumentBoxList);
		
		return approvalAllDocumentBoxList;
	}
	
	/**
	 * 체크된 문서 삭제
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param approvalDocVO
	 * @param deleteAdNo
	 * @return
	 */
	@RequestMapping(value="/admin/approvalDocumentDelete", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approvalDocumentDelete(ApprovalDocVO approvalDocVO
			, Model model
			) {
		NotyMessageVO message = null;
		
		ServiceResult result = approvalAllDocumentBoxService.deleteDocumentList(approvalDocVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 삭제되었습니다.").type(NotyType.success).build();
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
	 * 전체 문서 목록 상세조회
	 * @author 서대철
	 * @since 2021. 2. 23.
	 * @param adNo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/admin/approvalDocumentDetail/{adNo}")
	public String approvalDocumentDetail(@PathVariable("adNo") String adNo, Model model) throws IOException {
		
		ApprovalDocVO approvalDocVO = approvalAllDocumentBoxService.selectDocument(adNo); 
		model.addAttribute("approvalDocVO", approvalDocVO);
		
		List<ApprovalVO> commentList =approvalDetailService.retrieveApprovalComment(approvalDocVO);
		model.addAttribute("commentList", commentList);
		
		// 결재라인 작성에 필요한 detailJSON 을 모델에 담아줌  
		approvalDocCommonService.getApprovalLineByAdNo(adNo, model);
		
//		//=========================================================
//		ApprovalLineVO approvalLineVO = new ApprovalLineVO();
//		approvalLineVO = approvalDocCommonService.getApprovalLine(approvalDocVO);
//		ObjectMapper mapper = new ObjectMapper();
//		String detailJSON = mapper.writeValueAsString(approvalLineVO);
//		model.addAttribute("detailJSON", detailJSON);
//		//==========================================================
		
		return "approval/allDocumentBoxView";
	}
	
	/**
	 * 문서 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 9.
	 * @param approvalDocVO
	 * @param adNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/admin/approvalDocumentDetailDelete/{adNum}")
	public String approvalDocumentDelete(ApprovalDocVO approvalDocVO
			, @PathVariable("adNum") String adNo
			, RedirectAttributes redirectAttributes) {
	
		approvalDocVO.setAdNo(adNo);
		approvalDocVO = approvalAllDocumentBoxService.selectDocument(approvalDocVO.getAdNo());
		
		String goPage = null;
		NotyMessageVO message = null;
		
		ServiceResult result = approvalAllDocumentBoxService.deleteDocument(approvalDocVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("문서가 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/allDocumentBox";
			break;
		default :
			message = NotyMessageVO.builder("문서 삭제 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/approvalDocumentDetail" + adNo;
			break;
		}
		
		return goPage;
	}
	
	/**
	 * 결재 상태가 반려인 문서만 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 9.
	 * @param approvalDocVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/approvalSelectDocument", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> selectDocumentList(ApprovalDocVO approvalDocVO
			, Model model) {
		
		NotyMessageVO message = null;
		
		List<ApprovalDocVO> resultList = approvalAllDocumentBoxService.selectDocumentCode(approvalDocVO);
		
		message = NotyMessageVO.builder("반려된 문서만 삭제할 수 있습니다.").type(NotyType.error).build();
		model.addAttribute("message", message);
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("resultList", resultList);
		resultMap.put("message", message);
		return resultMap;
	}
}
