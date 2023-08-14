package hiFes.hiFes.service;

import hiFes.hiFes.domain.Comment;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.domain.festival.OrganizedFestival;

import hiFes.hiFes.dto.commentDto.CommentDto;
import hiFes.hiFes.dto.postDto.*;

import hiFes.hiFes.repository.CommentRepository;
import hiFes.hiFes.repository.PostRepository;
import hiFes.hiFes.repository.festival.OrganizedFestivalRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final OrganizedFestivalRepository organizedFestivalRepository;


    @Transactional
    public List<PostDto> allPostsByFestival(Long festivalId) {
        List<Post> allPostsInFestival = postRepository.findAllByOrganizedFestival_FestivalId(festivalId);
        ArrayList<PostDto> postDtoArrayList = new ArrayList<>();

        for (Post post : allPostsInFestival) {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .postType(post.getPostType())
                    .title(post.getTitle())
                    .commentsCount(post.getComments().size())
                    .views(post.getViews())
                    .createdBy(post.getCreatedBy())
                    .createdAt(post.getCreatedAt())
                    .organizedFestivalId(post.getOrganizedFestival().getFestivalId())
                    .build();
            postDtoArrayList.add(postDto);
        }
        return postDtoArrayList;

    }

    @Transactional
    public List<PostDto> postTypeInFestival(Long festivalId, String postType) {

        List<Post> postList = postRepository.findAllByOrganizedFestival_FestivalIdAndPostType(festivalId, postType);
        ArrayList<PostDto> postDtoArrayList = new ArrayList<>();

        for (Post post : postList) {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .postType(post.getPostType())
                    .title(post.getTitle())
                    .commentsCount(post.getComments().size())
                    .views(post.getViews())
                    .createdBy(post.getCreatedBy())
                    .createdAt(post.getCreatedAt())
                    .organizedFestivalId(post.getOrganizedFestival().getFestivalId())
                    .build();
            postDtoArrayList.add(postDto);
        }

        return postDtoArrayList;
    }


    @Transactional
    public ResponseEntity<?> create(PostCreateDto createDto, MultipartFile image) throws IOException {
        if (image != null) {
        String projectPath = System.getProperty("user.dir") +"\\hifes\\src\\main\\resources\\static\\images";
        UUID uuid = UUID.randomUUID();
        String imageName = uuid + "_" + image.getOriginalFilename();
//        String projectPath = "/home/ubuntu/images";
//        String imageName = image.getOriginalFilename();
        File saveImage = new File(projectPath, imageName);
        image.transferTo(saveImage);
        createDto.setImagePath("/images/" +  imageName);
        }

        // 글 종류에 따라 로직이 조금씩 바뀐다
        String postTypeInput = createDto.getPostType(); // 글 종류

        // 문의 글일 경우, 숨김 여부 정보가 필요
        // 근데 어차피 문의 글이 아니면 false 값일 것이다.
        // 문의 글이라도 비밀 글이 아니면 false 값이니 따로 설정은 안하고 값을 넘겨줄 때
        // true false 만 넘겨주면 될 듯
        // 공지도 어차피 프론트에서 댓글 작성 창이 없으니 넘어가도 될 듯
        // 자유는 딱히 제약 사항은 없고 필요 없는 부분은 null 로 온다
        // 후기 글일 경우, 평점이 있어야 한다.
        Float ratingInput = createDto.getRating();   // 평점

        if (Objects.equals(postTypeInput, "review") && ratingInput == null) {  // 평점이 없거나
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Rating");
        } else if (!Objects.equals(postTypeInput, "review") && ratingInput != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Review");
        }

        Optional<OrganizedFestival> organizedFestival =
                organizedFestivalRepository.findById(createDto.getFestivalId());
        if (organizedFestival.isPresent()) {
            Post post = Post.toEntity(createDto, organizedFestival.get());
            postRepository.save(post);  // 저장을 한다
            return ResponseEntity.status(HttpStatus.CREATED).body("CREATED");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail");
        }
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

        post.increaseView();
        postRepository.save(post);
        // 유저 id 로 작성자 정보 저장 로직
//        Long userId = post.getCreatedBy();

//        String userRecognizer;  // 주최 행사 이름이 될 수도 있고 별명이 될 수도 있으니 recognizer 하나를 만든다
//
//        if (userId >= 1 && userId <= 300) {  // 1 ~ 300 이면 hostUser 니까 주최 행사 명을 설정한다
//            HostUser hostUser = hostUserRepository.findById(userId)
//                    .orElseThrow(() -> new IllegalArgumentException("!!!No Host User Found!!!"));
//            userRecognizer = hostUser.getOrganization();
//
//        } else if (userId > 300) {  // 300 보다 크다면 normalUser 니까 별명을 저장한다
//            NormalUser normalUser = normalUserRepository.findById(userId)
//                    .orElseThrow(() -> new IllegalArgumentException("!!!No Normal User Found!!!"));
//            userRecognizer = normalUser.getNickname();
//        } else {  // 그 외의 경우는 유저가 없는 걸로 판단 에러 발생
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong userId");
//        }

        // 댓글 목록 불러오기
        List<Comment> topLevelComments = commentRepository.findAllByPostIdAndParentIsNull(postId);
        List<CommentDto> topLevelCommentListDto = topLevelComments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

        if (post.getRating() != null) {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .postType(post.getPostType())
                    .isHidden(post.getIsHidden())
                    .hideReason(post.getHideReason())
                    .organizedFestivalId(post.getOrganizedFestival().getFestivalId())
                    .createdBy(post.getCreatedBy())
                    .views(post.getViews())
                    .rating(post.getRating())
                    .topLevelComments(topLevelCommentListDto)
                    .commentsCount(post.getComments().size())
                    .createdAt(post.getCreatedAt())
                    .build();
            return postDto;
        } else {
            PostDto postDto = PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .postType(post.getPostType())
                    .isHidden(post.getIsHidden())
                    .hideReason(post.getHideReason())
                    .organizedFestivalId(post.getOrganizedFestival().getFestivalId())
                    .createdBy(post.getCreatedBy())
                    .views(post.getViews())
                    .topLevelComments(topLevelCommentListDto)
                    .commentsCount(post.getComments().size())
                    .createdAt(post.getCreatedAt())
                    .build();
            return postDto;
        }
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
