package com.example.app2.dao;

import com.example.app2.domain.dao.BoardDAO;
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
public class BoardDAOTests {
    @Autowired private BoardDAO boardDAO;

    @Test
    public void findAllTest(){
        Criteria criteria = new Criteria();
        Search search = new Search();

        criteria.setPage(1);
        criteria.create(boardDAO.findCountAll(search));

        search.setKeyword("김용우");
        search.setType("w");

        assertThat(boardDAO.findAll(criteria, search).size()).isEqualTo(1);
    }

    @Test
    public void findByBoardIdTest(){
        assertThat(boardDAO.findByBoardId(3L).getBoardContent()).isEqualTo("테스트 내용3");
    }

    @Test
    public void saveTest(){
        BoardVO boardVO = BoardVO.builder()
                .boardTitle("테스트 제목5")
                .boardWriter("테스트5")
                .boardContent("테스트 내용5")
                .build();
        boardDAO.save(boardVO);

        assertThat(boardVO.getBoardId()).isEqualTo(5L);
    }

    @Test
    public void setBoardTest(){
        BoardVO boardVO = boardDAO.findByBoardId(5L);
        boardVO.setBoardContent("수정된 테스트 내용5");
        boardDAO.setBoard(boardVO);
    }

    @Test
    public void deleteTest(){
        boardDAO.delete(5L);
    }

}
