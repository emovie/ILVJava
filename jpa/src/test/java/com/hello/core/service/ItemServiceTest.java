package com.hello.core.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.domain.item.Book;
import com.hello.core.domain.item.Item;
import com.hello.core.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

	@Autowired ItemService itemService;
	@Autowired ItemRepository itemRepository;
	@Autowired EntityManager em;
	
	@Test
	public void saveItem() throws Exception {
		//given
		Item item = new Book();
		item.setName("book1");
		item.setPrice(2000);
		
		//when
		itemService.saveItem(item);
		
		//then
		assertEquals(itemService.findItems().isEmpty(),false);
	}

}
