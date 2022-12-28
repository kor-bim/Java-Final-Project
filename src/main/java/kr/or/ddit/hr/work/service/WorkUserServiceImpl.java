package kr.or.ddit.hr.work.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.dao.WorkUserDAO;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

/**
 * 근태관련 비즈니스 로직
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
@Service
public class WorkUserServiceImpl implements WorkUserService {

	@Inject
	WorkUserDAO dao;

	@Override
	public ServiceResult createStartWork(AttendanceVO attendanceVO) {
		ServiceResult result = null;
		AttendanceVO saveAttendanceVO = dao.selectEndWork(attendanceVO);
		if (saveAttendanceVO == null) {
			int cnt = dao.insertStartWork(attendanceVO);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} else {
			result = ServiceResult.DISABLE;
		}
		return result;
	}

	@Override
	public ServiceResult updateEndWork(AttendanceVO attendanceVO) {
		ServiceResult result = null;
		int cnt = dao.upateEndWork(attendanceVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult updateWorkState(AttendanceVO attendanceVO) {
		ServiceResult result = null;
		int cnt = dao.updateWorkState(attendanceVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	/**
	 * 오늘 출퇴근 정보 조회
	 */
	@Override
	public AttendanceVO retrieveTodayWorkInfo(AttendanceVO attendanceVO) {
		return dao.selectTodayWorkInfo(attendanceVO);
	}


	@Override
	public List<AttendanceVO> retrieveWorkSchedule(AttendanceVO attendanceVO) {
		return dao.selectWorkSchedule(attendanceVO);
	}

	@Override
	public List<VacaRecVO> retrieveVacation(AttendanceVO attendanceVO) {
		return dao.selectVacation(attendanceVO);
	}

	@Override
	public List<WorkTimeVO> retrieveWeekWorkTime(MemberVO authMember) {
		return dao.selectWeekWorkTime(authMember);
	}
	@Override
	public List<MonthWorkTimeVO> retrieveMonthWorkTime(MemberVO authMember) {
		return dao.selectMonthWorkTime(authMember);
	}

	@Override
	public AttendanceVO retrieveTodayWorkTime(MemberVO authMember) {
		return dao.selectTodayWorkTime(authMember);
	}


}
