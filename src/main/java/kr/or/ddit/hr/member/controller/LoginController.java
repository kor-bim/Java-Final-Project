package kr.or.ddit.hr.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.base.vo.PagingVO;
import kr.or.ddit.base.vo.SearchVO;
import kr.or.ddit.board.service.DepartMentBoardService;
import kr.or.ddit.board.vo.DepartMentBoardVO;
import kr.or.ddit.commons.enumpkg.ServiceResult;
import kr.or.ddit.elecapproval.service.ElecapprovalDocumentProgressService;
import kr.or.ddit.elecapproval.service.ElecapprovalInBoxService;
import kr.or.ddit.elecapproval.vo.DocumentProgressVO;
import kr.or.ddit.elecapproval.vo.InBoxVO;
import kr.or.ddit.hr.member.service.MemberService;
import kr.or.ddit.hr.member.vo.MemberVO;
import kr.or.ddit.hr.work.service.WorkAdminService;
import kr.or.ddit.hr.work.vo.WorkTimeVO;
import kr.or.ddit.mail.service.MailService;
import kr.or.ddit.mail.vo.MailVO;

/**
 * @author 윤한빈
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * 
 *      <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 *      </pre>
 */
@Controller
public class LoginController {

	@Autowired
	private JavaMailSender mailSender;

	@Inject
	private MemberService service;

	@Inject
	private ElecapprovalDocumentProgressService approvalService;

	@Inject
	private ElecapprovalInBoxService inboxService;

	@Inject
	WorkAdminService workService;

	@Inject
	MailService mailService;

	@Inject
	DepartMentBoardService departMentBoardservice;

	@RequestMapping("/login/login.do")
	public String loginMove(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
		String targetUrl = (String) session.getAttribute("targetUrl");
		if (targetUrl == null) {
			targetUrl = "sign/signForm";
		}
		req.getSession().removeAttribute("targetUrl");

		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String home(Model model, @AuthenticationPrincipal(expression = "realMember") MemberVO authMember,
			@RequestParam(value = "departmentboardPage", required = false, defaultValue = "1") int departmentCurrentPage,
			@RequestParam(value = "mailInboxPage", required = false, defaultValue = "1") int mailInboxPage,
			@ModelAttribute("searchVO") SearchVO searchVO) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		PagingVO<DocumentProgressVO> progressPagingVO = new PagingVO<>(5, 5);
		progressPagingVO.setUserId(authMember.getMemId());
		int awaitApprovalTotalRecord = approvalService.retrieveApprovalAwaitListCount(progressPagingVO);
		model.addAttribute("awaitCount", awaitApprovalTotalRecord);

		PagingVO<InBoxVO> inBoxPagingVO = new PagingVO<>(5, 5);
		inBoxPagingVO.setUserId(authMember.getMemId());
		int inBoxTotalRecord = inboxService.retrieveInBoxDraftListCount(inBoxPagingVO);
		model.addAttribute("draftCount", inBoxTotalRecord);

		int confirmApprovalTotalRecord = approvalService.retrieveApprovalConfirmListCount(progressPagingVO);
		model.addAttribute("confirmCount", confirmApprovalTotalRecord);

		List<WorkTimeVO> workTime = workService.retrieveWeekWorkTime(authMember.getMemId());
		String workTimeList = mapper.writeValueAsString(workTime);
		model.addAttribute("workTimeList", workTimeList);

		PagingVO<DepartMentBoardVO> departmentBoardPagingVO = new PagingVO<>(100, 5);
		departmentBoardPagingVO.setSearchVO(searchVO);
		departmentBoardPagingVO.setDeptCode(authMember.getDeptCode());

		int totalRecord = departMentBoardservice.retrieveBoardCount(departmentBoardPagingVO);
		departmentBoardPagingVO.setTotalRecord(totalRecord);
		departmentBoardPagingVO.setCurrentPage(departmentCurrentPage);

		List<DepartMentBoardVO> DepartMentBoardList = departMentBoardservice.retrieveBoardList(departmentBoardPagingVO);
		departmentBoardPagingVO.setDataList(DepartMentBoardList);

		model.addAttribute("departmentBoardPagingVO", departmentBoardPagingVO);

		PagingVO<MailVO> mailInboxPagingVO = new PagingVO<>(4, 5);
		mailInboxPagingVO.setSearchVO(searchVO);
		mailInboxPagingVO.setUserId(authMember.getMemId());

		int mailInboxTotalRecord = mailService.retrieveInboxCount(mailInboxPagingVO);
		mailInboxPagingVO.setTotalRecord(mailInboxTotalRecord);
		mailInboxPagingVO.setCurrentPage(mailInboxPage);

		List<MailVO> mailList = mailService.retrieveInboxList(mailInboxPagingVO);
		mailInboxPagingVO.setDataList(mailList);

		model.addAttribute("mailInboxPagingVO", mailInboxPagingVO);

		return "home";
	}

