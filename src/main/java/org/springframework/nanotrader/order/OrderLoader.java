package org.springframework.nanotrader.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private HoldingController holdingController;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Order o = new Order();
        o.setAccountId(2L);
        o.setCompletionDate(new Date());
        o.setOpenDate(new Date());
        o.setOrderFee(345.67f);
        o.setOrderStatus("status");
        o.setOrderType("type");
        o.setPrice(45.67f);
        o.setQuantity(5);
        o.setQuoteSymbol("GOOG");

        Holding h = new Holding();
        h.setQuantity(5);
        h.setAccountId(2L);
        h.setPurchaseDate(new Date());
        h.setPurchasePrice(45.67f);
        h.setQuoteSymbol("GOOG");

        o.setHolding(h);
        List<Order> orders = new ArrayList<Order>();
        orders.add(o);
        h.setOrders(orders);

        holdingController.save(h);
    }
}
