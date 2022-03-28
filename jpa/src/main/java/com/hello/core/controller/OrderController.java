package com.hello.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hello.core.domain.Member;
import com.hello.core.domain.Order;
import com.hello.core.domain.item.Item;
import com.hello.core.repository.OrderSearch;
import com.hello.core.service.ItemService;
import com.hello.core.service.MemberService;
import com.hello.core.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;

	@GetMapping(value = "/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}

	@PostMapping(value = "/order")
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
			@RequestParam("count") int count) {
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}

	@GetMapping(value = "/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
	
}