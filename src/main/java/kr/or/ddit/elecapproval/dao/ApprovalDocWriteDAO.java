package kr.or.ddit.elecapproval.dao;


import org.springframework.stereotype.Repository;

import kr.or.ddit.elecapproval.vo.ApprovalDocVO;

@Repository
public interface ApprovalDocWriteDAO {
	
	/* 기안하기 
	 *  - 결재라인 저장 
	 *  - 결재라인 상세 저장  : 결재라인번호alNo 필요 
	 *  - 결재문서 저장         : 결재라인번호alNo 필요 
	 *  - 결재 테이블 셋팅     : 기안자(Complete), 기안자외(Wait) 
	 */
	public int insertLine(ApprovalDocVO approvalDocVO);       // 결재라인 등록
	public int insertLineDetail(ApprovalDocVO approvalDocVO); // 결재라인 상세 등록 
	public int insertApprovalDocument(ApprovalDocVO approvalDocVO);   // 기안문서 등록
	public int insertApprovalList(ApprovalDocVO approvalDocVO); // 결재테이블 셋팅
	
	

}
