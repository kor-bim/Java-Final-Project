package kr.or.ddit.FileBox.service;

import java.util.List;

import kr.or.ddit.FileBox.vo.FileBoxVO;
import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 서대철
 * @since 2021. 3. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 3. 2.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface FileBoxService {

	/**
	 * 개인 파일 보관함 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param pagingVO
	 * @return
	 */
	List<FileBoxVO> selectFileBoxList(PagingVO<FileBoxVO> pagingVO);

	/**
	 * 페이지 목록 수
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param pagingVO
	 * @return
	 */
	int retrieveFileCount(PagingVO<FileBoxVO> pagingVO);
	/**
	 * 파일 업로드
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult createFileUpload(FileBoxVO fileBoxVO);

	/**
	 * 삭제된 문서 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param pagingVO
	 * @return
	 */
	List<FileBoxVO> selectDeleteFileBoxList(PagingVO<FileBoxVO> pagingVO);

	/**
	 * 삭제 파일 목록 수
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param pagingVO
	 * @return
	 */
	int retrieveDeleteFileCount(PagingVO<FileBoxVO> pagingVO);

	/**
	 * 체크된 파일 삭제
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult deleteFileBox(FileBoxVO fileBoxVO);

	/**
	 * 체크된 파일 백업
	 * @author 서대철
	 * @since 2021. 3. 2.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult backUpFileBox(FileBoxVO fileBoxVO);
	/**
	 * 파일 다운로드
	 * @param fileNo
	 * @return
	 */
	FileBoxVO filedownload(int fileNo);

	/**
	 * 폴더 생성
	 * @author 서대철
	 * @since 2021. 3. 3.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult createFolder(FileBoxVO fileBoxVO);

	/**
	 * 폴더 클릭 시 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 3.
	 * @param fileBoxVO
	 * @return
	 */
	List<FileBoxVO> folderList(FileBoxVO fileBoxVO);

	/**
	 * 폴더 목록 조회
	 * @author 서대철
	 * @since 2021. 3. 3.
	 * @param fileBoxVO
	 * @return
	 */
	List<FileBoxVO> selectFileFolderList(FileBoxVO fileBoxVO);

	/**
	 * 파일 완전 삭제 처리
	 * @author 서대철
	 * @since 2021. 3. 4.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult realDeleteFile(FileBoxVO fileBoxVO);

	/**
	 * 폴더 내 파일 존재 여부 확인
	 * @author 서대철
	 * @since 2021. 3. 4.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult fileInFolderCount(FileBoxVO fileBoxVO);

	/**
	 * 폴더명 수정
	 * @author 서대철
	 * @since 2021. 3. 5.
	 * @param fileBoxVO
	 * @return
	 */
	ServiceResult folderNameUpdate(FileBoxVO fileBoxVO);
	
	/**
	 * 파일 사이즈 정보 조회
	 * @author 길영주
	 * @since 2021. 3. 5.
	 * @return
	 */
	FileBoxVO selectTotalSize(FileBoxVO fileBoxVO);

}
