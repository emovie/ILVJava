package com.hello.core.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.domain.Delivery;
import com.hello.core.domain.Member;
import com.hello.core.domain.Order;
import com.hello.core.domain.OrderItem;
import com.hello.core.domain.item.Item;
import com.hello.core.repository.ItemRepository;
import com.hello.core.repository.MemberRepository;
import com.hello.core.repository.OrderRepository;
import com.hello.core.repository.OrderSearch;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepositroy;
	
	/**
	 * 주문
	 */
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		
		//엔티티 조회
		Member member = memberRepository.findById(memberId).get();
		Item item = itemRepositroy.findOne(itemId);
		
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		
		//주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
//		OrderItem orderItem1 = new OrderItem();	// 무분별한 생성은 막아야 한다.
		
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		
		//주문 저장
		orderRepository.save(order);
		
		return order.getId();
	}
	
	/**
	 * 주문 취소
	 */
	@Transactional
	public void cancelOrder(Long orderId) {
		//주문 엔티티 조회
		Order order = orderRepository.findeOne(orderId);
		//주문 취소
		order.cancel();
	}
	
	//검색
	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderRepository.findAllByString(orderSearch);
	}
	
}
