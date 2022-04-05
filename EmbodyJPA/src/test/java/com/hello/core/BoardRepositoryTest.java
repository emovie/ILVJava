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
		Board board = Board.builder()
					.title("title1")
					.descript("descript1")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("Y")
					.isPage("Y")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
					
		//when
		Long boardIdx = boardRepository.boardCreate(board);
		
		//then
		Board findBoard = boardRepository.findOne(boardIdx);
		assertThat(board.getTitle()).isEqualTo("title1");
		assertThat(board.getIdx()).isEqualTo(findBoard.getIdx());
	}
	
	@Test
	@Transactional
	public void 게시판개별출력() throws Exception {
		//given
		Board board = Board.builder()
					.title("title2")
					.descript("descript2")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("N")
					.isPage("N")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board);

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
		Board board = Board.builder()
					.title("title1")
					.descript("descript1")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("Y")
					.isPage("Y")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board);
		Board board2 = Board.builder()
					.title("title2")
					.descript("descript2")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("N")
					.isPage("N")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board2);
		
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
		Board board = Board.builder()
					.title("title1")
					.descript("descript1")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("Y")
					.isPage("Y")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board);
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
		Board board = Board.builder()
					.title("title1")
					.descript("descript1")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("Y")
					.isPage("Y")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board);
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
		Board board = Board.builder()
					.title("title1")
					.descript("descript1")
					.pageLink("https://www.naver.com/")
					.videoLink("https://www.google.com/")
					.isBoard("Y")
					.isPage("Y")
					.isDel("N")
					.createDate("2022-03-31")
					.boardLike(0L)
					.build();
		boardRepository.boardCreate(board);
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