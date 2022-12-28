package kr.or.ddit.FileBox.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.FileBox.service.FileBoxService;
import kr.or.ddit.FileBox.vo.FileBoxVO;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * @author 서대철
 * @since 2021. 3. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 3. 5.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class EnterpriseFileBoxController {
	
	@Inject
	private FileBoxService fileBoxService;
	
	/**
	 * 전사 파일관리함 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param currentPage
	 * @param model
	 * @param authMember
	 * @return
	 */
	@RequestMapping("/fileBox/public")
	public String enterList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			Model model, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		
		String fileType = "PUBLIC";
		
		PagingVO<FileBoxVO> pagingVO = new PagingVO<>(10, 10);
		pagingVO.setUserId(authMember.getMemId());
		pagingVO.setFileType(fileType);
		
		int totalRecord = fileBoxService.retrieveFileCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<FileBoxVO> fileBoxList = fileBoxService.selectFileBoxList(pagingVO);
		pagingVO.setDataList(fileBoxList);
		
		FileBoxVO fileBoxVO = new FileBoxVO();
		fileBoxVO.setMemId(authMember.getMemId());
		fileBoxVO.setFileType(fileType);
		
		List<FileBoxVO> selectFolderList = fileBoxService.selectFileFolderList(fileBoxVO);

		model.addAttribute("pagingVO", pagingVO);
		model.addAttribute("selectFolderList", selectFolderList);
		
		return "fileBox/enterpriseFileBox";
	}
	
	/**
	 * 전사 파일보관함의 폴더 생성
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param authMember
	 * @param fileBoxVO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/public/folderInsert")
	public String enterpriseFolderInsert(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			,FileBoxVO fileBoxVO, RedirectAttributes redirectAttributes) {
		
		String fileType = "PUBLIC";
		
		fileBoxVO.setMemId(authMember.getMemId());
		fileBoxVO.setFileType(fileType);
		NotyMessageVO message = null;
		ServiceResult result = fileBoxService.createFolder(fileBoxVO);
		
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("폴더가 생성되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;

		default:
			message = NotyMessageVO.builder("폴더 생성 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/fileBox/public";
	}
	
	/**
	 * 전사파일보관함에서 선택된 파일 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param fileBoxVO
	 * @param deleteFileNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/publicDelete/{deleteFileNo}/{fileType}")
	public String publicFileDelete(FileBoxVO fileBoxVO
			, @PathVariable("deleteFileNo") List<Integer> deleteFileNo
			, @PathVariable("fileType") String fileType
			, RedirectAttributes redirectAttributes) {
		NotyMessageVO message = null;
		fileBoxVO.setDeleteFileNo(deleteFileNo);
		fileBoxVO.setFileType(fileType);
		
		ServiceResult result = fileBoxService.deleteFileBox(fileBoxVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("파일이 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default :
			message = NotyMessageVO.builder("파일 삭제 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		
		return "redirect:/fileBox/public";
	}
	
	/**
	 * 전사 파일보관함에서 폴더명 수정 처리
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param authMember
	 * @param fileNo
	 * @param fileRealName
	 * @param fileType
	 * @param fileBoxVO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/publicFolderUpdate/{fileNo}/{fileRealName}/{fileType}")
	public String publicFolderNameUpdate(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			, @PathVariable("fileNo") int fileNo
			, @PathVariable("fileRealName") String fileRealName
			, @PathVariable("fileType") String fileType
			, FileBoxVO fileBoxVO
			, RedirectAttributes redirectAttributes) {
		
		NotyMessageVO message = null;
		fileBoxVO.setMemId(authMember.getMemId());
		fileBoxVO.setFileNo(fileNo);
		fileBoxVO.setFileRealName(fileRealName);
		fileBoxVO.setFileType(fileType);
		
		ServiceResult result = fileBoxService.folderNameUpdate(fileBoxVO);
		
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("폴더가 수정되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;

		default:
			message = NotyMessageVO.builder("폴더 수정실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/fileBox/public";
		
	}
	
}
