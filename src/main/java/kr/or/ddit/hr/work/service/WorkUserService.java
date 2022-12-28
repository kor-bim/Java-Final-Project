package kr.or.ddit.hr.work.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.work.vo.AttendanceVO;
import kr.or.ddit.hr.work.vo.MonthWorkTimeVO;
import kr.or.ddit.hr.work.vo.WorkTimeVO;

/**
 * 근태관련 비즈니스 인터페이스
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
public interface WorkUserService {

	/**
	 * 출근 등록
	 * 
	 * @param attendanceVO
	 * @return 성공 OK, 실패 FAILED
	 */
	ServiceResult createStartWork(AttendanceVO attendanceVO);

	/**
	 * 퇴근 처리
	 * 
	 * @param attendanceVO
	 * @return 성공 OK, 실패 FAILED
	 */
	ServiceResult updateEndWork(AttendanceVO attendanceVO);

	/**
	 * 근무상태 변경
	 * 
	 * @param attendanceVO
	 * @return 성공 OK, 실패 FAILED
	 */
	ServiceResult updateWorkState(AttendanceVO attendanceVO);

	/**
	 * 오늘 출퇴근 정보 가져오기
	 * 
	 * @param attendanceVO
	 * @return
	 */
	AttendanceVO retrieveTodayWorkInfo(AttendanceVO attendanceVO);

	/**
	 * 로그인 회원의 모든 출퇴근 정보 가져오기
	 * @param attendanceVO
	 * @return 로그인 회원의 모든 출퇴근 정보
	 */
	List<AttendanceVO> retrieveWorkSchedule(AttendanceVO attendanceVO);

	/**
	 * 로그인 회원의 모든 휴가 정보 가져오기
	 * 
	 * @param attendanceVO
	 * @return 로그인 회원의 모든 휴가 정보
	 */
	List<VacaRecVO> retrieveVacation(AttendanceVO attendanceVO);
	
	/**
	 * 로그인 회원의 근무 시간 정보 가져오기
	 * @param authMember
	 * @return
	 */
	List<WorkTimeVO> retrieveWeekWorkTime(MemberVO authMember);
	
	/**
	 * 선택된 회원의 이번 월간 근무시간 통계 조회
	 * 
	 * @param authMember
	 * @return 선택된 회원의 이번 월간  근무시간 통계 정보
	 */
	List<MonthWorkTimeVO> retrieveMonthWorkTime(MemberVO authMember);
	/**
	 * 
	 * 선택된 회원의 현재 출근 및 퇴근 시간 조회
	 * @param authMember
	 * @return
	 */
	AttendanceVO retrieveTodayWorkTime(MemberVO authMember);
}
