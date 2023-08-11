package hiFes.hiFes.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
@Table(name = "hostUser")
public class HostUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hostUserId")
    private Long id;

    private String name;
    private String phoneNo;
    private String organization;
    private String orgNo;
    private String orgCode;
    private String email;
    private String org_code;
    private String phone_no;
    private String org_no;

    private String refreshToken;

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
