package kr.or.ddit.elecapproval.service;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.dao.ApprovalDocCommonDAO;
import kr.or.ddit.elecapproval.dao.ApprovalDocWriteDAO;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

@Service
public class ApprovalDocWriteServiceImpl implements ApprovalDocWriteService {
	
	@Inject
	public ApprovalDocWriteDAO docWriteDAO;
	@Inject
	public ApprovalDocCommonDAO docComnDAO;
	
	@Transactional
	@Override
	public ServiceResult createApprovalDocument(ApprovalDocVO approvalDocVO) {
		// alNo 가 없으면 - 자주 쓰는 결재선을 사용하지 않았거나, 내용을 변경한 것 
		// alNo 가 있으면 - 자주 쓰는 결재선 사용한 것 = 결재문서만 인서트 
	
		ServiceResult result = ServiceResult.FAILED;
		
		try {
			Integer lineNo = approvalDocVO.getAlNo();
			int cnt = 0 ;
			
			// 새로운 결재선
			if(lineNo == null) {  
				cnt = processApprovalLine(approvalDocVO); // 결재라인 생성
				if(cnt <= 0) { // 생성 실패
					return result; // FAILED
				}
			}
			
			cnt = docWriteDAO.insertApprovalDocument(approvalDocVO);
			if(cnt > 0) { // 결재문서 등록에 성공
				// 기안자 결재처리 > 나머지 결재자 대기처리
				cnt = draftApproval(approvalDocVO);
				if(cnt > 0) {
					result = ServiceResult.OK;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
//			return result; 
		}
		return result;
	}

	/**
	 * 결재라인 등록 절차 
	 * @author 이운주
	 * @since 2021. 2. 20.
	 * @param approvalDocVO
	 * @return
	 */
	private int processApprovalLine(ApprovalDocVO approvalDocVO) {
		// 결재라인 등록
		int cnt = docWriteDAO.insertLine(approvalDocVO);
		if(cnt > 0){ // 결재라인 등록 성공
			cnt = docWriteDAO.insertLineDetail(approvalDocVO); // 결재라인 상세 등록
		}
		return cnt;
	}

	/** 
	 * 결재 테이블에 결재자 전체 셋팅 
	 * @param approvalDocVO
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 24.
	 */
	private int draftApproval(ApprovalDocVO approvalDocVO) {
		
		int cnt = 0;
		cnt = docWriteDAO.insertApprovalList(approvalDocVO); 
		System.out.println();
		return cnt;
	}
}
