package com.vastika.userinfosystem.sevice;

import java.util.List;

import com.vastika.userinfosystem.model.User;



public interface UserService {
	void saveUser (User user);
	void updateUser (User user);
	void deleteUser (int id);
	User getUserInfoById(int id);
	List<User>getAllUserInfo();
	
	

}
