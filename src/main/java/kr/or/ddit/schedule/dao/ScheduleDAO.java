package kr.or.ddit.schedule.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

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

@Repository
public interface ScheduleDAO {

	/**
	 * 일정 추가
	 * @param scheduleVO
	 * @return
	 */
	int insertSchedule(ScheduleVO scheduleVO);

	/**
	 * 일정 목록 조회
	 * @param scheduleVO
	 * @return
	 */
	List<ScheduleVO> selectScheduleList(ScheduleVO scheduleVO);

	/**
	 * 일정 상세조회
	 * @param schdlNo
	 * @return
	 */
	ScheduleVO selectSchedule(int schdlNo);

	/**
	 * 일정 삭제
	 * @param schdlNo
	 * @return
	 */
	int deleteSchedule(Integer schdlNo);

	/**
	 * 일정 수정
	 * @param scheduleVO
	 * @return
	 */
	int updateSchedule(ScheduleVO scheduleVO);

}
