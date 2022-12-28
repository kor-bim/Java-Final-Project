<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2021. 2. 17.      이운주     최초작성
* Copyright (c) 2021 by DDIT All right reserved
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<c:set var="deptlist" value="${deptlist}"/>
<div>
	<ul id="treeDemo" class="ztree"></ul>
</div>	

<script type="text/javascript">
$(function(){
	var contextPath = "${cPath}";
	$.getContextPath = function(){
		return "${cPath }";
	}	

	const folderArray = [];
	const setting = {
		    data: {
		        simpleData: {
		            enable: true,
		        }
		    },
		    view : {
		    	showIcon : false,
		    	addDiyDom: addDiyDom,
		    }
		}; // setting end 

	$.ajax({
		url : $.getContextPath() + "/deptTree.do",
		dataType : "JSON",
		success : function(resp) {
			$(resp).each(function(idx, dept){
				folderArray.push({
					id : dept.deptCode,
					pId : dept.deptParentCode,
					name : dept.deptName,
					customClass : "deptSpan",
					value : dept.deptCode,
				});
			});
			$.fn.zTree.init($("#treeDemo"), setting, folderArray);
		},
		error : function(xhr) {
			console.log(xhr);
		}
	});
		
	function addDiyDom(treeId, treeNode) {
		var spaceWidth = 5;
		var spanObj = $("#" + treeNode.tId + "_span"),
		icoObj = $("#" + treeNode.tId + "_ico");
		spanObj.remove();
		icoObj.before(spanObj);
	
		if (treeNode.level >= 1) {
			var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
			spanObj.before(spaceStr);
		}
	}

});
</script>

<style type="text/css">
	.ztree * {font-size: 1.09375rem;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
	.ztree li {line-height:20px;}	
</style>