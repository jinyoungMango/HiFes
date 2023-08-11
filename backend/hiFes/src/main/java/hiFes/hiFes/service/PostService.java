package hiFes.hiFes.service;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.reponse.PostDto;
import hiFes.hiFes.dto.reponse.PostListDto;
import hiFes.hiFes.dto.request.PostCreateDto;
import hiFes.hiFes.dto.request.PostUpdateDto;
import hiFes.hiFes.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    @Transactional
    public Long create(PostCreateDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostUpdateDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No Such Post"));
        post.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }


    @Transactional
    public PostDto findById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
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
