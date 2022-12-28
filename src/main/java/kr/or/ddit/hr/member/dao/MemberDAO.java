package kr.or.ddit.hr.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * 전체 구성원 관련 Persistence Layer
 * 
 * @author 윤한빈
 * @since 2021. 1. 29.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 1. 29.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Repository
public interface MemberDAO {

	/**
	 * 회원 등록
	 * 
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int insertMember(MemberVO member);

	/**
	 * 회원 목록수 조회(totalRecord)
	 * 
	 * @return
	 */
	public int selectMemberCount(PagingVO<MemberVO> pagingVO);

	/**
	 * 회원목록 조회
	 * 
	 * @param pagingVO
	 * @return 존재하지 않을때, size == 0
	 */
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO);

	/**
	 * 회원 상세 조회
	 * 
	 * @param mem_id
	 * @return 존재하지 않을때 null 반환
	 */
	public MemberVO selectMember(String mem_id);

	/**
	 * 회원 정보 수정(자기 정보 수정)
	 * 
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int updateMember(MemberVO member);

	/**
	 * 회원 탈퇴 처리(???)
	 * 
	 * @param mem_id
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int deleteMember(String mem_id);

	/**
	 * 휴대폰번호로 아이디찾기
	 * 
	 * @param mem_id
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public MemberVO selectMemberHp(String mem_hp);

	/**
	 * 
	 * 임시비밀번호 등록
	 * 
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int insertTemporaryPassword(MemberVO member);

	/**
	 * 
	 * 직무 조회
	 * 
	 * @return 직업을 조회한 리스트 , 실패시 null
	 */
	public List<JobVO> selectJobList();
	
	/**
	 * 비밀번호 변경
	 * 
	 * @param member
	 * @return &gt; 0 : 성공 , &lt;= 0 : 실패
	 */
	public int updateMemberPassword(MemberVO member);

	/**
	 * 페이징없는 멤버 리스트
	 * 
	 * @return 조회된 멤버 리스트
	 */
	public List<MemberVO> selectNoPagingMemberList();

	/**
	 * 슈퍼관리자 목록 가져오기
	 * 
	 * @return 조회된 슈퍼관리자 목록
	 */
	public List<MemberVO> selectSuperAdminList();

	public ServiceResult updateSuperAdmin(List<String> memIdList);

	public int insertRole(MemberVO member);

}
