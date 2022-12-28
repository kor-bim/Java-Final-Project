package kr.or.ddit.board.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class VoteBoardReplyVO implements Serializable {
	
	@NotNull @Min(0) 
	private Integer vbrNo;  // 댓글번호
	@Size(max=255) 
	private String vbrContent; // 댓글 내용
	@Size(max=7) 
	private String vbrDate;  // 댓글 작성일 
	@NotBlank@Size(max=20) 
	private String memId;  // 댓글 작성자 아이디 
	@NotNull @Min(0) 
	private Integer parentNo; // 부모 댓글 번호 (vbrNo)
	@NotNull @Min(0) 
	private Integer vbNo;   // 게시글 번호 (vbNo)

	// 댓글 작성자 이름, 부서명, 프로필사진
	private String Name;
	private String DeptName;
	private String memImg;
	
	/** getter, setter */
	public Integer getVbrNo() {
		return vbrNo;
	}
	public void setVbrNo(Integer vbrNo) {
		this.vbrNo = vbrNo;
	}
	public String getVbrContent() {
		return vbrContent;
	}
	public void setVbrContent(String vbrContent) {
		this.vbrContent = vbrContent;
	}
	public String getVbrDate() {
		return vbrDate;
	}
	public void setVbrDate(String vbrDate) {
		this.vbrDate = vbrDate;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getParentNo() {
		return parentNo;
	}
	public void setParentNo(Integer parentNo) {
		this.parentNo = parentNo;
	}
	public Integer getVbNo() {
		return vbNo;
	}
	public void setVbNo(Integer vbNo) {
		this.vbNo = vbNo;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	

	
}
