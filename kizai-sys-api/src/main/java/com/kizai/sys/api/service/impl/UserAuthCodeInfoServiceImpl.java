package com.kizai.sys.api.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.UserAuthCodeInfo;
import com.kizai.sys.api.repository.UserAuthCodeInfoMapper;
import com.kizai.sys.api.service.UserAuthCodeInfoService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthCodeInfoServiceImpl implements UserAuthCodeInfoService{
	
	@Autowired
	private UserAuthCodeInfoMapper userAuthCodeInfoMapper;

	private final JavaMailSender javaMailSender;

	public void sendUserAuthCode(int userId, String userAddress) {
		
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
	
	public UserAuthCodeInfo createUserAuthCode(int userId, String userAddress) {
		
		Random rand = new Random();
		int authCode = rand.nextInt(9000) + 1000;
		
		userAuthCodeInfoMapper.insertUserAuthCodeInfo(userId, authCode);
		
		UserAuthCodeInfo userAuthCodeInfo = userAuthCodeInfoMapper.selectUserAuthCodeInfo(userId);
		
		return userAuthCodeInfo;
	}

}
