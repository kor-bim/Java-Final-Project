package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.base.vo.BaseVO;
import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;

/**
 * voteBoard VO
 * 
 * @author 이운주
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                    수정자               수정내용
 * ------------   --------    ----------------------
 * 2021. 1. 26.    이운주             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */

@SuppressWarnings("serial")
public class VoteBoardVO extends BaseVO implements Serializable {

	@NotNull(groups = { UpdateGroup.class, DeleteGroup.class })
	@Min(0)
	private Integer vbNo;
	@NotBlank
	@Size(max = 50)
	private String vbTitle;
	@Size(max = 200)
	private String vbContent;
	@Size(max = 7)
	private String vbDate;
	@Size(max = 7)
	private String vbEnd;
	@NotNull
	@Min(0)
	private Integer vbCount;
	@NotBlank
	@Size(max = 1)
	private String vbDup;
	@Size(max=200) 
	private String vbPass;
	private Integer vbHit;
	@Size(max=1) 
	private String vbDelYn; // 삭제여부

	@NotBlank
	@Size(max = 20)
	private String memId; // 투표 작성자
	private String memName; // 투표 작성자 이름 (조회용)

	private String rnum;

	@NotNull(groups = InsertGroup.class)
	private List<String> voteCateInsertList; // insert용 voteCateList

	// 투표 참여자
	private VotePrtcpVO votePrtcpVO;    // insert 용 
	private List<VotePrtcpVO> voterList; // 조회용          : has many 관계 (VoteBoard has many VotePrtcp)

	private List<VoteCateVO> voteCateList; // has many 관계 (VoteBoard has many VoteCate)
	

	/** getter, setter */
	public Integer getVbNo() {
		return vbNo;
	}

	public void setVbNo(Integer vbNo) {
		this.vbNo = vbNo;
	}

	public String getVbTitle() {
		return vbTitle;
	}

	public void setVbTitle(String vbTitle) {
		this.vbTitle = vbTitle;
	}

	public String getVbContent() {
		return vbContent;
	}

	public void setVbContent(String vbContent) {
		this.vbContent = vbContent;
	}

	public String getVbDate() {
		return vbDate;
	}

	public void setVbDate(String vbDate) {
		this.vbDate = vbDate;
	}

	public String getVbEnd() {
		return vbEnd;
	}

	public void setVbEnd(String vbEnd) {
		this.vbEnd = vbEnd;
	}

	public Integer getVbCount() {
		return vbCount;
	}

	public void setVbCount(Integer vbCount) {
		this.vbCount = vbCount;
	}

	public String getVbDup() {
		return vbDup;
	}

	public void setVbDup(String vbDup) {
		this.vbDup = vbDup;
	}
	
	public String getVbPass() {
		return vbPass;
	}

	public void setVbPass(String vbPass) {
		this.vbPass = vbPass;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public List<String> getVoteCateInsertList() {
		return voteCateInsertList;
	}

	public void setVoteCateInsertList(List<String> voteCateInsertList) {
		this.voteCateInsertList = voteCateInsertList;
	}
	public VotePrtcpVO getVotePrtcpVO() {
		return votePrtcpVO;
	}

	public void setVotePrtcpVO(VotePrtcpVO votePrtcpVO) {
		this.votePrtcpVO = votePrtcpVO;
	}

	public List<VotePrtcpVO> getVoterList() {
		return voterList;
	}

	public void setVoterList(List<VotePrtcpVO> voterList) {
		this.voterList = voterList;
	}

	public List<VoteCateVO> getVoteCateList() {
		return voteCateList;
	}

	public void setVoteCateList(List<VoteCateVO> voteCateList) {
		this.voteCateList = voteCateList;
	}

	public Integer getVbHit() {
		return vbHit;
	}

	public void setVbHit(Integer vbHit) {
		this.vbHit = vbHit;
	}

	public String getVbDelYn() {
		return vbDelYn;
	}

	public void setVbDelYn(String vbDelYn) {
		this.vbDelYn = vbDelYn;
	}
	
	
}
