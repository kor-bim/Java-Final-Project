package kr.or.ddit.hr.vacation.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.vacation.dao.VacaTypeDAO;
import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

@Service
public class VacaTypeServiceImpl implements VacaTypeService {
	@Inject
	private VacaTypeDAO vacaTypeDAO;

	@Override
	public List<VacaTypeVO> selectVacaTypeList() {
		List<VacaTypeVO> vacaTypeList = vacaTypeDAO.selectVacaTypeList();
		return vacaTypeList;
	}

	@Override
	public ServiceResult insertVacaType(VacaTypeVO vacaTypeVO) {
		ServiceResult result = ServiceResult.FAILED;

		try {
			List<VacaTypeVO> vacatypeList = vacaTypeVO.getVacatypeList();
			for (VacaTypeVO vacatype : vacatypeList) {
				if (!(StringUtils.isBlank(vacatype.getVtCode()) && StringUtils.isBlank(vacatype.getVtName()))) {
					vacaTypeDAO.insertVacaType(vacatype);
				}
			}
			result = ServiceResult.OK;

		} catch (Exception e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult deleteVacaType(VacaTypeVO vacaTypeVO) {
		ServiceResult result = null;
		int cnt = vacaTypeDAO.deleteVacaType(vacaTypeVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

}
