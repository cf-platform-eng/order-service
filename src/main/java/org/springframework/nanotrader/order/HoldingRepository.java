package org.springframework.nanotrader.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaSpecificationExecutor<Holding>,
        JpaRepository<Holding, Long> {
//
//    List<Holding> findByAccountId(Integer accountId);
//
//    @Query("SELECT count(h) FROM Holding h WHERE h.accountId = ?1")
//    Long findCountOfHoldings(Long accountId);
//
//    @Query("SELECT SUM(h.purchasePrice * h.quantity) as purchaseBasis FROM Holding h Where h.accountId =?1")
//    Float findPurchaseBasis(Long accountId);
}
