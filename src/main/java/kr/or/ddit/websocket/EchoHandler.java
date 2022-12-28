package kr.or.ddit.websocket;

import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.hr.member.vo.MemberWrapper;

public class EchoHandler extends TextWebSocketHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoHandler.class);

	@Resource(name = "wsSessionSet")
	private Set<WebSocketSession> wsSessionSet;

	// 서버에 접속이 성공 했을때
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Authentication authentication = (Authentication) session.getPrincipal();
		MemberWrapper wrapper = (MemberWrapper) authentication.getPrincipal();
		String name = wrapper.getRealMember().getMemName();
		String address = session.getRemoteAddress().toString();
		LOGGER.info("{}[{}] 연결 수립", name, address);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		LOGGER.error("웹소켓 핸들러 예외 발생 ", exception);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String address = session.getRemoteAddress().toString();
		LOGGER.info("{} 연결 종료", address);
	}

}
