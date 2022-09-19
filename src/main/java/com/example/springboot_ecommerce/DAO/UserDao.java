package com.example.springboot_ecommerce.DAO;

import com.example.springboot_ecommerce.Pojo.Users;

import java.util.List;

public interface UserDao {
    public void insertUser(Users user);

    public void updateUser(Users user);

    public Users getUser(String username,String password);

    public List<Users> ReadByUid(Integer uid);
}
