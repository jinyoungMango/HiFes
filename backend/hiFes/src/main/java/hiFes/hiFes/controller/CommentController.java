package hiFes.hiFes.controller;

import hiFes.hiFes.dto.commentDto.CommentCreateDto;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.dto.reponse.CommentResponseDto;
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


    @PostMapping("/create")
    @Operation(summary = "댓글 생성, 필요 값 postId(Long), content(Long), parentId(Long)")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }

    @PostMapping("/create/re")
    @Operation(summary = "대댓글 생성, 필요 값 postId(Long), content(Long), parentId(Long)")

    public ResponseEntity<?> createReComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }


//    @GetMapping("/list")
//    public List<CommentListDto> searchAllComments() {
//        return commentService.searchAllComments();
//    }


    @PutMapping("/update/{id}")
    @Operation(summary = "댓글 수정, 필요 값 commentId(Long), content(Long)")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateDto requestDto) {
        return commentService.update(id, requestDto);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "댓글 삭제, 필요 값 commentId(Long)")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
