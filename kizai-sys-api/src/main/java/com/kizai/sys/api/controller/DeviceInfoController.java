package com.kizai.sys.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kizai.sys.api.model.entity.DeviceInfoDetail;
import com.kizai.sys.api.model.entity.DeviceInfoList;
import com.kizai.sys.api.model.requestBody.DeviceInfoDetailRequestBody;
import com.kizai.sys.api.service.DeviceInfoService;

@RestController
public class DeviceInfoController {
	
	@Autowired
	private DeviceInfoService deviceInfoService;
	
	Logger logger =LoggerFactory.getLogger(DeviceInfoController.class);
	
	//機材情報一覧取得
	@RequestMapping(value = "/device-info", method = RequestMethod.GET)
	public List<DeviceInfoList> selectDeviceInfoList(){
		
		List<DeviceInfoList> deviceInfoList = deviceInfoService.selectDeviceInfoList();
		return deviceInfoList;
		
	}
	
	//機材情報詳細取得
	@RequestMapping(value = "/device-info/{device_id}", method = RequestMethod.GET)
	public DeviceInfoDetail selectDeviceInfoDetail(@PathVariable("device_id") String deviceId) {
		DeviceInfoDetail deviceInfo = deviceInfoService.selectDeviceInfoDetail(deviceId);
		return deviceInfo;
	}
	
	//機材情報編集
	@RequestMapping(value = "/device-info/update/{device_id}", method = RequestMethod.POST)
	public String updateDeviceInfoDetail(@PathVariable("device_id") String deviceId, @RequestBody DeviceInfoDetailRequestBody deviceInfoDetailRequestBody) {
		deviceInfoService.updateDeviceInfoDetail(deviceInfoDetailRequestBody);
		return "成功";
	}

}
