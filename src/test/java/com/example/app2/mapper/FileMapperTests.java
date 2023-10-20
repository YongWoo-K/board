package com.example.app2.mapper;

import com.example.app2.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class FileMapperTests {
    @Autowired private FileMapper fileMapper;

    @Test
    public void insertTest(){
        FileVO fileVO = new FileVO();
        fileVO.setBoardId(3885L);
        fileVO.setFileName("테스트파일2.png");
        fileVO.setFileSize("0.5");
        fileVO.setFileUploadPath("2023/07/20");
        fileVO.setFileUuid(UUID.randomUUID().toString());
        fileVO.setFileIsImage(true);
        fileMapper.insert(fileVO);
    }

    @Test
    public void selectAllByBoardIdTest(){
        assertThat(fileMapper.selectAllByBoardId(3885L).size()).isEqualTo(2);
    }

    @Test
    public void deleteTest(){
        fileMapper.delete(3885L);
        assertThat(fileMapper.selectAllByBoardId(3885L).size()).isEqualTo(0);
    }
}
