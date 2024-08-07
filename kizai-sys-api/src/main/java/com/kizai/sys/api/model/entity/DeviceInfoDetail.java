package com.kizai.sys.api.model.entity;

import lombok.Data;

@Data
public class DeviceInfoDetail {
	
	private String deviceId;
	private String genre;
	private int userEmployeeId;
	private String userEmployeeName;
	private int managerEmployeeId;
	private String managerEmployeeName;
	private String managerStatus;
	private String deviceLocation;
	private String deviceManufacturer;
	private String deviceModel;
	private String memory;
	private String cpu;
	private String os;
	private boolean mouseFlag;
	private boolean securityWireFlag;
	private boolean encryptionFlag;

}
