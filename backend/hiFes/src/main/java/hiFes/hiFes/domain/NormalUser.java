package hiFes.hiFes.domain;

import lombok.*;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@AllArgsConstructor
public class NormalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "normal_sequnce")
    @SequenceGenerator(name = "normal_sequnce", sequenceName = "normal_sequnce_name", initialValue = 300)
    @Column
    private Long id;


    private String name;
    private String email;
    private String profilePic;
    private String phoneNo;
    private String nickname;
    private String firebaseToken;

}
