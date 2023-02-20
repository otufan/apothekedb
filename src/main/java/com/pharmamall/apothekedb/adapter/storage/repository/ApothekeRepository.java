package com.pharmamall.apothekedb.adapter.storage.repository;


import com.pharmamall.apothekedb.domain.Apotheke;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ApothekeRepository extends JpaRepository<Apotheke, Long> {

    boolean existsByEmail(String email) throws ResourceNotFoundException;


}
