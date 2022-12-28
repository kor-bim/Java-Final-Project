package kr.or.ddit.FileBox.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of = "fileNo")
@ToString(exclude = "realFile")
@NoArgsConstructor
@AllArgsConstructor
public class FileBoxVO implements Serializable {
	public FileBoxVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.fileName = UUID.randomUUID().toString();
		this.fileRealName = realFile.getOriginalFilename();
		this.fileExtns = realFile.getContentType();
		this.fileSize = realFile.getSize();
		this.fileFancy = FileUtils.byteCountToDisplaySize(fileSize);
	}

	private transient MultipartFile realFile;

	public void saveTo(File saveFolder) throws IOException {
		if (realFile != null) {
			realFile.transferTo(new File(saveFolder, fileName));
		}
	}
//=====================================================
	private Integer fileNo;
	@Size(max = 255)
	private String fileName;
	@Size(max = 255)
	private String fileRealName;
	@Size(max = 20)
	private Long fileSize;
	@Size(max = 120)
	private String fileExtns;
	@Size(max = 20)
	private Integer fileParent;
	private String fileFancy;
	private String memId;

	private String fileDate;
	private String fileDir;
	private String fileDel;
//======================================================	
	private Integer level;
	private String fileParentName; 
	
	private Double sizetotal;
	
	private int[] fileNos;
	private int[] delAttNos;
	
	private transient List<MultipartFile> files;

	public void setFiles(List<MultipartFile> files) {
		if (files == null || files.size() == 0)
			return;
		this.files = files;
		this.fileList = new ArrayList<>();
		for (MultipartFile tmp : files) {
			if (StringUtils.isBlank(tmp.getOriginalFilename()))
				continue;
			fileList.add(new FileBoxVO(tmp));
		}
	}
	private transient List<FileBoxVO> fileList;
	
	private List<Integer> deleteFileNo;
	
	private String fileType;

}