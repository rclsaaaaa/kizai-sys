package com.kizai.sys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.DeviceInfo;
import com.kizai.sys.api.model.entity.DeviceInfoList;

@Service
public interface DeviceInfoService {
	
	public List<DeviceInfoList> selectDeviceInfoList();
	public DeviceInfo selectDeviceInfo(String deviceId);

}
