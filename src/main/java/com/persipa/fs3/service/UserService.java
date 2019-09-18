package com.persipa.fs3.service;

import com.persipa.fs3.pojo.Auctionuser;

public interface UserService {
	public Auctionuser login(String username, String password);

	public void addUser(Auctionuser user);
}
