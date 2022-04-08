package com.hello.core.repository.order.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class OrderItemQueryDto {

	@JsonIgnore
	private Long orderId;
	private String itemName;
	private int orderPrice;
	private int count;
	
	public OrderItemQueryDto(String itemName, int orderPrice, int count) {
		this.itemName = itemName;
		this.orderPrice = orderPrice;
		this.count = count;
	}
	
}
