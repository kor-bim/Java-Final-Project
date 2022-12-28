package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalFarmboxDAO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * @author 서대철
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Service
public class ApprovalFarmboxListImpl implements ApprovalFarmboxListService {
	
	@Inject
	private ApprovalFarmboxDAO approvalFarmboxDAO;

	@Override
	public List<DocumentsVO> selectApproalFarmboxList(DocumentsVO documentsVO) {
		return approvalFarmboxDAO.selectApprvalFarmboxList(documentsVO);
	}

	@Override
	public ServiceResult insertFarmbox(DocumentsVO documentsVO) {
		ServiceResult result = null;
		
		int cnt = approvalFarmboxDAO.insertFarmbox(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public DocumentsVO selectApproalFarm(int dfNo) {
		DocumentsVO farm = approvalFarmboxDAO.selectFarm(dfNo);
		if(farm == null) throw new CustomException(dfNo + "번의 양식이 없습니다.");
		return farm;
	}

	@Override
	public List<DocumentsVO> selectApprovalDocTypeList(DocumentsVO documentsVO) {
		return approvalFarmboxDAO.selectApprovalDocTypeList(documentsVO);
	}

	@Override
	public ServiceResult insertDocType(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.insertDocType(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult updateDocType(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.updateDocType(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteDocType(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.deleteDocType(documentsVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult UpdateFarmbox(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.updateFarmbox(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult deleteFarmbox(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.deleteFarmbox(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult approvalFarmCheck(DocumentsVO documentsVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = approvalFarmboxDAO.approvalFarmCheck(documentsVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}
}
