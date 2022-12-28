package kr.or.ddit.hr.organization.vo;

import java.util.List;

import kr.or.ddit.hr.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * voteBoard VO
 * 
 * @author 이재형
 * @since 2021. 1. 28.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                    수정자               수정내용
 * ------------   --------    ----------------------
 * 2021. 1. 28.    이재형             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DeptVO {
	private String deptCode;
	private String deptName;
	private int deptLevel;
	private String deptParentCode;
	private String line;
	
	/** 0221 운주  */
	private List<MemberVO> deptMemList; // 조직별 멤버 리스트 
}
