package com.lc.meq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lc.meq.service.SysUsersService;

/**
 * 统一注入service实例
 * @author ljz
 * @date 2019-12-13
 */
@Controller
public class BaseController {
	
	@Autowired
	SysUsersService sysUsersService;
	
}
