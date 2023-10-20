package com.example.app2.service;

import com.example.app2.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class FileServiceTest {
    @Autowired private FileService fileService;

    @Test
    public void registerTest(){
        FileVO fileVO = new FileVO();
        fileVO.setBoardId(3885L);
        fileVO.setFileName("테스트 파일1.png");
        fileVO.setFileSize("0.5");
        fileVO.setFileUploadPath("2023/07/20");
        fileVO.setFileUuid(UUID.randomUUID().toString());
        fileVO.setFileIsImage(true);
        fileService.register(fileVO);
    }

    @Test
    public void getListTest(){
        assertThat(fileService.getList(3885L).size()).isEqualTo(1);
    }

    @Test
    public void removeTest(){
        fileService.remove(3885L);
        assertThat(fileService.getList(3885L).size()).isEqualTo(0);
    }
}
