package springbook.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDaoDeleteAll extends UserDao {

    protected PreparedStatement makeStatement(Connection c) throws Exception {
        PreparedStatement ps = c.prepareStatement("delete from users");
        return ps;
    }
}
