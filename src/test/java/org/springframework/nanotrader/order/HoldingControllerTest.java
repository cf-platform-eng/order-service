package org.springframework.nanotrader.order;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebIntegrationTest(value = "server.port=9876")
@DatabaseSetup("testData.xml")
public class HoldingControllerTest {

    @Autowired
    HoldingController holdingController;

    @Test
    public void testCount() {
        assertEquals(1, holdingController.count());
    }

    @Test
    public void testFind() {
        Long id = 1L;
        Holding h = holdingController.findById(id);
        assertNotNull(h);
        assertEquals(id, h.getHoldingId());
        assertEquals("4", "" + h.getAccountId());
        List<Order> orders = h.getOrders();
        assertNotNull(orders);
        assertEquals(3, orders.size());

        h = holdingController.findById(12345L);
        assertNull(h);
    }

    @Test
    public void testSaveAndFindAndDelete() {
        Holding h = new Holding();
        h.setAccountId(1L);
        h.setPurchaseDate(new Date());
        h.setPurchasePrice(12.34f);
        h.setQuantity(5);
        h.setQuoteSymbol("GOOG");

        Order o = new Order();
        o.setAccountId(1L);
        o.setCompletionDate(new Date());
        o.setOpenDate(new Date());
        o.setOrderFee(23.45f);
        o.setOrderStatus("Closed");
        o.setOrderType("Sell");
        o.setPrice(12.34f);
        o.setQuantity(5);
        o.setQuoteSymbol("GOOG");
        o.setHolding(h);

        List<Order> orders = new ArrayList<Order>();
        orders.add(o);
        h.setOrders(orders);

        Holding h2 = holdingController.save(h);
        assertNotNull(h2);

        Holding h3 = holdingController.findById(h2.getHoldingId());
        assertNotNull(h3);
        assertNotNull(h3.getOrders());
        assertEquals(1, h3.getOrders().size());

        holdingController.delete(h3.getHoldingId());

        assertNull(holdingController.findById(h3.getHoldingId()));
    }

    @Test
    public void testFindByAccountId() {
        Long id = 4L;
        List<Holding> h = holdingController.findByAccountId(id);
        assertNotNull(h);
        assertTrue(h.size() > 0);
        assertEquals(id, h.get(0).getAccountId());
    }
}
