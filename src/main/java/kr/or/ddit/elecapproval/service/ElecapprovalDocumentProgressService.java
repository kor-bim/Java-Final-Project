package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.vo.DocumentProgressVO;

/**
 * 
 * 전자결재 진행중인 문서 비즈니스 로직
 * 
 * @author 길영주
 * @since 2021. 2. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 17.     길영주           최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
public interface ElecapprovalDocumentProgressService {

	/**
	 * 진행중인 문서 전체 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveApprovalAllListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행중인 문서 전체 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<DocumentProgressVO> retrieveApprovalAllList(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행중인 문서 대기 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveApprovalAwaitListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행중인 문서 대기 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<DocumentProgressVO> retrieveApprovalAwaitList(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 진행 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveApprovalProgressListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 진행 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<DocumentProgressVO> retrieveApprovalProgressList(PagingVO<DocumentProgressVO> pagingVO);
	/**
	 * 진행 중인 문서 확인 리스트 페이지 카운트
	 * @param pagingVO
	 * @return
	 */
	int retrieveApprovalConfirmListCount(PagingVO<DocumentProgressVO> pagingVO);
	/**
	 * 진행 중인 문서  확인 리스트 페이지
	 * @param pagingVO
	 * @return
	 */
	List<DocumentProgressVO> retrieveApprovalConfirmList(PagingVO<DocumentProgressVO> pagingVO);

}