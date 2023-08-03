package hiFes.hiFes.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class HostUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    private String profilePic;
    private String name;
    private String phoneNo;
    private String organization;
    private String orgNo;
    private String orgCode;
    private String email;
}
