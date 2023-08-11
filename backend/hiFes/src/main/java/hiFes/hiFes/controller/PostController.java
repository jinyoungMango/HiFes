package hiFes.hiFes.controller;

import hiFes.hiFes.dto.reponse.PostDto;
import hiFes.hiFes.dto.reponse.PostListDto;
import hiFes.hiFes.dto.request.PostCreateDto;
import hiFes.hiFes.dto.request.PostUpdateDto;
import hiFes.hiFes.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostCreateDto requestDto) {
        postService.create(requestDto);
        return ResponseEntity.ok(requestDto);
    }


    @GetMapping("/list")
    public List<PostListDto> searchAllPosts() {
        return postService.searchAllPosts();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> findById(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/update/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateDto requestDto) {
        return postService.update(id, requestDto);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
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

