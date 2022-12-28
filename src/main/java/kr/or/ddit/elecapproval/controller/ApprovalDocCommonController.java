package kr.or.ddit.elecapproval.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

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
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

@Controller
public class ApprovalDocCommonController extends BaseController {
	@Inject
	public ApprovalDocCommonService docComnService;
	
	/**
	 * 문서 양식 분류 코드, 이름 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	@RequestMapping("/approval/docTypeList")
	@ResponseBody
	public List<DocumentsVO> selectDocTypeList(DocumentsVO documentsVO) {
		return docComnService.selectDocTypeList(documentsVO);
	}
	
	/**
	 * 문서 양식 분류 코드에 해당하는 문서 양식 리스트 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	@RequestMapping("/approval/docFormList")
	@ResponseBody
	public List<DocumentsVO> selectDocFormList(DocumentsVO documentsVO){
		return docComnService.selectDocFormList(documentsVO);
	}
	
	/** 
	 * 자주 쓰는 결재선 리스트 조회 
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @param approvalLineVO
	 * @return
	 */
	@RequestMapping("/approval/get/savedLineList")
	@ResponseBody
	public List<ApprovalLineVO> getSavedApprovalLineList(ApprovalLineVO approvalLineVO) {
		List<ApprovalLineVO> approvalLineList = docComnService.selectApprovalLineList(approvalLineVO);
		return approvalLineList;
	}
	
	/**
	 * 자주 쓰는 결재선 한건 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 22.
	 * @return
	 */
	@RequestMapping("/approval/get/savedLine")
	@ResponseBody
	public ApprovalLineVO getSavedApprovalLine(ApprovalLineVO approvalLineVO) {
		approvalLineVO = docComnService.selectApprovalLine(approvalLineVO);
		return approvalLineVO;
		
	}
	
	/**
	 * 결재문서의 결재라인 조회 
	 * @author 이운주
	 * @since 2021. 2. 24.
	 * @param approvalLineVO
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/approval/get/line/{adNo}")
	@ResponseBody
	public ApprovalLineVO getApprovalline(@PathVariable(value="adNo", required=true) String adNo, Model model) throws IOException {
		ApprovalDocVO approvalDocVO = new ApprovalDocVO();
		approvalDocVO.setAdNo(adNo);
		//=========================================================
		ApprovalLineVO approvalLineVO = new ApprovalLineVO();
		approvalLineVO = docComnService.getApprovalLine(approvalDocVO);
		return approvalLineVO;
	}
	
	/**
	 * 결재 프로세스 
	 * @param approvalVO
	 * @param redirectAttributes
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 27.
	 */
	@RequestMapping(value="/approval/mainProcess", method=RequestMethod.POST)
	public String approvalProcess(ApprovalVO approvalVO
								, RedirectAttributes redirectAttributes) {
		String goPage = "redirect:/approval/" + approvalVO.getAdNo();
		ServiceResult result = docComnService.approvalProcess(approvalVO);
		NotyMessageVO message = null;
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("처리되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		case FAILED:
			message = NotyMessageVO.builder("다시 시도해주세요").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			break;
		}
		
		return goPage; 
	}
	

	

}
