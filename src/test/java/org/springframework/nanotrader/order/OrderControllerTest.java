package org.springframework.nanotrader.order;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("testData.xml")
public class OrderControllerTest {

    @Autowired
    OrderController orderController;

    @Autowired
    HoldingController holdingController;

    @Test
    public void testFind() {
        Long id = 3L;
        Order o = orderController.findById(id);
        assertNotNull(o);
        assertNotNull(o.getHolding());
        assertEquals(id, o.getOrderId());
        o = orderController.findById(12345L);
        assertNull(o);
    }

    @Test
    public void testSaveAndFindAndDelete() {
        Holding h = new Holding();

        Order o = new Order();
        o.setAccountId(1234L);
        o.setCompletionDate(new Date());
        o.setOpenDate(new Date());
        o.setOrderFee(23.45f);
        o.setOrderStatus("Closed");
        o.setOrderType("Sell");
        o.setPrice(56.78f);
        o.setQuantity(234);
        o.setQuoteSymbol("Foo");
        o.setHolding(h);
        List<Order> orders = new ArrayList<Order>();
        orders.add(o);
        h.setOrders(orders);

        Order o2 = orderController.save(o);
        assertNotNull(o2);

        Order o3 = orderController.findById(o2.getOrderId());
        assertNotNull(o3);

        Long id = o3.getOrderId();
        assertNotNull(id);
        assertEquals(new Long(1234), o3.getAccountId());
        assertNotNull(o3.getCompletionDate());
        assertNotNull(o3.getOpenDate());
        assertEquals("23.45", "" + o3.getOrderFee());
        assertEquals("Closed", o3.getOrderStatus());
        assertEquals("Sell", o3.getOrderType());
        assertEquals("56.78", "" + o3.getPrice());
        assertEquals(234, o3.getQuantity());
        assertEquals("Foo", o3.getQuoteSymbol());
        assertNotNull(o.getHolding());

        List<Order> l = orderController.search(o3.getAccountId(), null);
        assertNotNull(l);
        assertTrue(l.size() > 0);
        for (Order oo : l) {
            assertEquals(o3.getAccountId(), oo.getAccountId());
        }

        List<Order> l2 = orderController.search(123L, null);
        assertNotNull(l2);
        assertTrue(l2.size() == 0);

        List<Order> l3 = orderController.search(o3.getAccountId(), "Closed");
        assertNotNull(l3);
        assertTrue(l3.size() > 0);
        for (Order oo : l3) {
            assertEquals(o3.getAccountId(), oo.getAccountId());
            assertNotNull(oo.getHolding());
            assertEquals("Closed", oo.getOrderStatus());
        }

        List<Order> l4 = orderController.search(o3.getAccountId(), "foo");
        assertNotNull(l4);
        assertTrue(l4.size() == 0);

        assertEquals("1", "" + orderController.findCountOfOrders(o3.getAccountId(),  "Closed"));
        assertEquals("1", "" + orderController.findCountOfOrders(o3.getAccountId(), null));
        assertTrue(orderController.findCountOfOrders(null, null) > 0);
    }

    @Test
    public void testFindAll() {
        assertNotNull(orderController.findAll());
    }
}
