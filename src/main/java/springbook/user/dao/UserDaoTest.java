package springbook.user.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.User;

import java.sql.SQLException;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;

    @Autowired
    private ApplicationContext context;


    @Before
    public void setUp() {

        this.user1 = new User("gyumee", "박성철", "springno1", Level.BASIC, 1, 0);
        this.user2 = new User("leegw700", "이길원", "springno2", Level.SILVER, 55, 10);
        this.user3 = new User("bumjin", "박범진", "springno3", Level.GOLD, 100, 40);

    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        User user = new User();
        user.setId("whiteship");
        user.setName("baek");
        user.setPassword("married");
        user.setLevel(Level.GOLD);
        user.setLogin(1000);
        user.setRecommend(999);

        this.dao.add(user);
        assertThat(this.dao.getCount(), is(1));

        System.out.println(user.getId() + " add success");

        User user2 = this.dao.get(user.getId());
        checkSameUser(user, user2);

        System.out.println(user2.getId() + " retrieve success");

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        this.dao.add(this.user1);
        assertThat(this.dao.getCount(), is(1));

        this.dao.add(this.user2);
        assertThat(this.dao.getCount(), is(2));

        this.dao.add(this.user3);
        assertThat(this.dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        this.dao.get("unknown_id");
    }

    public void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }
}