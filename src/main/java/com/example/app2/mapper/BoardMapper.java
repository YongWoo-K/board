package com.example.app2.mapper;

import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    //게시글 목록 출력
    public List<BoardVO> selectAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

    //게시글 상세보기
    public BoardVO selectByBoardId(Long boardId);

    //게시글 작성
    public void insert(BoardVO boardVO);

    //게시글 수정
    public void update(BoardVO boardVO);

    //게시글 삭제
    public void delete(Long boardId);

    //전체 개시글 개수 조회
    public int selectCountAll(@Param("search") Search search);
}
