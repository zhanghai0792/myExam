package com.jm.serviceDao;

import org.springframework.stereotype.Service;

import com.jm.dao.UserDao;
import com.jm.pojo.User;
import com.jm.query.UserQueryParams;

@Service
public class UserServiceDao  extends ServiceDaoTemplate<User, UserDao>{

	public User login(UserQueryParams param)throws Exception{
		return dao.login(param);
	}
	
}
