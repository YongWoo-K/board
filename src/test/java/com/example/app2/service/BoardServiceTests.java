package com.example.app2.service;

import com.example.app2.domain.dto.BoardDTO;
import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class BoardServiceTests {
    @Autowired private BoardService boardService;

    @Test
    public void getBoardTest(){
        assertThat(boardService.getBoard(1L).getBoardContent()).isEqualTo("수정된 테스트 내용1");
    }

    @Test
    public void getListTest(){
        Criteria criteria = new Criteria();
        Search search = new Search();

        criteria.setPage(1);
        criteria.create(boardService.getTotal(search));

        search.setKeyword("김용우");
        search.setType("w");

        assertThat(boardService.getList(criteria, search).size()).isEqualTo(1);
    }

    @Test
    public void writeTest(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("테스트 제목6");
        boardDTO.setBoardWriter("테스트6");
        boardDTO.setBoardContent("테스트 내용6");
        boardService.write(boardDTO);

        assertThat(boardDTO.getBoardId()).isEqualTo(6L);
    }

    @Test
    public void modifyTest(){
        BoardVO boardVO = boardService.getBoard(6L);
        boardVO.setBoardContent("수정된 테스트 내용6");
        boardService.modify(boardVO.toDTO());
    }

    @Test
    public void deleteTest(){
        boardService.remove(6L);
    }
}
