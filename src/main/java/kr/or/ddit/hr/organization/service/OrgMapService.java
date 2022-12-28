package kr.or.ddit.hr.organization.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

/**
 * @author 이재형
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 28.      이재형            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 * 
 */
public interface OrgMapService {

	/**
	 * 
	 * @author 이재형
	 * @since 2021. 1. 28.
	 * @param 
	 * @return
	 */
	public List<DeptVO> retrieveDeptList();

	/**
	 * 
	 * @author 이재형
	 * @since 2021. 2. 03.
	 * @param 
	 * @return
	 */
//	public List<DeptVO> retrieveDeptLinelist(String deptCode);

	/**
	 * 해당 구성원의 부서와 상위 부서 연결만을 보여주는 VO.
	 * 
	 * @param deptCode
	 * @return DeptVO
	 */
	public DeptVO retrieveDeptLine(String deptCode);

	/**
	 * 직위 조회
	 * 
	 * @return 직위 List
	 */
	public List<PositionVO> retrievePsList();
	
	public ServiceResult modifyPosition(List<PositionVO> psVOList);

	public List<JobVO> retrieveJobList();
	
	/**
	 * 직무 업데이트
	 * 
	 * @param jobVOList
	 * @return 
	 */
	public ServiceResult modifyJob(List<JobVO> jobVOList, JobVO delJobVO);

	/**
	 * 조직 + 구성원 트리 
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 21.
	 */
	public List<DeptVO> retrieveDeptMemList();

	public ServiceResult createDept(DeptVO deptVO);

	public ServiceResult deleteDept(DeptVO deptVO);

	public ServiceResult modifyDeptName(DeptVO deptVO);



}
