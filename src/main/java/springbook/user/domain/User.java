package springbook.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import springbook.user.dao.Level;

@Data
@AllArgsConstructor
public class User {
    String id;
    String name;
    String password;
    Level level;
    private int login;
    private int recommend;

    public User() {
    }

    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(this.level + "은 업그레이드가 불가능합니다.");
        } else {
            this.level = nextLevel;
        }
    }
}

