/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.nanotrader.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId", scope = Order.class)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId = -1L;

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long l) {
        this.orderId = l;
    }

    @NotNull
    private Long accountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "holdingId")
    @NotNull
    private Holding holding;

    private float orderFee;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date completionDate;

    private String orderType;

    private String orderStatus;

    private float price;

    @NotNull
    private int quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date openDate;

    private String quoteSymbol;

    public String getQuoteSymbol() {
        return quoteSymbol;
    }

    public void setQuoteSymbol(String s) {
        this.quoteSymbol = s;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long l) {
        this.accountId = l;
    }

    public Holding getHolding() {
        return holding;
    }

    public void setHolding(Holding h) {
        this.holding = h;
    }

    public float getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(float f) {
        this.orderFee = f;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date d) {
        this.completionDate = d;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String s) {
        this.orderType = s;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String s) {
        this.orderStatus = s;
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

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date d) {
        this.openDate = d;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int hashCode() {
        return getOrderId().intValue();
    }

    public boolean equals(Object o) {
        return o != null && o instanceof Order && o.hashCode() == hashCode();
    }

}
