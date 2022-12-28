package kr.or.ddit.hr.vacation.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class VacaTypeVO implements Serializable {
	@NotBlank
	@Size(max = 10)
	private String vtCode;
	@NotBlank
	@Size(max = 20)
	private String vtName;
	@Size(max = 1)
	private String vtDdct = "N";

	// select 용
	private int rnum;

	// insert 용
	private List<VacaTypeVO> vacatypeList;

	public String getVtCode() {
		return vtCode;
	}

	public void setVtCode(String vtCode) {
		this.vtCode = vtCode;
	}

	public String getVtName() {
		return vtName;
	}

	public void setVtName(String vtName) {
		this.vtName = vtName;
	}

	public String getVtDdct() {
		return vtDdct;
	}

	public void setVtDdct(String vtDdct) {
		if (vtDdct == null)
			return;
		this.vtDdct = vtDdct;
	}

	public List<VacaTypeVO> getVacatypeList() {
		return vacatypeList;
	}

	public void setVacatypeList(List<VacaTypeVO> vacatypeList) {
		this.vacatypeList = vacatypeList;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

}
