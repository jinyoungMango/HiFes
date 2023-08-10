package com.example.downgrade.domain;

import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서 생성을 열어 둘 필요가 없을 때. 보안적으로 권장
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String organization;  // 기관명

    @Column(nullable = false)
    private String orgNo;  // 기관 전화번호

    @Column(nullable = false)
    private String orgCode;  // 기관코드

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String organization, String orgNo, String orgCode, String name, String email, String picture, Role role){
        this.organization = organization;
        this.orgNo = orgNo;
        this.orgCode = orgCode;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String organization, String orgNo, String orgCode, String name, String picture) {
        this.organization = organization;
        this.orgNo = orgNo;
        this.orgCode = orgCode;
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
