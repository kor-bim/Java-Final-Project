package kr.or.ddit.FileBox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.FileBox.service.FileBoxService;
import kr.or.ddit.FileBox.vo.FileBoxVO;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.service.MemberAdminService;
import kr.or.ddit.hr.member.vo.MemberVO;

@Controller
public class FileBoxController {
	
	@Inject
	private FileBoxService fileBoxService;
	
	@Inject
	MemberAdminService adminService;
	
	/**
	 * 파일 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param currentPage
	 * @param searchVO
	 * @param model
	 * @param authMember
	 * @return
	 */
	@RequestMapping("/fileBox/myFile")
	public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			Model model, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		
		String fileType = "PRIVATE";
		
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
		
		List<MemberVO> memberList = adminService.retrieveNoPagingMemberList();
		model.addAttribute("memberList", memberList);
		
		return "fileBox/individualFileBox";
	}
	
	/**
	 * 삭제된 파일 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param currentPage
	 * @param model
	 * @param authMember
	 * @return
	 */
	@RequestMapping("/fileBox/deleteFileBox")
	public String deleteFileBoxList(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			Model model, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		
		PagingVO<FileBoxVO> pagingVO = new PagingVO<>(10, 10);
		pagingVO.setUserId(authMember.getMemId());
		
		int totalRecord = fileBoxService.retrieveDeleteFileCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);
		
		List<FileBoxVO> deleteFileBoxList = fileBoxService.selectDeleteFileBoxList(pagingVO);
		pagingVO.setDataList(deleteFileBoxList);
		
		model.addAttribute("pagingVO", pagingVO);
		
		return "fileBox/deleteFileBox";
	}
	
	
	/**
	 * 파일 크기 정보 조회
	 * @author 길영주
	 * @return
	 */
	@RequestMapping("/fileBox/fileSize.do")
	@ResponseBody
	public FileBoxVO fileTotal(Model model) {
		FileBoxVO sizetotal = new FileBoxVO();
		sizetotal.setFileType("PRIVATE");
		sizetotal = fileBoxService.selectTotalSize(sizetotal);
		model.addAttribute("sizetotal", sizetotal);
		return sizetotal;
	}

	/**
	 * 파일 업로드 
	 * formData 로 보냄
	 * @author 이운주
	 * @since 2021. 3. 3.
	 * @param multiRequest
	 * @param fileBoxVO
	 * @param errors
	 * @param model
	 * @param redirectAttributes
	 */
	@RequestMapping("/fileBox/fileUploadInsert.do")
	@ResponseBody
	public void noticeBoardInsert(MultipartHttpServletRequest multiRequest, FileBoxVO fileBoxVO, BindingResult errors, Model model,
			RedirectAttributes redirectAttributes ) {
		
		NotyMessageVO message = null;
		
		List<MultipartFile> fileList = multiRequest.getFiles("uploadFile");
		fileBoxVO.setFiles(fileList);
		
		if (!errors.hasErrors()) {
			ServiceResult result = fileBoxService.createFileUpload(fileBoxVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("추가하였습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				break;
			default:
				model.addAttribute("message", NotyMessageVO.builder("실패하였습니다.").type(NotyType.error).build());
				break;
			}
		}else {
			model.addAttribute("message", NotyMessageVO.builder("실패하였습니다.").type(NotyType.error).build());
		}
	}
	
	/**
	 * 선택된 파일 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param fileBoxVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/fileBox/delete/{deleteFileNo}/{fileType}")
	public String fileDelete(FileBoxVO fileBoxVO
			, @PathVariable("deleteFileNo") List<Integer> deleteFileNo
			, @PathVariable("fileType") String fileType
			, RedirectAttributes redirectAttributes){
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
		return "redirect:/fileBox/myFile";
	}
	
	/**
	 * 체크된 파일 백업 처리
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param fileBoxVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/fileBox/backUp/{deleteFileNo}")
	public String fileBackUp(FileBoxVO fileBoxVO
			, @PathVariable("deleteFileNo") List<Integer> deleteFileNo
			, RedirectAttributes redirectAttributes){
		NotyMessageVO message = null;
		fileBoxVO.setDeleteFileNo(deleteFileNo);
		
		ServiceResult result = fileBoxService.backUpFileBox(fileBoxVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("파일이 복원되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default :
			message = NotyMessageVO.builder("파일 복원 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/fileBox/deleteFileBox";
	}
	
	/**
	 * 개인보관함의 폴더 생성
	 * @author 서대철
	 * @since 2021. 3. 3.
	 * @param authMember
	 * @param fileBoxVO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/folderInsert")
	public String folderInsert(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
		,FileBoxVO fileBoxVO, RedirectAttributes redirectAttributes) {
		
		String fileType = "PRIVATE";
		
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
		return "redirect:/fileBox/myFile";
	}
	
	/**
	 * 파일 내부 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 3.
	 * @param authMember
	 * @param fileBoxVO
	 * @return
	 */
	@RequestMapping("/fileBox/folderList")
	@ResponseBody
	public List<FileBoxVO> folderList(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			,FileBoxVO fileBoxVO){
		
		fileBoxVO.setMemId(authMember.getMemId());
		if(fileBoxVO.getFileNo() == 0) {
			fileBoxVO.setFileNo(null);
		}
		List<FileBoxVO> folderList = fileBoxService.folderList(fileBoxVO);
		
		return folderList;
	}
	
	/**
	 * 파일 완전 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 4.
	 * @param fileBoxVO
	 * @param authMember
	 * @param deleteFileNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/realDeleteFile/{deleteFileNo}")
	public String RealDeleteFile(FileBoxVO fileBoxVO
			, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			, @PathVariable("deleteFileNo") List<Integer> deleteFileNo
			, RedirectAttributes redirectAttributes) {
		
		NotyMessageVO message = null; 
		
		fileBoxVO.setMemId(authMember.getMemId());
		fileBoxVO.setDeleteFileNo(deleteFileNo);
		
		ServiceResult result = fileBoxService.realDeleteFile(fileBoxVO);
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("파일이 완전 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;

		default:
			message = NotyMessageVO.builder("파일 완전 삭제실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/fileBox/deleteFileBox";
	}
	
	/**
	 * 폴더 내부 파일 존재 시 삭제 불가처리
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param authMember
	 * @param fileNo
	 * @param fileBoxVO
	 * @param model
	 * @return
	 */
	@RequestMapping("/fileBox/fileCount/{fileNo}")
	@ResponseBody
	public Map<String, Object> fileInFolder(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			, @PathVariable("fileNo") int fileNo
			, FileBoxVO fileBoxVO
			, Model model) {
		NotyMessageVO message = null;
		fileBoxVO.setMemId(authMember.getMemId());
		fileBoxVO.setFileNo(fileNo);
		
		int cnt = 0;
		
		ServiceResult result = fileBoxService.fileInFolderCount(fileBoxVO);
		switch (result) {
		case OK:
			cnt = 1;
			message = NotyMessageVO.builder("선택한 폴더에 하위 폴더가 존재합니다. 하위폴더를 먼저 삭제하시기바랍니다.").type(NotyType.error).build();
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
	
	/**
	 * 폴더명 수정 처리
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param authMember
	 * @param fileNo
	 * @param fileRealName
	 * @param fileBoxVO
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/fileBox/folderNameUpdate/{fileNo}/{fileRealName}/{fileType}")
	public String folderNameUpdate(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember
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
		return "redirect:/fileBox/myFile";
	}
}
