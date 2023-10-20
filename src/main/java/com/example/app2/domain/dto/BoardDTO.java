package com.example.app2.domain.dto;

import com.example.app2.domain.vo.BoardVO;
import com.example.app2.domain.vo.FileVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardDTO {
    private Long boardId;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
    private List<FileVO> files;
    private List<FileVO> deleteFiles;

    public BoardVO toVO(){
        return BoardVO.builder()
                .boardId(boardId)
                .boardContent(boardContent)
                .boardTitle(boardTitle)
                .boardWriter(boardWriter)
                .boardRegisterDate(boardRegisterDate)
                .boardUpdateDate(boardUpdateDate)
                .build();
    }
}
