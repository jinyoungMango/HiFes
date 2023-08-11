package hiFes.hiFes.service;

import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.reponse.CommentResponseDto;
import hiFes.hiFes.dto.request.CommentCreateDto;
import hiFes.hiFes.dto.reponse.CommentListDto;
import hiFes.hiFes.dto.request.CommentUpdateDto;
import hiFes.hiFes.repository.CommentRepository;
import hiFes.hiFes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

//    @Transactional
//    public Comment create(CommentCreateDto createDto) {
//        Post post = postRepository.findById(createDto.getPostId())
//                .orElseThrow(() -> new EntityNotFoundException("No Post Found"));
//        Comment parent = null;
//        if (createDto.getParentId() != null) {
//            parent = commentRepository.findById(createDto.getParentId())
//                    .orElseThrow(() -> new EntityNotFoundException("대댓글이 아님"));
//        }
//
//        Comment newComment = Comment.builder()
//                .post(post)
//                .content(createDto.getContent())
//                .parent(parent)
//                .build();
//
//        if (parent != null) {
//            parent.addChildComment(newComment);
//        }
//
//        return commentRepository.save(newComment);
//    }

    @Transactional
    public CommentResponseDto create(CommentCreateDto createDto) {
        Post post = postRepository.findById(createDto.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("No Post Found"));
        Comment parent = null;
        if (createDto.getParentId() != null) {
            parent = commentRepository.findById(createDto.getParentId())
                    .orElseThrow(() -> new EntityNotFoundException("대댓글이 아님"));
        }

        Comment newComment = Comment.builder()
                .post(post)
                .content(createDto.getContent())
                .parent(parent)
                .build();

        if (parent != null) {
            parent.addChildComment(newComment);
        }

        commentRepository.save(newComment);
        return CommentResponseDto(newComment);
    }

    @Transactional
    public Long update(Long id, CommentUpdateDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Comments"));
        comment.update(requestDto.getContent());

        return id;
    }

    @Transactional(readOnly = true)
    public List<CommentListDto> getComments(Long postId) {
        return commentRepository.findByIdOrderByIdDesc().stream()
                .map(CommentListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not existing comment"));

        commentRepository.delete(comment);
    }
}