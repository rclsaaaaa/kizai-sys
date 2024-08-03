package com.kizai.sys.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kizai.sys.api.model.entity.DeviceInfo;
import com.kizai.sys.api.model.entity.DeviceInfoList;
import com.kizai.sys.api.model.entity.UserInfo;
import com.kizai.sys.api.service.DeviceInfoService;

@RestController
public class DeviceInfoController {
	
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	Logger logger =LoggerFactory.getLogger(DeviceInfoController.class);
	
	@RequestMapping(value = "/device-info", method = RequestMethod.GET)
	public List<DeviceInfoList> selectDeviceInfoList(){
		
		List<DeviceInfoList> deviceInfoList = deviceInfoService.selectDeviceInfoList();
		logger.info("get request successed!");
		return deviceInfoList;
		
	}
	
	//ユーザー情報取得
	@RequestMapping(value = "/device-info/{device_id}", method = RequestMethod.GET)
	public DeviceInfo selectDeviceInfo(@PathVariable("device_id") String deviceId) {
		DeviceInfo deviceInfo = deviceInfoService.selectDeviceInfo(deviceId);
		return deviceInfo;
	}

}
