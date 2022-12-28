package kr.or.ddit.mail.controller;

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

import kr.or.ddit.commons.enumpkg.Browser;
import kr.or.ddit.mail.service.MailService;
import kr.or.ddit.mail.vo.MailAttatchVO;

@Controller
public class MailDownloadController {

	@Inject
	private MailService service;

	@Value("#{appInfo.mailFiles}")
	private File saveFolder;

	@RequestMapping("/mail/download/{maNo}")
	public void mailDownload(@PathVariable("maNo") int maNo,
			@RequestHeader(value = "User-Agent", required = false) String agent, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		Browser browser = Browser.getBrowserConstant(agent);
		MailAttatchVO mailAttatchVO = service.download(maNo);
		String filename = mailAttatchVO.getMaRealname();
		String savename = mailAttatchVO.getMaName();
		if (Browser.TRIDENT.equals(browser)) {
			filename = URLEncoder.encode(filename, "UTF-8").replace("+", "%20");
		} else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\"" + filename + "\"");
		File saveFile = new File(saveFolder, savename);
		resp.setContentType("application/octet-stream");
		try (OutputStream os = resp.getOutputStream();) {
			FileUtils.copyFile(saveFile, os);
		}
	}
}
