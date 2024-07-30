package com.kizai.sys.api.model.entity;

import lombok.Data;

@Data
public class DeviceInfoList {
	
	private String deviceId;
	private String genre;
	private int userEmployeeId;
	private String userEmployeeName;
	private int managerEmployeeId;
	private String managerEmployeeName;
	private String managerStatus;
	private String deviceLocation;

}
