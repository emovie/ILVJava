package com.hello.core.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hello.core.domain.Member;

@Repository
public class MemberRepository {
	
	@PersistenceContext
	EntityManager em;
	
	public String save(Member member) {
		em.persist(member);
		return member.getId();
	}
	
	public Member findOne(String id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findById(String id) {
		return em.createQuery("select m from Member m where m.id = :id",Member.class)
							.setParameter("id", id)
							.getResultList();
	}

	public String login(String id, String pw) {
		Member member = em.createQuery("select m from Member m where m.id = :id and m.pw = :pw",Member.class)
					.setParameter("id", id)
					.setParameter("pw", pw)
					.getSingleResult();
		return member.getUsername();
	}
	
}
