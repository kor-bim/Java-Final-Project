package kr.or.ddit.board.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import kr.or.ddit.commons.validator.groups.UpdateGroup;

public class VoteCateVO {

	@NotNull(groups=UpdateGroup.class)
	@Min(0)
	private Integer vcNo;
	@NotNull
	@Min(0)
	private Integer vbNo;
	@Size(max = 100)
	private String vcName;
	@NotNull
	@Min(0)
	private Integer voteCount;

	private String[] vcNos;
	private String[] vcNames;
	
	/** getter, setter */
	public Integer getVcNo() {
		return vcNo;
	}
	public void setVcNo(Integer vcNo) {
		this.vcNo = vcNo;
	}
	public Integer getVbNo() {
		return vbNo;
	}
	public void setVbNo(Integer vbNo) {
		this.vbNo = vbNo;
	}
	public String getVcName() {
		return vcName;
	}
	public void setVcName(String vcName) {
		this.vcName = vcName;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public String[] getVcNames() {
		return vcNames;
	}
	public void setVcNames(String[] vcNames) {
		this.vcNames = vcNames;
	}
	public String[] getVcNos() {
		return vcNos;
	}
	public void setVcNos(String[] vcNos) {
		this.vcNos = vcNos;
	}

	
}
