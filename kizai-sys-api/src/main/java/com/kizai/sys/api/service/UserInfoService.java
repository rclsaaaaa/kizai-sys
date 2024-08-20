package com.kizai.sys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRegistRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoUpdateRequestBody;
import com.kizai.sys.api.model.requestBody.UserLoginRequestBody;

@Service
public interface UserInfoService {
	
	public List<UserInfoList> selectUserInfoList();
	public UserInfoDetail selectUserInfo(int userId);
	public UserInfoDetail insertUserInfo(UserInfoRegistRequestBody userInfoRequestBody);
	public UserInfoDetail login(UserLoginRequestBody userLoginRequestBody);
	public UserInfoDetail updateUserInfo(UserInfoUpdateRequestBody userInfoUpdateRequestBody);

}
