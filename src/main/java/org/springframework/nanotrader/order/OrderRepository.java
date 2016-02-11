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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Gary Russell
 * @author Brian Dussault
 */

@Repository
public interface OrderRepository extends JpaSpecificationExecutor<Order>,
        JpaRepository<Order, Long> {

//    @Query(value = "UPDATE Order o SET o.orderStatus = 'completed' WHERE o.accountId = ?1 AND o.orderStatus = 'closed'")
//    int updateClosedOrders(Long accountId);
//
//    @Query("SELECT o FROM Order o WHERE o.orderStatus = ?2 AND o.accountId  = ?1 order by orderId DESC")
//    List<Order> findOrdersByStatus(Long accountId, String status);
//
//    @Query("SELECT o FROM Order o WHERE o.accountId  = ?1 order by orderId DESC")
//    List<Order> findOrdersByAccountAccountid_Accountid(Long accountId);
//
//    @Query("SELECT o FROM Order o WHERE o.id = ?1 AND o.accountId  = ?2")
//    Order findByOrderidAndAccountAccountid(Long orderId,
//                                           Integer accountId);
//
//    @Query("SELECT count(o) FROM Order o WHERE o.accountId  = ?1")
//    Long findCountOfOrders(Integer accountId);
//
//    @Query("SELECT count(o) FROM Order o WHERE o.accountId  = ?1 and o.orderStatus = ?2")
//    Long findCountOfOrders(Integer accountId, String status);

}
