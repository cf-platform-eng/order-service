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
import java.util.List;

@Entity
@Table(name = "Holding")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "holdingId", scope = Holding.class)
public class Holding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long holdingId = -1L;

    public Long getHoldingId() {
        return this.holdingId;
    }

    public void setHoldingId(Long l) {
        this.holdingId = l;
    }

    @OneToMany(mappedBy = "holding", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders;

    private float purchasePrice;

    @NotNull
    private int quantity;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private Date purchaseDate;

    private Long accountId;

    private String quoteSymbol;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(float f) {
        this.purchasePrice = f;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date d) {
        this.purchaseDate = d;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long l) {
        this.accountId = l;
    }

    public String getQuoteSymbol() {
        return quoteSymbol;
    }

    public void setQuoteSymbol(String s) {
        this.quoteSymbol = s;
    }


    public String toString() {
        return ReflectionToStringBuilder.toString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public int hashCode() {
        return getHoldingId().intValue();
    }

    public boolean equals(Object o) {
        return o != null && o instanceof Holding && o.hashCode() == hashCode();
    }
}
