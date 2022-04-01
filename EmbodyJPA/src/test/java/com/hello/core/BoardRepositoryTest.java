package com.hello.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.domain.Board;
import com.hello.core.repository.BoardRepository;
import com.hello.core.repository.MemberRepository;

import junit.framework.AssertionFailedError;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
class BoardRepositoryTest {

	@Autowired BoardRepository boardRepository;
	
	@Test
	@Transactional
	public void 게시판저장() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title1");
		board.setDescript("descript1");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("Y");
		board.setIsPage("Y");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		
		//when
		Long boardIdx = boardRepository.boardSave(board);
		
		//then
		Board findBoard = boardRepository.findOne(boardIdx);
		assertThat(board.getTitle()).isEqualTo("title1");
		assertThat(board.getIdx()).isEqualTo(findBoard.getIdx());
	}
	
	@Test
	@Transactional
	public void 게시판개별출력() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title2");
		board.setDescript("descript2");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("N");
		board.setIsPage("N");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		boardRepository.boardSave(board);

		//when
		Board findBoard = boardRepository.findOne(2L);
		
		//then
		try {
			assertThat(findBoard.getIdx()).isEqualTo(board.getIdx());	
		} catch (org.opentest4j.AssertionFailedError e) {
			fail("게시판이 일치하지 않습니다");
		}
	}
	
	@Test
	@Transactional
	public void 게시판전체출력() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title1");
		board.setDescript("descript1");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("Y");
		board.setIsPage("Y");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		boardRepository.boardSave(board);
		Board board2 = new Board();
		board2.setTitle("title2");
		board2.setDescript("descript2");
		board2.setPageLink("https://www.naver.com/");
		board2.setVideoLink("https://www.google.com/");
		board2.setIsBoard("N");
		board2.setIsPage("N");
		board2.setIsDel("N");
		board2.setCreateDate("2022-03-31");
		board2.setBoardLike(0L);
		boardRepository.boardSave(board2);
		
		//when
		List<Board> boardList = boardRepository.findAll();
		
		//then
		try {
			assertThat(boardList).isNotEmpty();
		}catch (Exception e) {
			fail("게시판을 불러올 수 없습니다.");
		}
	}
	
	@Test
	@Transactional
	public void 게시판수정() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title1");
		board.setDescript("descript1");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("Y");
		board.setIsPage("Y");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		boardRepository.boardSave(board);
		Long boardIdx = 1L;
		String title = "new title";
		String descript = "new descript";
		String pageLink = "new pageLink";
		String videoLink = "new videoLink";
		String isBoard = "Y";
		String isPage ="Y";
		Long boardLike = 5L; 
		
		//when
		boardRepository.boardUpdate(boardIdx, title, descript, pageLink, videoLink, isBoard, isPage, boardLike);
		
		//then
		Board findBoard = boardRepository.findOne(1L);
		assertThat(findBoard.getTitle()).isEqualTo("new title");
		assertThat(findBoard.getDescript()).isEqualTo("new descript");
		assertThat(findBoard.getPageLink()).isEqualTo("new pageLink");
		assertThat(findBoard.getVideoLink()).isEqualTo("new videoLink");
		assertThat(findBoard.getIsBoard()).isEqualTo("Y");
		assertThat(findBoard.getIsPage()).isEqualTo("Y");
		assertThat(findBoard.getBoardLike()).isEqualTo(5L);
	}
	
	@Test
	@Transactional
	public void 게시판삭제() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title1");
		board.setDescript("descript1");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("Y");
		board.setIsPage("Y");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		boardRepository.boardSave(board);
		Long boardIdx = 1L;
		
		//when
		boardRepository.boardDelete(boardIdx);
		
		//then
		Board findBoard = boardRepository.findOne(1L);
		assertThat(findBoard.getIsDel()).isEqualTo("Y");
	}
	
	@Test
	@Transactional
	public void 좋아요클릭() throws Exception {
		//given
		Board board = new Board();
		board.setTitle("title1");
		board.setDescript("descript1");
		board.setPageLink("https://www.naver.com/");
		board.setVideoLink("https://www.google.com/");
		board.setIsBoard("Y");
		board.setIsPage("Y");
		board.setIsDel("N");
		board.setCreateDate("2022-03-31");
		board.setBoardLike(0L);
		boardRepository.boardSave(board);
		Long boardIdx = 1L;
		Long boardLikeClick = 5L;
		
		//when
		for(int i=0;i<boardLikeClick;i++) {
			boardRepository.boardLikeAddOne(boardIdx);
		}
		
		//then
		Board findBoard = boardRepository.findOne(boardIdx);
		assertThat(findBoard.getBoardLike()).isEqualTo(boardLikeClick);
	}
	
}