package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
  
    public Object getUsers() {
        return userDao.findAll();
    }
 
}
