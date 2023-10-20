package com.example.app2.domain.vo;

import com.example.app2.domain.dto.BoardDTO;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardVO {
    private Long boardId;
    private String boardTitle;
    private String boardWriter;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;

    public BoardDTO toDTO(){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardId(boardId);
        boardDTO.setBoardTitle(boardTitle);
        boardDTO.setBoardWriter(boardWriter);
        boardDTO.setBoardContent(boardContent);
        boardDTO.setBoardRegisterDate(boardRegisterDate);
        boardDTO.setBoardUpdateDate(boardUpdateDate);
        return boardDTO;
    }
}
