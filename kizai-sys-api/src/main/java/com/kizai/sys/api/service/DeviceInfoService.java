package com.kizai.sys.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.DeviceInfoDetail;
import com.kizai.sys.api.model.entity.DeviceInfoList;
import com.kizai.sys.api.model.requestBody.DeviceInfoDetailRequestBody;

@Service
public interface DeviceInfoService {
	
	public List<DeviceInfoList> selectDeviceInfoList();
	public DeviceInfoDetail selectDeviceInfoDetail(String deviceId);
	public void updateDeviceInfoDetail(DeviceInfoDetailRequestBody deviceInfoRequestBody);

}
