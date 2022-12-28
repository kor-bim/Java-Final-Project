package kr.or.ddit.hr.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.dao.MemberAdminDAO;
import kr.or.ddit.hr.member.vo.MemberVO;

@Service
public class MemberAdminServiceImpl implements MemberAdminService {

	@Inject
	MemberAdminDAO dao;

	@Override
	public List<MemberVO> retrieveNoPagingMemberList() {
		return dao.selectNoPagingMemberList();
	}

	@Override
	public List<MemberVO> retrievesuperAdminList() {
		return dao.selectSuperAdminList();
	}

	@Override
	public List<MemberVO> retrieveHrAdminList() {
		return dao.selectHrAdminList();
	}

	@Override
	public List<MemberVO> retrieveEaAdminList() {
		return dao.selectEaAdminList();
	}

	@Override
	public List<MemberVO> retrieveCmAdminList() {
		return dao.selectCmAdminList();
	}

	@Override
	public ServiceResult modifySuperAdmin(List<String> notAdminMemId, List<String> adminMemId) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = 0;
		try {
			for (String memId : adminMemId) {
				cnt = dao.updateSuperAdmin(memId);
			}
			for (String memId : notAdminMemId) {
				cnt += dao.updateSuperToUser(memId);
			}
			if (cnt > 1) {
				result = ServiceResult.OK;
			}
		} catch (Exception e) {

			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyCmAdmin(List<String> notAdminMemId, List<String> adminMemId) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = 0;
		try {
			for (String memId : adminMemId) {
				cnt = dao.updateCmAdmin(memId);
			}
			for (String memId : notAdminMemId) {
				cnt += dao.updateCmToUser(memId);
			}
			if (cnt > 1) {
				result = ServiceResult.OK;
			}
		} catch (Exception e) {

			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyEaAdmin(List<String> notAdminMemId, List<String> adminMemId) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = 0;
		try {
			for (String memId : adminMemId) {
				cnt = dao.updateEaAdmin(memId);
			}
			for (String memId : notAdminMemId) {
				cnt += dao.updateEaToUser(memId);
			}
			if (cnt > 1) {
				result = ServiceResult.OK;
			}
		} catch (Exception e) {

			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyHrAdmin(List<String> notAdminMemId, List<String> adminMemId) {
		ServiceResult result = ServiceResult.FAILED;
		int cnt = 0;
		try {
			for (String memId : adminMemId) {
				cnt = dao.updateHrAdmin(memId);
			}
			for (String memId : notAdminMemId) {
				cnt += dao.updateHrToUser(memId);
			}
			if (cnt > 1) {
				result = ServiceResult.OK;
			}
		} catch (Exception e) {

			result = ServiceResult.FAILED;
		}
		return result;
	}

}
