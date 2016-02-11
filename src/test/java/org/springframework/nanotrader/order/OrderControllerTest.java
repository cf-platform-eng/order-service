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

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@WebIntegrationTest(value = "server.port=9876")
@DatabaseSetup("testData.xml")
public class OrderControllerTest {

    @Autowired
    OrderController orderController;

    @Autowired
    HoldingController holdingController;

    @Test
    public void testCount() {
        assertEquals(3, orderController.count());
    }

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
        Holding h = holdingController.findById(1L);

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

        orderController.delete(o3);

        //assertNull(orderController.findById(o2.getOrderId()));
    }
}