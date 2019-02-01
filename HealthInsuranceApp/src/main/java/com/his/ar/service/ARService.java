package com.his.ar.service;

import com.his.ar.model.UserModel;

public interface ARService {

	public int saveUser(UserModel model);
	public String checkUserMail(String emailId);
}
