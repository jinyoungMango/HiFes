package hiFes.hiFes.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Table(name = "picture")
public class Picture extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String oriFileName;  // 파일 원본명

    @Column(nullable = false)
    private String filePath;  // 파일 저장 경로

    private Long fileSize;

    @Builder
    public Picture(String oriFileName, String filePath, Long fileSize) {
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    // Post 정보 저장
//    public void setPost(Post post) {
//        this.post = post;
//
//    // 게시글에 현재 파일이 존재하지 않는다면
//        if(!post.getPicture().contains(this))
//            // 파일 추가
//            post.getPicture().add(this);
//    }


}
