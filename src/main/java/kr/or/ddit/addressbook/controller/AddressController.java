package kr.or.ddit.addressbook.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.addressbook.service.AddressService;
import kr.or.ddit.addressbook.vo.AddressBookVO;
import kr.or.ddit.addressbook.vo.AddressbookFormVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.InsertGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.member.vo.MemberVO;

/**
 * @author 서대철
 * @since 2021. 1. 26.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                      수정자               		수정내용
 * --------     --------    ----------------------
 * 2021. 1. 26.       서대철                   	최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */

@Controller
public class AddressController {

	@Inject
	private AddressService addressService;

	private void addCommandAttribute(Model model) {
		model.addAttribute("command", "MODIFY");
	}
	
	/**
	 * 타입별 주소록 목록 조회
	 * @param session
	 * @param model
	 * @param adbkType
	 * @return
	 */
	@RequestMapping({ "/addressbook/{adbkType}" })
	public String addressbook(@AuthenticationPrincipal(expression = "realMember") MemberVO authMember, Model model,
			@PathVariable("adbkType") String adbkType) {
		model.addAttribute("memId", authMember.getMemId());
		model.addAttribute("adbkType", adbkType);
		return "/addressbook/addressbookList";
	}
	
	/**
	 * 주소록 목록조회
	 * @param memId
	 * @param adbkType
	 * @return
	 */
	@RequestMapping("/addressbook/list.do")
	@ResponseBody
	public List<AddressBookVO> addressList(@RequestParam("memId") String memId,
			@RequestParam("adbkType") String adbkType) {
		AddressBookVO addressBookVO = new AddressBookVO();
		addressBookVO.setMemId(memId);
		addressBookVO.setAdbkType(adbkType);
		List<AddressBookVO> addressList = addressService.selectAddressList(addressBookVO);

		return addressList;
	}

	/**
	 * 주소록 상세조회
	 * @param adbkCode
	 * @param adbkType
	 * @param model
	 * @return
	 */
	@RequestMapping({"/addressbook/{adbkType}/{adbkCode}"})
	public String selectAddressbook(@PathVariable(value = "adbkCode", required = true) String adbkCode,
			@PathVariable("adbkType") String adbkType, Model model) {

		AddressBookVO addressBookVO = addressService.selectAddress(adbkCode);
		model.addAttribute("address", addressBookVO);

		return "addressbook/addressView";
	}

	/**
	 * 주소록 추가 폼
	 * @param addressbookVO
	 * @param memId
	 * @param model
	 * @return
	 */
	@RequestMapping("/addressbook/insertAddressForm.do")
	public String insertAddressForm(AddressBookVO addressbookVO, @RequestParam("memId") String memId, Model model) {
		model.addAttribute("memId", memId);
		return "addressbook/others/addressForm";
	}

	/**
	 * 주소록 추가
	 * @param addressbookVO
	 * @param errors
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addressbook/insert.do")
	public String insertAddressbook(
			@Validated(InsertGroup.class) @ModelAttribute("addressBookVO") AddressBookVO addressbookVO, Errors errors,
			Model model, RedirectAttributes redirectAttributes) {
		String goPage = null;
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = addressService.createAddress(addressbookVO);
			String adbkType = addressbookVO.getAdbkType();
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("주소가 추가되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/addressbook/" + adbkType;
				break;
			default:
				message = NotyMessageVO.builder("등록이 실패하였습니다.").type(NotyType.error).build();
				model.addAttribute("message", message);
				goPage = "addressbook/addressForm";
				break;
			}
		} else {
			goPage = "addressbook/addressForm";
		}
		return goPage;
	}
	
	/**
	 * 주소록 수정 폼
	 * @param addressbookFormVO
	 * @param model
	 * @param adbkCode
	 * @return
	 */
	@RequestMapping("/addressbook/updateAddressForm/{adbkCode}")
	public String updateAddressForm(AddressbookFormVO addressbookFormVO, Model model,
			@PathVariable("adbkCode") String adbkCode) {
		addCommandAttribute(model);
		AddressBookVO addressBookVO = addressbookFormVO.getAddressBookVO();
		addressBookVO.setAdbkCode(adbkCode);
		addressBookVO = addressService.selectAddress(addressBookVO.getAdbkCode());

		model.addAttribute("addressBookVO", addressBookVO);

		return "addressbook/others/addressForm";
	}

	/**
	 * 주소록 수정
	 * @param addressbookVO
	 * @param errors
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addressbook/update.do")
	public String updateAddressbook(
			@Validated(UpdateGroup.class) @ModelAttribute("addressbookVO") AddressBookVO addressbookVO, Errors errors,
			Model model, RedirectAttributes redirectAttributes) {
		addCommandAttribute(model);
		String goPage = null;
		NotyMessageVO message = null;

		boolean valid = !errors.hasErrors();

		if (valid) {
			ServiceResult result = addressService.updateAddress(addressbookVO);
			String adbkType = addressbookVO.getAdbkType();
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("주소가 수정되었습니다.").type(NotyType.success).build();
				redirectAttributes.addFlashAttribute("message", message);
				goPage = "redirect:/addressbook/" + adbkType;
				break;
			default:
				message = NotyMessageVO.builder("수정 실패했습니다.").type(NotyType.error).build();
				goPage = "addressbook/addressForm";
				model.addAttribute("message", message);
				break;
			}
		} else {
			goPage = "addressbook/addressForm";
		}
		return goPage;
	}

	/**
	 * 주소록 삭제
	 * @param addressbookVO
	 * @param adbkCode
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/addressbook/delete.do/{adbkCode}")
	public String deleteAddressbook(
			@Validated(DeleteGroup.class) @ModelAttribute("address") AddressBookVO addressbookVO,
			@PathVariable("adbkCode") String adbkCode, RedirectAttributes redirectAttributes) {

		addressbookVO.setAdbkCode(adbkCode);
		addressbookVO = addressService.selectAddress(addressbookVO.getAdbkCode());

		NotyMessageVO message = null;

		ServiceResult result = addressService.removeAddress(addressbookVO);
		String adbkType = addressbookVO.getAdbkType().toLowerCase();

		switch (result) {
		case OK:
			message = NotyMessageVO.builder("주소가 삭제되었습니다.").type(NotyType.success).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("주소 삭제를 실패했습니다.").type(NotyType.error).build();
			redirectAttributes.addFlashAttribute("message", message);
			break;
		}
		return "redirect:/addressbook/" + adbkType;
	}
}