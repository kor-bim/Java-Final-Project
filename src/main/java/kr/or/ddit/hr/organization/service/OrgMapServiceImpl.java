package kr.or.ddit.hr.organization.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.organization.dao.OrgMapDAO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

@Service
public class OrgMapServiceImpl implements OrgMapService {

	@Inject
	private OrgMapDAO orgMapDAO;

	@Override
	public List<DeptVO> retrieveDeptList() {
		return orgMapDAO.selectDeptList();
	}

	@Override
	public DeptVO retrieveDeptLine(String deptCode) {
		return orgMapDAO.selectDeptLine(deptCode);
	}

	@Override
	public List<PositionVO> retrievePsList() {
		return orgMapDAO.selectPsList();
	}

	@Override
	public ServiceResult modifyPosition(List<PositionVO> psVOList) {
		int rowcnt = 0;
		ServiceResult result = null;
		for (PositionVO psVO : psVOList) {
			if (psVO.getPsCode() == null || "".equals(psVO.getPsCode()) || psVO.getPsCode().length() == 0) {
				continue;
			}
			rowcnt = orgMapDAO.updatePosition(psVO);
		}
		if (rowcnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public List<JobVO> retrieveJobList() {
		return orgMapDAO.selectJobList();
	}

	@Override
	public ServiceResult modifyJob(List<JobVO> jobVOList, JobVO delJobVO) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = 0;
		try {
			if(delJobVO.getDelJobCode()!=null) {
				cnt += orgMapDAO.deleteJob(delJobVO);
			}
			for (JobVO jobVO : jobVOList) {
				cnt += orgMapDAO.updateJob(jobVO);
			}
			if (cnt > 0) {
				result = ServiceResult.OK;
			}
		} catch (Exception e) {
			result = ServiceResult.FAILED;
		}

		return result;
	}

	@Override
	public List<DeptVO> retrieveDeptMemList() {
		return orgMapDAO.retrieveDeptMemList();
	}

	
	@Override
	public ServiceResult createDept(DeptVO deptVO) {
		ServiceResult result = ServiceResult.FAILED;
		List<DeptVO> curDeptVOList = orgMapDAO.selectDeptList();
		for(DeptVO dept : curDeptVOList) {
			if(dept.getDeptName().equals(deptVO.getDeptName())) {
				result = ServiceResult.ALREADYEXIST;
				return result;
			}
		}
		
		try {
			int rowcnt = orgMapDAO.insertDept(deptVO);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public ServiceResult deleteDept(DeptVO deptVO) {
		ServiceResult result = ServiceResult.FAILED;
		try {
			int target = orgMapDAO.selectMemberDeptCodeCount(deptVO);
			if(target > 0) {
				int cnt = orgMapDAO.updateMemberDeptCode(deptVO);
				if(cnt > 0) {
					int rowcnt = orgMapDAO.deleteDept(deptVO);
					if (rowcnt > 0) {
						result = ServiceResult.OK;
					} else {
						result = ServiceResult.FAILED;
					}
				}else {
					result = ServiceResult.FAILED;
				}
			}else {
				int rowcnt = orgMapDAO.deleteDept(deptVO);
				if (rowcnt > 0) {
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public ServiceResult modifyDeptName(DeptVO deptVO) {
		ServiceResult result = ServiceResult.FAILED;
		List<DeptVO> curDeptVOList = orgMapDAO.selectDeptList();
		for(DeptVO dept : curDeptVOList) {
			if(dept.getDeptName().equals(deptVO.getDeptName())) {
				result = ServiceResult.ALREADYEXIST;
				return result;
			}
		}
		
		try {
			int rowcnt = orgMapDAO.updateDeptName(deptVO);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
