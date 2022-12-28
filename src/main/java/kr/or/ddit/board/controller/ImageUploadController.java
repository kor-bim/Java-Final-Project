package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {

	@Inject
	private WebApplicationContext container;

	@Value("#{appInfo.boardImages}")
	String saveFolderUrl;

	File saveFolder;

	@PostConstruct
	public void init() throws IOException {
		saveFolder = container.getResource(saveFolderUrl).getFile();
		if (!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}

	@PostMapping(value = "/board/imageUpload")
	public String upload(@RequestPart(value = "upload", required = true) MultipartFile imageFile, Model model)
			throws IllegalStateException, IOException {
		String savename = UUID.randomUUID().toString();
		imageFile.transferTo(new File(saveFolder, savename));

		model.addAttribute("fileName", imageFile.getOriginalFilename());
		model.addAttribute("uploaded", 1);
		String saveUrl = container.getServletContext().getContextPath() + saveFolderUrl + "/" + savename;
		model.addAttribute("url", saveUrl);
		return "jsonView";
	}

}
