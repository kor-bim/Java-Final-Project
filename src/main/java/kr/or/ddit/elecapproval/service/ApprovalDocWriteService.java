package kr.or.ddit.elecapproval.service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

/**
 * 결재문서 작성 관련 비즈니스 로직 
 * 
 * @author 이운주
 * @since 2021. 2. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 18.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface ApprovalDocWriteService {
	
	/**
	 * 결재문서 등록 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 20.
	 * @param approvalDocVO
	 * @return
	 */
	public ServiceResult createApprovalDocument(ApprovalDocVO approvalDocVO);
	

}
