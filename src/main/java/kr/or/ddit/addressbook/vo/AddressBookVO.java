package kr.or.ddit.addressbook.vo;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 서대철
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of="adbkCode")
public class AddressBookVO implements Serializable{
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	@Size(max = 30)
	private String adbkCode;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class})
	@Size(max = 30)
	private String adbkName;
	@Size(max = 70)
	private String adbkMail;
	@Size(max = 13)
	private String adbkHp;
	@Size(max = 100)
	private String adbkAdd;
	@Size(max = 50)
	private String adbkUrl;
	@Size(max = 10)
	private String adbkBirth;
	@Size(max = 100)
	private String adbkNote;
	private String memId;
	@NotBlank(groups = {InsertGroup.class, UpdateGroup.class})
	private String adbkType;
}
