package com.example.app2.dao;

import com.example.app2.domain.dao.ReplyDAO;
import com.example.app2.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyDAOTests {
    @Autowired private ReplyDAO replyDAO;

    @Test
    public void findAllByBoardIdTest(){
        replyDAO.findAllByBoardId(1025L,1,10, null, null).stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void saveTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyContent("테스트 댓글 내용3");
        replyVO.setReplyWriter("테스터3");
        replyVO.setBoardId(1025L);
        replyDAO.save(replyVO);
    }

    @Test
    public void findCountOfNextPageTest(){
        log.info(replyDAO.findCountOfNextPage(1025L,1,10,null,null) + "건");
    }

    @Test
    public void setReplyTest(){
        ReplyVO replyVO = replyDAO.findAllByBoardId(1025L,1,10, null, null).get(0);
        replyVO.setReplyContent("수정된 내용");
        replyDAO.setReply(replyVO);
    }

    @Test
    public void deleteTest(){
        replyDAO.delete(4L);
    }
}
