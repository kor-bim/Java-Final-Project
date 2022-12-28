package kr.or.ddit.addressbook.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.CustomException;
import kr.or.ddit.addressbook.dao.AddressbookDAO;
import kr.or.ddit.addressbook.vo.AddressBookVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;

/**
 * @author 서대철
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                   수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.        서대철       		    최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Service
public class AddressBookServiceImpl implements AddressService{
	
	@Inject
	private AddressbookDAO addressbookDAO;

	/**
	 * 주소록 목록 조회
	 * @param searchAddressVO
	 */
	public List<AddressBookVO> selectAddressList(AddressBookVO addressBookVO) {
		return addressbookDAO.selectAddressList(addressBookVO);
	}

	/**
	 * 공유 주소록 목록 조회
	 * @param addressBookVO
	 */
	@Override
	public List<AddressBookVO> selectSharedAddressList(AddressBookVO addressBookVO) {
		return addressbookDAO.selectSharedAddressList(addressBookVO);
	}
	
	/**
	 * 주소록 상세조회
	 * @param adbk_code
	 * @return
	 */
	public AddressBookVO selectAddress(String adbkCode) {
		AddressBookVO address = addressbookDAO.selectAddress(adbkCode);
		if(address == null) throw new CustomException(adbkCode + "주소록이 없습니다.");
		return address;
	}
	
	/**
	 * 주소록 추가
	 * @param addressVO
	 * @return 성공 OK, 실패 FAILED
	 */
	@Override
	public ServiceResult createAddress(AddressBookVO addressBookVO) {
		
		ServiceResult result = null;
		
		int cnt = addressbookDAO.insertAddress(addressBookVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		}else {
			result = ServiceResult.FAILED;
		}
		return result;
	}
	
	/**
	 * 주소록 수정
	 * @param addressVO
	 * @return 수정 성공 OK, 실패 FAILED
	 */
	@Override
	public ServiceResult updateAddress(AddressBookVO addressBookVO) {
		
		ServiceResult result = null;
		
		int cnt = addressbookDAO.updateAddress(addressBookVO);
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}
	
	/**
	 * 주소록 삭제
	 * @param addressVO
	 * @return 삭제 성공 OK, 실패 FAILED
	 */
	@Override
	public ServiceResult removeAddress(AddressBookVO addressBookVO) {
		ServiceResult result = null;
		
		int cnt = addressbookDAO.deleteAddress(addressBookVO.getAdbkCode());
		
		if(cnt > 0) {
			result = ServiceResult.OK;
		} else {
			result = ServiceResult.FAILED;
		}
		
		return result;
	}

	
}