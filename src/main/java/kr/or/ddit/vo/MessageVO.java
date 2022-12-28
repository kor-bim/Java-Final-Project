package kr.or.ddit.vo;

import kr.or.ddit.commons.enumpkg.MessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageVO {
	private MessageType messageType;
	private String message;
}
