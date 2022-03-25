package com.hello.core.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hello.core.domain.item.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemUpdateTest {

	@Autowired EntityManager em;
	
	@Test
	public void updateTest() throws Exception {
		Book book = em.find(Book.class, 1L);

		//TX
		book.setName("asdfasdf");
		
		//변경감지 == dirty checking
		//TX commit
	}

}
