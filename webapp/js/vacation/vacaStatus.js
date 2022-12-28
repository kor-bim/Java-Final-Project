/**
 * 직원 휴가 관리
 */



//====부서 트리======================================================== 
const DEPT_TREE_DIV = $("#deptTreeDIV"); 
DEPT_TREE_DIV.load($.getContextPath() + "/ztree/dept");

$(document).on("click", ".deptLayerShow", function(){
	DEPT_TREE_DIV.slideToggle();
})

$(document).mouseup(function (e){
	if( DEPT_TREE_DIV.has(e.target).length === 0)
		DEPT_TREE_DIV.hide();
});

// 부서 이름 클릭 이벤트 
$(document).on("click", ".node_name", function(){
	let deptName = $(this).text();
	let deptCode = $(this).attr("value");

	$("#filtered_org_name").text(deptName);
	$("#searchDept").val(deptCode);
	
	DEPT_TREE_DIV.hide();
})

//================================================================== 

const VACASTATTABLE = $("#vacaStatTable");
VACASTATTABLE.dataTable({
	paging : true,
	search : false,
	dom : "tp",
	columnDefs : [{
	      targets : "_all", 
	      className: "dtCenter"
	    }]
	
});

const VACASTATFORM = $("#vacaStatForm");

$("#searchBtn").on("click", function(e){
	let searchYear = $("#vacaYearSelect").val();
	let searchDept = $("#vacaDeptSelect").val();
	
	$("#searchYear").val(searchYear);
	$("#searchDept").val(searchDept);
	
	VACASTATFORM.submit();
	
});


//====모달관련========================================================

let VACASTATUPDATEMODAL = $("#vacaStatUpdateModal").on("hidden.bs.modal", function() {
	$(this).find(".modal-body");
});

$(document).on("click", ".vacaStatUpdateBtn", function(){
	let memId = $(this).attr("id");
	let searchYear = $("#vacaYearSelect").val();
	console.log(searchYear)
	VACASTATUPDATEMODAL.find(".modal-body").load($.getContextPath() + "/admin/vacaStatForm.do/"+memId +"/"+searchYear, function() {
		VACASTATUPDATEMODAL.modal("show");
	});
});

// 정기/포상휴가 업데이트 폼
$("#modifyBtn").on("click", function(){
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	let VACASTATUPDATEFORM = $("#vacaStatUpdateForm");
	console.log(VACASTATUPDATEFORM);
	VACASTATUPDATEFORM.submit();
})


