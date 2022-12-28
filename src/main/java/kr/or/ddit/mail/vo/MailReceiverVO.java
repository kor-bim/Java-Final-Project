package kr.or.ddit.mail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MailReceiverVO {
	private Integer mailNo;
	private String receiverId;
	private String memName;
	private String memImg;
	private String memId;
	private String recStar;
	private String recImport;
	private String recTrash;
	private String recRead;
	private String recDelete;
}
