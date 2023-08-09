package hiFes.hiFes.controller;

import hiFes.hiFes.dto.CommentCreateDto;
import hiFes.hiFes.dto.CommentListDto;
import hiFes.hiFes.dto.CommentUpdateDto;
import hiFes.hiFes.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<CommentListDto> searchAllComments() {
        return commentService.searchAllComments();
    }

    @PutMapping("/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody CommentUpdateDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @DeleteMapping("/delete/{postId}")
    public void delete(@PathVariable Long id) {
        commentService.delete(id);
    }
}
