package kr.or.ddit.hr.member.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.hr.member.dao.MemberDAO;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.member.vo.MemberWrapper;

@Service("customUserService")
public class AuthenticateService implements UserDetailsService  {
	
	@Autowired
	private MemberDAO dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO member = dao.selectMember(username);
		
		List<GrantedAuthority> role = new ArrayList<>();
		for (int i = 0; i < member.getRoleInfoList().size(); i++) {
			role.add(new SimpleGrantedAuthority(member.getRoleInfoList().get(i).getRoleCode()));
		}
		return new MemberWrapper(member,role);
	}

}
