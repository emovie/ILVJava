package com.hello.core.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hello.core.domain.Member;
import com.hello.core.domain.Order;
import com.hello.core.domain.item.Item;
import com.hello.core.repository.OrderSearch;
import com.hello.core.service.ItemService;
import com.hello.core.service.MemberService;
import com.hello.core.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final MemberService memberService;
	private final OrderService orderService;
	private final ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "orders/orderForm";
	}
	
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId,
						@RequestParam("itemId") Long itemid,
						@RequestParam("count") int count) {
		
		orderService.order(memberId, itemid, count);
		return "redirect:/orders";
	}
	
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders",orders);
		
		return "orders/orderList";
	}
	
}
