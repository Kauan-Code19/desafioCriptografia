package com.desafios.cripftografia.repositories;

import com.desafios.cripftografia.entities.SensitiveDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensitiveDataRepository extends JpaRepository<SensitiveDataEntity, Long> {

}
