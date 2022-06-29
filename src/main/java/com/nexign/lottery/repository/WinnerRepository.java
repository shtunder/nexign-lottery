package com.nexign.lottery.repository;

import com.nexign.lottery.model.WinnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrey Shtunder
 */
@Repository
public interface WinnerRepository extends JpaRepository<WinnerEntity, Long> {
}
