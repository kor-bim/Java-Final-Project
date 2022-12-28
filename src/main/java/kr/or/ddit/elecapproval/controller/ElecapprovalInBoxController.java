package kr.or.ddit.elecapproval.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.elecapproval.service.ApprovalDetailService;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.service.ElecapprovalInBoxService;
import kr.or.ddit.elecapproval.service.InBoxDetailService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.InBoxVO;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * 전자결재 > 문서함 
 * 
 * @author 
 * @since 2021. 3. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 3.  1.   이운주            (수정) 페이지마다 의견 따로 가져오는 부분 수정.
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class ElecapprovalInBoxController {

	@Inject
	private ElecapprovalInBoxService service;
	
	@Inject
	public InBoxDetailService inBoxDetailService;
	
	@Inject
	public ApprovalDocCommonService approvalDocCommonService; 
	@Inject
	public ApprovalDetailService approvalDetailService; // 20210301 운주 : 의견 목록 조회

	@RequestMapping("/approval/inBoxAllList")
	public void inBoxAllList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxAllListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxAllList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	@RequestMapping("/approval/inBoxDraft")
	public void inBoxDraft(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxDraftListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxDraftList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}
	/**
	 * 상세조회
	 * @param adNo
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/approvalBox/{no}")
	public String inboxDetail(@PathVariable(value = "no", required = true) String adNo, Model model) throws IOException {
		ApprovalDocVO detail = new ApprovalDocVO();
		detail.setAdNo(adNo);
		detail = inBoxDetailService.retrieveInBoxDetail(detail);
		List<ApprovalVO> commentList =approvalDetailService.retrieveApprovalComment(detail); // 운주 
		
		//============================================================================
		ApprovalLineVO approvalLineVO = new ApprovalLineVO();
		approvalLineVO = approvalDocCommonService.getApprovalLine(detail);
		ObjectMapper mapper = new ObjectMapper();
		String detailJSON = mapper.writeValueAsString(approvalLineVO);
		model.addAttribute("detailJSON", detailJSON);
		//============================================================================

		model.addAttribute("Detail", detail);
		model.addAttribute("commentList", commentList);
		return "approval/inboxDetail";
	}
	
	@RequestMapping("/approval/inBoxApproval")
	public void inBoxApproval(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxApprovalListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxApprovalList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	@RequestMapping("/approval/inBoxReception")
	public void inBoxReception(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxReceptionListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxReceptionList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	@RequestMapping("/approval/inBoxPassAlong")
	public void inBoxPassAlong(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxPassAlongListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxPassAlongList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	@RequestMapping("/approval/inBoxReturn")
	public void inBoxReturn(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<InBoxVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveInBoxReturnListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<InBoxVO> memberList = service.retrieveInBoxReturnList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

}
