package com.kizai.sys.api.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserAuthCodeInfo;
import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.requestBody.UserPasswordResetAuthCodeRequestBody;
import com.kizai.sys.api.repository.UserAuthCodeInfoMapper;
import com.kizai.sys.api.repository.UserInfoMapper;
import com.kizai.sys.api.service.UserAuthCodeInfoService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthCodeInfoServiceImpl implements UserAuthCodeInfoService{

	@Autowired
	private UserAuthCodeInfoMapper userAuthCodeInfoMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	private final JavaMailSender javaMailSender;

	//認証コードメール送信
	public void sendUserAuthCodeMail(int userId, String userAddress) {

		UserAuthCodeInfo userAuthCodeInfo = createUserAuthCode(userId, userAddress);
		int authCode = userAuthCodeInfo.getAuthCode();

		var message = javaMailSender.createMimeMessage();
		try {
			var messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom("kizai.sys01@gmail.com");
			messageHelper.setTo(userAddress);
			messageHelper.setText("お元気ですかテストです。JavaMail利用です",
					"<span style='color: red'>赤文字出力</span><br>段落下げた" + authCode);
			messageHelper.setSubject("タイトルですよ");

			javaMailSender.send(message);
		} catch(MessagingException e) {
			throw new RuntimeException("メッセージの設定に失敗しました", e);
		}

	}

	//認証コード発行
	public UserAuthCodeInfo createUserAuthCode(int userId, String userAddress) {

		Random rand = new Random();
		int authCode = rand.nextInt(9000) + 1000;

		userAuthCodeInfoMapper.insertUserAuthCodeInfo(userId, authCode);

		UserAuthCodeInfo userAuthCodeInfo = userAuthCodeInfoMapper.selectUserAuthCodeInfo(userId);

		return userAuthCodeInfo;
	}

	//認証コード確認
	public UserInfoDetail authUserInfo(int userId, int authCode) {

		UserAuthCodeInfo userAuthCodeInfo = userAuthCodeInfoMapper.authUserInfo(userId, authCode);

		UserInfoDetail userInfoDetail = userInfoMapper.selectUserInfo(userAuthCodeInfo.getEmployeeId());


		return userInfoDetail;
	}

	//パスワード再設定
	public UserInfoDetail authUserPasswordReset(UserPasswordResetAuthCodeRequestBody userPasswordResetAuthCodeRequestBody) {

		UserInfoDetail userInfoDetail = authUserInfo(userPasswordResetAuthCodeRequestBody.getEmployeeId(), userPasswordResetAuthCodeRequestBody.getAuthCode());

		userPasswordResetAuthCodeRequestBody.setPassword(passwordEncoder.encode(userPasswordResetAuthCodeRequestBody.getPassword()));
		userInfoMapper.updateUserPassword(userPasswordResetAuthCodeRequestBody.getEmployeeId(), userPasswordResetAuthCodeRequestBody.getPassword());
		
		userInfoDetail = userInfoMapper.selectUserInfo(userInfoDetail.getEmployeeId());

		return userInfoDetail;
	}

}
