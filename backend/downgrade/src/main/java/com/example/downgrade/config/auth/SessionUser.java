package com.example.downgrade.config.auth;

import com.example.downgrade.domain.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // Serializable => 직렬화
    // User 클래스가 있는데 따로 SessionUser 클래스를 만들고 직렬화를 하는 이유 :
    // - Entity 클래스는 직렬화 코드를 넣지 않는게 좋다.
    // - 다른 Entity 와의 관계가 형성될 수도 있기 때문에
    // - @OneToMany, @ManyToMany 등 자식 Entity 를 갖고 있다면 직렬화 대상에 자식들까지 포함되니 성능 이슈, 부수 효과가 발생할 확률이 높다
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
