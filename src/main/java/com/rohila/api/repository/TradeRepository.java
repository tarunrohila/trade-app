package com.rohila.api.repository;

import com.rohila.api.repository.domain.TradeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Interface which is used to create repository to intract with DB
 *
 * @author Tarun Rohila
 */
@Repository
@Transactional
public interface TradeRepository extends JpaRepository<TradeDetails, Long> {
}