	@RequestMapping("/sign/findIdCheck.do")
	@ResponseBody
	public Map<String, Object> idCheckAndSendMail(@RequestParam Map<String, Object> paramMap) {

		Map<String, Object> authNumber = new HashMap<String, Object>();

		String setfrom = "dbsgksqlschl@naver.com";
		String userName = (String) paramMap.get("username");
		String tomail = (String) paramMap.get("email");
		String password = (int) (Math.random() * 899999) + 100000 + "";

		MemberVO member = new MemberVO();
		member.setMemHp(userName);

		member = service.retrieveMember(member);
		authNumber.put("password", password);
		authNumber.put("memId", member.getMemId());

		if (StringUtils.isNotBlank(member.getMemId())) {
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject("ForestGroupWare에서 발급한 인증번호 입니다"); // 메일제목은 생략이 가능하다
				StringBuffer sb = new StringBuffer();

				sb.append(
						"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
				sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
				sb.append("<head>");
				sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
				sb.append("</head>");
				sb.append(
						"<body style=\"font-family: Arial, '맑은 고딕', 'Malgun Gothic', Dotum, '돋움',sans-serif, Helvetica; font-size:12px; color:#464646; line-height:0;\">");
				sb.append("<div style=\"width:100%; padding:20px 0;\">");
				sb.append("<div style=\"width:700px; margin:0 auto;\">");
				sb.append(
						"<img src=\"https://i.imgur.com/Rx9ncCE.png\" width=\"700px\" height=\"80px\" />");
				sb.append("<div style=\"padding:30px;\">");
				sb.append("<p>임시 비밀번호</p>");
				sb.append(
						"<p style=\"background-color:#f4f4f4; color:#754c24; border:1px solid #d7d7d7; padding:30px; text-align:center; font-size:18px; font-weight:bold; margin-bottom:50px;\">"+password+"</p>");
				sb.append("</div>");
				sb.append(
						"<div style=\"font-size:11px; color:#636363; background-color:#f4f4f4; line-height:1.3em; padding:20px 30px; margin-top:50px;\">");
				sb.append("본 메일은 발신 전용이므로 메일로 문의 시 확인이 불가능합니다.<br />");
				sb.append(
						"다른 궁금하신 사항은  웹사이트(<a href=\"http://192.168.45.49/Forest\" target=\"_blank\">www.Forest.co.kr</a>)의 FAQ에서 확인 또는 Q&amp;A에서 문의해 주세요.<br />");
				sb.append("Copyrjght 2020 Forest GroupWare All rights reserved.");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</div>");
				sb.append("</body>");
				sb.append("</html>");

				message.setText(sb.toString(), "UTF-8", "html");
				mailSender.send(message);
			} catch (Exception e) {
				e.printStackTrace();
				authNumber.put("password", null);
				authNumber.put("memId", null);
				return authNumber;
			}
		} else {
			authNumber.put("password", null);
			authNumber.put("memId", null);
		}

		return authNumber;
	}

	@RequestMapping("/sign/findPassCheck.do")
	@ResponseBody
	public ServiceResult passCheck(@RequestParam Map<String, Object> paramMap) {
		String setfrom = "dbsgksqlschl@naver.com";
		String userName = (String) paramMap.get("username");
		String tomail = (String) paramMap.get("email");
		ServiceResult result = null;

		String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다.
		String password = uuid.substring(0, 10); // uuid를 앞에서부터 10자리 잘라줌.

		MemberVO member = new MemberVO();
		member.setMemId(userName);
		member = service.retrieveMember(member);

		if (member.getMemMail().equals(tomail)) {
			if (StringUtils.isNotBlank(member.getMemId()) && StringUtils.isNotBlank(member.getMemMail())) {
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
					messageHelper.setTo(tomail); // 받는사람 이메일
					messageHelper.setSubject("ForestGroupWare에서 발급한 임시비밀번호 입니다"); // 메일제목은 생략이 가능하다
					StringBuffer sb = new StringBuffer();

					sb.append(
							"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
					sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
					sb.append("<head>");
					sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
					sb.append("</head>");
					sb.append(
							"<body style=\"font-family: Arial, '맑은 고딕', 'Malgun Gothic', Dotum, '돋움',sans-serif, Helvetica; font-size:12px; color:#464646; line-height:0;\">");
					sb.append("<div style=\"width:100%; padding:20px 0;\">");
					sb.append("<div style=\"width:700px; margin:0 auto;\">");
					sb.append(
							"<img src=\"https://i.imgur.com/Rx9ncCE.png\" width=\"700px\" height=\"80px\" />");
					sb.append("<div style=\"padding:30px;\">");
					sb.append("<p>임시 비밀번호</p>");
					sb.append(
							"<p style=\"background-color:#f4f4f4; color:#754c24; border:1px solid #d7d7d7; padding:30px; text-align:center; font-size:18px; font-weight:bold; margin-bottom:50px;\">"+password+"</p>");
					sb.append("</div>");
					sb.append(
							"<div style=\"font-size:11px; color:#636363; background-color:#f4f4f4; line-height:1.3em; padding:20px 30px; margin-top:50px;\">");
					sb.append("본 메일은 발신 전용이므로 메일로 문의 시 확인이 불가능합니다.<br />");
					sb.append(
							"다른 궁금하신 사항은  웹사이트(<a href=\"http://192.168.45.49/Forest\" target=\"_blank\">www.Forest.co.kr</a>)의 FAQ에서 확인 또는 Q&amp;A에서 문의해 주세요.<br />");
					sb.append("Copyrjght 2020 Forest GroupWare All rights reserved.");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</body>");
					sb.append("</html>");

					message.setText(sb.toString(), "UTF-8", "html");
					mailSender.send(message);
					member.setMemPass(password);
					result = service.registTemporaryPassword(member);
				} catch (Exception e) {
					result = ServiceResult.FAILED;
					e.printStackTrace();
				}
			}
		} else {
			result = ServiceResult.NOTEXIST;
		}
		return result;
	}

}
