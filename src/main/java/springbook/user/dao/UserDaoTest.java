package springbook.user.dao;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import springbook.user.domain.User;

import java.sql.SQLException;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    private UserDao dao;

    @Before
    public void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        User user = new User();
        user.setId("whiteship");
        user.setName("baek");
        user.setPassword("married");

        this.dao.add(user);
        assertThat(this.dao.getCount(), is(1));

        System.out.println(user.getId() + " add success");

        User user2 = this.dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " retrieve success");

        assertThat(user2.getName(), is(user.getName()));
        assertThat(user2.getPassword(), is(user.getPassword()));
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        this.dao.add(user1);
        assertThat(this.dao.getCount(), is(1));

        this.dao.add(user2);
        assertThat(this.dao.getCount(), is(2));

        this.dao.add(user3);
        assertThat(this.dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        this.dao.deleteAll();
        assertThat(this.dao.getCount(), is(0));

        this.dao.get("unknown_id");
    }
}