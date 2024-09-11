package com.kizai.sys.api.model.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserInfoDetail{
	private int employeeId;
	private String password;
	private String employeeName;
	private String employeeAddress;
	private String employeeDepartment;
	private String employeePj;
}
