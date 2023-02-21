package com.pharmamall.apothekedb.application.service;

import com.pharmamall.apothekedb.annotations.ApplicationService;
import com.pharmamall.apothekedb.application.dto.InhaberDTO;
import com.pharmamall.apothekedb.application.port.in.InhaberUseCase;
import com.pharmamall.apothekedb.application.port.out.InhaberPort;
import com.pharmamall.apothekedb.domain.Inhaber;
import com.pharmamall.apothekedb.exception.BadRequestException;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ApplicationService
@AllArgsConstructor
public class InhaberService implements InhaberUseCase {

    private final InhaberPort inhaberPort;

    @Override
    public Inhaber createInhaber(Inhaber inhaber) throws BadRequestException {
        //TODO inhaberExist methodu yazilacak
        return inhaberPort.write(inhaber);
    }

    @Override
    public InhaberDTO findById(Long id) throws ResourceNotFoundException {

        Inhaber inhaber = inhaberPort.findById(id);

        return InhaberDTO.builder().
                vorname(inhaber.getVorname()).
                nachname(inhaber.getNachname()).
                geburtsdatum(inhaber.getGeburtsdatum()).
                geburtsort(inhaber.getGeburtsort()).build();
    }

    @Override
    public Inhaber updateInhaber(Long id, InhaberDTO inhaberDTO) throws BadRequestException {

        inhaberPort.findById(id);

        Inhaber inhaber = Inhaber.builder().
                id(id).
                vorname(inhaberDTO.getVorname()).
                nachname(inhaberDTO.getNachname()).
                geburtsdatum(inhaberDTO.getGeburtsdatum()).
                geburtsort(inhaberDTO.getGeburtsort()).build();

        return inhaberPort.write(inhaber);
    }

    @Override
    public void removeById(Long id) throws ResourceNotFoundException {

        if (inhaberPort.findById(id) != null) {
            inhaberPort.deleteById(id);
        }

    }

    @Override
    public List<Inhaber> fetchAllInhabers() {

        return inhaberPort.findAll();
    }
}
