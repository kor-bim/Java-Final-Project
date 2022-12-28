package kr.or.ddit.hr.organization.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionListVO {
//	@Valid
	private List<PositionVO> psVOList;  
}
