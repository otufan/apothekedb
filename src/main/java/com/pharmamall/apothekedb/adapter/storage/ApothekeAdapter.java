package com.pharmamall.apothekedb.adapter.storage;

import com.pharmamall.apothekedb.adapter.storage.repository.ApothekeRepository;
import com.pharmamall.apothekedb.annotations.Adapter;
import com.pharmamall.apothekedb.application.port.out.ApothekePort;
import com.pharmamall.apothekedb.domain.Apotheke;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@Component
@Adapter("persistence")
public class ApothekeAdapter implements ApothekePort {

    private final ApothekeRepository apothekeRepository;

    @Override
    public Apotheke write(Apotheke apotheke) {

        return apothekeRepository.save(apotheke);
    }

    @Override
    public boolean existsByEmail(String email) {
        return apothekeRepository.existsByEmail(email);
    }

    @Override
    public Apotheke findById(Long id) {
        return apothekeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Apotheke mit id %d ist nicht gefunden", id)));
    }

    @Override
    public void deleteById(Long id) {
        apothekeRepository.deleteById(id);
    }

    @Override
    public List<Apotheke> findAll() {
        return apothekeRepository.findAll();
    }


}
