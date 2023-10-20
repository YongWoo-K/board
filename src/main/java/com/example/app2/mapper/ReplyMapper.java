package com.example.app2.mapper;

import com.example.app2.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {
    //댓글 목록
    public List<ReplyVO>selectAllByBoardId(@Param("boardId") Long boardId, @Param("page") int page, @Param("rowCount") int rowCount, @Param("types") String[] types, @Param("keyword") String keyword);

    //다음 페이지 댓글의 개수
    public int selectCountOfNextPage(@Param("boardId") Long boardId, @Param("page") int page, @Param("rowCount") int rowCount, @Param("types") String[] types, @Param("keyword") String keyword);

    //댓글 작성
    public void insert(ReplyVO replyVO);

    //댓글 수정
    public void update(ReplyVO replyVO);

    //댓글 삭제
    public void delete(Long replyId);
}
