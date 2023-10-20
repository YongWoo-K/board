package com.example.app2.controller;

import com.example.app2.domain.dto.BoardDTO;
import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import com.example.app2.service.BoardService;
import com.example.app2.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {
    private final BoardService boardService;
    private final FileService fileService;

    //게시글 목록
    @GetMapping("list")
    public void showList(Criteria criteria, Search search, Model model){
        model.addAttribute("boards", boardService.getList(criteria, search));
    }

    //게시글 상세보기
    @GetMapping(value = {"read", "modify"})
    public void getBoard(Criteria criteria, Search search, Long boardId, Model model){
        model.addAttribute(boardService.getBoard(boardId));
        model.addAttribute("files", fileService.getList(boardId));
    }

    //게시글 작성 페이지로 이동
    @GetMapping("write")
    public void write(Criteria criteria, Search search, Model model){
        model.addAttribute(new BoardDTO());
    }

    //게시글 작성
    @PostMapping("write")
    public RedirectView write(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardService.write(boardDTO);
        redirectAttributes.addFlashAttribute("boardId", boardDTO.getBoardId());
        return new RedirectView("/board/list");
    }

    //게시글 수정
    @PostMapping("modify")
    public RedirectView modify(Criteria criteria, Search search, BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        boardService.modify(boardDTO);
        redirectAttributes.addAttribute("boardId", boardDTO.getBoardId());
        redirectAttributes.addFlashAttribute(criteria);
        redirectAttributes.addFlashAttribute(search);
        return new RedirectView("/board/read");
    }

    //게시글 삭제
    @GetMapping("remove")
    public RedirectView remove(Long boardId){
        boardService.remove(boardId);
        return new RedirectView("/board/list");
    }
}
