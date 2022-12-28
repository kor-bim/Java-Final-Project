package kr.or.ddit.elecapproval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.service.ApprovalFarmboxListService;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * @author 서대철
 * @since 2021. 2. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 18.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class EleApprovalFarmController {

	@Inject
	private ApprovalFarmboxListService approvalFarmboxListService;

	@Inject
	private ApprovalDocCommonService approvalDocCommonService;

	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}

	/**
	 * 결재 양식 포맷 설정 화면
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @return
	 */
	@RequestMapping("/admin/approvalForm")
	public String approvalFarm() {
		return "approval/others/approvalFarm";
	}
	
	@RequestMapping("/admin/approvalDocTypeList")
	@ResponseBody
	public List<DocumentsVO> approvalDocTypeList(DocumentsVO documentsVO, Model model) {
		List<DocumentsVO> approvalDocTypeList = approvalFarmboxListService.selectApprovalDocTypeList(documentsVO);
		model.addAttribute("approvalDocTypeList", approvalDocTypeList);

		return approvalDocTypeList;
	}

	/**
	 * 양식함 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmList")
	@ResponseBody
	public List<DocumentsVO> approvalFarmList(DocumentsVO documentsVO) {
		List<DocumentsVO> approvalFarmList = approvalFarmboxListService.selectApproalFarmboxList(documentsVO);

		return approvalFarmList;
	}
	
	/**
	 * 양식 등록 화면
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmForm")
	public String approvalFarmForm(DocumentsVO documentsVO, Model model) {

		List<DocumentsVO> docTypeList = approvalDocCommonService.selectDocTypeList(documentsVO);

		documentsVO.setDocTypeList(docTypeList);
		model.addAttribute("documentsVO", documentsVO);
		return "approval/approvalForm";
	}
	
	/**
	 * 양식 등록
	 * @author 서대철
	 * @since 2021. 2. 19.
	 * @param documentsVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmInsert")
	public String approvalFarmInsert(@Validated(InsertGroup.class) DocumentsVO documentsVO, Errors errors,
			RedirectAttributes redirectAttributes, Model model) {

		String goPage = null;
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = approvalFarmboxListService.insertFarmbox(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("양식이 등록되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/admin/approvalFarmboxList";
				break;
			default:
				message = NotyMessageVO.builder("양식 등록이 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "forward:/approval/approvalForm";
				break;
			}
		} else {
			message = NotyMessageVO.builder("양식 등록이 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/approvalFarmboxList";
		}
		return goPage;
	}

	/**
	 * 양식 상세조회
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param dfNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFarm/{dfNo}")
	public String approvalFarmSelect(@PathVariable(value = "dfNo", required = true) int dfNo, Model model) {

		DocumentsVO documentsVO = approvalFarmboxListService.selectApproalFarm(dfNo);
		model.addAttribute("documentsVO", documentsVO);

		return "approval/approvalFarmView";
	}
	
	/**
	 * 양식 수정 폼
	 * @param documentsVO
	 * @param dfNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFormUpdate/{dfNo}")
	public String approvalFormUpdate(DocumentsVO documentsVO
			, @PathVariable("dfNo") int dfNo
			, Model model) {
		addCommandAttribute(model);
		documentsVO.setDfNo(dfNo);
		
		documentsVO = approvalFarmboxListService.selectApproalFarm(documentsVO.getDfNo());
		model.addAttribute("documentsVO", documentsVO);
		
		List<DocumentsVO> docTypeList = approvalDocCommonService.selectDocTypeList(documentsVO);
		documentsVO.setDocTypeList(docTypeList);
		
		return "approval/approvalForm";
	}
	
	/**
	 * 양식 수정
	 * @param documentsVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmUpdate")
	public String approvalFarmUpdate(DocumentsVO documentsVO
			, Errors errors
			,RedirectAttributes redirectAttributes
			,Model model) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;

		int dfNo = documentsVO.getDfNo();
		
		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = approvalFarmboxListService.UpdateFarmbox(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("양식이 수정되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/admin/approvalFarm/" + dfNo;
				break;
			default:
				message = NotyMessageVO.builder("양식 수정을 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/approval/approvalForm";
				break;
			}
		} else {
			message = NotyMessageVO.builder("양식 수정을 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/admin/approvalFarmboxList";
		}
		return goPage;
	}
	
	/**
	 * 양식 삭제
	 * @author 서대철
	 * @since 2021. 2. 22.
	 * @param documentsVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmDelete/{dfNo}")
	public String approvalFarmDelete(DocumentsVO documentsVO
			, Errors errors
			, RedirectAttributes redirectAttributes
			, Model model) {
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();
		
		if (valid) {
			ServiceResult result = approvalFarmboxListService.deleteFarmbox(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("양식이 삭제되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				message = NotyMessageVO.builder("양식 삭제 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			}
		} else {
			message = NotyMessageVO.builder("양식 삭제 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
		}
		return "redirect:/admin/approvalFarmboxList";
	}
	
	/**
	 * 분류 등록
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalDocTypeInsert")
	public String approvalDocTypeInsert(@Validated(InsertGroup.class) DocumentsVO documentsVO, Errors errors,
			RedirectAttributes redirectAttributes, Model model) {
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = approvalFarmboxListService.insertDocType(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("분류가 등록되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				message = NotyMessageVO.builder("분류 등록이 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			}
		} else {
			message = NotyMessageVO.builder("분류 등록이 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
		}
		return "redirect:/admin/approvalFarmboxList";
	}

	/**
	 * 분류 수정
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @param docTypeName
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/admin/approvalDocTypeUpdate")
	@ResponseBody
	public void approvalDocTypeUpdate(DocumentsVO documentsVO, Errors errors, Model model) {
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = approvalFarmboxListService.updateDocType(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("분류설정이 수정되었습니다").type(NotyType.success).build();
				model.addAttribute("message", message);
				break;
			default:
				message = NotyMessageVO.builder("분류설정 수정 실패했습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				break;
			}
		} else {
			message = NotyMessageVO.builder("분류설정 수정을 실패했습니다.").type(NotyType.error).build();
			model.addAttribute("message", message);
		}
	}
	
	/**
	 * 분류 삭제
	 * @author 서대철
	 * @since 2021. 2. 20.
	 * @param documentsVO
	 * @param errors
	 * @param model
	 */
	@RequestMapping("/admin/approvalDocTypeDelete/{docTypeCode}/{docTypeName}")
	public String approvalDocTypeDelete(DocumentsVO documentsVO
			,@PathVariable("docTypeCode") String docTypeCode
			,@PathVariable("docTypeName") String docTypeName
			, Errors errors
			, RedirectAttributes redirectAttributes
			, Model model) {
		NotyMessageVO message = null;
		
		documentsVO.setDocTypeCode(docTypeCode);
		documentsVO.setDocTypeName(docTypeName);
		
		boolean valid = !errors.hasErrors();
		
		if (valid) {
			ServiceResult result = approvalFarmboxListService.deleteDocType(documentsVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("분류가 삭제되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				message = NotyMessageVO.builder("분류 삭제 실패했습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			}
		} else {
			message = NotyMessageVO.builder("분류 삭제 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
		}
		return "redirect:/admin/approvalFarmboxList";
	}
	
	/**
	 * 분류 설정 사용 여부 확인
	 * @author 서대철
	 * @since 2021. 3. 11.
	 * @param documentsVO
	 * @param docTypeCode
	 * @param docTypeName
	 * @return
	 */
	@RequestMapping("/admin/approvalFarmCheck/{docTypeCode}")
	@ResponseBody
	public Map<String, Object> approvalFarmCheck(DocumentsVO documentsVO
			, @PathVariable("docTypeCode") String docTypeCode
			, Model model){
		
		NotyMessageVO message = null;
		int cnt = 0;
		
		documentsVO.setDocTypeCode(docTypeCode);
		
		ServiceResult result = approvalFarmboxListService.approvalFarmCheck(documentsVO);
		
		switch (result) {
		case OK:
			cnt = 1;
			message = NotyMessageVO.builder("사용중인 분류는 삭제할 수 없습니다.").type(NotyType.error).build();
			model.addAttribute("message", message);
			model.addAttribute("cnt", cnt);
			break;
		default:
			
			break;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("message", message);
		resultMap.put("cnt", cnt);
		return resultMap;
	}
	
}
