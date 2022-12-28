package kr.or.ddit.websocket.event;

import org.springframework.context.ApplicationEvent;

import kr.or.ddit.vo.MessageVO;

@SuppressWarnings("serial")
public class MessageEvent extends ApplicationEvent {

	private MessageVO messageVO;

	public MessageEvent(MessageVO source) {
		super(source);
		this.messageVO = source;
	}

	public MessageVO getMessageVO() {
		return messageVO;
	}

}
