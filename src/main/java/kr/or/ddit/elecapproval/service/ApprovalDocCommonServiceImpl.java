package kr.or.ddit.elecapproval.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalDocCommonDAO;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.ApprovalVO;
import kr.or.ddit.elecapproval.vo.DocumentsVO;

/**
 * 전자결재 관련 공통으로 사용하는 비즈니스 로직 
 * ex. 문서양식 분류 코드 조회 
 * 
 * @author 이운주
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Service
public class ApprovalDocCommonServiceImpl implements ApprovalDocCommonService {
	@Inject
	public ApprovalDocCommonDAO docComnDAO;

	@Override
	public List<DocumentsVO> selectDocTypeList(DocumentsVO documentsVO) {
		return docComnDAO.selectDocTypeList(documentsVO); 
	}

	@Override
	public List<DocumentsVO> selectDocFormList(DocumentsVO documentsVO) {
		return docComnDAO.selectDocFormList(documentsVO);
	}

	@Override
	public DocumentsVO selectDocForm(DocumentsVO documentsVO) {
		return docComnDAO.selectDocForm(documentsVO);
	}
	
	@Override
	public List<ApprovalLineVO> selectApprovalLineList(ApprovalLineVO approvalLineVO) {
		return docComnDAO.selectApprovalLineList(approvalLineVO);
	}

	@Override
	public ApprovalLineVO selectApprovalLine(ApprovalLineVO approvalLineVO) {
		return docComnDAO.selectApprovalLine(approvalLineVO);
	}
	
	// 결재라인 작성에 필요한 detailJSON 을 모델에 담아줌
	@Override
	public void getApprovalLineByAdNo(String adNo, Model model) {
		ApprovalDocVO approvalDocVO = new ApprovalDocVO();
		approvalDocVO.setAdNo(adNo);
		//=========================================================
		ApprovalLineVO approvalLineVO = new ApprovalLineVO();
		approvalLineVO = docComnDAO.getApprovalLine(approvalDocVO);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String detailJSON = mapper.writeValueAsString(approvalLineVO);
			model.addAttribute("detailJSON", detailJSON);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		//==========================================================
	}
	@Override
	public ApprovalLineVO getApprovalLine(ApprovalDocVO approvalDocVO) {
		return docComnDAO.getApprovalLine(approvalDocVO);
	}

	@Transactional
	@Override
	public ServiceResult approvalProcess(ApprovalVO approvalVO) {
		// 승인 -결재자OK      : 다음결재자 없으면 문서 결재완료 (DS_CODE, AD_FINISH_DATE)
		// 협의 -결재자REQUEST : 결재문서 상태 = DISCUSS (진행중인 문서에도 조회되야함)
		// 반려 -결재자RETURN  : 결재문서 상태 = RETURN
		// 전결 -결재자ALLOK   : 최종 결재자까지 전부 ALLOK(참조자제외), 문서 결재 완료
		
		// 참조자 OK 
		
		ServiceResult result = ServiceResult.FAILED;
		// 결재처리
		int cnt = docComnDAO.signApprovalDoc(approvalVO);
		
		if(cnt > 0) {
			cnt = updateApprovalDocStatus(approvalVO);
			if(cnt > 0) {
				result = ServiceResult.OK;
			}
		}
		return result;
	}

	private int updateApprovalDocStatus(ApprovalVO approvalVO) {
		int cnt = 0;
		
		// 결재 종류
		String approvalKind = approvalVO.getAprvlTypeCode();
		// 결재 결과에 따라 문서 상태 변경하기 위해 선언함
		ApprovalDocVO approvalDocVO = new ApprovalDocVO();
		approvalDocVO.setAdNo(approvalVO.getAdNo());
		String dsCode = null;
		String nextMemId = null;
		
		if("OK".equals(approvalKind)) {
			// 다음 결재자 id 조회 = 없으면 내가 마지막 결재자 = 문서 결재완료 
			nextMemId = docComnDAO.selectNextMemId(approvalVO);
			if(StringUtils.isBlank(nextMemId)) {
				// 다음 결재자 없으면, aprvl_doc => 결재완료 
				dsCode = "COMPLETE";
			}else {
				// 다음 결재자 없으면 문서 상태 수정할 것 없음. 그냥 넘김.
				cnt = 1;
			}
		}else if("REQUEST".equals(approvalKind)) {
			dsCode = "DISCUSS";
		}else if("RETURN".equals(approvalKind)) {
			dsCode = "RETURN";
		}else if("ALLOK".equals(approvalKind)) {
			// 나머지 결재자 전부 ALLOK처리  (나머지 결재자들만. 전결한 사람은 위에서 이미 처리됨)
			docComnDAO.approvalAllComplete(approvalVO);
			dsCode = "COMPLETE";
		}else if("CHECK".equals(approvalKind)) {
			cnt = 1;
		}
		
		// 결재 문서 상태 변경
		if(StringUtils.isNotBlank(dsCode)) {
			approvalDocVO.setDsCode(dsCode);
			cnt = docComnDAO.updateApprovalDocStatus(approvalDocVO);  
		}
		return cnt;
	}
}
