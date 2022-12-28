package kr.or.ddit.hr.vacation.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

public interface VacaTypeService {
	/**
	 * 휴가 종류 리스트 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 8.
	 * @return
	 */
	public List<VacaTypeVO> selectVacaTypeList();
	
	/**
	 * 휴가 종류 생성 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 9.
	 * @param vacaTypeVO
	 * @return
	 */
	public ServiceResult insertVacaType(VacaTypeVO vacaTypeVO);
	
	
	/** 
	 * 휴가 종류 삭제
	 * @author 이운주
	 * @since 2021. 2. 9.
	 * @param vacaTypeVO
	 * @return
	 */
	public ServiceResult deleteVacaType(VacaTypeVO vacaTypeVO);
	
	
}
