package com.hello.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hello.core.domain.Board;

@Repository
public class BoardRepository {

	@PersistenceContext
	EntityManager em;
	
	public Long boardSave(Board board) {
		em.persist(board);
		return board.getIdx();
	}
	
	public void boardUpdate(Long boardIdx, String title, String descript, String pageLink, String videoLink, String isBoard, String isPage, Long boardLike) {
		Board findBoard = em.find(Board.class, boardIdx);
		findBoard.setTitle(title);
		findBoard.setDescript(descript);
		findBoard.setPageLink(pageLink);
		findBoard.setVideoLink(videoLink);
		findBoard.setIsBoard(isBoard);
		findBoard.setIsPage(isPage);
		findBoard.setBoardLike(boardLike);
	}
	
	public void boardDelete(Long boardIdx) {
		Board findBoard = em.find(Board.class, boardIdx);
		findBoard.setIsDel("Y");
	}
	
	public Board findOne(Long boardIdx) {
		return em.find(Board.class, boardIdx);
	}
	
	public List<Board> findAll() {
		return em.createQuery("select b from Board b",Board.class)
								.getResultList();
	}

	public Long boardLikeAddOne(Long boardIdx) {
		Board findBoard = em.find(Board.class, boardIdx);
		findBoard.setBoardLike(findBoard.getBoardLike()+1L);
		return findBoard.getBoardLike();
	}
	
}
