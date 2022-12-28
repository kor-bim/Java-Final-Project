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

	<ul id="deptMemTree" class="ztree" style="width:100%;"></ul>


<script type="text/javascript">
$(document).ready(function(){
	var treeObj = $("#deptMemTree");
	
	var curMenu = null, zTree_Menu = null;
	var setting = {
		view: {
			showLine: false,
			showIcon: false,
			selectedMulti: true,
			dblClickExpand: true,
			addDiyDom: addDiyDom
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check : {
		    	enable: true,
		    	chkboxType : {
			    	Y : "p"
			    	, N : "p" 
		    	}
		},
		callback: {
			onCheck: myOnCheck
		}
	};
	
	var deptMemArray =[];
		$.ajax({
			url : $.getContextPath() + "/deptMemTree.do",
			dataType : "JSON",
			success : function(resp) {
				$(resp).each(function(idx, dept){
					let mems = dept.deptMemList;
					let childrens = [];
					$(mems).each(function(index, mem){
						let memName = mem.memName
						let deptName = mem.deptName;
						let psName = mem.psName;
						
						childrens.push({
							name : memName
							, value : mem.memId
							, dataSet : JSON.stringify({
								  "memName"  : memName
								, "deptName" : deptName
								, "psName"   : psName	
							})
						});
					})
			
					let temp = {
						id : dept.deptCode
						, pId : dept.deptParentCode
						, name : dept.deptName
						, value : dept.deptCode
						, nocheck : true 
						, customClass   : 'deptSpan'
						, children : childrens
						, open : idx == 0 ? true : false
					}
					deptMemArray.push(temp);
				})
				$.fn.zTree.init(treeObj, setting, deptMemArray);
				
			},
			error : function(xhr) {
				console.log(xhr);
			}
		});
		
	function addDiyDom(treeId, treeNode) {
		var spaceWidth = 10;
		var switchObj = $("#" + treeNode.tId + "_switch")
			, icoObj = $("#" + treeNode.tId + "_ico")
			, spanObj = $("#" + treeNode.tId + "_span")
			, checkObj = $("#" + treeNode.tId + "_check").attr("value", spanObj.attr("value")).data("mem", spanObj.data("mem"));
		
		switchObj.remove();
		icoObj.before(switchObj);
		icoObj.after(checkObj);
		
		if (treeNode.level > 1) {
			var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
			switchObj.before(spaceStr);
		}
	}
	
	zTree_Menu = $.fn.zTree.getZTreeObj("deptMemTree");

});

</script>
<style type="text/css">
	.ztree * {font-size: 1rem;font-family:"Microsoft Yahei",Verdana,Simsun,"Segoe UI Web Light","Segoe UI Light","Segoe UI Web Regular","Segoe UI","Segoe UI Symbol","Helvetica Neue",Arial}
	.ztree li ul{ margin:0; padding:0}
	.ztree li {line-height:30px;}
	.ztree li a {width:200px;height:30px;padding-top: 0px;}
	.ztree li a:hover {text-decoration:none; background-color: #E7E7E7;}
	.ztree.showIcon li a span.button.switch {visibility:visible}
	.ztree li a.curSelectedNode {background-color:#D4D4D4;border:0;height:30px;}
/* 	.ztree li span {line-height:30px;} */
/* 	.ztree li span.button {margin-top: -7px;} */
	.ztree li span.button.switch {width: 16px;height: 16px;}
	
	.ztree li a .deptSpan {font-weight: bold;}
/* 	.ztree li a.level0 .deptSpan {font-size: 150%} */
	.ztree li span.switch {background-image:url("${cPath }/plugins/zTree_v3-master/demo/en/super/left_menuForOutLook.png"); *background-image:url("${cPath }/plugins/zTree_v3-master/demo/en/super/left_menuForOutLook.gif")}
	
	.ztree li span.button.switch.level0 {width: 20px; height:20px}
	.ztree li span.button.switch.level1 {width: 20px; height:20px}
	.ztree li span.button.noline_open {background-position: 0 0;}
	.ztree li span.button.noline_close {background-position: -18px 0;}
	.ztree li span.button.noline_open.level0 {background-position: 0 -18px;}
	.ztree li span.button.noline_close.level0 {background-position: -18px -18px;}

</style>


	