package com.example.velog.mapper;

import com.example.velog.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int writeBoard(BoardDTO boardDTO);
    
    int deleteBoard(Long boardNum);

    int updateBoard(BoardDTO boardDTO);

    List<BoardDTO> findAllBoard();

    int getBoardCount();

    BoardDTO findBoardByBoardNum();

    int getBoardCountByBoardNum(Long boardNum);
}