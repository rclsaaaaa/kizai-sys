package com.kizai.sys.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfo;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRequestBody;
import com.kizai.sys.api.repository.UserInfoMapper;
import com.kizai.sys.api.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	public List<UserInfoList> selectUserInfoList() {
		List<UserInfoList> userInfoList = userInfoMapper.selectUserInfoList();
		return userInfoList;
	}
	
	public UserInfo selectUserInfo(int userId) {
		UserInfo userInfoList = userInfoMapper.selectUserInfo(userId);
		return userInfoList;
	}
	
	public UserInfo insertUserInfo(UserInfoRequestBody userInfoRequestBody) {
		userInfoMapper.insertUserInfo(userInfoRequestBody);
		UserInfo userInfo = selectUserInfo(userInfoRequestBody.getEmployeeId());
		return userInfo;
	}

}
