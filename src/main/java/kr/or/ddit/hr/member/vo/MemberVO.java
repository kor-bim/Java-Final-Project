package kr.or.ddit.hr.member.vo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.hr.work.vo.AttendanceVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO implements Serializable {

	private String memId;
	private String memName;
	private transient String memPass;
	private String memHdate;
	private String memMail;
	private String memComtel;
	private String memHp;
	private String memZip;
	private String memAdd;
	private String memBirth;
	private String memEtc;
	private String autoLogin;
	private String idRemem;
	private String deptCode;
	private String deptName;
	private String psCode;
	private String psName;
	private String jobCode;
	private String jobName;
	private String asCode;
	private String langCode;
	private String memImg;
	private String memSignImg;
	private MultipartFile memSignImage;
	private MultipartFile memImage;

	private List<RoleInfoVO> roleInfoList;

	private List<AttendanceVO> attendanceList;
	
	private String workStatus;

	public void setMemImage(MultipartFile memImge) {
		if (memImge != null && !memImge.isEmpty()) {
			this.memImage = memImge;
			this.memImg = UUID.randomUUID().toString();
		}
	}

	public void setMemSignImage(MultipartFile memSignImage) {
		if (memSignImage != null && !memSignImage.isEmpty()) {
			this.memSignImage = memSignImage;
			this.memSignImg = UUID.randomUUID().toString();
		}
	}

	public void saveTo(File saveFolder) throws IOException {
		if (memImage != null) {
			memImage.transferTo(new File(saveFolder, memImg));
		}
		if (memSignImage != null) {
			memSignImage.transferTo(new File(saveFolder, memSignImg));
		}
	}

}
