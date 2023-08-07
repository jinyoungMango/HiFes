package com.example.downgrade.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "createdDate")
    @CreatedDate
    private String createdDate;

    @Column(name = "modifiedDate")
    @LastModifiedDate
    private String modifiedDate;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private int parentNo;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User user;  // 작성자

}
