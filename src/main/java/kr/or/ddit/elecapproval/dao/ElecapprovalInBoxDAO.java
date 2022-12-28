package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.vo.InBoxVO;

/**
 * 
 * 전자결재 문서함 DB에 접근하기 위한 repository
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
@Repository
public interface ElecapprovalInBoxDAO {

	/**
	 * 전자결재 전체 문서함의 페이징처리를 위한 메소드
	 * 
	 * @param pagingVO
	 * @return 페이징 갯수
	 */
	int selectInBoxAllListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 전자결재 전체 문서함의 전체 리스트 가져오기
	 * 
	 * @param pagingVO
	 * @return 문서함의 전체리스트
	 */
	List<InBoxVO> selectInBoxAllList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 기안 카테고리 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectInBoxDraftListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 기안 카테고리 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> selectInBoxDraftList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 결재 카테고리 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectInBoxApprovalListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 기안 카테고리 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> selectInBoxApprovalList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 수신 카테고리 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectInBoxReceptionListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 수신 카테고리 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> selectInBoxReceptionList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 회람/참조 카테고리 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectInBoxPassAlongListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 회람/참조 카테고리 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> selectInBoxPassAlongList(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 반려 카테고리 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return
	 */
	int selectInBoxReturnListCount(PagingVO<InBoxVO> pagingVO);

	/**
	 * 문서함 반려 카테고리 리스트
	 * 
	 * @param pagingVO
	 * @return
	 */
	List<InBoxVO> selectInBoxReturnList(PagingVO<InBoxVO> pagingVO);

}
