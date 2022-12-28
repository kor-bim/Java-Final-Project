package kr.or.ddit.hr.member.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.hr.member.vo.MemberVO;

@Repository
public interface MemberAdminDAO {

	List<MemberVO> selectNoPagingMemberList();

	List<MemberVO> selectSuperAdminList();

	int updateSuperAdmin(String memId);

	int updateSuperToUser(String memId);

	List<MemberVO> selectHrAdminList();

	List<MemberVO> selectEaAdminList();

	List<MemberVO> selectCmAdminList();

	int updateCmAdmin(String memId);

	int updateCmToUser(String memId);

	int updateEaAdmin(String memId);

	int updateEaToUser(String memId);

	int updateHrAdmin(String memId);

	int updateHrToUser(String memId);

}
