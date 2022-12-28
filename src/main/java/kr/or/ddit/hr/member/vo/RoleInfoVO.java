package kr.or.ddit.hr.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleInfoVO {
	private long riNo;
	private String roleCode;
	private String roleName;
	private String memId;

}
