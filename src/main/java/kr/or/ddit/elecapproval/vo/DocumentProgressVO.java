package kr.or.ddit.elecapproval.vo;

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
public class DocumentProgressVO {
	private String adNo;
	private String adTitle;
	private String memId;
	private String adDate;
	private String aldtName;
	private String adDel;
	private String alNo;
	private String dfNo;
	private String dsCode;
	private String aprvlStateCode;
	private String dsName;
	private String dsResult;
	

}
