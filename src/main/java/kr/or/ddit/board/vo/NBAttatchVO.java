/**
 * 영주
 */
package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "nbaNo")
@ToString(exclude = "realFile")
public class NBAttatchVO {
	public NBAttatchVO(MultipartFile realFile) {
		this.realFile = realFile;
		this.nbaRealname = UUID.randomUUID().toString();
		this.nbaName = realFile.getOriginalFilename();
		this.nbaExtns = realFile.getContentType();
		this.nbaSize = realFile.getSize();
	}

	private transient MultipartFile realFile;

	public void saveTo(File saveFolder) throws IOException {
		if (realFile != null) {
			realFile.transferTo(new File(saveFolder, nbaRealname));
		}
	}

	@NotNull
	@Min(0)
	private Integer nbaNo;

	@NotBlank
	@Size(max = 255)
	private String nbaName;

	@NotBlank
	@Size(max = 255)
	private String nbaRealname;
	@NotNull
	private Long nbaSize;
	@Size(max = 20)
	private String nbaExtns;
	@NotNull
	@Min(0)
	private Integer nbNo;
	

}
