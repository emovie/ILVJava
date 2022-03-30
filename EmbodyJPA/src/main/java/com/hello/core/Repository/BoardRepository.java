package com.hello.core.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

	@PersistenceContext
	EntityManager em;
	
	public void boardSave() {
		
	}
	
	public void boardUpdate() {
		
	}
	
	public void boardDelete() {
		
	}
	
}
