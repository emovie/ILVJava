package com.hello.core.repository.order.query;

import java.time.LocalDateTime;

import com.hello.core.domain.Address;
import com.hello.core.domain.OrderStatus;

import lombok.Data;

@Data
public class OrderFlagDto {

	private Long orderId;
	private String name;
	private LocalDateTime orderDate;
	private OrderStatus orderStatus;
	private Address address;

	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderFlagDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address,
			String itemName, int orderPrice, int count) {
		this.orderId = orderId;
		this.name = name;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.address = address;
		this.itemName = itemName;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
