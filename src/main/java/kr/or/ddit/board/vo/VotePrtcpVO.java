package kr.or.ddit.board.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class VotePrtcpVO {
	
	@NotBlank@Size(max=20) 
	private String memId;
	@NotNull @Min(0) 
	private Integer vbNo;
	@NotNull @Min(0) 
	private Integer vcNo;
	
	private Integer[] vcNos; // 중복투표도 가능하기 떄문에 배열로 받음.
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Integer getVbNo() {
		return vbNo;
	}
	public void setVbNo(Integer vbNo) {
		this.vbNo = vbNo;
	}
	public Integer getVcNo() {
		return vcNo;
	}
	public void setVcNo(Integer vcNo) {
		this.vcNo = vcNo;
	}
	public Integer[] getVcNos() {
		return vcNos;
	}
	public void setVcNos(Integer[] vcNos) {
		this.vcNos = vcNos;
	}
	
}
