package com.example.app2.mapper;

import com.example.app2.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired private ReplyMapper replyMapper;

    @Test
    public void selectAllByBoardIdTest(){
        replyMapper.selectAllByBoardId(1025L,1,10, null, null).stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void selectCountOfNextPageTest(){
        log.info(replyMapper.selectCountOfNextPage(1025L,1,10,null,null) + "건");
    }

    @Test
    public void insertTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyContent("테스트 댓글 내용3");
        replyVO.setReplyWriter("테스터3");
        replyVO.setBoardId(1025L);
        replyMapper.insert(replyVO);
    }

    @Test
    public void updateTest(){
        ReplyVO replyVO = replyMapper.selectAllByBoardId(1025L,1,10, null, null).get(0);
        replyVO.setReplyContent("수정된 내용");
        replyMapper.update(replyVO);
    }

    @Test
    public void deleteTest(){
        replyMapper.delete(3L);
    }
}
