package kr.or.ddit.schedule.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * @author 서대철
 * @since 2021. 2. 15.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 15.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Data
public class DepartmentVO {
	@NotBlank
	@Size(max = 20)
	private String deptCode;
	@NotBlank
	@Size(max = 100)
	private String deptName;
	@NotNull
	@Min(0)
	private Integer deptLevel;
	@Size(max = 20)
	private String deptParentCode;
}
