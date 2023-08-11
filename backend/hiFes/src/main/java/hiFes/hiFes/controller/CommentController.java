package hiFes.hiFes.controller;

import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.dto.commentDto.CommentCreateDto;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

//    public CommentController(CommentService commentService) {
//        this.commentService  = commentService;
//    }

    @PostMapping("/comment/create")
    @Operation(summary = "댓글 생성, 필요 값 postId(Long), content(Long), parentId(Long)" +
            " 전부 JSON 형식으로 주시면 됩니다. 단 parentId 는 없으면 0이 아니라 null 로 주셔야 됩니다.")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }

    @PostMapping("/comment/create/re")
    @Operation(summary = "대댓글 생성, 필요 값 postId(Long), content(Long), parentId(Long) 전부 JSON 형식으로 주시면 됩니다.")
    public ResponseEntity<?> createReComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }

//    @GetMapping("/list")
//    public List<CommentListDto> searchAllComments() {
//        return commentService.searchAllComments();
//    }

    @PutMapping("/comment/update/{id}")
    @Operation(summary = "댓글 수정, 필요 값 commentId(Long), content(Long), 수정하려는 댓글의 commentId 는 url 에 포함해 주시고" +
            " 나머지는JSON 형식으로 주시면 됩니다.")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @DeleteMapping("/comment/delete/{id}")
    @Operation(summary = "댓글 삭제, 필요 값 commentId(Long), 삭제하려는 댓글의 commentId만 주시면 됩니다.")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
