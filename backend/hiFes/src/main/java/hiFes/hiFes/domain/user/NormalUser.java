package hiFes.hiFes.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="normalUser")
@Builder
@AllArgsConstructor

public class NormalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "normalSequence")
    @SequenceGenerator(name = "normalSequence", sequenceName = "normalSequenceName", initialValue = 300)
    @Column(name = "nomalUserId")
    private Long id;


    private String name;
    private String email;
    private String profilePic;
    private String phoneNo;
    private String nickname;

    private String firebaseToken;
    private String refreshToken;

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
    public void updateFCMToken(String FCMToken){this.firebaseToken = FCMToken;}

}
