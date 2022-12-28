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
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.elecapproval.service.ApprovalDetailService;
import kr.or.ddit.elecapproval.service.ApprovalDocCommonService;
import kr.or.ddit.elecapproval.service.ElecapprovalDocumentProgressService;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.CountVO;
import kr.or.ddit.elecapproval.vo.DocumentProgressVO;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * @author
 * @since 2021. 3. 1.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자           수정내용
 * ------------- --------    ----------------------
 * 2021. 3.  1.   이운주            (수정X) 페이지마다 의견 따로 가져오는 부분 수정. 현재페이지의 메서드 사용함.
 * 							  List<ApprovalVO> commentList =approvalDetailService.retrieveApprovalComment(detail);		
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class ElecApprovalDocumentProgressController {

	@Inject
	private ElecapprovalDocumentProgressService service;

	@Inject
	public ApprovalDetailService approvalDetailService;

	@Inject
	public ApprovalDocCommonService approvalDocCommonService;

	@RequestMapping("/approval/approvalCount")
	@ResponseBody
	public CountVO approvalCount(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model) {
		PagingVO<DocumentProgressVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setUserId(authMember.getMemId());
		int awaitTotalRecord = service.retrieveApprovalAwaitListCount(pagingVO);
		int confirmTotalRecord = service.retrieveApprovalConfirmListCount(pagingVO);
		int progressTotalRecord = service.retrieveApprovalProgressListCount(pagingVO);
		
		CountVO count = new CountVO();
		count.setAwaitCount(awaitTotalRecord);
		count.setConfirmCount(confirmTotalRecord);
		count.setProgressCount(progressTotalRecord);
		
		return count;
	}

	/**
	 * 전체 리스트 조회
	 * 
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @param authMember
	 */
	@RequestMapping("/approval/approvalAllList")
	public void approvalAllList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<DocumentProgressVO> pagingVO = new PagingVO<>(10, 10);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveApprovalAllListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<DocumentProgressVO> memberList = service.retrieveApprovalAllList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	/**
	 * 상세조회
	 * 
	 * @param adNo
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/approval/{what}")
	public String approvalDetail(@PathVariable(value = "what", required = true) String adNo, Model model)
			throws IOException {
		ApprovalDocVO detail = new ApprovalDocVO();
		detail.setAdNo(adNo);
		detail = approvalDetailService.retrieveApprovalDetail(detail);
		List<ApprovalVO> commentList = approvalDetailService.retrieveApprovalComment(detail);
		// ============================================================================
		ApprovalLineVO approvalLineVO = new ApprovalLineVO();
		approvalLineVO = approvalDocCommonService.getApprovalLine(detail);
		ObjectMapper mapper = new ObjectMapper();
		String detailJSON = mapper.writeValueAsString(approvalLineVO);
		model.addAttribute("detailJSON", detailJSON);
		// ============================================================================
		detail.setLastApprovalId(detail.getLineDetailList());
		model.addAttribute("Detail", detail);
		model.addAttribute("commentList", commentList);

		return "approval/approvalDetail";
	}

	// 진행중인 문서 > 대기
	@RequestMapping("/approval/approvalAwait")
	public void approvalAwaitList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<DocumentProgressVO> pagingVO = new PagingVO<>(5, 5);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveApprovalAwaitListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<DocumentProgressVO> memberList = service.retrieveApprovalAwaitList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	// 진행중인 문서 > 진행
	@RequestMapping("/approval/approvalProgress")
	public void approvalProgressList(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<DocumentProgressVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveApprovalProgressListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<DocumentProgressVO> memberList = service.retrieveApprovalProgressList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}

	// 진행중인 문서 > 확인
	@RequestMapping("/approval/approvalConfirm")
	public void approvalConfirmList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		PagingVO<DocumentProgressVO> pagingVO = new PagingVO<>(5, 2);
		pagingVO.setUserId(authMember.getMemId());

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = service.retrieveApprovalConfirmListCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<DocumentProgressVO> memberList = service.retrieveApprovalConfirmList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
	}
}
