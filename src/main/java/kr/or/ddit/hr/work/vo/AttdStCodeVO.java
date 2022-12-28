package kr.or.ddit.hr.work.vo;

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
public class AttdStCodeVO {
	private String asCode;
	private String asName;
	private String asExceptYn;
}
