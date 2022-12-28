package kr.or.ddit.elecapproval.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.base.controller.BaseController;
import kr.or.ddit.commons.enumpkg.MessageType;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.service.ApprovalDocWriteService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;
import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.websocket.event.MessageEvent;

/**
 * 결재 문서 작성 관련 컨트롤러
 * 
 * @author 이운주
 * @since 2021. 2. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 18.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class ApprovalDocWriteController extends BaseController {

	@Inject
	ApplicationEventPublisher publisher;

	@Inject
	public ApprovalDocWriteService approvalDocWriteService;
	@Inject
	public ApprovalDocCommonService approvalDocCommonService;

	/**
	 * 결재문서 작성 폼으로 이동
	 * 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param approvalDocVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/approval/document/write")
	public String docForm(ApprovalDocVO approvalDocVO, Model model) {
		// 문서양식 분류 코드
		List<DocumentsVO> docTypeList = approvalDocCommonService.selectDocTypeList(new DocumentsVO());
		model.addAttribute("docTypeList", docTypeList);

		return "approval/approvalDocWrite";
	}

	/**
	 * 기안하기
	 * 
	 * @author 이운주
	 * @since 2021. 2. 19.
	 * @param approvalDocVO
	 * @return
	 */
	@RequestMapping("/approval/document/draft")
	public String insertApprovalDoc(ApprovalDocVO approvalDocVO, RedirectAttributes redirectAttributes, Model model,
			HttpServletRequest req) {
		String[] approvalMember = req.getParameterValues("approvalMember");
		String[] referenceMember = req.getParameterValues("referenceMember");

		approvalDocVO.setLineDetailList(approvalMember, referenceMember);

		ServiceResult result = approvalDocWriteService.createApprovalDocument(approvalDocVO);
		NotyMessageVO message = null;
		String goPage = "";

		switch (result) {
		case OK:
			message = NotyMessageVO.builder("저장되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			goPage = "redirect:/approval/approvalProgress";

			break;

		default:
			message = NotyMessageVO.builder("저장에 실패했습니다.").type(NotyType.error).build();
			model.addAttribute("message", message);
			model.addAttribute("approvalDocVO", approvalDocVO);
			
			MessageVO source = new MessageVO(MessageType.APPROVAL, "알림");
			MessageEvent event = new MessageEvent(source);
			publisher.publishEvent(event);
			goPage = "forward:/approval/document/write";
			break;
		}

		return goPage;
	}
}
