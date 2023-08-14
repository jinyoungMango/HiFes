package hiFes.hiFes.controller;


import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentUpdateDto;
import hiFes.hiFes.dto.postDto.*;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.PostService;
import hiFes.hiFes.service.user.HostUserService;
import hiFes.hiFes.service.user.NormalUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/post/create")
    @Operation(summary = "게시글 생성, 필요 값 createdBy(Long), title(String), content(String), postType(String), " +
            "isHidden(Boolean), rating(Float), festivalId(Long), 전부 JSON 형식으로 주시면 됩니다.")
    @CrossOrigin("*")
    public ResponseEntity<?> create(@RequestPart(value = "data") PostCreateDto createDto,
                                    @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        return postService.create(createDto, image);
    }


    @GetMapping(value = "/post/get/{id}")
    @Operation(summary = "게시글 단일 조회, 필요 값 postId(Long), 조회하려는 게시글의 postId 를 주시면 됩니다.")
    @CrossOrigin("*")
    public ResponseEntity<?> postDetail(@PathVariable Long id) {
        PostDto postDto = postService.findById(id);
        if (postDto == null || !Objects.equals(postDto.getId(), id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Post Found");
        }
        return ResponseEntity.ok(postDto);
    }

//    @PutMapping("/update/{id}")
//    public Long updatePost(@PathVariable Long id, @RequestBody PostUpdateDto postUpdateDto) {
//        return postService.update(id, postUpdateDto);
//    }


    @PutMapping("/post/update/{id}")
    @Operation(summary = "게시글 수정, 필요 값 postId(Long), title(String), content(String), postType(String)" +
            " 수정할 대상의 postId 는 url 에 같이 넣고 나머지는 JSON 형식으로 주시면 됩니다.")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody @Valid PostUpdateRequestDto requestDto) {

        PostUpdateResponseDto updatedPost = postService.update(id, requestDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }

    @GetMapping("/post/list")
    @Operation(summary = "전체 게시글 조회, 필요 값 X")
    @CrossOrigin("*")
    public List<PostListDto> searchAllPosts() {
        return postService.searchAllPosts();
    }


    @DeleteMapping("/post/delete/{id}")
    @Operation(summary = "게시글 삭제, 필요 값 postId(Long), 삭제할 대상의 postId 는 url 에 같이 넣어주시면 됩니다.")
    @CrossOrigin("*")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }


    @GetMapping("/post/{festivalId}")
    @Operation(summary = "축제별 게시글 조회, 필요 값 festivalId(Long), 현재 축제의 festivalId 를 주시면 됩니다.")
    @CrossOrigin("*")
    public List<PostDto> searchAllPosts(@PathVariable Long festivalId) {
        return postService.allPostsByFestival(festivalId);
    }

    @GetMapping("/post/{festivalId}/{postType}")
    @Operation(summary = "게시글 종류 별로 조회, 필요 값 festivalId(Long), postType(String)," +
            " 현재 축제의 festivalId 와 검색하려는 글 종류를 url 에 같이 주시면 됩니다.")
    public List<PostDto> getPosts(@PathVariable String postType, @PathVariable Long festivalId) {
        return postService.postTypeInFestival(festivalId, postType);
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

