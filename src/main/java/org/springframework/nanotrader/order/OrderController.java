package org.springframework.nanotrader.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Order findById(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> search(
            @RequestParam(value = "accountId", required = false) Long accountId,
            @RequestParam(value = "orderStatus", required = false) String orderStatus) {

        if (accountId == null && orderStatus == null) {
            return null;
        }

        if (accountId != null && orderStatus != null) {
            return orderRepository.findByAccountIdAndOrderStatus(accountId, orderStatus);
        }

        return orderRepository.findByAccountId(accountId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Order save(@RequestBody Order order) {
        return orderRepository.save(order);
    }
}
