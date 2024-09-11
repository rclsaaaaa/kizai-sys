package com.kizai.sys.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kizai.sys.api.model.entity.CustomUserDetails;
import com.kizai.sys.api.model.entity.UserInfoDetail;
import com.kizai.sys.api.repository.UserInfoMapper;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
    public UserDetails loadUserByUsername(String employeeId) throws UsernameNotFoundException {
        // ここで社員番号からユーザーをデータベースから取得
        // 例えばUserInfoDetailを利用してDBから情報を取得する
        UserInfoDetail user = findUserByEmployeeNumber(employeeId);
        if (user == null) {
            throw new UsernameNotFoundException("社員番号が見つかりません: " + employeeId);
        }
        return new CustomUserDetails(String.valueOf(user.getEmployeeId()), user.getPassword());
    }

    private UserInfoDetail findUserByEmployeeNumber(String employeeId) {
        // 社員番号からユーザーを検索するロジックを実装
    	
    	UserInfoDetail userInfoDetail = userInfoMapper.selectUserInfo(Integer.parseInt(employeeId));
		
        return userInfoDetail;
    }

}
