package com.hello.core.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.hello.core.domain.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final EntityManager em;
	
	public Long save(User user) {
		em.persist(user);
		return user.getId();
	}
	
	public User find(Long id) {
		return em.find(User.class, id);
	}
	
}