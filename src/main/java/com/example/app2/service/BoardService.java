package com.example.app2.service;

import com.example.app2.domain.dto.BoardDTO;
import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    //게시글 목록 출력
    public List<BoardVO> getList(Criteria criteria, Search search);

    //게시글 상세보기
    public BoardVO getBoard(Long boardId);

    //게시글 작성
    public void write(BoardDTO boardDTO);

    //게시글 수정
    public void modify(BoardDTO boardDTO);

    //게시글 삭제
    public void remove(Long boardId);

    //전체 게시물 개수 조회
    public int getTotal(Search search);
}
