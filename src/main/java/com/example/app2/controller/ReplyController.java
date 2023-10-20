package com.example.app2.controller;

import com.example.app2.domain.dto.ReplyDTO;
import com.example.app2.domain.vo.ReplyVO;
import com.example.app2.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/replies/*")
public class ReplyController {
    private final ReplyService replyService;
    
    //댓글 목록
    @GetMapping("list/{boardId}/{page}/{rowCount}")
    public ReplyDTO getList(@PathVariable Long boardId, @PathVariable int page, @PathVariable int rowCount, String type, String keyword){
        return new ReplyDTO(replyService.getList(boardId, page, rowCount, type, keyword), replyService.getCountOfNextPage(boardId, page, rowCount, type, keyword));
    }
    
    //댓글 작성
    @PostMapping("write")
    public void write(@RequestBody ReplyVO replyVO){
        replyService.register(replyVO);
    }
    
    //댓글 수정
    @PostMapping("modify")
    public void modify(@RequestBody ReplyVO replyVO){
        replyService.modify(replyVO);
    }

    //댓글 삭제
    @DeleteMapping("{replyId}")
    public void remove(@PathVariable Long replyId){
        replyService.remove(replyId);
    }
}
