package com.kizai.sys.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserAuthCodeInfoRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoRegistRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoUpdateRequestBody;
import com.kizai.sys.api.model.requestBody.UserLoginRequestBody;
import com.kizai.sys.api.model.requestBody.UserPasswordResetAuthCodeRequestBody;
import com.kizai.sys.api.service.UserAuthCodeInfoService;
import com.kizai.sys.api.service.UserInfoService;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserAuthCodeInfoService userAuthCodeInfoService;

	Logger logger =LoggerFactory.getLogger(DeviceInfoController.class);

	//ログイン
	@RequestMapping(value = "/user-login", method = RequestMethod.POST)
	public UserInfoDetail login(@RequestBody UserLoginRequestBody userLoginRequestBody) {
		UserInfoDetail userInfoDetail = userInfoService.login(userLoginRequestBody);
		logger.info("get request successed!");
		return userInfoDetail;
	}

	//新規登録
	@RequestMapping(value = "/user-info/registration", method = RequestMethod.PUT)
	public UserInfoDetail insertUserInfo(@RequestBody UserInfoRegistRequestBody userInfoRegistRequestBody) {
		UserInfoDetail userInfo = userInfoService.insertUserInfo(userInfoRegistRequestBody);
		userAuthCodeInfoService.sendUserAuthCodeMail(userInfoRegistRequestBody.getEmployeeId(), userInfoRegistRequestBody.getEmployeeAddress());
		return userInfo;
	}

	//新規登録ユーザー情報認証
	@RequestMapping(value = "/user-info/auth", method = RequestMethod.PUT)
	public UserInfoDetail authUserInfo(@RequestBody UserAuthCodeInfoRequestBody userAuthCodeInfoRequestBody) {
		UserInfoDetail userInfoDetail = userAuthCodeInfoService.authUserInfo(userAuthCodeInfoRequestBody.getEmployeeId(), userAuthCodeInfoRequestBody.getAuthCode());
		if (userInfoDetail == null) {
			logger.info("get request failed!");
		}else {
			logger.info("get request successed!");
		}

		return userInfoDetail;
	}

	//パスワード再設定
	@RequestMapping(value = "/user-info/password-reset/{user_id}", method = RequestMethod.PUT)
	public UserInfoDetail userPasswordReset(@PathVariable("user_id") int userId) {

		userAuthCodeInfoService.sendUserAuthCodeMail(userId, userInfoService.selectUserInfo(userId).getEmployeeAddress());

		return null;
	}

	//パスワード再設定認証
	@RequestMapping(value = "/user-info/password-reset/auth", method = RequestMethod.POST)
	public UserInfoDetail authUserPasswordReset(@RequestBody UserPasswordResetAuthCodeRequestBody userPasswordResetAuthCodeRequestBody) {

		UserInfoDetail userInfoDetail = userAuthCodeInfoService.authUserPasswordReset(userPasswordResetAuthCodeRequestBody);

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


	//ユーザー情報編集
	@RequestMapping(value = "/user-info/update", method = RequestMethod.POST)
	public UserInfoDetail updateUserInfo(@RequestBody UserInfoUpdateRequestBody userInfoUpdateRequestBody) {
		UserInfoDetail userInfo = userInfoService.updateUserInfo(userInfoUpdateRequestBody);
		return userInfo;
	}






}
