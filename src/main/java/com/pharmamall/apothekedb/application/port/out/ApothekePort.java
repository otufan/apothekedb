package com.pharmamall.apothekedb.application.port.out;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.domain.Apotheke;

import java.util.List;

@Port
public interface ApothekePort {

    Apotheke write(Apotheke apotheke);

    boolean existsByEmail(String email);

    Apotheke findById(Long id);

    void deleteById(Long id);

    List<Apotheke> findAll();
}
