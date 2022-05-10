package com.hello.core.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hello.core.entity.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public String save(Member member) throws Exception {
		em.persist(member);
		return member.getId();
	}
	
	public Member findOne(String id) throws Exception {
		return em.find(Member.class, id);
	}
	
	public List<Member> findById(String id) throws Exception {
		return em.createQuery("select m from Member m where m.id = :id",Member.class)
							.setParameter("id", id)
							.getResultList();
	}

	public String login(String id, String pw) throws Exception {
		Member member = em.createQuery("select m from Member m where m.id = :id and m.pw = :pw",Member.class)
					.setParameter("id", id)
					.setParameter("pw", pw)
					.getSingleResult();
		return member.getUserName();
	}
	
}
