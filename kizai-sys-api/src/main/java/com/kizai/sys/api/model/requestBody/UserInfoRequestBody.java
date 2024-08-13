package com.kizai.sys.api.model.requestBody;

import lombok.Data;

@Data
public class UserInfoRequestBody {
	private int employeeId;
	private String employeeName;
	private String employeeAddress;
	private String employeeDepartment;
	private String employeePj;

}
