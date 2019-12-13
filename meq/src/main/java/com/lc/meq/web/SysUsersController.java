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
import com.alibaba.fastjson.JSONObject;
import com.lc.meq.constant.StatusCode;
import com.lc.meq.dao.SysUsersDao;
import com.lc.meq.entity.ResultBean;
import com.lc.meq.entity.SysUsers;
import com.lc.meq.service.SysUsersService;

@Controller
@RequestMapping("/sysUsers") // url:/模块/资源/{id}/细分 /seckill/list
public class SysUsersController extends BaseController {

	@Autowired
	private SysUsersDao sysUsersDao;
	
	@RequestMapping(value = "login",method = RequestMethod.POST)
	@ResponseBody
	public SysUsers login(HttpServletRequest request, @RequestBody String sysUsers) {
		String uid = "8f02bda8-e22d-4ea2-b1cb-0873d1be8b6e";
		SysUsers users = sysUsersDao.queryById(uid);
		System.out.println(sysUsers);
		return users;
//		return users.jsonString(users);
//		return sysUsers.jsonString(sysUsers);
	}
	
	@RequestMapping(value = "queryAll",method = RequestMethod.POST)
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
	
	@RequestMapping(value = "testLogin", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean testLogin(@RequestParam(value = "userCode") String userCode, @RequestParam(value = "userName") String userName) {
		ResultBean resultBean = new ResultBean();
		try {
			SysUsers sysUsers = sysUsersService.getSysUsers(userCode, userName);
			if(sysUsers == null) {
				resultBean.setCode(StatusCode.HTTP_FAILURE);
				resultBean.setMsg("登录失败，用户账号或密码错误！");
			}else {
				To
				
				resultBean.setData(sysUsers);
			}
		} catch (Exception e) {
			resultBean.setCode(StatusCode.HTTP_FAILURE);
			resultBean.setMsg("登录失败，用户账号或密码错误！");
		}
		
		return resultBean;
	}
	
}
