package com.example.app2.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class BoardControllerTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void showListTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                .param("page", "1")
                .param("type", "wc")
                .param("keyword", "김용우")
        ).andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void getBoardTest() throws Exception{
        //log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/modify")
                .param("boardId","1")).andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void writeTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/write")
        .param("boardTitle", "테스트 제목4")
        .param("boardWriter", "테스트4")
        .param("boardContent", "테스트 내용4")
        ).andReturn().getFlashMap().toString());
    }

    @Test
    public void modifyTest() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("boardId","3")
                .param("boardTitle", "수정된 제목")
                .param("boardContent", "수정된 내용")
        ).andReturn().getModelAndView().getModel().toString());
    }

    @Test
    public void removeTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/board/remove").param("boardId", "7"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
