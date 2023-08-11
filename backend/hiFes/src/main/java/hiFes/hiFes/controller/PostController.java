package hiFes.hiFes.controller;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.dto.postDto.*;
import hiFes.hiFes.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @Operation(summary = "게시글 생성, 필요 값 userId(Long), title(String), content(String), postType(String)")
    public ResponseEntity<?> create(@RequestBody PostCreateDto createDto) {
        postService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATE");
//        status(HttpStatus.CREATED).body("OK")
    }


    @GetMapping("/list")
    @Operation(summary = "전체 게시글 조회, 필요 값 X")
    public List<PostListDto> searchAllPosts() {
        return postService.searchAllPosts();
    }

    @GetMapping(value = "/get/{id}")
    @Operation(summary = "게시글 단일조회, 필요 값 postId(Long)")
    public ResponseEntity<PostDto> findById(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        return ResponseEntity.ok(postDto);
    }

//    @PutMapping("/update/{id}")
//    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
//        return postService.update(id, postUpdateDto);
//    }

    @PutMapping("/update/{id}")
    @Operation(summary = "게시글 수정, 필요 값 postId(Long), title(String), content(String), postType(String)")
    public PostUpdateResponseDto updatePost(@PathVariable Long id,
                                            @RequestBody @Valid PostUpdateRequestDto requestDto) {
        ResponseEntity.status(HttpStatus.OK).body("OK");
        return postService.update(id, requestDto);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "게시글 삭제, 필요 값 postId(Long)")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @GetMapping("/{postType}")
    @Operation(summary = "게시글 종류 별로 조회, 필요 값 postType(String)")
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

