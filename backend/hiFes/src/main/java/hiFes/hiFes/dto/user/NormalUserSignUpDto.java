package hiFes.hiFes.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NormalUserSignUpDto {

    private String email;
    private String name;
    private String profilePic;
    private String phoneNo;
    private String nickname;
    private String accessToken;

}
