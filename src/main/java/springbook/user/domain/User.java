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
}

