package com.kizai.sys.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRegistRequestBody;
import com.kizai.sys.api.model.requestBody.UserInfoUpdateRequestBody;
import com.kizai.sys.api.model.requestBody.UserLoginRequestBody;

@Mapper
public interface UserInfoMapper {
	public List<UserInfoList> selectUserInfoList();
	public UserInfoDetail selectUserInfo(int userId);
	public void insertUserInfo(UserInfoRegistRequestBody userInfoRegistRequestBody);
	public UserInfoDetail login(UserLoginRequestBody userLoginRequestBody);
	public void updateUserInfo(UserInfoUpdateRequestBody userInfoUpdateRequestBody);
	public void updateUserPassword(@Param("employeeId") int userId, @Param("password") String password);
}
