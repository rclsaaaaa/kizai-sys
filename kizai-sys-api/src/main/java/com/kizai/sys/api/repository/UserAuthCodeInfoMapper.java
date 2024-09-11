package com.kizai.sys.api.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kizai.sys.api.model.entity.UserAuthCodeInfo;

@Mapper
public interface UserAuthCodeInfoMapper {
	
	public void insertUserAuthCodeInfo(@Param("employeeId") int userId, @Param("authCode") int authCode);
	public UserAuthCodeInfo selectUserAuthCodeInfo(int userId);
	public UserAuthCodeInfo authUserInfo(@Param("employeeId") int userId, @Param("authCode") int authCode);
}
