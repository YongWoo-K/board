package com.example.app2.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileVO {
   private Long fileId;
   private String fileName;
   private String fileUuid;
   private String fileSize;
   private String fileUploadPath;
   private boolean fileIsImage;
   private Long boardId;
}
