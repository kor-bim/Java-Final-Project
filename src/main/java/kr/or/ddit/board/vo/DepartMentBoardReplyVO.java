package kr.or.ddit.board.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 윤한빈
 * @since 2021. 2. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Data
@EqualsAndHashCode(of = "dbrNo")
@ToString(exclude = "dbrContent")
public class DepartMentBoardReplyVO {
	@NotNull
	private Integer dbrNo;
	private String dbrContent;
	private String dbrDate;
	@NotBlank
	@Size(max = 80)
	private String memId;
	private Integer dbrParent;
	@NotNull
	private Integer dbNo;
	private String deptCode;
}
