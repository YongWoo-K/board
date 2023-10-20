package com.example.app2.mapper;

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
public class BoardMappertests {
    @Autowired private BoardMapper boardMapper;

    @Test
    public void selectByBoardIdtest(){
        assertThat(boardMapper.selectByBoardId(1L).getBoardContent()).isEqualTo("수정된 테스트 내용1");
    }

    @Test
    public void selectAlltest(){
        Criteria criteria = new Criteria();
        Search search = new Search();

        criteria.setPage(1);
        criteria.create(boardMapper.selectCountAll(search));

        search.setKeyword("김용우");
        search.setType("w");

        assertThat(boardMapper.selectAll(criteria, search).size()).isEqualTo(1);
    }

    @Test
    public void inserttest(){
        BoardVO boardVO = BoardVO.builder()
                .boardTitle("테스트 제목5")
                .boardWriter("테스트5")
                .boardContent("테스트 내용5")
                .build();
        boardMapper.insert(boardVO);
    }

    @Test
    public void updatetest(){
        BoardVO boardVO = boardMapper.selectByBoardId(1L);
        boardVO.setBoardTitle("수정된 테스트 제목1");
        boardVO.setBoardWriter("테스트1");
        boardVO.setBoardContent("수정된 테스트 내용1");
        boardMapper.update(boardVO);
    }

    @Test
    public void deletetest(){
        boardMapper.delete(4L);
    }
}