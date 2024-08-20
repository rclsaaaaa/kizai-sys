package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserLoginRequestBody {
	private int employeeId;
	private String password;

}
