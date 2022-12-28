package kr.or.ddit.hr.work.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.service.WorkUserService;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

/**
 * 근태 관련 컨틀롤러
 * 
 * @author 윤한빈
 * @since 2021. 2. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 24.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class WorkUserController {

	@Inject
	WorkUserService service;

	@RequestMapping("/work/startWork")
	@ResponseBody
	public ServiceResult startWork(@ModelAttribute("attendanceVO") AttendanceVO attendanceVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		ServiceResult result = service.createStartWork(attendanceVO);
		AttendanceVO saveVO = new AttendanceVO();
		saveVO.setAttdDate(attendanceVO.getAttdDate());
		List<AttendanceVO> attendanceList = authMember.getAttendanceList();
		attendanceList.add(saveVO);
		authMember.setAttendanceList(attendanceList);
		return result;
	}

	@RequestMapping("/work/endWork")
	@ResponseBody
	public ServiceResult endWork(@ModelAttribute("attendanceVO") AttendanceVO attendanceVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		ServiceResult result = service.updateEndWork(attendanceVO);
		AttendanceVO saveVO = new AttendanceVO();
		saveVO.setAttdDate(attendanceVO.getAttdDate());
		List<AttendanceVO> attendanceList = authMember.getAttendanceList();
		attendanceList.add(saveVO);
		authMember.setAttendanceList(attendanceList);
		return result;
	}

	@RequestMapping("/work/changeWorkState")
	@ResponseBody
	public ServiceResult changeWorkState(@ModelAttribute("attendanceVO") AttendanceVO attendanceVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		ServiceResult result = service.updateWorkState(attendanceVO);
		return result;
	}

	@RequestMapping("/work/workDate")
	@ResponseBody
	public AttendanceVO workDate(@ModelAttribute("attendanceVO") AttendanceVO attendanceVO,
			@AuthenticationPrincipal(expression = "realMember") MemberVO authMember) {
		attendanceVO = service.retrieveTodayWorkInfo(attendanceVO);
		return attendanceVO;
	}

	/**
	 * 길영주 
	 * 나의 근태현황
	 */
	@RequestMapping("/work/absenteeism")
	public String allworkDate(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model)
			throws JsonProcessingException {
		
		AttendanceVO attendanceVO = new AttendanceVO();
		attendanceVO.setMemId(authMember.getMemId());

		VacaRecVO vacaRecVO = new VacaRecVO();
		vacaRecVO.setMemId(authMember.getMemId());
		
		List<WorkTimeVO> workTime = service.retrieveWeekWorkTime(authMember);
		List<AttendanceVO> attendance = service.retrieveWorkSchedule(attendanceVO);
		List<VacaRecVO> vacaRec = service.retrieveVacation(attendanceVO);
		List<MonthWorkTimeVO> monthWorkTime = service.retrieveMonthWorkTime(authMember);
		AttendanceVO attendanceVO2 = service.retrieveTodayWorkTime(authMember);
		
		ObjectMapper mapper = new ObjectMapper();
		String attendanceList = mapper.writeValueAsString(attendance);
		String vacaRecList = mapper.writeValueAsString(vacaRec);
		String workTimeList = mapper.writeValueAsString(workTime);
		String monthWorkTimeList = mapper.writeValueAsString(monthWorkTime);
		
		model.addAttribute("attendanceVO2", attendanceVO2);
		model.addAttribute("attendanceList", attendanceList);
		model.addAttribute("vacaRecList", vacaRecList);
		model.addAttribute("workTimeList", workTimeList);
		model.addAttribute("monthWorkTimeList", monthWorkTimeList);

		return "hr/absenteesim";
	} 
}
