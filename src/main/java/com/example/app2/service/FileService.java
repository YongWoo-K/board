package com.example.app2.service;

import com.example.app2.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FileService {
    //파일 추가
    public void register(FileVO fileVO);

    //파일 삭제
    public void remove(Long boardId);

    //파일 목록
    public List<FileVO> getList(Long boardId);

    //파일 업로드
    public List<FileVO> upload(List<MultipartFile> upload) throws IOException;

    //어제 업로드된 파일 목록
    public List<FileVO> getFilesOfYesterday();
}
