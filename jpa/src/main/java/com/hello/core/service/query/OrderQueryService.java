package com.hello.core.service.query;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.transaction.annotation.Transactional;

import com.hello.core.api.OrderApiController;
import com.hello.core.repository.OrderRepository;

@Transactional(readOnly = true)
public class OrderQueryService {

//	public List<OrderDto> ordersV3() {
//		List<Order> order = OrderRepository.findAllWithItem();
//		
//		List<OrderApiController.OrderDto> result = orders.stream()
//				.map(o -> new OrderApliController.OrderDto(O))
//				.collect(toList);
//		
//		return result;
//	}
	
}
