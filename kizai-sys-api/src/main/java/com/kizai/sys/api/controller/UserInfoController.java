package com.kizai.sys.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.kizai.sys.api.model.entity.CustomUserDetails;
import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserAuthCodeInfoRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoRegistRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoUpdateRequestBody;
import com.kizai.sys.api.model.requestBody.UserLoginRequestBody;
import com.kizai.sys.api.model.requestBody.UserPasswordResetAuthCodeRequestBody;
import com.kizai.sys.api.service.UserAuthCodeInfoService;
import com.kizai.sys.api.service.UserInfoService;
import com.kizai.sys.api.service.impl.CustomUserDetailsServiceImpl;

@RestController
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserAuthCodeInfoService userAuthCodeInfoService;

	Logger logger =LoggerFactory.getLogger(DeviceInfoController.class);

	//ログイン
	@PostMapping("/user-login")
	public ResponseEntity<String> login(@RequestBody UserLoginRequestBody userLoginRequestBody) {
		// ユーザー名とパスワードを取得
		String username = String.valueOf(userLoginRequestBody.getEmployeeId());
		String password = userLoginRequestBody.getPassword(); // リクエストからパスワードも取得する前提

		try {
			// 認証トークンの作成
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

			// 認証を実行
			Authentication result = authenticationManager.authenticate(authentication);

			// 認証成功
			SecurityContextHolder.getContext().setAuthentication(result);
			Authentication token = SecurityContextHolder.getContext().getAuthentication();
			logger.info("ログイン成功！　ユーザー情報" + token.getPrincipal());
			return ResponseEntity.ok("ログイン成功");
		} catch (BadCredentialsException e) {
			// 認証失敗
			logger.warn("認証失敗: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("認証失敗");
		}
	}

	//ユーザー情報編集
	@PostMapping("/user-info/update")
	public UserInfoDetail updateUserInfo(@RequestBody UserInfoUpdateRequestBody userInfoUpdateRequestBody) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		logger.info("ログインユーザー情報：" + authentication.getPrincipal());

		// 認証されていない場合は、エラーレスポンスを返す
		if (authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
			logger.info("セッションが無効です。");
			return null;
		}

		UserInfoDetail userInfo = userInfoService.updateUserInfo(userInfoUpdateRequestBody);
		return userInfo;
	}

	//新規登録
	@PutMapping("/user-info/registration")
	public UserInfoDetail insertUserInfo(@RequestBody UserInfoRegistRequestBody userInfoRegistRequestBody) {
		UserInfoDetail userInfo = userInfoService.insertUserInfo(userInfoRegistRequestBody);
		userAuthCodeInfoService.sendUserAuthCodeMail(userInfoRegistRequestBody.getEmployeeId(), userInfoRegistRequestBody.getEmployeeAddress());
		return userInfo;
	}

	//新規登録ユーザー情報認証
	@PutMapping("/user-info/auth")
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
	@PutMapping("/user-info/password-reset/{user_id}")
	public UserInfoDetail userPasswordReset(@PathVariable("user_id") int userId) {

		userAuthCodeInfoService.sendUserAuthCodeMail(userId, userInfoService.selectUserInfo(userId).getEmployeeAddress());

		return null;
	}

	//パスワード再設定認証
	@PostMapping("/user-info/password-reset/auth")
	public UserInfoDetail authUserPasswordReset(@RequestBody UserPasswordResetAuthCodeRequestBody userPasswordResetAuthCodeRequestBody) {

		UserInfoDetail userInfoDetail = userAuthCodeInfoService.authUserPasswordReset(userPasswordResetAuthCodeRequestBody);

		return userInfoDetail;
	}


	//ユーザー情報一覧取得
	@GetMapping("/user-info")
	public List<UserInfoList> selectUserInfoList() {

		List<UserInfoList> userInfoList = userInfoService.selectUserInfoList();
		return userInfoList;

	}

	//ユーザー情報取得
	@GetMapping("/user-info/{user_id}")
	public UserInfoDetail selectUserInfo(@PathVariable("user_id") int userId) {
		UserInfoDetail userInfo = userInfoService.selectUserInfo(userId);
		return userInfo;

	}




}
