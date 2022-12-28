package kr.or.ddit.base.controller;


import javax.inject.Inject;

import org.apache.log4j.Logger;

import kr.or.ddit.hr.organization.service.OrgMapService;

public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Inject
	public OrgMapService orgMapService;

//	/** EgovPropertyService */
//	@Resource(name = "propertiesService")
//	protected EgovPropertyService propertiesService;
//
//	/** Validator */
//	@Resource(name = "beanValidator")
//	protected DefaultBeanValidator beanValidator;
}
