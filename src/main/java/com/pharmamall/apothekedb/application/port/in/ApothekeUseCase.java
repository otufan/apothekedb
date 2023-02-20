package com.pharmamall.apothekedb.application.port.in;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.application.dto.ApothekeDTO;
import com.pharmamall.apothekedb.domain.Apotheke;

import java.util.List;

@Port
public interface ApothekeUseCase {

    Apotheke createApotheke(Apotheke apotheke);

    ApothekeDTO findById(Long id);

    Apotheke updateApotheke(Long id, ApothekeDTO apothekeDTO);

    void removeById(Long id);

    List<ApothekeDTO> fetchAllApotheken();

}
