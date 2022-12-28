package kr.or.ddit.mail.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailVO implements Serializable {
	private Integer rnum;

	private Integer mailNo;
	private String mailTitle;
	private String mailDate;
	private String mailContent;
	private String mailStar;
	private String mailImport;
	private String mailTrash;
	private String mailRead;
	private String senderId;
	private String memName;
	private String memImg;
	private String mailDelete;
	private transient List<MailReceiverVO> mailReceiverList;
	private MailReceiverVO mailReceiverVO;
	private String[] receiverId;

	private int startAttNo;
	private int[] delAttNos;

	private List<Integer> deleteMailNo;
	private List<Integer> restoreMailNo;
	private List<Integer> trashMailNo;

	private transient List<MultipartFile> mailFiles;

	private transient List<MailAttatchVO> attatchList;

	public void setReceiverId(String[] receiverId) {
		mailReceiverList = new ArrayList<MailReceiverVO>();
		if (receiverId == null) {
			return;
		}
		for (String member : receiverId) {
			MailReceiverVO receiverVO = new MailReceiverVO();
			receiverVO.setReceiverId(member);
			mailReceiverList.add(receiverVO);
		}
	}

	public void setMailFiles(List<MultipartFile> mailFiles) {
		if (mailFiles == null || mailFiles.size() == 0)
			return;
		this.mailFiles = mailFiles;
		if(attatchList == null) {
			this.attatchList = new ArrayList<>();
		}
		for (MultipartFile tmp : mailFiles) {
			if (StringUtils.isBlank(tmp.getOriginalFilename()))
				continue;
			attatchList.add(new MailAttatchVO(tmp));
		}
	}
	
}
