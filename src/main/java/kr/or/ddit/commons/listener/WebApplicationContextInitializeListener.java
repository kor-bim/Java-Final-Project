package kr.or.ddit.commons.listener;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


/**
 * cPath (컨텍스트 패스) 생성
 * 
 * @author 이운주
 * @since 2021. 1. 25.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2021. 1. 25.       이운주              최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@Component
public class WebApplicationContextInitializeListener {
	private static final Logger logger = LoggerFactory.getLogger(WebApplicationContextInitializeListener.class);
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void initalize(ContextRefreshedEvent event) {
		WebApplicationContext container = (WebApplicationContext)event.getApplicationContext();
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		logger.info("{} 컨텍스트 초기화, Root 컨테이너 초기화.", application.getContextPath());
	}
   
	
}
