package com.kizai.sys.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.DeviceInfoDetail;
import com.kizai.sys.api.model.entity.DeviceInfoList;
import com.kizai.sys.api.model.requestBody.DeviceInfoDetailRequestBody;
import com.kizai.sys.api.repository.DeviceInfoMapper;
import com.kizai.sys.api.service.DeviceInfoService;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService{

	@Autowired
	private DeviceInfoMapper deviceInfoMapper;

	//機材情報一覧取得
	public List<DeviceInfoList> selectDeviceInfoList(){

		List<DeviceInfoList> deviceInfoList = deviceInfoMapper.selectDeviceInfoList();
		return deviceInfoList;

	}

	//機材情報詳細取得
	public DeviceInfoDetail selectDeviceInfoDetail(String deviceId) {
		DeviceInfoDetail deviceInfo = deviceInfoMapper.selectDeviceInfoDetail(deviceId);
		return deviceInfo;

	}
	
	//機材情報編集
	public void updateDeviceInfoDetail(DeviceInfoDetailRequestBody deviceInfoRequestBody) {
		deviceInfoMapper.updateDeviceInfoDetail(deviceInfoRequestBody);
	}

}
