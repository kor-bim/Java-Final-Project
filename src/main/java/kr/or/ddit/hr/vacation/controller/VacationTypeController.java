package kr.or.ddit.hr.vacation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.commons.vo.NotyMessageVO;
import kr.or.ddit.commons.vo.NotyMessageVO.NotyType;
import kr.or.ddit.hr.vacation.service.VacaTypeService;
import kr.or.ddit.hr.vacation.vo.VacaTypeVO;

/**
 * 휴가 종류 관련 컨트롤러
 * 
 * @author 이운주
 * @since 2021. 2. 8.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                 수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 2. 8.      이운주            최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Controller
public class VacationTypeController {
	@Inject
	private VacaTypeService vacaTypeService;
	
	@RequestMapping("/admin/vacatype")
	public String vacaType(VacaTypeVO vacaTypeVO) {
		return "vacation/vacaType";
	}
	
	/**
	 * 휴가 종류 리스트 조회 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 9.
	 * @return
	 */
	@RequestMapping("/admin/vacatype/list.do")
	@ResponseBody
	public List<VacaTypeVO> vacaTypeList() {
		return vacaTypeService.selectVacaTypeList();
	}
	
	/**
	 * 휴가 종류 생성 
	 * 
	 * @author 이운주
	 * @since 2021. 2. 9.
	 * @param vacaTypeVO
	 * @param errors
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/admin/vacatype/insert.do", method=RequestMethod.POST)
	public String insert(VacaTypeVO vacaTypeVO, BindingResult errors, Model model, RedirectAttributes redirectAttributes) {
		
		
		String goPage = "forward:/admin/vacatype";
		
		NotyMessageVO message = null;
		if (!errors.hasErrors()) {
			ServiceResult result = vacaTypeService.insertVacaType(vacaTypeVO);
			switch (result) {
			case OK:
				message = NotyMessageVO.builder("저장되었습니다.")
				.type(NotyType.success)
				.build();
				model.addAttribute("message", message);
				break;

			default:
				message = NotyMessageVO.builder("실패하였습니다.")
				.type(NotyType.error)
				.build();
				model.addAttribute("message", message);
				model.addAttribute("vacaTypeVO", vacaTypeVO);
				break;
			}
		}
		return goPage;
	}
	
	/**
	 * 휴가 종류 삭제
	 * @author 이운주
	 * @since 2021. 2. 9.
	 * @param vacaTypeVO
	 * @return
	 */
	@RequestMapping(value = "/admin/vacatype/delete.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(VacaTypeVO vacaTypeVO
							, @RequestParam(value="vtCode", required=true) String vtCode
							, Model model) {
		vacaTypeVO.setVtCode(vtCode);
		ServiceResult result = vacaTypeService.deleteVacaType(vacaTypeVO);
		
		NotyMessageVO message = null;
		switch (result) {
		case OK:
			message = NotyMessageVO.builder("삭제되었습니다.")
			.type(NotyType.success)
			.build();
			model.addAttribute("message", message);
			break;
		default:
			message = NotyMessageVO.builder("실패하였습니다.")
			.type(NotyType.error)
			.build();
			model.addAttribute("message", message);
			model.addAttribute("vacaTypeVO", vacaTypeVO);
			break;
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("message", message);
		return resultMap;
	}
}
