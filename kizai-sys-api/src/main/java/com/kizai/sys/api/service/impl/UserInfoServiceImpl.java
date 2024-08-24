package com.kizai.sys.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRegistRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoUpdateRequestBody;
import com.kizai.sys.api.model.requestBody.UserLoginRequestBody;
import com.kizai.sys.api.repository.UserInfoMapper;
import com.kizai.sys.api.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

	public List<UserInfoList> selectUserInfoList() {
		List<UserInfoList> userInfoList = userInfoMapper.selectUserInfoList();
		return userInfoList;
	}

	public UserInfoDetail selectUserInfo(int userId) {
		UserInfoDetail userInfoDetail = userInfoMapper.selectUserInfo(userId);
		return userInfoDetail;
	}

	public UserInfoDetail insertUserInfo(UserInfoRegistRequestBody userInfoRegistRequestBody) {
		
		userInfoRegistRequestBody.setPassword(passwordEncoder.encode(userInfoRegistRequestBody.getPassword()));
		userInfoMapper.insertUserInfo(userInfoRegistRequestBody);
		UserInfoDetail userInfo = selectUserInfo(userInfoRegistRequestBody.getEmployeeId());
		return userInfo;
	}

	public UserInfoDetail login(UserLoginRequestBody userLoginRequestBody) {
		UserInfoDetail userInfoDetail = selectUserInfo(userLoginRequestBody.getEmployeeId());
		String password = userLoginRequestBody.getPassword();
		userLoginRequestBody.setPassword(passwordEncoder.encode(userLoginRequestBody.getPassword()));
		
		if(passwordEncoder.matches(password, userInfoDetail.getPassword())) {
			System.out.println("一致したよ");
            return userInfoDetail;
		}
		
		return null;
	}

	public UserInfoDetail updateUserInfo(UserInfoUpdateRequestBody userInfoUpdateRequestBody) {

		userInfoMapper.updateUserInfo(userInfoUpdateRequestBody);
		UserInfoDetail userInfoDetail = selectUserInfo(userInfoUpdateRequestBody.getEmployeeId());
		return userInfoDetail;
	}

}
