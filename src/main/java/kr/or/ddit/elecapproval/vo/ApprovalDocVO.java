package kr.or.ddit.elecapproval.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.base.vo.BaseVO;
import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import kr.or.ddit.hr.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 결재 문서 VO
 * 
 * @author 이운주
 * @since 2021. 2. 18.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 18.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "adNo")
public class ApprovalDocVO extends BaseVO {

	@NotBlank
	@Size(max = 20)
	private String adNo; // 결재문서 번호 : YYYYMMDD-0001
	@Size(max = 100)
	private String adTitle; // 제목
	@Size(max = 7)
	private String adDate; // 기안일
	@Size(max = 1)
	private String adJgYn; // 전결여부
	@Size(max = 1)
	private String adDgYn; // 대결여부
	@Size(max = 1)
	private String adHgYn; // 후결여부
	@NotBlank
	@Size(max = 1)
	private String adDel; // 삭제여부
	@NotBlank
	@Size(max = 20)
	private String memId; // 기안자ID

	@NotNull(groups = { UpdateGroup.class, DeleteGroup.class })
	@Min(0)
	private Integer alNo; // 결재선번호
	@NotNull
	@Min(0)
	private Integer dfNo; // 문서양식번호
	private String dfName; // 문서 양식명

	@NotBlank
	@Size(max = 10)
	private String dsCode; // 문서상태코드
	private String dsName; // 문서상태명
	
	@Size(max = 7)
	private String adFinishDate; // 완료일

	private transient String adContent; // 결재문서 저장된 내용

	private List<LineDetailVO> lineDetailList; // 결재라인 상세
	
	private MemberVO memberVO; // MEMBERVO
	
	private DocumentsVO documentsVO; // 문서 양식 VO

	private List<String> deleteAdNo; // 삭제 문서
	
	private List<ApprovalVO> approvalVOList;
	
	private String[] approvalMember;
	private String[] referenceMember;
	
	public void setLineDetailList(String[] approvalMember, String[] referenceMember) {
		if(approvalMember == null) {
			return;
		}
		lineDetailList = new ArrayList<>();
		for (int i = 0 ; i < approvalMember.length ; i++) {
			LineDetailVO line = new LineDetailVO();
			line.setMemId(approvalMember[i]);
			if(i==0) {
				line.setAldtCode("DRAFT");
			}else {
				line.setAldtCode("APPROVAL");
			}
			lineDetailList.add(line);
		}
		
		if(referenceMember == null) {
			return;
		}
		for (String member : referenceMember) {
			LineDetailVO line = new LineDetailVO();
			line.setMemId(member);
			line.setAldtCode("REFERENCE");
			lineDetailList.add(line);
		}
	}
	
	private Integer apNo; // 결재번호 (인서트)
	
	private String lastApprovalId;
	public void setLastApprovalId(List<LineDetailVO> lineDetailList) {
		int listSize = lineDetailList.size();
		for(int i = listSize -1; i >= 0 ; i--) {
			if("APPROVAL".equals(lineDetailList.get(i).getAldtCode())) {
				this.lastApprovalId = lineDetailList.get(i).getMemId();
				return;
			}
		}
	}
}
