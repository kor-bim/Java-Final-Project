package kr.or.ddit.hr.vacation.vo;

import java.util.List;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class VacaRecVO {
	private String vacaNo;		// 휴가번호
	private String vacaBegin;	// 시작일
	private String vacaEnd;		// 종료일
	private String vtCode;		// 휴가분류코드
	private String memId;		// 휴가ID
	
	private ApprovalDocVO approvalDocVO;
	
	private String adNo;		// 휴가 신청 결재 문서번호
	private String vacaHalf;	// 휴가 반차여부
	
	private String days; 		// 휴가 일수
	
	private List<VacaRecVO> vacaList;
	
	private String vtName;
	private String dsCode;
	private String dsName;
	
	private String memName;
	private String deptName;
	private String adDate;
}
