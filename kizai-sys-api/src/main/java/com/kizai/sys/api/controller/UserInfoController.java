package com.kizai.sys.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRequestBody;
import com.kizai.sys.api.service.UserInfoService;

@RestController
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	//新規登録
	@RequestMapping(value = "/user-resist", method = RequestMethod.PUT)
	public UserInfoDetail insertUserInfo() {
		UserInfoDetail userInfoDetail = null;
		return userInfoDetail;
	}
	
	//ユーザー情報一覧取得
	@RequestMapping(value = "/user-info", method = RequestMethod.GET)
	public List<UserInfoList> selectUserInfoList() {
		List<UserInfoList> userInfoList = userInfoService.selectUserInfoList();
		return userInfoList;
		
	}
	
	//ユーザー情報取得
	@RequestMapping(value = "/user-info/{user_id}", method = RequestMethod.GET)
	public UserInfoDetail selectUserInfo(@PathVariable("user_id") int userId) {
		UserInfoDetail userInfo = userInfoService.selectUserInfo(userId);
		return userInfo;
		
	}
	
	@RequestMapping(value = "/user-info/registration", method = RequestMethod.POST)
	public UserInfoDetail insertUserInfo(@RequestBody UserInfoRequestBody userInfoRequestBody) {
		UserInfoDetail userInfo = userInfoService.insertUserInfo(userInfoRequestBody);
		return userInfo;
		
	}
}
