package kr.or.ddit.elecapproval.vo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import lombok.Data;

/**
 * @author 서대철
 * @since 2021. 2. 20.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 20.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Data
public class DocTypeCodeVO {

	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	@Size(max = 10)
	private String docTypeCode; // 문서분류 코드
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 10)
	private String docTypeName; // 문서분류 이름 
}
