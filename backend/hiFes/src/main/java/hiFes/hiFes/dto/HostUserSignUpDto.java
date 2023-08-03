package hiFes.hiFes.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class HostUserSignUpDto {
    private String email;
    private String name;
    private String phoneNo;
    private String organization;
    private String orgNo;
    private String orgCode;
}
