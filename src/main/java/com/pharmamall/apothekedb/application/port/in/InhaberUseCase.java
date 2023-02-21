package com.pharmamall.apothekedb.application.port.in;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.application.dto.InhaberDTO;
import com.pharmamall.apothekedb.domain.Inhaber;

import java.util.List;

@Port
public interface InhaberUseCase {

    Inhaber createInhaber(Inhaber inhaber, Long apothekeId);

    InhaberDTO findById(Long id);

    Inhaber updateInhaber(Long id, InhaberDTO inhaberDTO);

    void removeInhaberById(Long apothekeId, Long inhaberId);

    List<Inhaber> fetchAllInhabers();

}
