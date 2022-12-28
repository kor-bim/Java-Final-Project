package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.vo.ApprovalLineVO;
import kr.or.ddit.elecapproval.vo.DeputyVO;

/**
 * 전자결재 사용자설정을 위하 Persistance 인터페이스
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
@Repository
public interface ElecApprovalUserDAO {

	/**
	 * 자주찾는 결재선 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectAprvlLineListCount(PagingVO<ApprovalLineVO> pagingVO);

	/**
	 * 자주찾는 결재선 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<ApprovalLineVO> selectAprvlLineList(PagingVO<ApprovalLineVO> pagingVO);

	/**
	 * 대결자 비사용
	 * 
	 * @param deputyVO
	 * @return
	 */
	int notUseDeputy(DeputyVO deputyVO);

	/**
	 * 자주 찾는 결제선 등록
	 * 
	 * @param approvalLineVO
	 * @return
	 */
	int insertApprovalLine(ApprovalLineVO approvalLineVO);

	/**
	 * 자주 찾는 결재선 상세 등록
	 * 
	 * @param approvalLineVO
	 * @return
	 */
	int insertApprovalLineDetail(ApprovalLineVO approvalLineVO);

	/**
	 * 자주 찾는 결재선 삭제
	 * 
	 * @param aprvlVO
	 * @return
	 */
	int deleteApprovalLine(ApprovalLineVO aprvlVO);

}
