package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 윤한빈
 * @since 2021. 2. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "dbNo")
@ToString(exclude = "realFile")
public class DepartMentBoardAttatchVO {
	public DepartMentBoardAttatchVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.dbaName = UUID.randomUUID().toString();
		this.dbaRealname = realFile.getOriginalFilename();
		this.dbaExtns = realFile.getContentType();
		this.dbaSize = realFile.getSize();
		this.dbaFancy = FileUtils.byteCountToDisplaySize(dbaSize);
	}

	private transient MultipartFile realFile;

	public void saveTo(File saveFolder) throws IOException {
		if (realFile != null) {
			realFile.transferTo(new File(saveFolder, dbaName));
		}
	}

	private Integer dbaNo;
	@NotBlank
	@Size(max = 280)
	private String dbaName;
	@NotBlank
	@Size(max = 200)
	private String dbaRealname;
	@Size(max = 120)
	private String dbaExtns;
	@NotNull
	private Integer dbNo;
	@NotNull
	private Long dbaSize;
	@NotBlank
	@Size(max = 20)
	private String dbaFancy;

	private String dbaPath;
	private String deptCode;
}
