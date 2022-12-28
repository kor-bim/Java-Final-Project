package kr.or.ddit.hr.organization.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionVO {
	private String psCode;
	private String psName;
	private String psGrade;
	private String psUseYn;
}
