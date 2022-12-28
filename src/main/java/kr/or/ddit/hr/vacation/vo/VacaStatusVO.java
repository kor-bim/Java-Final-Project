package kr.or.ddit.hr.vacation.vo;

import java.io.Serializable;
import java.util.List;

import kr.or.ddit.base.vo.BaseVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 직원 휴가 관리 : 휴가 현황
 * 
 * @author 이운주
 * @since 2021. 2. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 15.       이운주              최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "memId")
public class VacaStatusVO extends BaseVO implements Serializable {

	private String memId;
	private String memName;
	private String memHdate;
	private String deptCode; // 검색조건
	private String deptName;

	private String vtCode;
	private String vtName;
	private int vacaInday;
	private int vacaRmday;
	private int vacaYear;

	private String searchYear; // 검색조건

	private int useCnt;

	private List<VacaStatusVO> vacaStatList;

	private String psName;

	private VacaRecVO vacaRecVO;
}
