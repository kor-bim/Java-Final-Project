package kr.or.ddit.mail.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.mail.vo.MailReceiverVO;
import kr.or.ddit.mail.vo.MailVO;

/**
 * 메일 관련 Persistance 로직
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
@Repository
public interface MailDAO {

	/**
	 * 받은 메일함 리스트 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int selectInboxCount(PagingVO<MailVO> pagingVO);

	/**
	 * 받은 메일함 리스트
	 * 
	 * @param pagingVO
	 * @return 받은 메일함 리스트
	 */
	List<MailVO> selectInboxList(PagingVO<MailVO> pagingVO);

	/**
	 * 보낸 메일함 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */

	int selectSentBoxCount(PagingVO<MailVO> pagingVO);

	/**
	 * 보낸 메일함 리스트
	 * 
	 * @param pagingVO
	 * @return 보낸 메일함 리스트
	 */
	List<MailVO> selectSentBoxList(PagingVO<MailVO> pagingVO);

	/**
	 * 별표 표시된 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int selectStarredCount(PagingVO<MailVO> pagingVO);

	/**
	 * 별표 표시된 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 별표 표시된 메일 리스트
	 */
	List<MailVO> selectStarredList(PagingVO<MailVO> pagingVO);

	/**
	 * 중요 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int selectImportantCount(PagingVO<MailVO> pagingVO);

	/**
	 * 중요 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 중요 메일 리스트
	 */
	List<MailVO> selectImportantList(PagingVO<MailVO> pagingVO);

	/**
	 * 삭제된 메일 리스트 페이지 카운트
	 * 
	 * @param pagingVO
	 * @return 페이지 갯수
	 */
	int selectTrashCount(PagingVO<MailVO> pagingVO);

	/**
	 * 삭제된 메일 리스트
	 * 
	 * @param pagingVO
	 * @return 삭제된 메일 리스트
	 */
	List<MailVO> selectTrashList(PagingVO<MailVO> pagingVO);

	/**
	 * 메일 상세 조회
	 * 
	 * @param mailVO
	 * @return 단건 조회된 MailVO
	 */
	MailVO selectMail(MailVO mailVO);

	/**
	 * 수신자 메일 북마크
	 * 
	 * @param mailVO
	 * @return
	 */
	int updateSenderBookmark(MailVO mailVO);

	/**
	 * @param mailReceiverVO
	 * @return
	 */
	int updateReceiverBookmark(MailReceiverVO mailReceiverVO);

	/**
	 * 수신자 메일 별표
	 * 
	 * @param mailVO
	 * @return
	 */
	int updateReceiverStarred(MailReceiverVO mailReceiverVO);

	/**
	 * 발신자 메일 별표
	 * 
	 * @param mailVO
	 * @return
	 */
	int updateSenderStarred(MailVO mailVO);

	/**
	 * 메일 등록
	 * 
	 * @param mailVO
	 * @return
	 */
	int insertMail(MailVO mailVO);

	/**
	 * 메일 등록시 수신자 등록
	 * 
	 * @param mailVO
	 * @return
	 */
	int insertReceiver(MailVO mailVO);

	/**
	 * 휴지통으로 이동, 수신자가 로그인된 아이디인 경우
	 * 
	 * @return
	 */
	int updateSenderTrash(MailVO mailVO);

	/**
	 * 휴지통으로 이동, 발신자가 로그인된 아이디인 경우
	 * 
	 * @return
	 */
	int updateReceiverTrash(MailReceiverVO receiverVO);

	/**
	 * 읽음 여부 업데이트, 수신자가 로그인된 아이디인 경우
	 * 
	 * @param mailVO
	 * @return
	 */
	int updateSenderRead(MailVO mailVO);

	/**
	 * 읽음 여부 업데이트, 발신자가 로그인된 아이디인 경우
	 * 
	 * @param receiverVO
	 * @return
	 */
	int updateReceiverRead(MailReceiverVO receiverVO);

	/**
	 * 메일 완전삭제, 수신자가 로그인된 아이디인 경우
	 * 
	 * @param receiverVO
	 * @return
	 */
	int deleteReceiverMail(MailReceiverVO receiverVO);

	/**
	 * 메일 완전삭제, 발신자가 로그인된 아이디인 경우
	 * 
	 * @param mailVO
	 * @return
	 */
	int deleteSenderMail(MailVO mailVO);

	/**
	 * 메일 복구, 발신자가 로그인된 아이디인 경우
	 * 
	 * @param receiverVO
	 * @return
	 */
	int restoreReceiverMail(MailReceiverVO receiverVO);

	/**
	 * 메일 복구, 수신자가 로그인된 아이디인 경우
	 * 
	 * @param mailVO
	 * @return
	 */
	int restoreSenderMail(MailVO mailVO);

	/**
	 * 다수의 메일 휴지통으로 이동, 발신자가 로그인된 아이디인 경우
	 * 
	 * @param receiverVO
	 * @return
	 */
	int receiverMailsToTrash(MailReceiverVO receiverVO);

	/**
	 * 다수의 메일 휴지통으로 이동, 수신자가 로그인된 아이디인 경우
	 * 
	 * @param mailVO
	 * @return
	 */
	int senderMailsToTrash(MailVO mailVO);

}
