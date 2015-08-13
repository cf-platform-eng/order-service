package org.springframework.nanotrader.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

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

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@WebIntegrationTest(value = "server.port=9876")
@DatabaseSetup("testData.xml")
public class OrderControllerTest {

	@Autowired
	OrderController orderController;

	@Test
	public void testCount() {
		assertEquals(3, orderController.count());
	}

	@Test
	public void testFind() {
		Long id = new Long(3);
		Order o = orderController.findById(id);
		assertNotNull(o);
		assertEquals(id, o.getId());
		o = orderController.findById(new Long(12345));
		assertNull(o);
	}

	@Test
	public void testSaveAndFindAndDelete() {
		Order o = new Order();
		o.setAccountid(new Long(1234));
		o.setCompletiondate(new Date());
		o.setOpendate(new Date());
		o.setOrderfee(23.45f);
		o.setOrderstatus(Order.OrderStatus.closed);
		o.setOrdertype(Order.OrderType.sell);
		o.setPrice(56.78f);
		o.setQuantity(234);
		o.setSymbol("Foo");

		Order o2 = orderController.saveOrder(o);
		assertNotNull(o2);
		assertNotNull(orderController.findById(o2.getId()));

		Long id = o2.getId();
		assertNotNull(id);
		assertEquals(new Long(1234), o2.getAccountid());
		assertNotNull(o2.getCompletiondate());
		assertNotNull(o2.getOpendate());
		assertEquals("23.45", "" + o2.getOrderfee());
		assertEquals(Order.OrderStatus.closed, o2.getOrderstatus());
		assertEquals(Order.OrderType.sell, o2.getOrdertype());
		assertEquals("56.78", "" + o2.getPrice());
		assertEquals(234, o2.getQuantity());
		assertEquals("Foo", o2.getSymbol());

		orderController.deleteOrder(o2);

		assertNull(orderController.findById(id));
	}
}
