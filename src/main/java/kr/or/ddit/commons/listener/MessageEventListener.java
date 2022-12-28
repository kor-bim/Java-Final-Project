package kr.or.ddit.commons.listener;

import java.io.IOException;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.MessageVO;
import kr.or.ddit.websocket.event.MessageEvent;

@Component
public class MessageEventListener {

	@Resource(name = "wsSessionSet")
	private Set<WebSocketSession> wsSessionSet;

	@EventListener(MessageEvent.class)
	@Async
	public void eventHandler(MessageEvent event) throws IOException {
		MessageVO messageVO = event.getMessageVO();

		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writeValueAsString(messageVO);
		for (WebSocketSession wsSession : wsSessionSet) {
			wsSession.sendMessage(new TextMessage(payload));
		}
	}
}
