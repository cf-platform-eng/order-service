package org.springframework.nanotrader.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	public enum OrderStatus {
		open, cancelled, closed, completed
	}

	public enum OrderType {
		buy, sell
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = new Long(-1);

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		if (id != null) {
			this.id = id;
		}
	}

	private Long accountId;

	private float orderfee;

	private Date completiondate;

	private OrderType ordertype;

	private OrderStatus orderstatus;

	private float price;

	private int quantity;

	private Date opendate;

	private String symbol;

	public Long getAccountid() {
		return accountId;
	}

	public void setAccountid(Long l) {
		this.accountId = l;
	}

	public float getOrderfee() {
		return orderfee;
	}

	public void setOrderfee(float f) {
		this.orderfee = f;
	}

	public Date getCompletiondate() {
		return completiondate;
	}

	public void setCompletiondate(Date d) {
		this.completiondate = d;
	}

	public OrderType getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(OrderType o) {
		this.ordertype = o;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus o) {
		this.orderstatus = o;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float f) {
		this.price = f;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int i) {
		this.quantity = i;
	}

	public Date getOpendate() {
		return opendate;
	}

	public void setOpendate(Date d) {
		this.opendate = d;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String s) {
		this.symbol = s;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderfee=" + orderfee
				+ ", completiondate=" + completiondate + ", ordertype="
				+ ordertype + ", orderstatus=" + orderstatus + ", price="
				+ price + ", quantity=" + quantity + ", opendate=" + opendate
				+ "]";
	}

	public int hashCode() {
		return getId().intValue();
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}

		if (!(o instanceof Order)) {
			return false;
		}
		return o.hashCode() == hashCode();
	}

}
