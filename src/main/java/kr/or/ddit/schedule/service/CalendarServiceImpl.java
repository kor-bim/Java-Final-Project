package kr.or.ddit.schedule.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.schedule.dao.CalendarDAO;
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
@Service
public class CalendarServiceImpl implements CalendarService {

	@Inject
	private CalendarDAO calendarDAO;

	@Override
	public List<CalendarVO> selectCalendarList(CalendarVO calendarVO) {
		return calendarDAO.selectCalendarList(calendarVO);
	}

	@Override
	public List<CalendarVO> selectShareCalendarList(CalendarVO calendarVO) {
		return calendarDAO.selectShareCalendarList(calendarVO);
	}

	@Override
	public List<CalendarVO> selectTypeList(CalendarVO calendarVO) {
		return calendarDAO.selectTypeCalendar(calendarVO);
	}

	@Override
	public List<DeptVO> selectDepartmentList(DeptVO deptVO) {
		return calendarDAO.selectDepartmentList(deptVO);
	}

	@Override
	public List<MemberVO> selectDeptMemberList(MemberVO memberVO) {
		return calendarDAO.selectDeptMemberList(memberVO);
	}
	
	@Override
	public CalendarVO selectCalendar(int calNo) {
		CalendarVO calendarVO = calendarDAO.selectCalendar(calNo);
		return calendarVO;
	}

	@Override
	public ServiceResult calendarInsert(CalendarVO calendarVO) {
		ServiceResult result = null;
		
		int cnt = calendarDAO.insertCalendar(calendarVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult calendarUpdate(CalendarVO calendarVO) {
		ServiceResult result = null;
		
		int cnt = calendarDAO.updateCalendar(calendarVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult removeCalendar(CalendarVO calendarVO) {
		ServiceResult result = null;
		
		int cnt = calendarDAO.deleteCalendar(calendarVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}


	@Transactional
	@Override
	public ServiceResult sharedCalendarInsert(CalendarVO calendarVO) {
		int cnt = 0;
		ServiceResult result = null;
		cnt = calendarDAO.insertSharedCalendar(calendarVO);
		if(cnt > 0) {
			calendarVO.setCalNo(calendarVO.getCalNo());
			cnt = sharedCalendarMemberList(calendarVO);
			if(cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		return result;
	}

	private int sharedCalendarMemberList(CalendarVO calendarVO) {
		String[] memberList = calendarVO.getCalendarMember();
		int cnt = 0;
		if(memberList != null && memberList.length > 0) {
			cnt += calendarDAO.insertSharedMemberList(calendarVO);
		}
		return cnt;
	}
	
	@Override
	public ServiceResult removeSharedCalendar(CalendarVO calendarVO) {
		int cnt = 0;
		cnt = removeSharedCalendarList(calendarVO);
		ServiceResult result = null;
		if(cnt > 0) {
			cnt = calendarDAO.deleteSharedCalendar(calendarVO);
			if(cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		return result;
	}
	
	private int removeSharedCalendarList(CalendarVO calendarVO) {
		int cnt = 0;
		cnt += calendarDAO.deleteSharedCalendarMemberList(calendarVO);
		return cnt;
	}
	
	@Override
	public List<CalendarVO> calendarMemberList(CalendarVO calendarVO) {
		return calendarDAO.selectOptionMemberList(calendarVO);
	}

	@Override
	public ServiceResult shareCalednarUpdate(CalendarVO calendarVO) {
		ServiceResult result = null;
		int cnt = 0;
		cnt = calendarDAO.insertCalendarMember(calendarVO);
		cnt = calendarDAO.deleteCalendarMember(calendarVO);
		cnt = calendarDAO.updateCalendar(calendarVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult scheduleInCalendarCount(CalendarVO calendarVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = calendarDAO.scheduleInCalendarCount(calendarVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}
	
	
}
