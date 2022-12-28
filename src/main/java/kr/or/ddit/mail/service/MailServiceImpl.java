package kr.or.ddit.mail.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.CustomException;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.mail.dao.MailAttatchDAO;
import kr.or.ddit.mail.dao.MailDAO;
import kr.or.ddit.mail.vo.MailAttatchVO;
import kr.or.ddit.mail.vo.MailReceiverVO;
import kr.or.ddit.mail.vo.MailVO;

@Service
public class MailServiceImpl implements MailService {

	@Inject
	private WebApplicationContext container;

	@Inject
	MailDAO dao;

	@Inject
	MailAttatchDAO attDao;

	@Value("#{appInfo.mailFiles}")
	private String mailFilePath;

	private File saveFolder;

	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(mailFilePath));
		if (saveFolder != null && !saveFolder.exists())
			saveFolder.mkdirs();
	}

	@Override
	public int retrieveInboxCount(PagingVO<MailVO> pagingVO) {
		return dao.selectInboxCount(pagingVO);
	}

	@Override
	public List<MailVO> retrieveInboxList(PagingVO<MailVO> pagingVO) {
		return dao.selectInboxList(pagingVO);
	}

	@Override
	public int retrieveSentBoxCount(PagingVO<MailVO> pagingVO) {
		return dao.selectSentBoxCount(pagingVO);
	}

	@Override
	public List<MailVO> retrieveSentBoxList(PagingVO<MailVO> pagingVO) {
		return dao.selectSentBoxList(pagingVO);
	}

	@Override
	public int retrieveStarredCount(PagingVO<MailVO> pagingVO) {
		return dao.selectStarredCount(pagingVO);
	}

	@Override
	public List<MailVO> retrieveStarredList(PagingVO<MailVO> pagingVO) {
		return dao.selectStarredList(pagingVO);
	}

	@Override
	public int retrieveImportantCount(PagingVO<MailVO> pagingVO) {
		return dao.selectImportantCount(pagingVO);
	}

	@Override
	public List<MailVO> retrieveImportantList(PagingVO<MailVO> pagingVO) {
		return dao.selectImportantList(pagingVO);
	}

	@Override
	public int retrieveTrashCount(PagingVO<MailVO> pagingVO) {
		return dao.selectTrashCount(pagingVO);
	}

	@Override
	public List<MailVO> retrieveTrashList(PagingVO<MailVO> pagingVO) {
		return dao.selectTrashList(pagingVO);
	}

	@Override
	public MailVO retrieveMail(MailVO mailVO) {
		MailVO mail = dao.selectMail(mailVO);
		if (mail == null)
			throw new CustomException(mailVO.getMailNo() + "번 글이 없음.");
		return mail;
	}

	@Override
	public ServiceResult modifyBookmark(MailVO mailVO, MemberVO authMember) {
		ServiceResult result = ServiceResult.FAILED;
		String memId = authMember.getMemId();
		mailVO.setSenderId(memId);
		MailVO selectedMail = dao.selectMail(mailVO);
		MailReceiverVO mrVO = new MailReceiverVO();
		mrVO.setReceiverId(memId);
		mrVO.setMailNo(mailVO.getMailNo());
		int cnt = 0;
		if (selectedMail.getSenderId().equals(memId)) {
			if (selectedMail.getMailImport().equals("Y")) {
				mailVO.setMailImport("N");

			} else if (selectedMail.getMailImport().equals("N")) {
				mailVO.setMailImport("Y");
			} else {
				return result;
			}
			cnt = dao.updateSenderBookmark(mailVO);
		}
		for (MailReceiverVO receiver : selectedMail.getMailReceiverList()) {
			if (memId.equals(receiver.getReceiverId())) {
				if (receiver.getRecImport().equals("Y")) {
					mrVO.setRecImport("N");
				} else if (receiver.getRecImport().equals("N")) {
					mrVO.setRecImport("Y");

				} else {
					return result;
				}
				cnt = dao.updateReceiverBookmark(mrVO);
			} else {
				return result;
			}
		}

		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public ServiceResult modifyStarred(MailVO mailVO, MemberVO authMember) {
		ServiceResult result = ServiceResult.FAILED;
		String memId = authMember.getMemId();
		mailVO.setSenderId(memId);
		MailVO selectedMail = dao.selectMail(mailVO);
		MailReceiverVO mrVO = new MailReceiverVO();
		mrVO.setReceiverId(memId);
		mrVO.setMailNo(mailVO.getMailNo());
		int cnt = 0;
		if (selectedMail.getSenderId().equals(memId)) {
			if (selectedMail.getMailStar().equals("Y")) {
				mailVO.setMailStar("N");

			} else if (selectedMail.getMailStar().equals("N")) {
				mailVO.setMailStar("Y");
			} else {
				return result;
			}
			cnt = dao.updateSenderStarred(mailVO);
		}
		for (MailReceiverVO receiver : selectedMail.getMailReceiverList()) {
			if (memId.equals(receiver.getReceiverId())) {
				if (receiver.getRecStar().equals("Y")) {
					mrVO.setRecStar("N");
				} else if (receiver.getRecStar().equals("N")) {
					mrVO.setRecStar("Y");

				} else {
					return result;
				}
				cnt = dao.updateReceiverStarred(mrVO);
			} else {
				return result;
			}
		}

		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Transactional
	@Override
	public ServiceResult createMail(MailVO mailVO) {
		int cnt = dao.insertMail(mailVO);
		if (cnt > 0) {
			cnt += dao.insertReceiver(mailVO);
			cnt += processAttatches(mailVO);
		}
		ServiceResult result = null;
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private int processAttatches(MailVO mailVO) {
		List<MailAttatchVO> attatchList = mailVO.getAttatchList();
		int cnt = 0;
		if (attatchList != null && !attatchList.isEmpty()) {
			cnt += attDao.insertAttaches(mailVO);
			try {
				for (MailAttatchVO attatch : attatchList) {
					attatch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}

	@Override
	public MailAttatchVO download(int maNo) {
		MailAttatchVO attatch = attDao.selectAttach(maNo);
		if (attatch == null)
			throw new CustomException(maNo + " 파일이 없음.");
		return attatch;
	}

	@Override
	public ServiceResult moveToTrash(int mailNo, String memId) {
		int cnt = 0;
		ServiceResult result = null;
		MailVO mailVO = new MailVO();
		mailVO.setMailNo(mailNo);
		mailVO.setSenderId(memId);
		MailReceiverVO receiverVO = new MailReceiverVO();
		receiverVO.setMailNo(mailNo);
		receiverVO.setReceiverId(memId);

		MailVO saveMailVO = dao.selectMail(mailVO);
		if (memId.equals(saveMailVO.getSenderId())) {
			cnt = dao.updateSenderTrash(mailVO);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		for (MailReceiverVO receiver : saveMailVO.getMailReceiverList()) {
			if (memId.equals(receiver.getReceiverId())) {
				cnt = dao.updateReceiverTrash(receiverVO);
				if (cnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult modifyRead(int mailNo, String memId) {
		int cnt = 0;
		ServiceResult result = null;
		MailVO mailVO = new MailVO();
		mailVO.setMailNo(mailNo);
		mailVO.setSenderId(memId);
		MailReceiverVO receiverVO = new MailReceiverVO();
		receiverVO.setMailNo(mailNo);
		receiverVO.setReceiverId(memId);

		MailVO saveMailVO = dao.selectMail(mailVO);
		if (memId.equals(saveMailVO.getSenderId())) {
			cnt = dao.updateSenderRead(mailVO);
			if (cnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		}
		for (MailReceiverVO receiver : saveMailVO.getMailReceiverList()) {
			if (memId.equals(receiver.getReceiverId())) {
				cnt = dao.updateReceiverRead(receiverVO);
				if (cnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult removeMail(MailVO mailVO, String memId) {
		int cnt = 0;
		ServiceResult result = null;

		for (int mailNo : mailVO.getDeleteMailNo()) {
			mailVO.setMailNo(mailNo);
			MailVO saveMailVO = dao.selectMail(mailVO);
			for (MailReceiverVO receiverVO : saveMailVO.getMailReceiverList()) {
				if (memId.equals(receiverVO.getReceiverId())) {
					receiverVO.setReceiverId(memId);
					receiverVO.setMailNo(mailNo);
					cnt = dao.deleteReceiverMail(receiverVO);
					if (cnt > 0) {
						result = ServiceResult.OK;
					} else {
						result = ServiceResult.FAILED;
					}
				}
			}
			if (memId.equals(saveMailVO.getSenderId())) {
				mailVO.setMailNo(mailNo);
				mailVO.setSenderId(memId);
				cnt = dao.deleteSenderMail(mailVO);
				if (cnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult restoreMail(MailVO mailVO, String memId) {
		int cnt = 0;
		ServiceResult result = null;

		for (int mailNo : mailVO.getRestoreMailNo()) {
			mailVO.setMailNo(mailNo);
			MailVO saveMailVO = dao.selectMail(mailVO);
			for (MailReceiverVO receiverVO : saveMailVO.getMailReceiverList()) {
				if (memId.equals(receiverVO.getReceiverId())) {
					receiverVO.setReceiverId(memId);
					receiverVO.setMailNo(mailNo);
					cnt = dao.restoreReceiverMail(receiverVO);
					if (cnt > 0) {
						result = ServiceResult.OK;
					} else {
						result = ServiceResult.FAILED;
					}
				}
			}
			if (memId.equals(saveMailVO.getSenderId())) {
				mailVO.setMailNo(mailNo);
				mailVO.setSenderId(memId);
				cnt = dao.restoreSenderMail(mailVO);
				if (cnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

	@Override
	public ServiceResult mailsToTrash(MailVO mailVO, String memId) {
		int cnt = 0;
		ServiceResult result = null;

		for (int mailNo : mailVO.getTrashMailNo()) {
			mailVO.setMailNo(mailNo);
			MailVO saveMailVO = dao.selectMail(mailVO);
			for (MailReceiverVO receiverVO : saveMailVO.getMailReceiverList()) {
				if (memId.equals(receiverVO.getReceiverId())) {
					receiverVO.setReceiverId(memId);
					receiverVO.setMailNo(mailNo);
					cnt = dao.receiverMailsToTrash(receiverVO);
					if (cnt > 0) {
						result = ServiceResult.OK;
					} else {
						result = ServiceResult.FAILED;
					}
				}
			}
			if (memId.equals(saveMailVO.getSenderId())) {
				mailVO.setMailNo(mailNo);
				mailVO.setSenderId(memId);
				cnt = dao.senderMailsToTrash(mailVO);
				if (cnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		}
		return result;
	}

}
