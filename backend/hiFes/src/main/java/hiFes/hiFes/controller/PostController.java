package hiFes.hiFes.controller;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.dto.postDto.*;
import hiFes.hiFes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostCreateDto createDto) {
        postService.create(createDto);
        return ResponseEntity.ok(createDto);
    }


    @GetMapping("/list")
    public List<PostListDto> searchAllPosts() {
        return postService.searchAllPosts();
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        return ResponseEntity.ok(postDto);
    }

//    @PutMapping("/update/{id}")
//    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
//        return postService.update(id, postUpdateDto);
//    }

    @PutMapping("/update/{id}")
    public PostUpdateResponseDto updatePost(@PathVariable Long id,
                                            @RequestBody @Valid PostUpdateRequestDto requestDto) {
        return postService.update(id, requestDto);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @GetMapping("/{postType}")
    public List<PostListDto> getPosts(@PathVariable String postType) {
        return postService.getPostsByType(postType);
    }



//    private final PictureService fileService;

//    @PostMapping("/post")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Long create(PostFileVo postFileVo) throws Exception {
//        PostCreateDto requestDto = PostCreateDto.builder()
//                .title(postFileVo.getTitle())
//                .content(postFileVo.getContent())
//                .build();
//
//        return postService.create(requestDto, postFileVo.getFiles());
//    }



//    @PostMapping("/create-post")
//    public ResponseEntity<Post> createPost(@RequestBody @Valid PostForm postForm) {
//        Long id = postService.join(post);
//        return new CreatePost(id);
//    }

//    @Data
//    static class CreatePost {
//        private Long id;
//        public CreatePost(Long id) {
//            this.id = id;
//        }
//    }

}

