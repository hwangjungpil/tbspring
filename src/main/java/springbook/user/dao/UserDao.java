package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private ConnectionMaker connectionMaker;
    private Connection c;
    private User user;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // JDBC API를 이용한 사용자 등록 코드
        this.c = connectionMaker.makeConnection();
        this.user = user;
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");

        ps.setString(1, this.user.getId());
        ps.setString(2, this.user.getName());
        ps.setString(3, this.user.getPassword());

        ps.executeUpdate();

        ps.addBatch();
        this.c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        // JDBC API를 이용한 사용자 조회 코드
        this.c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();


        this.user.setId(rs.getString("id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.addBatch();
        c.close();

        return this.user;
    }
}
