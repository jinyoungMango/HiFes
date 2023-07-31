package com.example.downgrade.controller;

import com.example.downgrade.dto.BoardDto;
import com.example.downgrade.service.BoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BoardController {
    private BoardService boardService;

    // 게시판

    // 게시판 목록
    // list 경로로 GET 메서드 요청이 들어올 경우 list 메서드와 맵핑시킴
    // list 경로에 요청 파라미터가 있을 경우 (?page=1), 그에 따른 페이지네이션 수행

    @GetMapping({"", "/list"})
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.getAttribute("boardList", boardList);
        model.getAttribute("pageList", pageList);

        return "board/list";
    }

    // 글 쓰는 페이지
    @GetMapping("/post")
    public String write() {
        return "board/write";
    }

    // 글을 쓴 뒤 POST 메서드로 글 쓴 내용을 DB에 저장
    // 그 후에는 /list 경로로 리디렉션해준다.
    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    // 게시글 상세 페이지. {no}로 페이지 번호를 받는다.
    // PathVariable 어노테이션을 통해 no 를 받음
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 게시글 수정 페이지. {no}로 페이지 번호를 받는ㄷ.
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDto boardDTO = boardService.getPost(no);

        model.addAttribute("boardDto", boardDTO);
        return "board/update";
    }

    // 위는 GET 메서드, 아래는 PUT 메서드. 이를 이용해 수저한 부분을 적용
    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDTO) {
        boardService.savePost(boardDTO);

        return "redirect:/board/list";
    }

    // 게시글 삭제는 deletePost 메서드를 사용해 간단히 할 수 있다.
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/board/list";
    }

    // 검색기능
    // keyword 를 view 에서 전달받고
    // Service 에게 받은 boardDtoList 를 model 의 attribute 로 전달
    // 1. `@GetMapping("/board/search")`: 해당 메서드를 HTTP GET 요청과 매핑하며, `/board/search`라는 URL 경로로 접근할 때 이 메서드가 실행됩니다.
    @GetMapping("/board/search")
    // 2. `public String search(@RequestParam(value = "keyword") String keyword, Model model)`
    // : `search`라는 메서드를 정의하며, 두 개의 파라미터를 입력받습니다.

    // - `@RequestParam(value = "keyword") String keyword`
    // : 클라이언트(브라우저)에서 전달한 `keyword`라는 이름의 쿼리 파라미터 값을 가져오고, 이 값을 `keyword` 변수에 저장합니다.
    // - `Model model`: 뷰(view)에게 전달할 데이터를 저장하는 객체입니다.
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPosts(keyword);
    // `List<BoardDto> boardDtoList = boardService.searchPosts(keyword);`
    // : 검색 키워드를 사용하여 게시물을 검색하는 `searchPosts` 메서드를 호출하고, 그 결과를 `boardDtoList`라는 변수에 저장합니다.
    // `searchPosts` 메서드는 `BoardService` 클래스의 인스턴스에 정의되어 있어야 합니다.

        model.addAttribute("boardList", boardDtoList);
    // `model.addAttribute("boardList", boardDtoList);`: 검색된 게시물 목록을 뷰에게 전달하기 위해,
    // `model` 객체에 `boardList`라는 이름으로 저장합니다.

        return "board/list";
    // `return "board/list";`: 최종적으로 `board/list`라는 뷰 이름을 반환합니다.
    // 이 이름에 해당하는 뷰(예: Thymeleaf, JSP 등)가 게시물 목록을 보여주게 됩니다.
    }
}
