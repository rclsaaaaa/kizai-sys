package com.kizai.sys.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.kizai.sys.api.model.entity.DeviceInfo;
import com.kizai.sys.api.model.entity.DeviceInfoList;
import com.kizai.sys.api.repository.DeviceInfoMapper;
import com.kizai.sys.api.service.DeviceInfoService;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService{

	@Autowired
	private DeviceInfoMapper deviceInfoMapper;

	public List<DeviceInfoList> selectDeviceInfoList(){

		List<DeviceInfoList> deviceInfoList = deviceInfoMapper.selectDeviceInfoList();
		return deviceInfoList;

	}

	public DeviceInfo selectDeviceInfo(String deviceId) {
		DeviceInfo deviceInfo = deviceInfoMapper.selectDeviceInfo(deviceId);
		return deviceInfo;

	}

}
