package com.hello.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hello.core.DTO.BoardDTO;
import com.hello.core.domain.Board;

@Repository
public class BoardRepository {

	@PersistenceContext
	EntityManager em;
	
	public Long boardCreate(Board board) throws Exception {
		em.persist(board);
		return board.getIdx();
	}
	
	public void boardUpdate(Board board) throws Exception {
		Board findBoard = em.find(Board.class, board.getIdx());
		findBoard.setTitle(board.getTitle());
		findBoard.setDescript(!(board.getDescript() == null) ? board.getDescript() : findBoard.getDescript());
		findBoard.setPageLink(!(board.getPageLink() == null) ? board.getPageLink() : findBoard.getPageLink());
		findBoard.setVideoLink(!(board.getVideoLink() == null) ? board.getVideoLink() : findBoard.getVideoLink());
		findBoard.setIsBoard(!(board.getIsBoard() == null) ? board.getIsBoard() : findBoard.getIsBoard());
		findBoard.setIsPage(!(board.getIsPage() == null) ? board.getIsPage() : findBoard.getIsPage());
	}
	
	public void boardDelete(Long boardIdx) throws Exception {
		Board findBoard = em.find(Board.class, boardIdx);
		findBoard.setIsDel("Y");
	}
	
	public Board findOne(Long boardIdx) throws Exception {
		return em.find(Board.class, boardIdx);
	}
	
	public List<Board> findAll() throws Exception{
		return em.createQuery("select b from Board b where isDel = :isDel",Board.class)
								.setParameter("isDel", "N")
								.getResultList();
	}

	public Long boardLikeAddOne(Long boardIdx) throws Exception {
		Board findBoard = em.find(Board.class, boardIdx);
		findBoard.setBoardLike(findBoard.getBoardLike()+1L);
		return findBoard.getBoardLike();
	}
	
}
