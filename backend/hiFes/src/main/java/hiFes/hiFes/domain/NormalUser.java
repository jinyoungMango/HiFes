package hiFes.hiFes.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="NormalUser")
public class NormalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="normalUserId", updatable = false)
    private Long normalUserId;

    @Column(name="id", nullable = false, length = 15)
    private String id;

    @Column(name="password", nullable = false, length = 31)
    private String password;

    @Column(name="name", nullable = false, length = 7)
    private String name;

    @Column(name="phoneNo", nullable = false, length = 11)
    private String phoneNo;

    @Column(name="nickname", nullable = false, length = 15)
    private String nickname;

    @Column(name="profilePic", nullable = false)
    private String profilePic;

    //일단 파이어베이스 토큰 생략

    ////////////////////////코드 추가 해야함


}
