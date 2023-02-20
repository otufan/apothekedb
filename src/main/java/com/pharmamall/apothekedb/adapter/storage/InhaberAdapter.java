package com.pharmamall.apothekedb.adapter.storage;

import com.pharmamall.apothekedb.adapter.storage.repository.InhaberRepository;
import com.pharmamall.apothekedb.annotations.Adapter;
import com.pharmamall.apothekedb.application.port.out.InhaberPort;
import com.pharmamall.apothekedb.domain.Inhaber;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Adapter("persistence")
public class InhaberAdapter implements InhaberPort {

    private final InhaberRepository inhaberRepository;

    @Override
    public Inhaber write(Inhaber inhaber) {
        return inhaberRepository.save(inhaber);
    }

    @Override
    public Inhaber findById(Long id) {
        return inhaberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Inhaber mit id %d ist nicht gefunden", id)));
    }


    @Override
    public void deleteById(Long id) {
        inhaberRepository.deleteById(id);
    }

    @Override
    public List<Inhaber> findAll() {
        return inhaberRepository.findAll();
    }
}
