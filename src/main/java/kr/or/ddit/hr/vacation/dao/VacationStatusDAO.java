package kr.or.ddit.hr.vacation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;

@Repository
public interface VacationStatusDAO {
	
	public List<VacaStatusVO> selectTotalStatus(VacaStatusVO vacaStatusVO);

	public VacaStatusVO selectVacaStat(VacaStatusVO vacaStatusVO);
	
	public int updateVacaStat(VacaStatusVO vacaStatusVO);

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

}
