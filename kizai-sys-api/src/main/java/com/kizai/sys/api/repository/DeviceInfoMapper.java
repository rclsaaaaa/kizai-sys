package com.kizai.sys.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kizai.sys.api.model.entity.DeviceInfoDetail;
import com.kizai.sys.api.model.entity.DeviceInfoList;

@Mapper
public interface DeviceInfoMapper {
	
	public List<DeviceInfoList> selectDeviceInfoList();
	public DeviceInfoDetail selectDeviceInfoDetail(String deviceId);

}
