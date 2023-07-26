package hiFes.hiFes.controller;

import hiFes.hiFes.entity.Board;
import hiFes.hiFes.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class BoardController {
    private BoardService boardService;

    @GetMapping("/create")
    public String boardWriteForm() {
        return "boardWrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model, MultipartFile file) throws IOException {
        boardService.create(board, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
