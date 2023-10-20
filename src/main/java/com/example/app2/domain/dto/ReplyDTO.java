package com.example.app2.domain.dto;

import com.example.app2.domain.vo.ReplyVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
   private List<ReplyVO> replies;
   private int countOfNextPage;

   public ReplyDTO(List<ReplyVO> replyVOs, int countOfNextPage){
        this.replies = replyVOs;
        this.countOfNextPage = countOfNextPage;
   }
}
