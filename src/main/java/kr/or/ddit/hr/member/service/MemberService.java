package kr.or.ddit.hr.member.service;

import java.util.List;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * 전체회원 관련 BusinessLayer의 인터페이스
 * 
 * @author 윤한빈
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 * 
 *      회원 관련 Service
 */
public interface MemberService {

	/**
	 * 구성원 조회
	 * 
	 * @param memberVO
	 * @return
	 * @author 윤한빈
	 * @date 2021. 1. 26.
	 */
	public MemberVO retrieveMember(MemberVO memberVO);

	/**
	 * 구성원 정보 수정
	 * 
	 * @param memberVO
	 * @return 존재하지 않는다면, custom exception 발생, OK, FAILED
	 */
	public ServiceResult modifyMember(MemberVO memberVO);

	/**
	 * 임시비밀번호 등록
	 * 
	 * @param member
	 * @return PKDUPLICATED, OK, FAILED
	 */
	public ServiceResult registTemporaryPassword(MemberVO memberVO);

	/**
	 * @return
	 */
	public List<JobVO> retrieveJobList();

	/**
	 * 멤버 리스트 페이징 처리위한
	 * 
	 * @param pagingVO
	 * @return
	 */
	public int retrieveMemberCount(PagingVO<MemberVO> pagingVO);

	/**
	 * 페이징 멤버 리스트 가져오기
	 * 
	 * @param pagingVO
	 * @return 조회된 멤버 리스트
	 */
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO);
	
	/**
	 * @param memberVO
	 * @return
	 */
	public ServiceResult registMember(MemberVO memberVO);

	/**
	 * 프로필 사진 변경
	 * 
	 * @param memberVO
	 * @return
	 */
	public ServiceResult modifyMemberImg(MemberVO memberVO);
	
	/**
	 * 비밀번호 변경
	 * @param oldPassword
	 * @param newPassword
	 */
	public void changePassword(String oldPassword, String newPassword);

	/**
	 * 서명이미지 변경
	 * 
	 * @param memberVO
	 * @return
	 */
	public ServiceResult modifyMemberSignImg(MemberVO memberVO);

}
