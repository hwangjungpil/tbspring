package org.example;

import org.junit.runner.JUnitCore;
import org.springframework.beans.factory.annotation.Autowired;
import springbook.user.dao.ConnectionMaker;
import springbook.user.dao.SimpleConnectionMaker;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        JUnitCore.main("springbook.user.dao.UserDaoTest");
    }
}