package com.kizai.sys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfo;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRequestBody;

@Service
public interface UserInfoService {
	
	public List<UserInfoList> selectUserInfoList();
	public UserInfo selectUserInfo(int userId);
	public UserInfo insertUserInfo(UserInfoRequestBody userInfoRequestBody);

}
