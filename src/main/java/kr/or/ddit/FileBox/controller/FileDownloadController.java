package kr.or.ddit.FileBox.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.FileBox.service.FileBoxService;
import kr.or.ddit.FileBox.vo.FileBoxVO;
import kr.or.ddit.base.controller.BaseController;
import kr.or.ddit.commons.enumpkg.Browser;
@Controller
public class FileDownloadController extends BaseController {
	@Inject
	private FileBoxService service;

	@Value("#{appInfo.fileBox}")
	private File saveFolder;

	@RequestMapping("/fileBox/download/{fileNo}")
	public void fileDownload(@PathVariable("fileNo") int fileNo,
			@RequestHeader(value = "User-Agent", required = false) String agent, HttpServletRequest request,
			HttpServletResponse resp) throws IOException {
		Browser browser = Browser.getBrowserConstant(agent);
		FileBoxVO fileBoxVO = service.filedownload(fileNo);
		String filename = fileBoxVO.getFileRealName();
		String savename = fileBoxVO.getFileName();
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
	@RequestMapping("/fileBox/download")
	public void fileDownload(FileBoxVO fileBoxVO,
	@RequestHeader(value = "User-Agent", required = false) String agent, HttpServletRequest request,
	HttpServletResponse resp) throws IOException {
		
		int bufferSize = 1024 * 2;
		String ouputName = "download";
		            
		ZipOutputStream zos = null;
		            
		try {
		                
		    if (request.getHeader("User-Agent").indexOf("MSIE 5.5") > -1) {
		        resp.setHeader("Content-Disposition", "filename=" + ouputName + ".zip" + ";");
		    } else {
		        resp.setHeader("Content-Disposition", "attachment; filename=" + ouputName + ".zip" + ";");
		    }
		    resp.setHeader("Content-Transfer-Encoding", "binary");
		    
		                
		    OutputStream os = resp.getOutputStream();
		    zos = new ZipOutputStream(os); // ZipOutputStream
		    zos.setLevel(8); // 압축 레벨 - 최대 압축률은 9, 디폴트 8
		    BufferedInputStream bis = null;
		                
		    
		    int[] fileNos = fileBoxVO.getFileNos();
		    String[] fileNames = new String[fileNos.length];
		    String[] saveNames = new String[fileNos.length];
		    
		    
    		for(int i = 0 ; i < fileNos.length ; i++) {
    			FileBoxVO fileBox = service.filedownload(fileNos[i]);
    			String savename = fileBox.getFileName();
    			String fileName = fileBox.getFileRealName();
    			fileNames[i] = fileName;
    			saveNames[i] = savename;
    		}
    		int i = 0;
    		for(String save : saveNames) {
    			File saveFile = new File(saveFolder, save);
    			bis = new BufferedInputStream(new FileInputStream(saveFile));
    			ZipEntry zentry = new ZipEntry(fileNames[i]);
    			zentry.setTime(saveFile.lastModified());
    			zos.putNextEntry(zentry);
    			
    			byte[] buffer = new byte[bufferSize];
    			int cnt = 0;
    			while ((cnt = bis.read(buffer, 0, bufferSize)) != -1) {
    				zos.write(buffer, 0, cnt);
    			}
    			zos.closeEntry();
    			i++;
    		}
    		
		    zos.close();
		    bis.close();
		                
		} catch(Exception e){
		    e.printStackTrace();
		}
		
	}
}
