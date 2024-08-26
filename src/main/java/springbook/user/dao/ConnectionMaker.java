package springbook.user.dao;

import java.sql.SQLException;

public interface ConnectionMaker {
    public java.sql.Connection makeConnection() throws ClassNotFoundException, SQLException;
}
