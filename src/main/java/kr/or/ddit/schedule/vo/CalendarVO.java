package kr.or.ddit.schedule.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import kr.or.ddit.hr.member.vo.MemberVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 서대철
 * @since 2021. 2. 6.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 2. 6.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of="calNo")
@AllArgsConstructor
@NoArgsConstructor
public class CalendarVO implements Serializable{
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	@Min(0)
	private Integer calNo;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class})
	@Size(max = 20)
	private String calName;
	@NotBlank
	@Size(max = 15)
	private String calColor;
	@NotBlank
	@Size(max = 10)
	private String calCode;
	@NotBlank
	@Size(max = 20)
	private String memId;
	
	
	/**
	 * @return the calNo
	 */
	public int getCalNo() {
		return calNo;
	}

	/**
	 * @param calNo the calNo to set
	 */
	public void setCalNo(Integer calNo) {
		this.calNo = calNo;
	}

	private transient String[] calendarMember;
	
	private transient List<MemberVO> memberList;
}








