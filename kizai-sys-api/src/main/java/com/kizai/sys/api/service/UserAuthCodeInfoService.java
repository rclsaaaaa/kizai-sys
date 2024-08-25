package com.kizai.sys.api.service;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.requestBody.UserPasswordResetAuthCodeRequestBody;

@Service
public interface UserAuthCodeInfoService {
	
	public void sendUserAuthCodeMail(int userId, String userAddress);
	public UserInfoDetail authUserInfo(int userId, int authCode);
	public UserInfoDetail authUserPasswordReset(UserPasswordResetAuthCodeRequestBody userPasswordResetAuthCodeRequestBody);

}
