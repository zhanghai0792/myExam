package com.jm.dao;
import com.jm.pojo.User;
import com.jm.query.UserQueryParams;
public interface UserDao extends DaoTemplate<User,UserQueryParams>{
  public User login(UserQueryParams params)throws Exception;
}