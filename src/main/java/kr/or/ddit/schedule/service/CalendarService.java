package kr.or.ddit.schedule.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.schedule.vo.CalendarVO;

/**
 * @author 서대철
 * @since 2021. 2. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 6.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface CalendarService {

	/**
	 * 내 캘린더 목록 조회
	 * @param calendarVO 
	 * @return
	 */
	List<CalendarVO> selectCalendarList(CalendarVO calendarVO);

	/**
	 * 캘린더 상세조회
	 * @param calNo
	 * @return
	 */
	CalendarVO selectCalendar(int calNo);

	/**
	 * 내 캘린더 추가
	 * @param calendarVO
	 * @return
	 */
	ServiceResult calendarInsert(CalendarVO calendarVO);

	/**
	 * 내 캘린더 수정
	 * @param calendarVO
	 * @return
	 */
	ServiceResult calendarUpdate(CalendarVO calendarVO);

	/**
	 * 내 캘린더 삭제
	 * @param calendarVO
	 * @return
	 */
	ServiceResult removeCalendar(CalendarVO calendarVO);

	/**
	 * 공유 캘린더 조회
	 * @param calendarVO
	 * @return
	 */
	List<CalendarVO> selectShareCalendarList(CalendarVO calendarVO);

	/**
	 * 조직도 목록 조회
	 * @param departmentVO
	 * @return
	 */
	List<DeptVO> selectDepartmentList(DeptVO deptVO);

	/**
	 * 부서별 사원 목록 조회
	 * @param authMember
	 * @return
	 */
	List<MemberVO> selectDeptMemberList(MemberVO memberVO);

	/**
	 * 공유 캘린더 추가
	 * @param calendarVO
	 * @return
	 */
	ServiceResult sharedCalendarInsert(CalendarVO calendarVO);

	/**
	 * 캘린더 분류 목록 조회
	 * @param calendarVO
	 * @return
	 */
	List<CalendarVO> selectTypeList(CalendarVO calendarVO);

	/**
	 * 공유 캘린더 삭제
	 * @param calendarVO
	 * @return
	 */
	ServiceResult removeSharedCalendar(CalendarVO calendarVO);

	/**
	 * 구성원 초대 목록 조회
	 * @param calendarVO
	 * @return
	 */
	List<CalendarVO> calendarMemberList(CalendarVO calendarVO);

	/**
	 * 공유 캘린더 수정
	 * @param calendarVO
	 * @return
	 */
	ServiceResult shareCalednarUpdate(CalendarVO calendarVO);

	/**
	 * 캘린더 내 일정 확인
	 * @param calendarVO
	 * @return
	 */
	ServiceResult scheduleInCalendarCount(CalendarVO calendarVO);

}
