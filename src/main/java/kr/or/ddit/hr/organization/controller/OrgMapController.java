package kr.or.ddit.hr.organization.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemFormVO;
import kr.or.ddit.hr.organization.service.OrgMapService;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionListVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

/**
 * 투표게시판
 * 
 * @author 이재형
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.      이재형            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class OrgMapController {

	@Inject
	private OrgMapService orgMapService;

//	@RequestMapping("/org/orgmap")
//	public List<DeptVO> deptList(DeptVO deptVO) throws Exception {
//		return orgMapService.retrieveDeptList();
//	}

//	@RequestMapping(value="{dept_code}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public void deptView(@PathVariable(value="dept_code", required=true) int vb_no,	ModelMap model) {
//		
//		DeptVO deptVO = orgMapService.selectDept(dept_code);
//		model.addAttribute("deptVo", deptVO);
//	}

	@RequestMapping("/admin/org/posJob")
	public String position(MemFormVO memFormVO, Model model) throws Exception {
		List<PositionVO> psVOList = orgMapService.retrievePsList();
		List<JobVO> jobVOList = orgMapService.retrieveJobList();
		model.addAttribute("psVOList", psVOList);
		model.addAttribute("jobVOList", jobVOList);

		return "org/orgPosition";
	}

	@RequestMapping("/org/psList.do")
	public List<PositionVO> psList() {
		return orgMapService.retrievePsList();
	}

	@PostMapping("/admin/org/updatePosition")
	@ResponseBody
	public ServiceResult modifyPosition(PositionListVO psVOList, Model model) {
		ServiceResult result = orgMapService.modifyPosition(psVOList.getPsVOList());
		return result;
	}

	@PostMapping("/admin/org/updateJob")
	@ResponseBody
	public String modifyJob(@RequestParam("jobCode") String[] jobCode, @RequestParam("jobName") String[] jobName, 
			@ModelAttribute("jobVO") JobVO delJobVO, Model model) {
		String message = null;
		List<JobVO> jobVOList = new ArrayList<>();
		for (int i = 0; i < jobCode.length; i++) {
			JobVO jobVO = new JobVO();
			jobVO.setJobCode(jobCode[i]);
			jobVO.setJobName(jobName[i]);
			jobVOList.add(jobVO);
		}

		ServiceResult result = orgMapService.modifyJob(jobVOList, delJobVO);
		switch (result) {
		case OK:
			message = "OK";
			break;
		default:
			message = "FAIL";
			break;
		}
		return message;
	}

	/**
	 * 조직 트리 
	 * @param deptVO
	 * @return
	 * @throws Exception
	 * @authoe 이운주
	 * @date 2021. 2. 21.
	 */
	@RequestMapping("/deptTree.do")
	@ResponseBody
	public List<DeptVO> deptTreeData(DeptVO deptVO) throws Exception {
		return orgMapService.retrieveDeptList();
	}

	/**
	 * 조직 + 구성원 트리 
	 * @param deptVO
	 * @return
	 * @throws Exception
	 * @authoe 이운주
	 * @date 2021. 2. 21.
	 */
	@RequestMapping("/deptMemTree.do")
	@ResponseBody
	public List<DeptVO> deptMemTreeData(DeptVO deptVO) throws Exception {
		return orgMapService.retrieveDeptMemList();
	}
	
	@RequestMapping("/admin/org/insertDept")
	@ResponseBody
	public String createDept(DeptVO deptVO, Model model) throws Exception{
		String message = null;
		ServiceResult result = orgMapService.createDept(deptVO);
		switch (result) {
		case OK:
			message = "OK";
			model.addAttribute("message", NotyMessageVO.builder("수정 성공했습니다.").build());
			break;
		case ALREADYEXIST:
			message = "ALREADYEXIST";
			break;
		default:
			message = "FAIL";
			break;
		}
		return message;
	}
	
	@RequestMapping("/admin/org/deleteDept")
	@ResponseBody
	public String deleteDept(DeptVO deptVO, Model model) throws Exception{
		String message = null;
		ServiceResult result = orgMapService.deleteDept(deptVO);
		switch (result) {
		case OK:
			message = "OK";
			model.addAttribute("message", NotyMessageVO.builder("수정 성공했습니다.").build());
			break;
		case ALREADYEXIST:
			message = "ALREADYEXIST";
			break;
		default:
			message = "FAIL";
			break;
		}
		return message;
	}
	
	@RequestMapping("/admin/org/modifyDeptName")
	@ResponseBody
	public String modifyDeptName(DeptVO deptVO, Model model) throws Exception{
		String message = null;
		ServiceResult result = orgMapService.modifyDeptName(deptVO);
		switch (result) {
		case OK:
			message = "OK";
			model.addAttribute("message", NotyMessageVO.builder("수정 성공했습니다.").build());
			break;
		case ALREADYEXIST:
			message = "ALREADYEXIST";
			break;
		default:
			message = "FAIL";
			break;
		}
		return message;
	}
}
