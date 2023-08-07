package com.example.downgrade.dto;

import com.example.downgrade.domain.Board;
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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String writer, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
