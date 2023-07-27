package hiFes.hiFes.controller;

import hiFes.hiFes.dto.BoardDto;
import hiFes.hiFes.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("board")  // /board 경로로 들어오는 경우 아래 Method 들로 분기 되도록 설정하는 것
public class BoardController {
    private BoardService boardService;
    // 게시판

    // 게시글목록
    // list 경로로 GET 메서드 요청이 들어올 경우 list 메서드와 맵핑시킴.
    // list 경로에 요청 파라미터가 잇을 경우 (?page=1), 그에 따른 페이지네이션을 수행함.
    @GetMapping({"", "/list"})
    public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
        List<BoardDto> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list";
    }

    // 글 쓰는 페이지
    @GetMapping("/write")
    public String write() {
        return "board/write";
    }

    // 글을 쓴 뒤 POST 메서드로 쓴 내용을 DB에 저장
    // 그 후에는 /list 경로로 리다이렉션 해준다.
    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    // 게시물 상세 페이지. {postId} 로 페이지를 넘겨받는다.
    // PathVariable 어노테이션을 통해 postId 를 받는다
    @GetMapping("/post/{postId}")
    public String detail(@PathVariable("postId") Long postId, Model model) {
        BoardDto boardDTO = boardService.getPost(postId);

        model.addAttribute("boardDto", boardDTO);
        return "board/detail";
    }

    // 게시물 수정 페이지. {postId} 로 페이지를 넘겨받는다.
    @GetMapping("/post/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, Model model) {
        BoardDto boardDTO = boardService.getPost(postId);

        model.addAttribute("boardDto", boardDTO);
        return "/board/update";
    }

    // 위가 GET 메서드로 수정 페이지를 불러오는 것.
    // 아래가 PUT 메서드로 수정한 내용을 적용하는 것.
    @PutMapping("post/edit/{postId}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);

        return "redirect:board/list";
    }

    //게시물 삭제는 deletePost 메서드로 간단하게 할 수 있다.
    @DeleteMapping("/post/{postId}")
    public String delete(@PathVariable("postId") Long postId) {
        boardService.deletePost(postId);

        return "redirect:/board/list";
    }

    // 검색
    // keyword를 view 에서 전달받고 Service 로부터받은
    // boardDtoList 를 model 의 attribute 로 전달해준다.
    @GetMapping("/board/search")
    public String search(@RequestParam(value="keyword") String keyword, Model model) {
        List<BoardDto> boardDtoList = boardService.searchPost(keyword);

        model.addAttribute("boardList", boardDtoList);

        return "board/list";
    }

}
