package kr.or.ddit.elecapproval.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.vo.InBoxVO;

/**
 * 
 * 전자결재 문서함 비즈니스 로직
 * 
 * @author 윤한빈
 * @since 2021. 2. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 17.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
public interface ElecapprovalInBoxService {

	/**
	 * 문서함 전체카테고리의 목록의 페이지 처리를 위한 비즈니스 로직
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxAllListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 
	 * 문서함 전체 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxAllList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 기안 카테고리의 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxDraftListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 기안 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxDraftList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 결재 카테고리의 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxApprovalListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 결재 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxApprovalList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 수신 카테고리의 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxReceptionListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 
	 * 문서함 수신 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxReceptionList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 회람/참조 카테고리의 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxPassAlongListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 회람/참조 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxPassAlongList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 반려 카테고리의 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int retrieveInBoxReturnListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 반려 카테고리의 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> retrieveInBoxReturnList(PagingVO<InBoxVO> pagingVO);

}
