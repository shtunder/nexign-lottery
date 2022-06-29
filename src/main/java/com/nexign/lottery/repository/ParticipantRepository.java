package com.nexign.lottery.repository;

import com.nexign.lottery.model.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Andrey Shtunder
 */
@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
