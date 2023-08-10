package hiFes.hiFes.controller;

import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.dto.commentDto.CommentCreateDto;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

//    public CommentController(CommentService commentService) {
//        this.commentService  = commentService;
//    }

    @PostMapping("/create")
    public ResponseEntity<?> createComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }

    @PostMapping("/create/re")
    public ResponseEntity<?> createReComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }

//    @GetMapping("/list")
//    public List<CommentListDto> searchAllComments() {
//        return commentService.searchAllComments();
//    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        commentService.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Comment deleted successfully.");
        return ResponseEntity.ok(response);
    }
}
