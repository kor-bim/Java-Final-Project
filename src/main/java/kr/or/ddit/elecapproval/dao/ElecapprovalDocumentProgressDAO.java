package kr.or.ddit.elecapproval.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.elecapproval.vo.DocumentProgressVO;

/**
 * <pre>
 * 
 * </pre>
 * @author 길영주
 * @since ${date}
 * @version 1.0
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일        수정자       수정내용
 * --------     --------    ----------------------
 * ${date}      길영주       최초작성
 * Copyright (c) ${year} by DDIT All right reserved
 * </pre>
 */ 
@Repository
public interface ElecapprovalDocumentProgressDAO {
	
	/**
	 * 진행 중인 문서 전체 리스트  페이지 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectApprovalAllListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 전체 리스트 
	 * @param pagingVO
	 * @return
	 */
	public List<DocumentProgressVO> selectApprovalAllList(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 대기 리스트  페이지 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectApprovalAwaitListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 대기 리스트  
	 * @param pagingVO
	 * @return
	 */
	public List<DocumentProgressVO> selectApprovalAwaitList(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 진행 리스트  페이지 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectApprovalProgressListCount(PagingVO<DocumentProgressVO> pagingVO);

	/**
	 * 진행 중인 문서 진행 리스트  
	 * @param pagingVO
	 * @return
	 */
	public List<DocumentProgressVO> selectApprovalProgressList(PagingVO<DocumentProgressVO> pagingVO);
	
	/**
	 * 진행 중인 문서 확인 리스트 카운트
	 * @param pagingVO
	 * @return
	 */
	public int selectApprovalConfirmListCount(PagingVO<DocumentProgressVO> pagingVO);
	
	/**
	 * 진행 중인 문서 확인 리스트  
	 * @param pagingVO
	 * @return
	 */
	public List<DocumentProgressVO> selectApprovalConfirmList(PagingVO<DocumentProgressVO> pagingVO);
}
