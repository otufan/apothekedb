package com.pharmamall.apothekedb.adapter.storage.repository;

import com.pharmamall.apothekedb.domain.Inhaber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InhaberRepository extends JpaRepository<Inhaber, Long> {

}
