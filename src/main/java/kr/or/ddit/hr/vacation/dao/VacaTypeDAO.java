package kr.or.ddit.hr.vacation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

@Repository
public interface VacaTypeDAO {
	// 휴가 종류 리스트 조회
	public List<VacaTypeVO> selectVacaTypeList();
	
	// 휴가 종류 생성
	public int insertVacaType(VacaTypeVO vacaTypeVO);
	
	// 휴가 종류 삭제
	public int deleteVacaType(VacaTypeVO vacaTypeVO);

}
