package hiFes.hiFes.controller;

<<<<<<< HEAD
import hiFes.hiFes.dto.reponse.CommentResponseDto;
import hiFes.hiFes.dto.request.CommentCreateDto;
import hiFes.hiFes.dto.reponse.CommentListDto;
import hiFes.hiFes.dto.request.CommentUpdateDto;
=======
import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.dto.commentDto.CommentCreateDto;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5
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
        CommentResponseDto responseDto = commentService.create(requestDto);
        return ResponseEntity.ok(responseDto);
    }

<<<<<<< HEAD
//    @GetMapping("/list")
//    public List<CommentListDto> searchAllComments() {
//        return commentService.searchAllComments();
//    }
=======
    @PostMapping("/create/re")
    public ResponseEntity<?> createReComment(@RequestBody CommentCreateDto requestDto) {
        commentService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }
>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5

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
