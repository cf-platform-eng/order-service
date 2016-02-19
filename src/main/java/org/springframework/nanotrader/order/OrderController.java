package org.springframework.nanotrader.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping("/count")
    public long count() {
        return orderRepository.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order findById(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
    public List<Order> findByAccountId(@PathVariable Long accountId) {
        return orderRepository.findByAccountId(accountId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Order save(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
