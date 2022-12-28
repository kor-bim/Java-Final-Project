package kr.or.ddit.hr.vacation.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.vacation.dao.VacationWriteDAO;
import kr.or.ddit.hr.vacation.vo.VacaRecVO;
import kr.or.ddit.hr.vacation.vo.VacaStatusVO;

/**
 * @author 서대철
 * @since 2021. 2. 24.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 24.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Service
public class VacationWriteServiceImpl implements VacationWriteService {
	
	@Inject
	private VacationWriteDAO vacationWriteDAO;

	@Override
	public ServiceResult insertVacation(VacaRecVO vacaRecVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = vacationWriteDAO.insertVacation(vacaRecVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public List<VacaRecVO> selectVacaRecList(VacaStatusVO vacaStatusVO) {
		return vacationWriteDAO.selectVacaRecList(vacaStatusVO);
	}

	
}
