package kr.or.ddit.hr.member.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.hr.member.dao.MemberDAO;
import kr.or.ddit.hr.member.vo.JobVO;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * 전체회원 관련 BusinessLayer의 구현체
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
 *      회원관련 Service
 * 
 */

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private AuthenticationManager authenticationManager;

	@Inject
	private WebApplicationContext container;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Inject
	private MemberDAO dao;

	@Value("#{appInfo.profileImages}")
	private String imagePath;

	private File saveFolder;

	public void setSaveFolder(File saveFolder) {
		this.saveFolder = saveFolder;
	}

	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(imagePath));
		if (saveFolder != null && !saveFolder.exists())
			saveFolder.mkdirs();
	}

	private void encodePassword(MemberVO member) {
		String encoded = passwordEncoder.encode(member.getMemPass());
		member.setMemPass(encoded);
	}

	@Override
	public MemberVO retrieveMember(MemberVO memberVO) {

		if (memberVO.getMemId() != null && StringUtils.isNotBlank(memberVO.getMemId())) {
			memberVO = dao.selectMember(memberVO.getMemId());
		} else if (memberVO.getMemId() == null && memberVO.getMemHp() != null) {
			memberVO = dao.selectMemberHp(memberVO.getMemHp());
		}

		if (memberVO == null)
			throw new UsernameNotFoundException("해당하는 유저가 없음.");
		return memberVO;
	}

	@Override
	public ServiceResult registTemporaryPassword(MemberVO member) {
		ServiceResult result = null;
		if (dao.selectMember(member.getMemId()) != null) {
			encodePassword(member);
			int rowcnt = dao.insertTemporaryPassword(member);
			if (rowcnt > 0) {
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult result = null;
		try {
			int rowcnt = dao.updateMember(member);
			if (rowcnt > 0) {
				member.saveTo(saveFolder);

				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public List<JobVO> retrieveJobList() {
		return dao.selectJobList();
	}

	@Override
	public int retrieveMemberCount(PagingVO<MemberVO> pagingVO) {
		return dao.selectMemberCount(pagingVO);
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> pagingVO) {
		return dao.selectMemberList(pagingVO);
	}

	@Transactional
	@Override
	public ServiceResult registMember(MemberVO member) {
		ServiceResult result = null;
		try {
			if (dao.selectMember(member.getMemId()) == null) {
				encodePassword(member);
				int rowcnt = dao.insertMember(member);
				rowcnt += dao.insertRole(member);
				if (rowcnt > 0) {
					member.saveTo(saveFolder);
					result = ServiceResult.OK;
				} else {
					result = ServiceResult.FAILED;
				}
			} else {
				result = ServiceResult.PKDUPLICATED;
			}
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult modifyMemberImg(MemberVO member) {
		ServiceResult result = null;
		try {
			int rowcnt = dao.updateMember(member);
			if (rowcnt > 0) {
				member.saveTo(saveFolder);
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}

		} catch (IOException e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMemberSignImg(MemberVO member) {
		ServiceResult result = null;
		try {
			int rowcnt = dao.updateMember(member);
			if (rowcnt > 0) {
				member.saveTo(saveFolder);
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAILED;
			}

		} catch (IOException e) {
			result = ServiceResult.FAILED;
		}
		return result;
	}

	private void changeAuthentication(MemberVO member) {
		Authentication newAuthentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
		SecurityContextHolder.getContext().setAuthentication(newAuthentication);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

		if (currentUser == null) {
			throw new AccessDeniedException("현재 SecurityContext 에 Authentication 객체가 없음.");
		}

		String username = currentUser.getName();

		// 기존 비밀번호로 재 인증
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		MemberVO updateMember = new MemberVO();
		updateMember.setMemId(username);
		updateMember.setMemPass(newPassword);
		encodePassword(updateMember);

		dao.updateMemberPassword(updateMember);

		updateMember.setMemPass(newPassword);

		changeAuthentication(updateMember);
	}

}
