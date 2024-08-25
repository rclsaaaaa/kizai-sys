package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserPasswordResetAuthCodeRequestBody {
	
	private int employeeId;
	private int authCode;
	private String password;

}
