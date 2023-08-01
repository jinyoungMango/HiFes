package hiFes.hiFes.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {
    private String email;
    private String password;
    private String name;
    private String profilePic;
    private String phoneNo;
    private String organization;
    private String orgNo;
    private String orgCode;
}
