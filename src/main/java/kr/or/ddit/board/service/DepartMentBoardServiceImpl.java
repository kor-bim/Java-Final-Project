package kr.or.ddit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.CustomException;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.board.dao.DepartMentBoardAttatchDAO;
import kr.or.ddit.board.dao.DepartmentBoardDAO;
import kr.or.ddit.board.vo.DepartMentBoardAttatchVO;
import kr.or.ddit.board.vo.DepartMentBoardVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

@Service
public class DepartMentBoardServiceImpl implements DepartMentBoardService {

	@Inject
	private WebApplicationContext container;

	@Inject
	private DepartMentBoardAttatchDAO attDAO;

	@Inject
	private DepartmentBoardDAO boardDAO;

	@Value("#{appInfo.departMentBoardFiles}")
	private String boarFilePath;

	private File saveFolder;

	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(boarFilePath));
		if (saveFolder != null && !saveFolder.exists())
			saveFolder.mkdirs();
	}

	@Transactional
	@Override
	public ServiceResult createBoard(DepartMentBoardVO board) {
		int cnt = boardDAO.insertBoard(board);
		if (cnt > 0) {
			cnt += processAttatches(board);
		}
		ServiceResult result = null;
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private int processAttatches(DepartMentBoardVO board) {
		List<DepartMentBoardAttatchVO> attatchList = board.getAttatchList();
		int cnt = 0;
		if (attatchList != null && !attatchList.isEmpty()) {
			cnt += attDAO.insertAttaches(board);
			try {
				for (DepartMentBoardAttatchVO attatch : attatchList) {
					attatch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cnt;
	}

	@Override
	public int retrieveBoardCount(PagingVO<DepartMentBoardVO> paging) {
		return boardDAO.selectBoardCount(paging);
	}

	@Override
	public List<DepartMentBoardVO> retrieveBoardList(PagingVO<DepartMentBoardVO> paging) {
		return boardDAO.selectBoardList(paging);
	}

	@Override
	public DepartMentBoardVO retrieveBoard(DepartMentBoardVO board) {
		DepartMentBoardVO dbBoard = boardDAO.selectBoard(board.getDbNo());
		if (dbBoard == null)
			throw new CustomException(board.getDbNo() + "번 글이 없음.");
		boardDAO.incrementHit(board.getDbNo());
		return dbBoard;
	}

	@Transactional
	@Override
	public ServiceResult modifyBoard(DepartMentBoardVO board) {
		DepartMentBoardVO savedBoard = boardDAO.selectBoard(board.getDbNo());
		if (savedBoard == null)
			throw new CustomException(board.getDbNo() + "번 글이 없음.");

		ServiceResult result = ServiceResult.FAILED;
		int cnt = boardDAO.updateBoard(board);
		if (cnt > 0) {
			// 신규 등록 첨부 파일
			processAttatches(board);
			processDeleteAttatch(board);
			result = ServiceResult.OK;
		}
		return result;
	}

	private int processDeleteAttatch(DepartMentBoardVO board) {
		int cnt = 0;
		int[] delAttNos = board.getDelAttNos();
		if (delAttNos != null && delAttNos.length > 0) {
			String[] saveNames = new String[delAttNos.length];
			for (int i = 0; i < delAttNos.length; i++) {
				saveNames[i] = attDAO.selectAttach(delAttNos[i]).getDbaName();
			}
			cnt = attDAO.deleteAttatches(board);
			if (cnt == saveNames.length) {
				for (String savename : saveNames) {
					FileUtils.deleteQuietly(new File(saveFolder, savename));
				}
			}
		}
		return cnt;
	}

	@Transactional
	@Override
	public ServiceResult removeBoard(DepartMentBoardVO board) {
		// 게시글 존재 여부 확인
		DepartMentBoardVO savedBoard = boardDAO.selectBoard(board.getDbNo());
		if (savedBoard == null)
			throw new CustomException(board.getDbNo() + "번 글이 없음.");
		ServiceResult result = ServiceResult.FAILED;
		// 1. 첨부파일 메타 삭제
		List<DepartMentBoardAttatchVO> attatchList = savedBoard.getAttatchList();
		String[] saveNames = null;
		int cnt = 0;
		if (attatchList != null && attatchList.size() > 0) {
			saveNames = new String[attatchList.size()];
			for (int i = 0; i < saveNames.length; i++) {
				saveNames[i] = attatchList.get(i).getDbaName();
			}
			cnt = attDAO.deleteAttatches(board);
		}
		// 2. 게시글 삭제
		cnt += boardDAO.deleteBoard(board.getDbNo());
		// 3. 첨부파일 2진 데이터 삭제
		if (saveNames != null) {
			for (String savename : saveNames) {
				FileUtils.deleteQuietly(new File(saveFolder, savename));
			}
		}
		if (cnt > 0)
			result = ServiceResult.OK;
		return result;
	}

	@Override
	public DepartMentBoardAttatchVO download(int dbaNo) {
		DepartMentBoardAttatchVO attatch = attDAO.selectAttach(dbaNo);
		if (attatch == null)
			throw new CustomException(dbaNo + " 파일이 없음.");
		return attatch;
	}

}
