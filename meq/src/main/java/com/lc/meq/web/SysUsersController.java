package com.lc.meq.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.lc.meq.authorization.manager.TokenManager;
import com.lc.meq.authorization.model.TokenModel;
import com.lc.meq.common.annotation.IgnoreSecurity;
import com.lc.meq.common.constant.StatusCode;
import com.lc.meq.common.utils.Base64Util;
import com.lc.meq.dao.SysUsersDao;
import com.lc.meq.entity.ResultBean;
import com.lc.meq.entity.SysUsers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(description = "帮小蛮登录", tags = "SysUsersController", basePath = "/sysUsers")
@Controller
@RequestMapping("/sysUsers") // url:/模块/资源/{id}/细分 /seckill/list
public class SysUsersController extends BaseController {

	@Autowired
	private SysUsersDao sysUsersDao;

	@Autowired
	private TokenManager tokenManager;

	@ApiOperation(value = "用户登录", notes = "用户登录")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "userCode", value = "账号", required = true, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String") })
	@ResponseBody
	public SysUsers login(HttpServletRequest request, @RequestBody String sysUsers) {
		String uid = "a42b50ab-b436-4162-beda-ca73338eb3b2";
		SysUsers users = sysUsersDao.queryById(uid);
		return users;
	}

	@RequestMapping(value = "queryAll", method = RequestMethod.POST)
	@ResponseBody
	public String queryAll(HttpServletRequest request) {
		List<SysUsers> sysUsers = sysUsersDao.queryUsersAll();
		JSONArray jsonArray = new JSONArray();
		for (SysUsers users : sysUsers) {
			jsonArray.add(users.jsonString(users));
		}
		return jsonArray.toJSONString();
//		return sysUsers.jsonString(sysUsers);
	}

	/**
	 * 登录
	 * @param userCode 用户名
	 * @param userName 用户名称
	 * @return 登录结果信息
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录")
	@RequestMapping(value = "testLogin", method = RequestMethod.POST)
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query",name = "userCode", value = "用户账号", required = true, dataType = "String"),
		@ApiImplicitParam(paramType = "query",name = "userName", value = "用户名称", required = true, dataType = "String")
	})
	@ResponseBody
	@IgnoreSecurity
	public ResultBean testLogin(@RequestParam(value = "userCode") String userCode,
			@RequestParam(value = "userName") String userName) {
		ResultBean resultBean = new ResultBean();
		try {
			SysUsers sysUsers = sysUsersService.getSysUsers(userCode, userName);
			if (sysUsers == null) {
				resultBean.setCode(StatusCode.HTTP_FAILURE);
				resultBean.setMsg("登录失败，用户账号或密码错误！");
			} else {
				TokenModel tokenModel;
				if (tokenManager.hasToken(sysUsers.getUserUid())) {
					tokenManager.deleteToken(sysUsers.getUserUid());
					tokenModel = tokenManager.createToken(sysUsers.getUserUid());
				} else {
					// 创建token
					tokenModel = tokenManager.createToken(sysUsers.getUserUid());
				}

				resultBean.setData(sysUsers);
				resultBean.setToken(Base64Util.encodeData(tokenModel.getToken()));
			}
		} catch (Exception e) {
			resultBean.setCode(StatusCode.HTTP_FAILURE);
			resultBean.setMsg("登录失败，用户账号或密码错误！");
		}

		return resultBean;
	}

	@RequestMapping(value = "/queryUserAll", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean queryUsersAll() {
		ResultBean resultBean = new ResultBean();
		try {
			List<SysUsers> sysUsersList = sysUsersDao.queryUsersAll();
			resultBean.setData(sysUsersList);
		} catch (Exception e) {
			resultBean.setCode(StatusCode.HTTP_FAILURE);
			resultBean.setMsg("请求用户列表失败");
		}
		return resultBean;
	}
}
