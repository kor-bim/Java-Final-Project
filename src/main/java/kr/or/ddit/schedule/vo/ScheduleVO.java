package kr.or.ddit.schedule.vo;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 서대철
 * @since 2021. 2. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 5.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of="schdlNo")
public class ScheduleVO implements Serializable{
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	@Min(0)
	private Integer schdlNo;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class})
	@Size(max = 100)
	private String schdlName;
	@Size(max = 11)
	private String schdlBegin;
	@Size(max = 11)
	private String schdlEnd;
	@Size(max = 255)
	private String schdlContent;
	@NotBlank
	@Size(max = 20)
	private String memId;
	@Min(0)
	private Integer calNo;
	
	private String calColor;
	
}
