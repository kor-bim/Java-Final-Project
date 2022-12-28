package kr.or.ddit.elecapproval.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 결재라인 상세 VO
 * 
 * @author 이운주
 * @since 2021. 2. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 20.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDetailVO {

	@NotNull
	@Min(0)
	private Integer alNo;
	@NotNull
	@Min(0)
	private Integer aldNo;
	@NotBlank
	@Size(max = 20)
	private String memId;
	@Size(max = 10)
	private String memName; // 결재자 이름
	private String deptName; // 결재자의 소속 조직 
	private String psName; // 결재자의 직위 
	
	@Size(max=10) 
	private String aldtCode;
	
	private String aprvlTypeCode;
	private String aprvlStateCode;

	// ====================
	private String memSignImg; // 서명이미지
	private String adAd; // 결재일자
	private ApprovalVO approvalVO; // 결재 
	
}
