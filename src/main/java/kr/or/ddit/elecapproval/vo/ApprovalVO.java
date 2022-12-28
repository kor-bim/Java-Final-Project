package kr.or.ddit.elecapproval.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.hr.member.vo.MemberVO;
import lombok.Data;

@Data
public class ApprovalVO {
	@NotNull
	@Min(0)
	private Integer apNo;
	@NotBlank
	@Size(max = 20)
	private String adNo;
	@Size(max = 255)
	private String adComment;
	@Size(max = 7)
	private String adRd;
	@Size(max = 7)
	private String adAd; // 결재일자
	@NotBlank
	@Size(max = 20)
	private String memId; // 결재자 아이디
	@Size(max = 10)
	private String aprvlTypeCode;
	@Size(max = 10)
	private String aprvlStateCode;
	
	private MemberVO memberVO; // 결재자 정보
}
