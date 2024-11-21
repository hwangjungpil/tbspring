package springbook.user.service;

import lombok.Data;
import springbook.user.dao.UserDao;

@Data
public class UserService {
    private UserDao userDao;

    public void upgradeLevels() {
        userDao.getAll().forEach(user -> {
            if (canUpgradeLevel(user)) {
                upgradeLevel(user);
            }
        });
    }

    public boolean canUpgradeLevel(springbook.user.domain.User user) {
        switch (user.getLevel()) {
            case BASIC: return (user.getLogin() >= 50);
            case SILVER: return (user.getRecommend() >= 30);
            case GOLD: return false;
            default: throw new IllegalArgumentException("Unknown Level: " + user.getLevel());
        }
    }
    public void upgradeLevel(springbook.user.domain.User user) {
        user.upgradeLevel();
        userDao.update(user);
    }
}
