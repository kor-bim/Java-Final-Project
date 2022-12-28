package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;

/**
 * 전자결재 사용자 설정 비즈니스 로직
 * 
 * @author 윤한빈
 * @since 2021. 2. 19.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 19.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
public interface ElecApprovalUserService {

	/**
	 * 자주찾는 결재선리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 카운트
	 */
	int retrieveAprvlLineListCount(PagingVO<ApprovalLineVO> pagingVO);

	/**
	 * 자주찾는 결재선 리스트
	 * 
	 * @param pagingVO
	 * @return 자주찾는 결재선 리스트
	 */
	List<ApprovalLineVO> retrieveAprvlLineList(PagingVO<ApprovalLineVO> pagingVO);


	/**
	 * 자주찾는 결재선 추가
	 * 
	 * @param approvalLineVO
	 * @return
	 */
	ServiceResult createApprovalLine(ApprovalLineVO approvalLineVO);

	/**
	 * 자주찾는 결재선 조회
	 * 
	 * @param aprvlVO
	 * @return
	 */
	ApprovalLineVO retrieveAprvlLine(ApprovalLineVO aprvlVO);

	/**
	 * @param aprvlVO
	 */
	ServiceResult removeAprvlLine(ApprovalLineVO aprvlVO);


}
