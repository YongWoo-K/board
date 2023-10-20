package com.example.app2.domain.dao;

import com.example.app2.domain.vo.ReplyVO;
import com.example.app2.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    //댓글 목록
    public List<ReplyVO> findAllByBoardId(Long boardId, int page, int rowCount, String[] types, String keyword){
        return replyMapper.selectAllByBoardId(boardId, page, rowCount, types, keyword);
    }

    //다음 페이지 댓글의 개수
    public int findCountOfNextPage(Long boardId, int page, int rowCount, String[] types, String keyword){
        return replyMapper.selectCountOfNextPage(boardId, page, rowCount, types, keyword);
    }

    //댓글 작성
    public void save(ReplyVO replyVO){
        replyMapper.insert(replyVO);
    }

    //댓글 수정
    public void setReply(ReplyVO replyVO){
        replyMapper.update(replyVO);
    }

    //댓글 삭제
    public void delete(Long replyId){
        replyMapper.delete(replyId);
    }
}
