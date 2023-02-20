package com.pharmamall.apothekedb.application.port.out;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.domain.Inhaber;

import java.util.List;

@Port
public interface InhaberPort {

    Inhaber write(Inhaber inhaber);

    Inhaber findById(Long id);

    void deleteById(Long id);

    List<Inhaber> findAll();
}
