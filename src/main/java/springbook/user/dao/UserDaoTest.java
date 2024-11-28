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
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import springbook.user.service.UserService;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private List<User> users;

    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;

    @Autowired
    private ApplicationContext context;


    @Before
    public void setUp() {
        users = Arrays.asList(
                new User("bumjin", "박범진", "p1", Level.BASIC, 49, 0),
                new User("joytouch", "이길원", "p2", Level.BASIC, 50, 0),
                new User("erwins", "신승한", "p3", Level.SILVER, 60, 29),
                new User("madnite1", "오민규", "p4", Level.SILVER, 60, 30),
                new User("green", "오민규", "p5", Level.GOLD, 100, 100)
        );

        user1 = users.get(0);
        user2 = users.get(1);
        user3 = users.get(2);
        user4 = users.get(3);
        user5 = users.get(4);
    }

    @Test
    public void bean() {
        assertThat(this.userService, is(this.context.getBean("userService")));
        assertThat(this.userDao, is(this.context.getBean("userDao")));
    }

    @Test
    public void update() {
        this.userDao.deleteAll();

        this.userDao.add(user1);
        this.userDao.add(user2);

        user1.setName("오민규");
        user1.setPassword("springno6");
        user1.setLevel(Level.GOLD);
        user1.setLogin(1000);
        user1.setRecommend(999);

        this.userDao.update(user1);

        User user1update = this.userDao.get(user1.getId());
        checkSameUser(user1, user1update);
        User user2same = this.userDao.get(user2.getId());
        checkSameUser(user2, user2same);
}
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        this.userDao.deleteAll();
        assertThat(this.userDao.getCount(), is(0));

        User user = new User();
        user.setId("whiteship");
        user.setName("baek");
        user.setPassword("married");
        user.setLevel(Level.GOLD);
        user.setLogin(1000);
        user.setRecommend(999);

        this.userDao.add(user);
        assertThat(this.userDao.getCount(), is(1));

        System.out.println(user.getId() + " add success");

        User user2 = this.userDao.get(user.getId());
        checkSameUser(user, user2);

        System.out.println(user2.getId() + " retrieve success");

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        this.userDao.deleteAll();
        assertThat(this.userDao.getCount(), is(0));

        this.userDao.add(this.user1);
        assertThat(this.userDao.getCount(), is(1));

        this.userDao.add(this.user2);
        assertThat(this.userDao.getCount(), is(2));

        this.userDao.add(this.user3);
        assertThat(this.userDao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        this.userDao.deleteAll();
        assertThat(this.userDao.getCount(), is(0));

        this.userDao.get("unknown_id");
    }

    public void checkSameUser(User user1, User user2) {
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
        assertThat(user1.getLevel(), is(user2.getLevel()));
        assertThat(user1.getLogin(), is(user2.getLogin()));
        assertThat(user1.getRecommend(), is(user2.getRecommend()));
    }

    @Test
    public void upgradeLevels() {
        userDao.deleteAll();
        for(User user : users) userDao.add(user);

        userService.upgradeLevels();

        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);
    }

    public void checkLevel(User user, Level expectedLevel) {
        User userUpdate = userDao.get(user.getId());
        assertThat(userUpdate.getLevel(), is(expectedLevel));
    }

    public void checkLevelUpgraded(User user, boolean upgraded) {
        User userUpdate = userDao.get(user.getId());
        if(upgraded) {
            assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
        } else {
            assertThat(userUpdate.getLevel(), is(user.getLevel()));
        }
    }

    @Test
    public void add() {
        
        userDao.deleteAll();

        User userWithLevel = users.get(4);
        User userWithoutLevel = users.get(0);

        userService.add(userWithLevel);
        userService.add(userWithoutLevel);

        User userWithLevelRead = userDao.get(userWithLevel.getId());
        User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());

        assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel()));
        assertThat(userWithoutLevelRead.getLevel(), is(userWithoutLevel.getLevel()));

    }
}