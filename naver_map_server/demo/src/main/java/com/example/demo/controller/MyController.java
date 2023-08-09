package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MyController {
    @GetMapping("/navermap")
    public String hello(Model model) {
        return "navermap"; // templates 폴더 안에 있는 index.html 파일을 사용하여 HTML 렌더링
    }
}