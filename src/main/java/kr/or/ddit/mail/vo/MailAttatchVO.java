package kr.or.ddit.mail.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "maNo")
@ToString(exclude = "realFile")
@AllArgsConstructor
public class MailAttatchVO {
	
	public MailAttatchVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.maName = UUID.randomUUID().toString();
		this.maRealname = realFile.getOriginalFilename();
		this.maExtns = realFile.getContentType();
		this.maSize = realFile.getSize();
		this.maFancy = FileUtils.byteCountToDisplaySize(maSize);
	}

	public void saveTo(File saveFolder) throws IOException {
		if (realFile != null) {
			realFile.transferTo(new File(saveFolder, maName));
		}
	}

	private transient MultipartFile realFile;
	private Integer maNo;
	private String maName;
	private String maRealname;
	private Long maSize;
	private String maExtns;
	private Integer mailNo;
	private String maFancy;
}
