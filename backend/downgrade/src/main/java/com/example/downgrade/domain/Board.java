package com.example.downgrade.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 접근을 열어둘 필요가 없을 때 추천
@Getter
@Entity
@Table(name = "board")  // 이걸 보고 테이블 생성
public class Board extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK 생성 규칙
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(columnDefinition = "integer default 0")
    private int view;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("createdDate desc")  // 댓글 정렬, 생성된 시간 내림차순
    private List<Comment> comments;

    // Java 디자인 패턴, 생성 시점에 값을 채워줌
    @Builder
    public Board(Long id, String writer, String title, String content, int view, User user) {
        // Assert 구문으로 안전한 객체 생성 패턴을 구현
        Assert.hasText(title,"title must not be empty");
        Assert.hasText(content,"content must not be empty");

        this.id = id;
        this.title = title;
        this.writer = writer;
        this.view = view;
        this.content = content;
        this.user = user;
    }

}
