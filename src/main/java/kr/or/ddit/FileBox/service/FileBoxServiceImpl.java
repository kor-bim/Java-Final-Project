package kr.or.ddit.FileBox.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.CustomException;
import kr.or.ddit.FileBox.dao.FileBoxDAO;
import kr.or.ddit.FileBox.vo.FileBoxVO;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 서대철
 * @since 2021. 3. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 3. 2.        서대철       		    최초작성
 * 2021. 3. 2.        길영주       		    파일 업로드 추가
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Service
public class FileBoxServiceImpl implements FileBoxService {

	@Inject
	private WebApplicationContext container;

	@Inject
	private FileBoxDAO fileBoxDAO;

	@Value("#{appInfo.fileBox}")
	private String FileBoxPath;

	private File saveFolder;

	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(FileBoxPath));
		if (saveFolder != null && !saveFolder.exists())
			saveFolder.mkdirs();
	}

	@Override
	public int retrieveFileCount(PagingVO<FileBoxVO> pagingVO) {
		return fileBoxDAO.selectFileCount(pagingVO);
	}

	@Override
	public List<FileBoxVO> selectFileBoxList(PagingVO<FileBoxVO> pagingVO) {
		List<FileBoxVO> fileBoxList = fileBoxDAO.selectFileBoxList(pagingVO);
		return fileBoxList;
	}

	@Override
	public ServiceResult createFileUpload(FileBoxVO fileBoxVO) {
		int cnt = fileBoxDAO.insertFile(fileBoxVO);
		List<FileBoxVO> fileList = fileBoxVO.getFileList();
		if (cnt > 0) {
			try {
				for (FileBoxVO attatch : fileList) {
					attatch.saveTo(saveFolder);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		ServiceResult result = null;
		if (cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<FileBoxVO> selectDeleteFileBoxList(PagingVO<FileBoxVO> pagingVO) {
		List<FileBoxVO> deleteFileBoxList = fileBoxDAO.selectDeleteFileBoxList(pagingVO);
		return deleteFileBoxList;
	}

	@Override
	public int retrieveDeleteFileCount(PagingVO<FileBoxVO> pagingVO) {
		return fileBoxDAO.selectDeleteFileCount(pagingVO);
	}

	@Override
	public ServiceResult deleteFileBox(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;

		int cnt = fileBoxDAO.deleteFileBox(fileBoxVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}

		return result;
	}

	@Override
	public ServiceResult backUpFileBox(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;

		int cnt = fileBoxDAO.backUpFileBox(fileBoxVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}

		return result;
	}

	@Override
	public FileBoxVO filedownload(int fileNo) {
		FileBoxVO fileattatch = fileBoxDAO.selectAttach(fileNo);
		if (fileattatch == null)
			throw new CustomException(fileNo + " 파일이 없음.");
		return fileattatch;
	}

	@Override
	public ServiceResult createFolder(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;

		int cnt = fileBoxDAO.createFolder(fileBoxVO);
		if (cnt > 0) {
			result = ServiceResult.OK;
		}

		return result;
	}

	@Override
	public List<FileBoxVO> folderList(FileBoxVO fileBoxVO) {
		List<FileBoxVO> folderList = fileBoxDAO.folderList(fileBoxVO);
		return folderList;
	}

	@Override
	public List<FileBoxVO> selectFileFolderList(FileBoxVO fileBoxVO) {
		List<FileBoxVO> selectFolederList = fileBoxDAO.selectFolderList(fileBoxVO);
		return selectFolederList;
	}

	@Transactional
	@Override
	public ServiceResult realDeleteFile(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;
		String saveNames = null;
		int cnt = 0;
		// 파일 존재 여부 확인
		for (int fileNo : fileBoxVO.getDeleteFileNo()) {
			FileBoxVO fileAttatch = fileBoxDAO.selectAttach(fileNo);
			if (fileAttatch == null)
				throw new CustomException(fileBoxVO.getFileNo() + " 파일이 없음.");
			// 1. 첨부파일 메타 삭제
			if (fileAttatch != null) {
				saveNames = fileAttatch.getFileName();
				cnt += fileBoxDAO.deleteFile(fileNo);
			}
			// 파일 삭제
			if (saveNames != null) {
				FileUtils.deleteQuietly(new File(saveFolder, saveNames));
			}
		}
		if (cnt > 0) {
			result = ServiceResult.OK;
		}
		return result;
	}

	@Override
	public ServiceResult fileInFolderCount(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = fileBoxDAO.fileInFolederCount(fileBoxVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public ServiceResult folderNameUpdate(FileBoxVO fileBoxVO) {
		ServiceResult result = ServiceResult.FAILED;
		
		int cnt = fileBoxDAO.folderNameUpdate(fileBoxVO);
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		}
		
		return result;
	}

	@Override
	public FileBoxVO selectTotalSize(FileBoxVO fileBoxVO) {
		return fileBoxDAO.selectTotalSize(fileBoxVO);
	}
}