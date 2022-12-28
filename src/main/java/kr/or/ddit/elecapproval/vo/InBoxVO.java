package kr.or.ddit.elecapproval.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class InBoxVO {
	private String adNo;
	private String adTitle;
	private String memId;
	private String adDate;
	private String adFinishDate;
	private String dfName;
	private String aldtName;
	private String adJgYn;
	private String adDgYn;
	private String adHgYn;
	private String adDel;
	private String alNo;
	private String dfNo;
	private String dsCode;
	private String userId;
}
