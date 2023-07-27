package hiFes.hiFes.entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.util.Assert;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "board")
public class Board extends Time {

    // 내가 필요한 필드값
//    private Long postId;
//
//    private int festivalId;
//    private int hostId;
//    private int normalUserId;
//
//    private String title;
//    private String content;
//    private String postType;
//    private boolean hidden;
//    private String hideReason;

//    private String createdBy;
//    private String createdAt;
//    private String updatedAt;
//
//    private int seenTimes;
//    private float rating;
//
//    private String picture;  // picture
//    private String picturePath;  // picturePath

    // 일단 예시를 따라하며 만드는 필드 값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK 생성 규칙
    private Long id;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Board(Long id, String title, String content, String writer, User user) {
        // Assert 구문으로 안전한 객체 생성 패턴을 구현
        Assert.hasText(writer, "작성자가 비어있습니다!");
        Assert.hasText(title, "제목을 입력해주세요!");
        Assert.hasText(content, "내용을 입력해주세요!");

        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
    }

}
