package kr.or.ddit.hr.member.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class JobVO implements Serializable{
	private String jobCode;
	private String jobName;

	private String[] delJobCode;
	
}
