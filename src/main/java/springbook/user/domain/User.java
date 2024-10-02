package springbook.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String id;
    String name;
    String password;

    public User() {
    }
}

