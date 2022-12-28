package kr.or.ddit.elecapproval.vo;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import kr.or.ddit.commons.validator.groups.DeleteGroup;
import kr.or.ddit.commons.validator.groups.UpdateGroup;
import lombok.Data;

@Data
public class DocumentsVO {
	@NotNull(groups= {UpdateGroup.class, DeleteGroup.class})
	@Min(0)
	private Integer dfNo; // 문서 양식 번호
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 100)
	private String dfName; // 문서 양식명
	private String dfContent; //문서 양식 내용
	@Size(max = 1)
	private String dfUseYn; // 사용 여부
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 10)
	private String docTypeCode; // 문서분류 코드
	private String docTypeName; // 문서분류 이름 
	
	@Size(max = 10)
	private String docAliasCode; // 문서 약칭 코드
	private String docALias;     // 문서 약칭 
	
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 10)
	private String scuCode; // 보안 등급 코드
	@NotBlank(groups=UpdateGroup.class)
	@Size(max = 10)
	private String presCode; // 보존연한 코드
	private String presName; // 보존연한
	
	@Size(max = 255)
	private String dfDescription; // 문서 양식 설명
	@Size(max=20)
	private String alCode;  // 결재선 종류 코드
	private String alName;  // 결재선 종류 이름
	
	private List<DocumentsVO> docTypeList;
}
