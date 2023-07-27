package hiFes.hiFes.dto;

import hiFes.hiFes.entity.Board;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString  // 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메서드
@NoArgsConstructor  // 인자 없이 객체 생성 가능
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content, String writer, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
