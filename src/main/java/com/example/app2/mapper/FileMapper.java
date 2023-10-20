package com.example.app2.mapper;

import com.example.app2.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    //파일 추가
    public void insert(FileVO fileVO);

    //파일 삭제
    public void delete(Long boardId);

    //개별 파일 삭제
    public void deleteFile(Long fileId);

    //파일 목록
    public List<FileVO> selectAllByBoardId(Long boardId);

    //어제 업로드된 파일 목록
    public List<FileVO> selectOldFiles();
}
