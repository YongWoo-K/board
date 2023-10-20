package com.example.app2.service;

import com.example.app2.domain.dao.ReplyDAO;
import com.example.app2.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyDAO replyDAO;
    
    //댓글 목록
    @Override
    public List<ReplyVO> getList(Long boardId, int page, int rowCount, String type, String keyword) {
        return replyDAO.findAllByBoardId(boardId, page, rowCount, type.split(""), keyword);
    }
    
    //다음 페이지 댓글의 개수
    @Override
    public int getCountOfNextPage(Long boardId, int page, int rowCount, String type, String keyword) {
        return replyDAO.findCountOfNextPage(boardId, page, rowCount, type.split(""), keyword);
    }
    
    //댓글 작성
    @Override
    public void register(ReplyVO replyVO) {
        replyDAO.save(replyVO);
    }
    
    //댓글 수정
    @Override
    public void modify(ReplyVO replyVO) {
        replyDAO.setReply(replyVO);
    }
    
    //댓글 삭제
    @Override
    public void remove(Long replyId) {
        replyDAO.delete(replyId);
    }
}
