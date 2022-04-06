package com.hello.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.DTO.BoardWriteDTO;
import com.hello.core.domain.Board;
import com.hello.core.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {

	private final BoardRepository boardRepository;
	
	@Transactional
	public Long boardCreate(BoardWriteDTO board) throws Exception {
		Board createBoard = Board.builder()
							.title(board.getTitle())
							.descript(board.getDescript())
							.pageLink(board.getPageLink())
							.videoLink(board.getVideoLink())
							.isBoard(isYorN(board.getIsBoard()))
							.isPage(isYorN(board.getIsPage()))
							.boardLike(0L)
							.createDate(new Date().toLocaleString())
							.isDel("N")
							.build();
		return boardRepository.boardCreate(createBoard);
	}
	
	@Transactional
	public Long boardUpdate(BoardDTO board) throws Exception{
		System.out.println("boardService update : "+board.toString());
		boardRepository.boardUpdate(BoardDTOToBoard(board));
		return board.getIdx();
	}
	
	@Transactional
	public List<BoardDTO> findAll() throws Exception {
		List<Board> boardList = boardRepository.findAll();
		List<BoardDTO> boardListDTO = new ArrayList<BoardDTO>();
		for(Board board : boardList) {
			BoardDTO boardDTO = boardToBoardDTO(board);
			boardListDTO.add(boardDTO);
		}
		return boardListDTO;
	}
	
	@Transactional
	public String boardLikeAddOne(String idx) throws Exception {
		Long boardLikeCnt = boardRepository.boardLikeAddOne(Long.valueOf(idx));
		return String.valueOf(boardLikeCnt);
	}
	
	public BoardDTO boardToBoardDTO(Board board) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setIdx(board.getIdx());
		boardDTO.setTitle(board.getTitle());
		boardDTO.setDescript(board.getDescript());
		boardDTO.setPageLink(board.getPageLink());
		boardDTO.setVideoLink(board.getVideoLink());
		boardDTO.setIsBoard(board.getIsBoard());
		boardDTO.setIsPage(board.getIsPage());
		boardDTO.setBoardLike(board.getBoardLike());
		boardDTO.setCreateDate(board.getCreateDate());
		boardDTO.setIsDel(board.getIsDel());
		return boardDTO;
	}
	
	public Board BoardDTOToBoard(BoardDTO boardDTO) {
		return  Board.builder()
				.idx(boardDTO.getIdx())
				.title(boardDTO.getTitle())
				.descript(boardDTO.getDescript())
				.pageLink(boardDTO.getPageLink())
				.videoLink(boardDTO.getVideoLink())
				.isBoard(boardDTO.getIsBoard())
				.isPage(boardDTO.getIsPage())
				.boardLike(boardDTO.getBoardLike())
				.createDate(boardDTO.getCreateDate())
				.isDel(boardDTO.getIsDel())
				.build();
	}

	private String isYorN(String isFlag) throws Exception {
		return (isFlag != null || isFlag != "" ? "Y" : "N");
	}
	
	
}