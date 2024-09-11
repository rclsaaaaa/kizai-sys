package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserAuthCodeInfoRequestBody {
	
	private int employeeId;
	private int authCode;

}
