package com.example.app2.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReplyVO {
    @EqualsAndHashCode.Include
    private Long replyId;
    private String replyContent;
    private String replyWriter;
    private String replyRegisterDate;
    private String replyUpdateDate;
    private Long boardId;
}
