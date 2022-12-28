package kr.or.ddit.schedule.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.schedule.service.CalendarService;
import kr.or.ddit.schedule.service.ScheduleService;
import kr.or.ddit.schedule.vo.CalendarVO;
import kr.or.ddit.schedule.vo.ScheduleVO;

/**
 * @author 서대철
 * @since 2021. 2. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class ScheduleController {

	@Inject
	private ScheduleService scheduleService;

	@Inject
	private CalendarService calendarService;
	
	
	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	/**
	 * 일정 목록 조회, 달력 목록 조회
	 * @param session
	 * @param model
	 * @return 
	 * @throws JsonProcessingException
	 * @since 2021-02-09
	 * @author 서대철
	 */
	@RequestMapping("/schedule/scheduleList")
	public String scheduleList(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model)
			throws JsonProcessingException {
		// 달력 목록 조회
		CalendarVO calendarVO = new CalendarVO();
		calendarVO.setMemId(authMember.getMemId());
		List<CalendarVO> calendarList = calendarService.selectCalendarList(calendarVO);
		model.addAttribute("calendarList", calendarList);
		
		List<CalendarVO> shareCalendarList = calendarService.selectShareCalendarList(calendarVO);
		model.addAttribute("shareCalendarList", shareCalendarList);
		
		// 일정 목록 조회
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setMemId(authMember.getMemId());
		List<ScheduleVO> dataList = scheduleService.retrieveScheduleList(scheduleVO);
		ObjectMapper mapper = new ObjectMapper();
		String scheduleList = mapper.writeValueAsString(dataList);
		model.addAttribute("scheduleList", scheduleList);
		return "schedule/schedule";
	}
	
	/**
	 * 일정 추가 폼
	 * @param scheduleVO
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/scheduleForm.do")
	public String scheduleForm(ScheduleVO scheduleVO
			, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			, Model model) {
		
		scheduleVO.setMemId(authMember.getMemId());
		
		// 캘린더 목록
		CalendarVO calendarVO = new CalendarVO();
		calendarVO.setMemId(authMember.getMemId());
		List<CalendarVO> calendarList = calendarService.selectCalendarList(calendarVO);
		model.addAttribute("calendarList", calendarList);
		
		List<CalendarVO> calendarTypeList = calendarService.selectTypeList(calendarVO);
		model.addAttribute("calendarTypeList", calendarTypeList);
		
		List<CalendarVO> shareCalendarList = calendarService.selectShareCalendarList(calendarVO);
		model.addAttribute("shareCalendarList", shareCalendarList);
		
		return "schedule/others/scheduleForm";
	}
	
	/**
	 * 일정 추가
	 * @param scheduleVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/insert.do")
	public String scheduleInsert(@Validated(InsertGroup.class) @ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, Errors errors, RedirectAttributes redirectAttributes, Model model) {
		String goPage = null;
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = scheduleService.scheduleInsert(scheduleVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("일정 등록이 되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("일정 등록이 실패했습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				goPage = "redirect:/schedule/scheduleForm";
				break;
			}
		}else {
			goPage = "redirect:/schedule/scheduleList";
		}
		return goPage;
	}

	/**
	 * 일정 상세조회
	 * @param schdlNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/{schdlNo}")
	public String selectSchedule(@PathVariable(value="schdlNo", required=true) int schdlNo
			, Model model) {
		ScheduleVO scheduleVO = scheduleService.selectSchedule(schdlNo); 
		model.addAttribute("scheduleVO", scheduleVO);
		
		CalendarVO calendarVO = calendarService.selectCalendar(scheduleVO.getCalNo());
		model.addAttribute("calendarVO", calendarVO);
		return "schedule/others/scheduleView";
	}
	
	/**
	 * 일정 수정 폼
	 * @param scheduleVO
	 * @param model
	 * @param schdlNo
	 * @return
	 */
	@RequestMapping("/schedule/updateScheduleForm/{schdlNo}")
	public String updateScheduleForm(ScheduleVO scheduleVO
			, @PathVariable("schdlNo") int schdlNo
			, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember
			, Model model) {
		addCommandAttribute(model);
		CalendarVO calendarVO = new CalendarVO();
		calendarVO.setMemId(authMember.getMemId());
		List<CalendarVO> calendarList = calendarService.selectCalendarList(calendarVO);
		model.addAttribute("calendarList", calendarList);
		
		List<CalendarVO> calendarTypeList = calendarService.selectTypeList(calendarVO);
		model.addAttribute("calendarTypeList", calendarTypeList);
		
		scheduleVO.setSchdlNo(schdlNo);
		scheduleVO = scheduleService.selectSchedule(scheduleVO.getSchdlNo());
		
		
		model.addAttribute("scheduleVO", scheduleVO);
		
		return "schedule/others/scheduleForm";
	}
	
	/**
	 * 일정 수정
	 * @param scheduleVO
	 * @param errors
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/update.do")
	public String scheduleUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, Errors errors
			, RedirectAttributes redirectAttributes
			, Model model) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = scheduleService.scheduleUpdate(scheduleVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("일정이 수정되었습니다").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("일정 수정을 실패했습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				goPage = "redirect:/schedule/scheduleForm";
				break;
			}
		}else {
			goPage = "redirect:/schedule/scheduleForm";
		}
		return goPage;
	}
	
	/**
	 * 일정 삭제
	 * @param scheduleVO
	 * @param schdlNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/schedule/delete.do/{schdlNo}")
	public String deleteSchedule(
			@Validated(DeleteGroup.class) @ModelAttribute("scheduleVO") ScheduleVO scheduleVO
			, @PathVariable("schdlNo") int schdlNo
			, RedirectAttributes redirectAttributes) {
		
		scheduleVO.setSchdlNo(schdlNo);
		scheduleVO = scheduleService.selectSchedule(scheduleVO.getSchdlNo());
		
		NotyMessageVO message = null;
		
		ServiceResult result = scheduleService.removeSchdule(scheduleVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("일정이 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("일정 삭제를 실패했습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		
		return "redirect:/schedule/scheduleList";
	}
	
	/**
	 * 내 캘린더 추가 폼
	 * @param calendarVO
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/calendar/insertForm.do")
	public String calendarForm() {
		return "schedule/others/calendarForm";
	}
	
	/**
	 * 내 캘린더 등록
	 * @param calendarVO
	 * @param authMember
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/calendarInsert.do")
	public String calendarInsert(
			@Validated(InsertGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
			, RedirectAttributes redirectAttributes
			, Errors errors
			, Model model) {
		String goPage = null;
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = calendarService.calendarInsert(calendarVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("캘린더가 추가되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("캘린더 추가 실패하였습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				goPage = "schdule/others/calendarForm";
				break;
			}
		} else {
			goPage = "schedule/others/calendarForm";
		}
		return goPage;
	}
	
	/**
	 * 내 캘린더 수정 폼
	 * @param calendarVO
	 * @param calNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/updateForm.do/{calNo}")
	public String updateCalendarForm(
			CalendarVO calendarVO,
			@PathVariable("calNo") int calNo,
			Model model) {
		addCommandAttribute(model);
		calendarVO.setCalNo(calNo);
		
		calendarVO = calendarService.selectCalendar(calendarVO.getCalNo());
		model.addAttribute("calendarVO", calendarVO);
		
		return "schedule/others/calendarForm";
	}
	
	/**
	 * 내 캘린더 수정
	 * @param calendarVO
	 * @param redirectAttributes
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping("/schedule/calendarUpdate.do")
	public String calendarUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
			, RedirectAttributes redirectAttributes
			, Errors errors
			, Model model) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = calendarService.calendarUpdate(calendarVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("캘린더가 수정되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("캘린더 수정 실패하였습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				goPage = "schdule/others/calendarForm";
				break;
			}
		} else {
			goPage = "schedule/others/calendarForm";
		}
		return goPage;
	}
	
	/**
	 * 내 캘린더 삭제
	 * @param calendarVO
	 * @param calNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/schedule/calendarDelete.do/{calNo}")
	public String calendarDelete(
		@Validated(DeleteGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
		, @PathVariable("calNo") int calNo
		, RedirectAttributes redirectAttributes) {
	
		calendarVO.setCalNo(calNo);
		calendarVO = calendarService.selectCalendar(calNo);
		
		NotyMessageVO message = null;
		
		ServiceResult result = calendarService.removeCalendar(calendarVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("캘린더가 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("캘린더 삭제를 실패했습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		
		return "redirect:/schedule/scheduleList";
	}
	
	/**
	 * 조직조 목록 조회
	 * @param memberVO
	 * @param deptName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/calendar/deptMemberList.do/{deptName}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String departMemberList(MemberVO memberVO
			, @PathVariable("deptName") String deptName
			, Model model) {
		DeptVO deptVO = new DeptVO();
		deptVO.setDeptName(deptName);
		List<MemberVO> deptMemberList = calendarService.selectDeptMemberList(memberVO);
		model.addAttribute("deptMemberList", deptMemberList);
		model.addAttribute("deptVO", deptVO);
		
		return "jsonView";
	}
	
	/**
	 * 공유캘린더 폼
	 * @return
	 */
	@RequestMapping("/calendar/sharedCalendarForm.do")
	public String sharedCalendarForm(DeptVO deptVO, MemberVO memberVO, Model model) {
		List<DeptVO> departmentList = calendarService.selectDepartmentList(deptVO);
		model.addAttribute("departmentList", departmentList);
		
		List<MemberVO> deptMemberList = calendarService.selectDeptMemberList(memberVO);
		model.addAttribute("deptMemberList", deptMemberList);
		
		return "schedule/others/sharedCalendar";
	}
	
	/**
	 * 공유 캘린더 일정 등록
	 * @param calendarVO
	 * @param redirectAttributes
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping("/calendar/sharedCalendarInsert.do")
	public String sharedCalendarInsert(
			@Validated(InsertGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
			, RedirectAttributes redirectAttributes
			, Errors errors
			, Model model) {
		String goPage = null;
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = calendarService.sharedCalendarInsert(calendarVO);
			
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("공유 캘린더가 추가되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("캘린더 추가 실패하였습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			}
		} else {
			goPage = "schedule/others/calendarForm";
		}
		return goPage;
	}
	
	/**
	 * 공유 캘린더 수정 폼
	 * @param calendarVO
	 * @param calNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/calendar/sharedCalendarUpdateForm.do/{calNo}")
	public String updateSharedCalendarForm(
			CalendarVO calendarVO, DeptVO deptVO, MemberVO memberVO,
			@PathVariable("calNo") int calNo,
			Model model) {
		addCommandAttribute(model);
		calendarVO.setCalNo(calNo);
		
		List<DeptVO> departmentList = calendarService.selectDepartmentList(deptVO);
		model.addAttribute("departmentList", departmentList);
		
		List<MemberVO> deptMemberList = calendarService.selectDeptMemberList(memberVO);
		model.addAttribute("deptMemberList", deptMemberList);
		
		List<CalendarVO> optionList = calendarService.calendarMemberList(calendarVO);
		if(!optionList.isEmpty()) {
			CalendarVO option = optionList.get(0);
			model.addAttribute("option", option);
		}
		
		calendarVO = calendarService.selectCalendar(calendarVO.getCalNo());
		model.addAttribute("calendarVO", calendarVO);
		
		return "schedule/others/sharedCalendar";
	}
	
	/**
	 * 공유 캘린더 수정
	 * @param calendarVO
	 * @param redirectAttributes
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping("/calendar/sharedCalendarUpdate.do")
	public String sharedCalendarUpdate(
			@Validated(UpdateGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
			, RedirectAttributes redirectAttributes
			, Errors errors
			, Model model) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;
		
		boolean valid = !errors.hasErrors();
		
		if(valid) {
			ServiceResult result = calendarService.shareCalednarUpdate(calendarVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("공유 캘린더가 수정되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			default:
				message = NotyMessageVO.builder("공유 캘린더 수정 실패하였습니다.").type(NotyType.error).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/schedule/scheduleList";
				break;
			}
		} else {
			goPage = "schedule/others/calendarForm";
		}
		return goPage;
	}
	
	/**
	 * 공유 캘린더 삭제
	 * @param calendarVO
	 * @param calNo
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/schedule/sharedCalendarDelete.do/{calNo}")
	public String sharedCalendarDelete(
		@Validated(DeleteGroup.class) @ModelAttribute("calendarVO") CalendarVO calendarVO
		, @PathVariable("calNo") int calNo
		, RedirectAttributes redirectAttributes) {
	
		calendarVO.setCalNo(calNo);
		calendarVO = calendarService.selectCalendar(calNo);
		
		NotyMessageVO message = null;
		
		ServiceResult result = calendarService.removeSharedCalendar(calendarVO);
		
		switch(result) {
		case OK:
			message = NotyMessageVO.builder("공유 캘린더가 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("공유 캘린더 삭제실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/schedule/scheduleList";
	}
	
	/**
	 * 캘린더 사용여부 확인
	 * @author 서대철
	 * @since 2021. 3. 11.
	 * @param calendarVO
	 * @param calNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/schdule/sharedCalendarCount/{calNo}")
	@ResponseBody
	public Map<String, Object> scheduleInCalendarCount(CalendarVO calendarVO
			, @PathVariable("calNo") int calNo
			, Model model){
		
		NotyMessageVO message = null;
		int cnt = 0;
		
		calendarVO.setCalNo(calNo);
		
		ServiceResult result = calendarService.scheduleInCalendarCount(calendarVO);
		
		switch (result) {
		case OK:
			cnt = 1;
			message = NotyMessageVO.builder("캘린더가 사용중입니다."
					+ "캘린더 내 일정을 삭제하시기바랍니다.").type(NotyType.error).build();
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
