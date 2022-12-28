package kr.or.ddit.hr.work.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.dao.WorkAdminDAO;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

@Service
public class WorkAdminServiceImpl implements WorkAdminService {

	@Inject
	WorkAdminDAO dao;

	@Override
	public List<AttendanceVO> retrieveWorkSchedule(String memId) {
		return dao.selectWorkSchedule(memId);
	}

	@Override
	public List<VacaRecVO> retrieveVacation(String memId) {
		return dao.selectVacation(memId);
	}

	@Override
	public List<WorkTimeVO> retrieveWeekWorkTime(String memId) {
		return dao.selectWeekWorkTime(memId);
	}

	@Override
	public List<MonthWorkTimeVO> retrieveMonthWorkTime(String memId) {
		return dao.selectMonthWorkTime(memId);
	}

}
