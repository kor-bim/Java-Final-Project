package kr.or.ddit.commons.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
 * 
 *      인증관련
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String saveId = request.getParameter("saveId");
		String mem_id = authentication.getName();
		Cookie idCookie = new Cookie("idCookie", mem_id);
		idCookie.setPath(request.getContextPath());
		int maxAge = 0;
		if (StringUtils.isNotBlank(saveId)) {
			maxAge = 60 * 60 * 24 * 7;
		}
		idCookie.setMaxAge(maxAge);
		response.addCookie(idCookie);

		super.handle(request, response, authentication);

	}
}
