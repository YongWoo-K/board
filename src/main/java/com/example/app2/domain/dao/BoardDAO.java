package com.example.app2.domain.dao;

import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import com.example.app2.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardMapper boardMapper;

    //게시글 목록 출력
    public List<BoardVO> findAll(Criteria criteria, Search search){
        return boardMapper.selectAll(criteria, search);
    }

    //게시글 상세보기
    public BoardVO findByBoardId(Long boardId){
        return boardMapper.selectByBoardId(boardId);
    }

    //게시글 작성
    public void save(BoardVO boardVO){
        boardMapper.insert(boardVO);
    }

    //게시글 수정
    public void setBoard(BoardVO boardVO){
        boardMapper.update(boardVO);
    }

    //게시글 삭제
    public void delete(Long boardId){
        boardMapper.delete(boardId);
    }

    //전체 게시글 개수 조회
    public int findCountAll(Search search){
        return boardMapper.selectCountAll(search);
    }
}
