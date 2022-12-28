package kr.or.ddit.hr.member.vo;

import java.util.List;

import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

public class MemFormVO {
	private JobVO jobVO;
	private MemberVO memberVO;
	private List<JobVO> jobVOList;
	private List<PositionVO> psVOList;
	private List<DeptVO> deptList;
	private DeptVO deptLine;
	

	public List<DeptVO> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DeptVO> deptList) {
		this.deptList = deptList;
	}

	public List<PositionVO> getPsVOList() {
		return psVOList;
	}

	public void setPsVOList(List<PositionVO> psVOList) {
		this.psVOList = psVOList;
	}


	public DeptVO getDeptLine() {
		return deptLine;
	}

	public void setDeptLine(DeptVO deptLine) {
		this.deptLine = deptLine;
	}

	public JobVO getJobVO() {
		return jobVO;
	}

	public void setJobVO(JobVO jobVO) {
		this.jobVO = jobVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public List<JobVO> getJobVOList() {
		return jobVOList;
	}

	public void setJobVOList(List<JobVO> jobVOList) {
		this.jobVOList = jobVOList;
	}

}
