package com.pharmamall.apothekedb.application.service;

import com.pharmamall.apothekedb.annotations.ApplicationService;
import com.pharmamall.apothekedb.application.dto.InhaberDTO;
import com.pharmamall.apothekedb.application.port.in.InhaberUseCase;
import com.pharmamall.apothekedb.application.port.out.ApothekePort;
import com.pharmamall.apothekedb.application.port.out.InhaberPort;
import com.pharmamall.apothekedb.domain.Apotheke;
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
    private final ApothekePort apothekePort;

    @Override
    public Inhaber createInhaber(Inhaber inhaber, Long apothekeId) throws BadRequestException {
        //TODO inhaberExist methodu yazilacak
        Apotheke apotheke = apothekePort.findById(apothekeId);
        apotheke.getInhabers().add(inhaber);
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
    public void removeInhaberById(Long apothekeId, Long inhaberId) throws ResourceNotFoundException {

        inhaberPort.findById(inhaberId);
        Apotheke apotheke = apothekePort.findById(apothekeId);
        apotheke.getInhabers().removeIf(inhaberDetail -> inhaberDetail.getId().equals(inhaberId));

        apothekePort.write(apotheke);
    }

    @Override
    public List<Inhaber> fetchAllInhabers() {

        return inhaberPort.findAll();
    }

}
