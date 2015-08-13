package org.springframework.nanotrader.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	@RequestMapping("/count")
	public long count() {
		return orderRepository.count();
	}

	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order findById(@PathVariable Long id) {
		return orderRepository.findOne(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
}
