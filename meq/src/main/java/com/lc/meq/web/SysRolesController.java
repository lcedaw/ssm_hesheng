package com.lc.meq.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lc.meq.entity.ResultBean;
import com.lc.meq.entity.SysRoles;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "系统角色", tags = "SysRolesController", basePath = "/sysRoles")
@Controller
@RequestMapping("/sysRoles")
public class SysRolesController extends BaseController {

	@ApiOperation(value = "通过ID查询角色",notes = "通过ID查询角色")
	@RequestMapping(value = "queryById",method = RequestMethod.GET)
	@ApiImplicitParam(paramType = "query", name = "roleUid",value = "角色ID", required = true,dataType = "String")
	@ResponseBody
	public ResultBean queryById(@RequestParam(value = "roleUid") String roleUid) throws Exception{
		ResultBean resultBean = new ResultBean();
		SysRoles sysRoles = sysRolesService.queryById(roleUid);
		resultBean.setData(sysRoles);
		return resultBean;
	}
	
	@RequestMapping(value = "updateById",method = RequestMethod.POST)
	@ResponseBody
	public ResultBean updateById(@RequestBody String updateString) throws Exception{
		ResultBean resultBean = new ResultBean();
		try {
			SysRoles sysRoles = JSON.parseObject(updateString,SysRoles.class);
			sysRolesService.updateById(sysRoles);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return resultBean;
	}
}
