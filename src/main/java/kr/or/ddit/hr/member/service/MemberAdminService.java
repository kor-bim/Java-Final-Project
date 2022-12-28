package kr.or.ddit.hr.member.service;

import java.util.List;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.vo.MemberVO;

public interface MemberAdminService {

	/**
	 * 멤버 리스트 가져오기
	 * 
	 * @return 조회된 멤버 리스트
	 */
	public List<MemberVO> retrieveNoPagingMemberList();

	/**
	 * 슈퍼관리자 목록 가져오기
	 * 
	 * @return 조회된 슈퍼관리자 목록
	 */
	public List<MemberVO> retrievesuperAdminList();

	/**
	 * 인사관리자 목록 가져오기
	 * 
	 * @return
	 */
	public List<MemberVO> retrieveHrAdminList();

	/**
	 * 전자결재 관리자 목록 가져오기
	 * 
	 * @return
	 */
	public List<MemberVO> retrieveEaAdminList();

	/**
	 * 게시판 관리자 목록 가져오기
	 * 
	 * @return
	 */
	public List<MemberVO> retrieveCmAdminList();

	/**
	 * @param memIdList
	 * @param adminMemId
	 * @return
	 */
	public ServiceResult modifySuperAdmin(List<String> notAdminMemId, List<String> adminMemId);

	/**
	 * @param notAdminMemId
	 * @param adminMemId
	 * @return
	 */
	public ServiceResult modifyCmAdmin(List<String> notAdminMemId, List<String> adminMemId);

	/**
	 * @param notAdminMemId
	 * @param adminMemId
	 * @return
	 */
	public ServiceResult modifyEaAdmin(List<String> notAdminMemId, List<String> adminMemId);

	/**
	 * @param notAdminMemId
	 * @param adminMemId
	 * @return
	 */
	public ServiceResult modifyHrAdmin(List<String> notAdminMemId, List<String> adminMemId);

}
