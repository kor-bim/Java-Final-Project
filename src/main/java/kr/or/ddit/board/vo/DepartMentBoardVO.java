package kr.or.ddit.board.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 윤한빈
 * @since 2021. 2. 2.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일              수정자             수정내용
 * --------     --------    ----------------------
 * 2021. 2. 2.      윤한빈             최초작성
 * Copyright (c) 2021 by DDIT All right reserved
 * </pre>
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(of = "dbNo")
@ToString(exclude = { "dbFiles", "attatchList", "replyList" })
public class DepartMentBoardVO implements Serializable {

	@NotNull(groups = { UpdateGroup.class, DeleteGroup.class })
	private Integer dbNo;
	private String deptCode;

	@NotBlank
	@Size(max = 200)
	private String dbTitle;
	private String dbDate;
	private String dbContent;

	@NotBlank
	@Size(max = 80)
	private String memId;

	private String dbDelYn;
	private Integer dbParent;
	private Integer dbHit;

	private Integer rnum;
	private Integer rep_cnt;
	private int[] delAttNos;

	private transient List<MultipartFile> dbFiles;

	public void setDbFiles(List<MultipartFile> dbFiles) {
		if (dbFiles == null || dbFiles.size() == 0)
			return;
		this.dbFiles = dbFiles;
		this.attatchList = new ArrayList<>();
		for (MultipartFile tmp : dbFiles) {
			if (StringUtils.isBlank(tmp.getOriginalFilename()))
				continue;
			attatchList.add(new DepartMentBoardAttatchVO(tmp));
		}
	}

	private transient List<DepartMentBoardAttatchVO> attatchList;
	private transient List<DepartMentBoardReplyVO> replyList;
	private int startAttNo;
}
