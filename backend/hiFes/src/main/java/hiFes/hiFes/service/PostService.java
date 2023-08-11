package hiFes.hiFes.service;

import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.domain.Post;
<<<<<<< HEAD
import hiFes.hiFes.dto.reponse.PostDto;
import hiFes.hiFes.dto.reponse.PostListDto;
import hiFes.hiFes.dto.request.PostCreateDto;
import hiFes.hiFes.dto.request.PostUpdateDto;
=======
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.commentDto.CommentDto;
import hiFes.hiFes.dto.postDto.*;
import hiFes.hiFes.repository.CommentRepository;
>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5
import hiFes.hiFes.repository.PostRepository;
import hiFes.hiFes.repository.user.HostUserRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final HostUserRepository hostUserRepository;
    private final NormalUserRepository normalUserRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public List<PostListDto> getPostsByType(String postType) {
        List<PostListDto> allPosts = searchAllPosts();
        return allPosts.stream()
                .filter(postListDto -> postListDto.getPostType().equals(postType))
                .collect(Collectors.toList());
    }


    @Transactional
    public void create(PostCreateDto createDto) {
        Long userId = createDto.getCreatedBy();
//        String userType
        String userRecognizer;

        if (userId >= 1 && userId <= 300) {
            HostUser hostUser = hostUserRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("!!!No Host User Found!!!"));
//            userType = "Host";
            userRecognizer = hostUser.getOrganization();

        } else {
            NormalUser normalUser = normalUserRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("!!!No Normal User Found!!!"));

//            userType = "Normal";
            userRecognizer = normalUser.getNickname();
        }
        postRepository.save(createDto.toEntity());
    }

//    @Transactional
//    public Long update(Long id, PostUpdateDto requestDto) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("No Such Post"));
//        post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPostType());
//        return id;
//    }

    public PostUpdateResponseDto update(Long id, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Such Post"));

        post.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPostType());

        Post updatedPost = postRepository.save(post);

        return new PostUpdateResponseDto(updatedPost);
    }


    @Transactional
    public PostDto findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));

        List<Comment> topLevelComments = commentRepository.findAllByPostIdAndParentIsNull(postId);
        List<CommentDto> topLevelCommentListDto = topLevelComments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        PostDto postDto = new PostDto(post);

        return postDto;
    }


    @Transactional(readOnly = true)
    public List<PostListDto> searchAllPosts() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(PostListDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다."));

        postRepository.delete(post);
    }

//    private final PictureRepository pictureRepository;
//    private final FileHandler fileHandler;

    // 게시글 등록

//    @Transactional
//    public Long create(
//            PostCreateDto requestDto,
//            List<MultipartFile> files
//    ) throws Exception {
//        // 파일 처리를 위한 Board 객체 생성
//        Post post = new Post(
//                requestDto.getPictureId(),
//                requestDto.getTitle(),
//                requestDto.getContent()
//        );
//
//        List<Picture> pictures = FileHandler.parseFileInfo(files);
//
//        // 파일이 존재할 때에만 처리
//        if(!pictures.isEmpty()) {
//            for(Picture picture : pictures) {
//                // 파일을 DB에 저장
//                post.addPicture(postRepository.save(picture));
//            }
//        }
//
//        return postRepository.save(post).getId();
//    }
//    @Transactional
//    public Long savePost(PostCreateDto postDto) {
//        return postRepository.save(postDto.toEntity()).getId();
//    }

}
