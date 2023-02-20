package com.pharmamall.apothekedb.adapter.storage;

import com.pharmamall.apothekedb.adapter.storage.repository.ABEZertifikatRepository;
import com.pharmamall.apothekedb.annotations.Adapter;
import com.pharmamall.apothekedb.application.port.out.ABEZertifikatPort;
import com.pharmamall.apothekedb.domain.ABEZertifikat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Data
@Component
@Adapter("persistence")
public class ABEZertifikatAdapter implements ABEZertifikatPort {

    private final ABEZertifikatRepository abeZertifikatRepository;

    @Override
    public Optional<ABEZertifikat>  findById(Long id) {

        return abeZertifikatRepository.findById(id);
    }
    @Override
    public void save(ABEZertifikat abeZertifikat) {
        abeZertifikatRepository.save(abeZertifikat);
    }

    @Override
    public Stream<ABEZertifikat> findAll() {
        return abeZertifikatRepository.findAll().stream();
    }
}
