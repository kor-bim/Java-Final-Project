package kr.or.ddit.hr.work.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

/**
 * 근태관련 Persistance 인터페이스
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
@Repository
public interface WorkUserDAO {

	/**
	 * 출근 등록
	 * 
	 * @param attendanceVO
	 * @return
	 */
	int insertStartWork(AttendanceVO attendanceVO);

	/**
	 * 퇴근 처리
	 * 
	 * @param attendanceVO
	 * @return
	 */
	int upateEndWork(AttendanceVO attendanceVO);

	/**
	 * 근태 조회
	 * 
	 * @param attendanceVO
	 * @return
	 */
	AttendanceVO selectEndWork(AttendanceVO attendanceVO);

	/**
	 * 근무상태 변경
	 * 
	 * @param attendanceVO
	 * @return
	 */
	int updateWorkState(AttendanceVO attendanceVO);

	/**
	 * 출퇴근 정보 조회
	 * 
	 * @param attendanceVO
	 * @return
	 */
	AttendanceVO selectTodayWorkInfo(AttendanceVO attendanceVO);

	/**
	 * 로그인된 회원의 모든 출퇴근 정보 조회 
	 * 
	 * @param attendanceVO
	 * @return 로그인된 회원의 모든 출퇴근 정보
	 */
	List<AttendanceVO> selectWorkSchedule(AttendanceVO attendanceVO);

	/**
	 * 로그인된 회원의 모든 휴가 정보 조회 
	 * 
	 * @param attendanceVO
	 * @return 로그인된 회원의 모든 휴가 정보
	 */
	List<VacaRecVO> selectVacation(AttendanceVO attendanceVO);
	
	/**
	 * 로그인 된 회원의 근무시간 정보 조회
	 * @param attendanceVO 
	 * @param authMember
	 * @return 로그인 된 회원의 근무시간 정보
	 */
	List<WorkTimeVO> selectWeekWorkTime(MemberVO authMember);
	/**
	 * 로그인 된 회원의 월별 근무일 수 정보 조회
	 * @param attendanceVO 
	 * @param authMember
	 * @return 로그인 된 회원의 근무시간 정보
	 */
	List<MonthWorkTimeVO> selectMonthWorkTime(MemberVO authMember);
	/**
	 * 로그인 된 회원의 오늘 출근 시간 및 퇴근시간 조회
	 * @param attendanceVO 
	 * @param authMember
	 * @return 로그인 된 회원의 근무시간 정보
	 */
	AttendanceVO selectTodayWorkTime(MemberVO authMember);

}
