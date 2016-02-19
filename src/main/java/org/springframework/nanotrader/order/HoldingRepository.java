package org.springframework.nanotrader.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldingRepository extends
        CrudRepository<Holding, Long> {

    List<Holding> findByAccountId(Long accountId);

}
