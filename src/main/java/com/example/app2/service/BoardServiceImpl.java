package com.example.app2.service;

import com.example.app2.domain.dao.BoardDAO;
import com.example.app2.domain.dao.FileDAO;
import com.example.app2.domain.dto.BoardDTO;
import com.example.app2.domain.dto.Criteria;
import com.example.app2.domain.dto.Search;
import com.example.app2.domain.vo.BoardVO;
import com.example.app2.domain.vo.FileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardDAO boardDAO;
    private final FileDAO fileDAO;
    
    //게시글 목록 출력
    @Override
    public List<BoardVO> getList(Criteria criteria, Search search) {
        criteria.create(getTotal(search));
        return boardDAO.findAll(criteria, search);
    }
    
    //게시글 상세보기
    @Override
    public BoardVO getBoard(Long boardId) {
        return boardDAO.findByBoardId(boardId);
    }
    
    //게시글 작성
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void write(BoardDTO boardDTO) {
        BoardVO boardVO =boardDTO.toVO();
        boardDAO.save(boardVO);

        boardDTO.getFiles().forEach(file -> {
            file.setBoardId(boardVO.getBoardId());
            fileDAO.save(file);
        });
    }
    
    //게시글 수정
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modify(BoardDTO boardDTO) {
        boardDAO.setBoard(boardDTO.toVO());

        if(boardDTO.getFiles() != null){
            boardDTO.getFiles().forEach(file -> {
                file.setBoardId(boardDTO.getBoardId());
                fileDAO.save(file);
            });
        }

        if(boardDTO.getDeleteFiles() != null){
            boardDTO.getDeleteFiles().stream().map(FileVO::getFileId).forEach(fileDAO::deleteById);
        }
    }
    
    //게시글 삭제
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(Long boardId) {
        List<FileVO> files = fileDAO.findAllByBoardId(boardId);
        if(files != null){
            fileDAO.delete(boardId);
        }
        boardDAO.delete(boardId);
    }

    //전체 개시글 개수 조회
    @Override
    public int getTotal(Search search) {
        return boardDAO.findCountAll(search);
    }
}
