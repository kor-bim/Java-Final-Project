package kr.or.ddit.hr.member.vo;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class MemberWrapper extends User {

	public MemberWrapper(MemberVO member, List<GrantedAuthority> role) {

		super(member.getMemId(), member.getMemPass(), "ON".equals(member.getAsCode()), "ON".equals(member.getAsCode()),
				"ON".equals(member.getAsCode()), "ON".equals(member.getAsCode()), role);

		this.realMember = member;
	}

	private MemberVO realMember;

	public MemberVO getRealMember() {
		return realMember;
	}
}
