package com.kizai.sys.api.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.model.entity.UserInfoList;
import com.kizai.sys.api.model.requestBody.UserInfoRequestBody;

@Mapper
public interface UserInfoMapper {
	public List<UserInfoList> selectUserInfoList();
	public UserInfoDetail selectUserInfo(int userId);
	public void insertUserInfo(UserInfoRequestBody userInfoRequestBody);
}
