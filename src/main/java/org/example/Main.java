package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.SimpleConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        DataSource dataSource;
//
//        UserDao dao = new UserDao();
//        dao.setDataSource(dataSource);
//        User user = new User();
//        user.setId("whiteship");
//        user.setName("baek");
//        user.setPassword("married");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + " add success");
//
//        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//
//        System.out.println(user2.getId() + " retrieve success");

    }
}