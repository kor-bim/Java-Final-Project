package kr.or.ddit.addressbook.service;

import java.util.List;

import kr.or.ddit.addressbook.vo.AddressBookVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 서대철
 * @since 2021. 1. 30.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 30.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
public interface AddressService {
	/**
	 * @param searchAddressVO
	 * @return
	 */
	List<AddressBookVO> selectAddressList(AddressBookVO addressVO);

	/**
	 * @param adbkCode
	 * @return
	 */
	AddressBookVO selectAddress(String adbkCode);

	/**
	 * 주소록 추가
	 * @param addressVO
	 * @return OK, FAILED
	 */
	ServiceResult createAddress(AddressBookVO addressVO);

	/**
	 * @param addressVO
	 * @return
	 */
	ServiceResult updateAddress(AddressBookVO addressVO);

	/**
	 * @param addressVO
	 * @return
	 */
	ServiceResult removeAddress(AddressBookVO addressVO);

	/**
	 * @param addressBookVO
	 * @return
	 */
	List<AddressBookVO> selectSharedAddressList(AddressBookVO addressBookVO);

}
