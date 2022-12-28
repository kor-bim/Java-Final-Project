package kr.or.ddit.addressbook.vo;

import lombok.Data;

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
@Data
public class AddressbookFormVO{
	
	private AddressBookVO addressBookVO;
	
	private AddressBookVO searchAddressVO;
	
	public AddressbookFormVO() {
		this.addressBookVO = new AddressBookVO();
		this.searchAddressVO = new AddressBookVO();
	}

	
	
	
	
}
