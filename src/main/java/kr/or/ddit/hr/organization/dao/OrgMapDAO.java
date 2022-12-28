package kr.or.ddit.hr.organization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.organization.vo.DeptVO;
import kr.or.ddit.hr.organization.vo.PositionVO;

@Repository
public interface OrgMapDAO {

	/**
	 * 내 정보 관리에 들어갈 회원의 소속 라인
	 * 
	 * @param deptCode
	 * @return 소속 라인 리스트
	 */
	public List<DeptVO> selectDeptLineList(String dept_code);

	/**
	 * 부서 전체 목록
	 * 
	 * @param deptVO
	 * @return 부서 List
	 */
	public List<DeptVO> selectDeptList();

	/**
	 * 해당 구성원의 부서와 상위 부서 연결만을 보여주는 VO.
	 * 
	 * @param dept_code
	 * @return
	 */
	public DeptVO selectDeptLine(String dept_code);

	/**
	 * 직위 조회
	 * 
	 * @return 직무 List
	 */
	public List<PositionVO> selectPsList();

	public List<JobVO> selectJobList();

	public int updatePosition(PositionVO psVO);

	/**
	 * 직무 업데이트
	 * 
	 * @param jobVO
	 * @return
	 */
	public int updateJob(JobVO jobVO);

	/**
	 * 직무 삭제
	 * 
	 * @param delJobVO
	 * @return 
	 */
	public int deleteJob(JobVO delJobVO);

	/**
	 * 조직 + 구성원 트리
	 * @return
	 * @authoe 이운주
	 * @date 2021. 2. 21.
	 */
	public List<DeptVO> retrieveDeptMemList();

	/**
	 * 부서 생성
	 * 
	 * @param deptVO
	 * @return
	 */
	public int insertDept(DeptVO deptVO);

	/**
	 * 부서 삭제
	 * 
	 * @param deptVO
	 * @return
	 */
	public int deleteDept(DeptVO deptVO);

	/**
	 * 부서 삭제를 위해 해당 부서에 속해있는 구성원의 부서명을 null로 바꾸는 쿼리
	 * 
	 * @param deptVO
	 * @return
	 */
	public int updateMemberDeptCode(DeptVO deptVO);

	/**
	 * 삭제해야할 부서에 속해있는 사람이 존재하는지 세는 쿼리.
	 * 
	 * @param deptVO
	 * @return
	 */
	public int selectMemberDeptCodeCount(DeptVO deptVO);

	public int updateDeptName(DeptVO deptVO);

}
