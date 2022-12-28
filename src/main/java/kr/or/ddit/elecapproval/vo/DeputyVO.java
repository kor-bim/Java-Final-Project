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
public class DeputyVO {
	private String dpapNo;
	private String useYn;
	private String dpapBegin;
	private String dpapEnd;
	private String requestorId;
	private String deputyId;
}
