package hiFes.hiFes.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="phoneNo")
    private String phoneNo;

    @Column(name="nickname")
    private String nickname;

    @Column(name="profilePic", nullable = false)
    private String profilePic;

    @JsonManagedReference
    @OneToMany(mappedBy = "eventNotificationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventNotification> eventNotificationList = new ArrayList<>();

    //일단 파이어베이스 토큰 생략

    ////////////////////////코드 추가 해야함


}
