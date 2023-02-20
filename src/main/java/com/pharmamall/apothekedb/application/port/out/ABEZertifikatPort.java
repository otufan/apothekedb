package com.pharmamall.apothekedb.application.port.out;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.domain.ABEZertifikat;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Port
public interface ABEZertifikatPort {
    Optional<ABEZertifikat> findById(Long id);

    void save(ABEZertifikat abeZertifikat);

    Stream<ABEZertifikat> findAll();
}
