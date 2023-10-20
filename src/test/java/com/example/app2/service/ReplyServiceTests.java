package com.example.app2.service;

import com.example.app2.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyServiceTests {
    @Autowired private ReplyService replyService;

    @Test
    public void getListTest(){
        replyService.getList(1025L,1,10, null, null).stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void registerTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyContent("테스트 댓글 내용3");
        replyVO.setReplyWriter("테스터3");
        replyVO.setBoardId(1025L);
        replyService.register(replyVO);
    }

    @Test
    public void modifyTest(){
        ReplyVO replyVO = replyService.getList(1025L,1,10, null, null).get(0);
        replyVO.setReplyContent("수정된 내용");
        replyService.modify(replyVO);
    }

    @Test
    public void removeTest(){
        replyService.remove(5L);
    }
}
