package kr.or.ddit.hr.work.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

/**
 * 관리자의 근태관련 Persistance 인터페이스
 * 
 * @author 윤한빈
 * @since 2021. 2. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 25.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Repository
public interface WorkAdminDAO {

	/**
	 * 선택된 회원의 근태 정보 조회
	 * 
	 * @param memId
	 * @return 선택된 회원의 근태정보
	 */
	List<AttendanceVO> selectWorkSchedule(String memId);

	/**
	 * 선택된 회원의 휴가 정보 조회
	 * 
	 * @param memId
	 * @return 선택된 회원의 휴가 정보
	 */
	List<VacaRecVO> selectVacation(String memId);

	/**
	 * 선택된 회원의 이번 주간 근무시간 통계 조회
	 * 
	 * @param memId
	 * @return 선택된 회원의 이번 주간 근무시간 통계 정보
	 */
	List<WorkTimeVO> selectWeekWorkTime(String memId);

	/**
	 * 선택된 회원의 이번 월간 근무시간 통계 조회
	 * 
	 * @param memId
	 * @return 선택된 회원의 이번 월간 근무시간 통계 정보
	 */
	List<MonthWorkTimeVO> selectMonthWorkTime(String memId);

}
