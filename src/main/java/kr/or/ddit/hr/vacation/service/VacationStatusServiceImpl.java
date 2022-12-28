package kr.or.ddit.hr.vacation.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.base.service.BaseService;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.vacation.dao.VacationStatusDAO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;
@Service
public class VacationStatusServiceImpl extends BaseService implements VacationStatusService {

	@Inject
	private VacationStatusDAO vacaStatDAO;
	
	@Override
	public List<VacaStatusVO> vacaStatusList(VacaStatusVO vacaStatusVO) {
		List<VacaStatusVO> vacaStatList = vacaStatDAO.selectTotalStatus(vacaStatusVO);
		return vacaStatList;
	}

	@Override
	public VacaStatusVO selectVacaStat(VacaStatusVO vacaStatusVO) {
		VacaStatusVO resultVO = vacaStatDAO.selectVacaStat(vacaStatusVO);
		
		return resultVO;
	}

	@Override
	public ServiceResult updateVacaStat(VacaStatusVO vacaStatusVO) {
		ServiceResult result = ServiceResult.OK;
		
		try {
			vacaStatDAO.updateVacaStat(vacaStatusVO);
		} catch (Exception e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<VacaRecVO> selectVacationList(VacaRecVO vacaRecVO) {
		List<VacaRecVO> selectRecList = vacaStatDAO.selectVacationList(vacaRecVO);
		return selectRecList;
	}

	@Override
	public List<VacaStatusVO> selectStatusList(VacaStatusVO vacaStatusVO) {
		List<VacaStatusVO> vacationStatusList = vacaStatDAO.selectStatusList(vacaStatusVO);
		return vacationStatusList;
	}

	@Override
	public List<VacaRecVO> selectVacaAllList(VacaRecVO vacaRecVO) {
		List<VacaRecVO> vacaAllList = vacaStatDAO.selectVacaAllList(vacaRecVO);
		return vacaAllList;
	}

}
