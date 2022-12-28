package kr.or.ddit.schedule.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.schedule.dao.ScheduleDAO;
import kr.or.ddit.schedule.vo.ScheduleVO;

/**
 * @author 서대철
 * @since 2021. 2. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Service
public class ScheduleServicecImpl implements ScheduleService {
	
	@Inject
	private ScheduleDAO scheduleDAO;
	
	@Override
	public ServiceResult scheduleInsert(ScheduleVO scheduleVO) {
		ServiceResult result = null;
		
		int cnt = scheduleDAO.insertSchedule(scheduleVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<ScheduleVO> retrieveScheduleList(ScheduleVO scheduleVO) {
		return scheduleDAO.selectScheduleList(scheduleVO);
	}

	@Override
	public ScheduleVO selectSchedule(int schdlNo) {
		
		ScheduleVO schduleVO = scheduleDAO.selectSchedule(schdlNo);
		if(schduleVO == null) throw new CustomException(schdlNo + "일정이 없습니다.");
		
		return schduleVO;
	}

	@Override
	public ServiceResult removeSchdule(ScheduleVO scheduleVO) {
		ServiceResult result = null;
		
		int cnt = scheduleDAO.deleteSchedule(scheduleVO.getSchdlNo());
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	@Override
	public ServiceResult scheduleUpdate(ScheduleVO scheduleVO) {
		ServiceResult result = null;
		
		int cnt = scheduleDAO.updateSchedule(scheduleVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	
}
