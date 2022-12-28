package kr.or.ddit.mail.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.mail.vo.MailAttatchVO;
import kr.or.ddit.mail.vo.MailVO;

/**
 * 메일 관련 비즈니스 로직
 * 
 * @author 윤한빈
 * @since 2021. 2. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 27.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
public interface MailService {

	/**
	 * 받은 메일함 리스트 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int retrieveInboxCount(PagingVO<MailVO> pagingVO);

	/**
	 * 받은 메일함 리스트
	 * 
	 * @param pagingVO
	 * @return 받은 메일함 리스트
	 */
	List<MailVO> retrieveInboxList(PagingVO<MailVO> pagingVO);

	/**
	 * 보낸 메일함 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int retrieveSentBoxCount(PagingVO<MailVO> pagingVO);

	/**
	 * 보낸 메일함 리스트
	 * 
	 * @param pagingVO
	 * @return 보낸 메일함 리스트
	 */
	List<MailVO> retrieveSentBoxList(PagingVO<MailVO> pagingVO);

	/**
	 * 별표 표시된 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int retrieveStarredCount(PagingVO<MailVO> pagingVO);

	/**
	 * 별표 표시된 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 별표 표시된 메일 리스트
	 */
	List<MailVO> retrieveStarredList(PagingVO<MailVO> pagingVO);

	/**
	 * 중요 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int retrieveImportantCount(PagingVO<MailVO> pagingVO);

	/**
	 * 중요 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 중요 메일 리스트
	 */
	List<MailVO> retrieveImportantList(PagingVO<MailVO> pagingVO);

	/**
	 * 삭제된 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int retrieveTrashCount(PagingVO<MailVO> pagingVO);

	/**
	 * 삭제된 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 삭제된 메일 리스트
	 */
	List<MailVO> retrieveTrashList(PagingVO<MailVO> pagingVO);

	/**
	 * 메일 상세 조회
	 * 
	 * @param mailVO
	 * @return 단건 조회된 MailVO
	 */
	MailVO retrieveMail(MailVO mailVO);

	/**
	 * 메일 북마크 체크 업데이트
	 * 
	 * @param mailVO
	 * @return
	 */
	ServiceResult modifyBookmark(MailVO mailVO, MemberVO authMember);

	/**
	 * 메일 별표 업데이트
	 * 
	 * @param mailVO
	 * @param authMember
	 * @return
	 */
	ServiceResult modifyStarred(MailVO mailVO, MemberVO authMember);

	/**
	 * 메일 등록
	 * 
	 * @param mailVO
	 * @return OK, FAIL
	 */
	ServiceResult createMail(MailVO mailVO);

	/**
	 * 메일 첨부파일 다운로드
	 * 
	 * @param maNo
	 * @return
	 */
	MailAttatchVO download(int maNo);

	/**
	 * 메일 휴지통으로 이동
	 * 
	 * @param mailNo
	 * @return
	 */
	ServiceResult moveToTrash(int mailNo, String memId);

	/**
	 * 읽음 여부시 업데이트
	 * 
	 * @param mailNo
	 * @param memId
	 */
	ServiceResult modifyRead(int mailNo, String memId);

	/**
	 * 메일 완전삭제
	 * 
	 * @param mailVO
	 * @return
	 */
	ServiceResult removeMail(MailVO mailVO, String memId);

	/**
	 * 메일 복구
	 * 
	 * @param mailVO
	 * @param memId
	 * @return
	 */
	ServiceResult restoreMail(MailVO mailVO, String memId);

	/**
	 * 선택한 메일을 휴지통으로 이동
	 * 
	 * @param mailVO
	 * @param memId
	 * @return
	 */
	ServiceResult mailsToTrash(MailVO mailVO, String memId);
}
