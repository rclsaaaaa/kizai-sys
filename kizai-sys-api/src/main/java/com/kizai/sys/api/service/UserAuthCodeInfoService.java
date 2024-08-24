package com.kizai.sys.api.service;

import org.springframework.stereotype.Service;

@Service
public interface UserAuthCodeInfoService {
	
	public void sendUserAuthCode(int userId, String userAddress);

}
