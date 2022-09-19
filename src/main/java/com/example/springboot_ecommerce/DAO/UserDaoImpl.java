package com.example.springboot_ecommerce.DAO;

import com.example.springboot_ecommerce.Mapper.UserMapper;
import com.example.springboot_ecommerce.Pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class UserDaoImpl implements UserDao{
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public void insertUser(Users user) {
        String sql="insert into Users(email,enabledStatus,first_name,last_name,password,photo) value(:email,:enabledStatus,:first_name,:last_name,:password,:photo)";
        Map<String ,Object> map=new HashMap<>();
        map.put(":email",user.getEmail());
        map.put(":enabledStatus",user.getFirst_name());
        map.put(":first_name",user.getFirst_name());
        map.put(":last_name",user.getLast_name());
        map.put(":password",user.getPassword());
        map.put(":photo",user.getPhoto());
        List<Users> list=new ArrayList<>();
        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
    }

    @Override
    public void updateUser(Users user) {
        String sql="update Users) set email=:email,first_name=:first_name,last_name=:last_name,password=:password,photo=:photo) where id=:id";
        Map<String ,Object> map=new HashMap<>();
        map.put("email",user.getEmail());
        map.put("enabledStatus",user.getFirst_name());
        map.put("first_name",user.getFirst_name());
        map.put("last_name",user.getLast_name());
        map.put("password",user.getPassword());
        map.put("photo",user.getPhoto());
        map.put("id",user.getId());

        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public Users getUser(String username, String password) {
        String sql="select * from Users where first_name=:uname and password=:pwd";
        Map<String ,Object> map=new HashMap<>();
        map.put("uname",username);
        map.put("pwd",password);
        List<Users> list=new ArrayList<>();
        list=namedParameterJdbcTemplate.query(sql,map,new UserMapper());
       Users user=new Users();
        if(list.size()>0){
            user=list.get(0);
            return user;
        }
        return null;
    }

    @Override
    public List<Users> ReadByUid(Integer uid) {
        String sql="select * from Users where id=:id";
        Map<String ,Object> map=new HashMap<>();
       map.put("id",uid);
        List<Users> list=new ArrayList<>();
        list=namedParameterJdbcTemplate.query(sql,map,new UserMapper());
        Users user=new Users();
        if(list.size()>0){
            return list;
        }
        return null;
    }
}
