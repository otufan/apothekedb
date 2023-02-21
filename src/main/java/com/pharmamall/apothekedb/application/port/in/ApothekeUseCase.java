package com.pharmamall.apothekedb.application.port.in;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.application.dto.ApothekeDTO;
import com.pharmamall.apothekedb.domain.Apotheke;

import java.util.List;

@Port
public interface ApothekeUseCase {

    void createApotheke(Apotheke apotheke, Long inhaberId);

    ApothekeDTO findById(Long id);

    void updateApotheke(Long id, ApothekeDTO apothekeDTO);

    void removeById(Long id);

    List<ApothekeDTO> fetchAllApotheken();

}
