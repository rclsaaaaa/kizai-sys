package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserInfoUpdateRequestBody {
	private int employeeId;
	private String employeeName;
	private String employeeAddress;
	private String employeeDepartment;
	private String employeePj;
}
