package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserInfoRegistRequestBody {
	private int employeeId;
	private String password;
	private String employeeAddress;
}
