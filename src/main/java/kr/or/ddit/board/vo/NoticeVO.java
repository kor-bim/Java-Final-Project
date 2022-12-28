package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.hr.member.vo.MemberVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@ToString(exclude= {"nbFiles","nbAttatchList"})
public class NoticeVO implements Serializable{

	private String rnum;
	@NotNull
	@Min(0)
	private Integer nbNo;
	@NotBlank
	@Size(max = 100)
	private String nbTitle;
	@Size(max = 255)
	private String nbContent;
	@Size(max = 7)
	private String nbDate;
	@Size(max = 10)
	private String memId;
	@Size(max=200)
	private String nbPass;
	
	private String memName;
	
	private Integer nbHit;
	@Size(max=1)
	private char nbDelYn;

	private int[] delNbAttNos;
	private transient List<MultipartFile> nbFiles;
	
	
	private transient List<NBAttatchVO> nbAttatchList;
	private int startNbAttNo;

	public void setNbFiles(List<MultipartFile> nbFiles) {
		if (nbFiles == null || nbFiles.size() == 0) 
			return;
		this.nbFiles = nbFiles;
		this.nbAttatchList = new ArrayList<>();
		for (MultipartFile tmp : nbFiles) {
			if (StringUtils.isBlank(tmp.getOriginalFilename()))
				continue;
			nbAttatchList.add(new NBAttatchVO(tmp));
		}
	}
	
	private MemberVO memberVO;

}