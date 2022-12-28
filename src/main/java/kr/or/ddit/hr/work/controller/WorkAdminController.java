package kr.or.ddit.hr.work.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.service.WorkAdminService;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

@Controller
public class WorkAdminController {

	@Inject
	MemberService memService;

	@Inject
	WorkAdminService workService;

	@RequestMapping("/admin/abenteesimManage")
	public String abenteesimManage(@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model) {

		// 회원 목록 조회 //
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 5);

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = memService.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MemberVO> memberList = memService.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
		// 회원 목록 조회 끝 //

		return "hrAdmin/abenteesimManage";
	}

	@RequestMapping("/admin/workScheduleCalendar/{memId}")
	public String workScheduleCalendar(
			@RequestParam(value = "page", required = false, defaultValue = "1") int currentPage,
			@ModelAttribute("searchVO") SearchVO searchVO, Model model,
			@PathVariable(value = "memId", required = true) String memId) throws JsonProcessingException {

		// 회원 목록 조회 //
		PagingVO<MemberVO> pagingVO = new PagingVO<>(5, 5);

		// 목록 조회와 카운트 조회시 동일 검색 조건 사용.
		pagingVO.setSearchVO(searchVO);

		int totalRecord = memService.retrieveMemberCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		pagingVO.setCurrentPage(currentPage);

		List<MemberVO> memberList = memService.retrieveMemberList(pagingVO);
		pagingVO.setDataList(memberList);

		model.addAttribute("pagingVO", pagingVO);
		// 회원 목록 조회 끝 //

		List<WorkTimeVO> workTime = workService.retrieveWeekWorkTime(memId);
		List<AttendanceVO> attendance = workService.retrieveWorkSchedule(memId);
		List<VacaRecVO> vacaRec = workService.retrieveVacation(memId);
		List<MonthWorkTimeVO> monthWorkTime = workService.retrieveMonthWorkTime(memId);
		ObjectMapper mapper = new ObjectMapper();
		String attendanceList = mapper.writeValueAsString(attendance);
		String vacaRecList = mapper.writeValueAsString(vacaRec);
		String workTimeList = mapper.writeValueAsString(workTime);
		String monthWorkTimeList = mapper.writeValueAsString(monthWorkTime);
		model.addAttribute("attendanceList", attendanceList);
		model.addAttribute("vacaRecList", vacaRecList);
		model.addAttribute("workTimeList", workTimeList);
		model.addAttribute("monthWorkTimeList", monthWorkTimeList);

		return "hrAdmin/abenteesimManage";
	}
}
