package com.persipa.fs3.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persipa.fs3.mapper.AuctionuserMapper;
import com.persipa.fs3.pojo.Auctionuser;
import com.persipa.fs3.pojo.AuctionuserExample;
import com.persipa.fs3.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuctionuserMapper userMapper;
	
	@Override
	public Auctionuser login(String username, String password) {
		
		AuctionuserExample example = new AuctionuserExample();
		
		AuctionuserExample.Criteria criteria = example.createCriteria();
		
		criteria.andUsernameEqualTo(username);
		criteria.andUserpasswordEqualTo(password);
		
		List<Auctionuser> list = userMapper.selectByExample(example);
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public void addUser(Auctionuser user) {
		user.setUserisadmin(0);
		userMapper.insert(user);
		
	}

}
