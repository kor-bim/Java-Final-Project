package kr.or.ddit.mail.dao;

import org.springframework.stereotype.Repository;

import kr.or.ddit.mail.vo.MailAttatchVO;
import kr.or.ddit.mail.vo.MailVO;

@Repository
public interface MailAttatchDAO {

	/**
	 * 메일 첨부파일 등록
	 * 
	 * @param mailVO
	 * @return
	 */
	int insertAttaches(MailVO mailVO);

	/**
	 * 메일 첨부파일 조회
	 * 
	 * @param maNo
	 * @return 첨부파일 조회된 MailAttatchVO
	 */
	MailAttatchVO selectAttach(int maNo);

}
