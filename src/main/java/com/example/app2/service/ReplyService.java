package com.example.app2.service;

import com.example.app2.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReplyService {
    //댓글 목록
    public List<ReplyVO> getList(Long boardId, int page, int rowCount, String type, String keyword);

    //다음 페이지 댓글의 개수
    public int getCountOfNextPage(Long boardId, int page, int rowCount, String type, String keyword);

    //댓글 작성
    public void register(ReplyVO replyVO);

    //댓글 수정
    public void modify(ReplyVO replyVO);

    //댓글 삭제
    public void remove(Long replyId);
}
