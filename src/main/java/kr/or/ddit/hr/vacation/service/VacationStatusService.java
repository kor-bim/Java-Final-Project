package kr.or.ddit.hr.vacation.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;

public interface VacationStatusService {

	/**
	 * 전직원 또는 검색조건에 따른 직원 휴가 생성/사용내역 리스트 조회 
	 * 
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 11.
	 */
	public List<VacaStatusVO> vacaStatusList(VacaStatusVO vacaStatusVO);

	/** 
	 * 직원 한명 연차 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 17.
	 * @param vacaStatusVO
	 * @return
	 */
	public VacaStatusVO selectVacaStat(VacaStatusVO vacaStatusVO);
	
	/**
	 * 직원 한명 정기/포상 업데이트
	 *  
	 * @author 이운주
	 * @since 2021. 2. 17.
	 * @param vacaStatusVO
	 * @return
	 */
	public ServiceResult updateVacaStat(VacaStatusVO vacaStatusVO);

	/**
	 * 휴가 신청 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 2. 27.
	 * @param vacaRecVO
	 * @return
	 */
	public List<VacaRecVO> selectVacationList(VacaRecVO vacaRecVO);

	/**
	 * 휴가 생성 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 1.
	 * @param vacaStatusVO
	 * @return
	 */
	public List<VacaStatusVO> selectStatusList(VacaStatusVO vacaStatusVO);

	/**
	 * 모든 휴가 신청 내역 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 1.
	 * @param vacaRecVO
	 * @return
	 */
	public List<VacaRecVO> selectVacaAllList(VacaRecVO vacaRecVO);

	/**
	 * 휴가 생성 
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 11.
	 */
//	public ServiceResult insertVaca();
	
	/**
	 * 포상 휴가 생성 
	 * 
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 11.
	 */
//	public ServiceResult insertRewardVaca();
	
}
