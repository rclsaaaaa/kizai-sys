package com.kizai.sys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRequestBody;

@Service
public interface UserInfoService {
	
	public List<UserInfoList> selectUserInfoList();
	public UserInfoDetail selectUserInfo(int userId);
	public UserInfoDetail insertUserInfo(UserInfoRequestBody userInfoRequestBody);

}
