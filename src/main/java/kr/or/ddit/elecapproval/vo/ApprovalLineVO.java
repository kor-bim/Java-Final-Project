package kr.or.ddit.elecapproval.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 결재라인VO 자주 쓰는 결재선 조회에 사용
 * 
 * @author 이운주
 * @since 2021. 2. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 18.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "alNo")
public class ApprovalLineVO {
	@NotNull
	@Min(0)
	private Integer alNo; // 결재선번호
	@Size(max = 20)
	private String alName; // 결재선이름
	@Size(max = 1)
	private String alSave; // 저장여부
	@NotBlank
	@Size(max = 20)
	private String memId; // 구성원ID
	@NotBlank
	@Size(max = 10)
	private String alCode; // 결재선종류코드

	private String rnum;

	private String[] approvalMember;
	private String[] referenceMember;

	private List<LineDetailVO> lineDetailList = new ArrayList<>();; // 결재라인 상세 리스트

	public void setApprovalMember(String[] approvalMember) {
		if (approvalMember == null) {
			return;
		}
		for (String member : approvalMember) {
			LineDetailVO line = new LineDetailVO();
			line.setMemId(member);
			line.setAldtCode("APPROVAL");
			lineDetailList.add(line);
		}
	}

	public void setReferenceMember(String[] referenceMember) {
		if (referenceMember == null) {
			return;
		}
		for (String member : referenceMember) {
			LineDetailVO line = new LineDetailVO();
			line.setMemId(member);
			line.setAldtCode("REFERENCE");
			lineDetailList.add(line);
		}
	}
}
