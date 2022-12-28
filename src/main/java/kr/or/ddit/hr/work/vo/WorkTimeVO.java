package kr.or.ddit.hr.work.vo;

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
public class WorkTimeVO {
	private Integer workTime;
	private String week;
	private String weekDay;
}
