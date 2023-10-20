package com.example.app2.dao;

import com.example.app2.domain.dao.FileDAO;
import com.example.app2.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class FileDAOTest {
    @Autowired private FileDAO fileDAO;

    @Test
    public void saveTest(){
        FileVO fileVO = new FileVO();
        fileVO.setBoardId(3885L);
        fileVO.setFileName("테스트파일2.png");
        fileVO.setFileSize("0.5");
        fileVO.setFileUploadPath("2023/07/20");
        fileVO.setFileUuid(UUID.randomUUID().toString());
        fileVO.setFileIsImage(true);
        fileDAO.save(fileVO);
    }

    @Test
    public void findAllByBoardIdTest(){
        assertThat(fileDAO.findAllByBoardId(3885L).size()).isEqualTo(2);
    }

    @Test
    public void deleteTest(){
        fileDAO.delete(3885L);
        assertThat(fileDAO.findAllByBoardId(3885L).size()).isEqualTo(0);
    }
}
