package com.kizai.sys.api.model.entity;

import lombok.Data;

@Data
public class UserInfoDetail {
	private int employeeId;
	private String password;
	private String employeeName;
	private String employeeAddress;
	private String employeeDepartment;
	private String employeePj;
}
