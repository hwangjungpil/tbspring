package springbook.user.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import springbook.user.domain.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


public class UserDaoJdbc implements UserDao {


    private JdbcTemplate jdbcTemplate;


    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }





    public User get(String id) {
        return Optional.ofNullable(this.jdbcTemplate.query(
                "select * from users where id = ?",
                new Object[] {id},
                rs -> rs.next() ? new User(rs.getString("id"), rs.getString("name"), rs.getString("password"), Level.valueOf(rs.getInt("level")), rs.getInt("login"), rs.getInt("recommend")) : null

        )).orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(
                "select * from users",
                (rs, rowNum) -> new User(rs.getString("id"), rs.getString("name"), rs.getString("password"), Level.valueOf(rs.getInt("level")), rs.getInt("login"), rs.getInt("recommend"))
        );
    }

    public void deleteAll()  {
       this.jdbcTemplate.update("delete from users");
    }

    public void add(final User user) {
        this.jdbcTemplate.update("insert into users(id, name, password,level, login, recommend) values(?, ?, ?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(),user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }




    public int getCount()  {
        return Optional.ofNullable(this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class)).orElse(0);
    }

}
