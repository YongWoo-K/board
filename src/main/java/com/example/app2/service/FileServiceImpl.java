package com.example.app2.service;

import com.example.app2.domain.dao.FileDAO;
import com.example.app2.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    private final FileDAO fileDAO;

    //파일 추가
    @Override
    public void register(FileVO fileVO) {
        fileDAO.save(fileVO);
    }

    //파일 삭제
    @Override
    public void remove(Long boardId) {
        fileDAO.delete(boardId);
    }

    //파일 목록
    @Override
    public List<FileVO> getList(Long boardId) {
        return fileDAO.findAllByBoardId(boardId);
    }

    //파일 업로드
    @Override
    public List<FileVO> upload(List<MultipartFile> upload) throws IOException {
        String rootPath = "c:/upload2";
        String todayPath = createDirectoryToday();
        File uploadPath = new File(rootPath, todayPath);
        List<FileVO> files = new ArrayList<>();

        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for(MultipartFile multipartFile : upload){
            FileVO fileVO = new FileVO();
            String uuid = UUID.randomUUID().toString();
            String fileName = multipartFile.getOriginalFilename();
            String fileSize = String.format("%.2f", multipartFile.getSize() / 1024.0);
            boolean isImage = multipartFile.getContentType().startsWith("image");

            fileVO.setFileUuid(uuid);
            fileVO.setFileName(fileName);
            fileVO.setFileUploadPath(todayPath);
            fileVO.setFileSize(fileSize);
            fileVO.setFileIsImage(isImage);

            File save = new File(uploadPath, uuid + "_" + fileName);
            multipartFile.transferTo(save);

            if(isImage){
                FileOutputStream out = new FileOutputStream(new File(uploadPath, "t_" + uuid + "_" + fileName));
                Thumbnailator.createThumbnail(multipartFile.getInputStream(), out, 100, 100);
                out.close();
            }
            files.add(fileVO);
        }
        return files;
    }
    
    //어제 업로드된 파일 목록
    @Override
    public List<FileVO> getFilesOfYesterday() {
        return fileDAO.findByYesterday();
    }

    //당일 날짜를 경로로 만들어주는 메소드
    private String createDirectoryToday(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
