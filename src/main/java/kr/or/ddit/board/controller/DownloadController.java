package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.DepartMentBoardService;
import kr.or.ddit.board.service.NoticeService;
import kr.or.ddit.board.vo.DepartMentBoardAttatchVO;
import kr.or.ddit.board.vo.NBAttatchVO;
import kr.or.ddit.commons.enumpkg.Browser;

@Controller
public class DownloadController {

	@Inject
	private NoticeService nservice;

	@Inject
	private DepartMentBoardService dservice;

	@Value("#{appInfo.NBoardFiles}")
	private File nbSaveFolder;

	@Value("#{appInfo.departMentBoardFiles}")
	private File dbSaveFolder;

	@RequestMapping("/noticeBoard/download/{nbaNo}")
	public void noticeBoardDownload(@PathVariable("nbaNo") int nbaNo,
			@RequestHeader(value = "User-Agent", required = false) String agent, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		Browser browser = Browser.getBrowserConstant(agent);
		NBAttatchVO nbAttatch = nservice.download(nbaNo);
		String savename = nbAttatch.getNbaRealname();
		String filename = nbAttatch.getNbaName();
		if (Browser.TRIDENT.equals(browser)) {
			filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
		} else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\"" + filename + "\"");
		File saveFile = new File(nbSaveFolder, savename);
		resp.setContentType("application/octet-stream");
		try (OutputStream os = resp.getOutputStream();) {
			FileUtils.copyFile(saveFile, os);
		}
	}

	@RequestMapping("/departMentBoard/download/{dbaNo}")
	public void departMentBoardDownload(@PathVariable("dbaNo") int dbaNo,
			@RequestHeader(value = "User-Agent", required = false) String agent, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		Browser browser = Browser.getBrowserConstant(agent);
		DepartMentBoardAttatchVO dbAttatch = dservice.download(dbaNo);
		String filename = dbAttatch.getDbaRealname();
		String savename = dbAttatch.getDbaName();
		if (Browser.TRIDENT.equals(browser)) {
			filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
		} else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\"" + filename + "\"");
		File saveFile = new File(dbSaveFolder, savename);
		resp.setContentType("application/octet-stream");
		try (OutputStream os = resp.getOutputStream();) {
			FileUtils.copyFile(saveFile, os);
		}
	}
}
