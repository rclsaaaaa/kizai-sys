package com.kizai.sys.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.controller.DeviceInfoController;
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
	
	Logger logger =LoggerFactory.getLogger(DeviceInfoController.class);

	//ユーザー情報一覧取得
	public List<UserInfoList> selectUserInfoList() {
		List<UserInfoList> userInfoList = userInfoMapper.selectUserInfoList();
		return userInfoList;
	}

	//ユーザー情報詳細取得
	public UserInfoDetail selectUserInfo(int userId) {
		UserInfoDetail userInfoDetail = userInfoMapper.selectUserInfo(userId);
		return userInfoDetail;
	}

	//ユーザー情報登録
	public UserInfoDetail insertUserInfo(UserInfoRegistRequestBody userInfoRegistRequestBody) {

		userInfoRegistRequestBody.setPassword(passwordEncoder.encode(userInfoRegistRequestBody.getPassword()));
		userInfoMapper.insertUserInfo(userInfoRegistRequestBody);
		UserInfoDetail userInfo = selectUserInfo(userInfoRegistRequestBody.getEmployeeId());
		return userInfo;
	}

//	//ログイン
//	public UserInfoDetail login(UserLoginRequestBody userLoginRequestBody) {
//		UserInfoDetail userInfoDetail = selectUserInfo(userLoginRequestBody.getEmployeeId());
//		String password = userLoginRequestBody.getPassword();
//		userLoginRequestBody.setPassword(passwordEncoder.encode(userLoginRequestBody.getPassword()));
//
//		if(passwordEncoder.matches(password, userInfoDetail.getPassword())) {
//			logger.info("ログイン成功");
//			return userInfoDetail;
//		}else {
//			logger.info("ログイン失敗");
//			return userInfoDetail;
//		}
//	}

	//ユーザー情報編集
	public UserInfoDetail updateUserInfo(UserInfoUpdateRequestBody userInfoUpdateRequestBody) {

		userInfoMapper.updateUserInfo(userInfoUpdateRequestBody);
		UserInfoDetail userInfoDetail = selectUserInfo(userInfoUpdateRequestBody.getEmployeeId());
		return userInfoDetail;
	}

	

}
