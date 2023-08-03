package hiFes.hiFes.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="Host")
public class Host {
    @Column(name="id", nullable = false)
    private Long id;

    @Id
    @Column(name="hostId", length = 15)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hostId;

    @Column(name = "password", nullable = false, length = 31)
    private String password;

    @Column(name = "name", nullable = false, length = 7)
    private String name;

    @Column(name="phoneNo", nullable = false, length = 11)
    private String phoneNo;

    @Column(name="organization", nullable = false, length = 31)
    private String organization;

    @Column(name="orgNo", nullable = false, length = 11)
    private String orgNo;

    @Column(name="orgCode", nullable = false, length = 31)
    private String  orgCode;

    @Column(name="email", nullable = false)
    private String email;

    @JsonManagedReference
    @OneToMany(mappedBy = "host", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrganizedFestival> organizedFestivals = new ArrayList<>();

}
