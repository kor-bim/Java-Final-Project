package kr.or.ddit.addressbook.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.addressbook.vo.AddressBookVO;

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
@Repository
public interface AddressbookDAO {
	/**
	 * @param searchAddressVO
	 * @return
	 */
	List<AddressBookVO> selectAddressList(AddressBookVO addressBookVO);

	/**
	 * @param addressBookVO
	 * @return
	 */
	List<AddressBookVO> selectSharedAddressList(AddressBookVO addressBookVO);

	/**
	 * @param adbk_code
	 * @return
	 */
	AddressBookVO selectAddress(String adbkCode);

	/**
	 * @param addressVO
	 * @return
	 */
	int insertAddress(AddressBookVO addressBookVO);

	/**
	 * @param addressVO
	 * @return
	 */
	int updateAddress(AddressBookVO addressBookVO);

	/**
	 * @param adbkCode
	 * @return
	 */
	int deleteAddress(String adbkCode);

}
