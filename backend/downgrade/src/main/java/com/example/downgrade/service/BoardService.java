package com.example.downgrade.service;

import com.example.downgrade.domain.Board;
import com.example.downgrade.dto.BoardDto;
import com.example.downgrade.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardService {

    // boardRepository 객체생성
    private BoardRepository boardRepository;

    private static final int PAGE_PER_BLOCK = 5;  // 블럭에 존재하는 페이지 수
    private static final int POST_PER_PAGE = 6;  // 페이지 당 게식글 개수

    // Entity -> Dto 로 변환
    private BoardDto convertEntityToDto(Board board) {
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();
    }

    @Transactional
    public List<BoardDto> getBoardList(int pageNum) {
        Page<Board> page = boardRepository.findAll(PageRequest.of(
                pageNum - 1, POST_PER_PAGE, Sort.by(Sort.Direction.ASC, "createdDate")
        ));

        List<Board> boardEntities = page.getContent();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        // Optional : NPE(NullPointerException) 방지
        Optional<Board> boardWrapper = boardRepository.findById(id);
        Board board = boardWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate())
                .build();

        return boardDTO;
    }

    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }

    // 검색 API
    @Transactional
    public List<BoardDto> searchPosts(String keyword) {
        List<Board> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if (boardEntities.isEmpty()) return boardDtoList;

        for (Board board : boardEntities) {
            boardDtoList.add(this.convertEntityToDto(board));
        }

        return boardDtoList;
    }

    // 페이징
    @Transactional
    public Long getBoardCount() {
        return boardRepository.count();
    }

    public Integer[] getPageList(Integer curPageNum) {
        Integer[] pageList = new Integer[PAGE_PER_BLOCK];

        // 총 게시글 수
        Double totalPosts = Double.valueOf(this.getBoardCount());

        // 총 게시글 기준으로 마지막 페이지 번호 계산 (올림으로 계산)
        // 전체 게시글 수를 페이지로 나누어서 올림 == 전체 페이지 수
        Integer totalPageNum = (int)(Math.ceil(totalPosts/POST_PER_PAGE));

        // 현재 페이지를 기준으로 블럭의 마지막 번호 계산
        Integer blockLastPageNum = (totalPageNum > curPageNum + PAGE_PER_BLOCK)
                ? curPageNum + PAGE_PER_BLOCK
                : totalPageNum;

        // 페이지 시작 번호 조정
        curPageNum = (curPageNum <=3) ? 1 : curPageNum - 2;

        // 페이지 번호 할당
        for (int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {
            pageList[idx] = val;
        }

        return pageList;
    }
}
