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
public class AttendanceVO {
	private Integer attdNo;
	private String attdDate;
	private String attdStarth;
	private String attdStartm;
	private String attdEndh;
	private String attdEndm;
	private String wsCode;
	private String memId;

	private String workStartTime;
	private String workEndTime;
}

