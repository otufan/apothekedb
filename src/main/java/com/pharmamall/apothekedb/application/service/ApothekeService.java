package com.pharmamall.apothekedb.application.service;

import com.pharmamall.apothekedb.annotations.ApplicationService;
import com.pharmamall.apothekedb.application.dto.ApothekeDTO;
import com.pharmamall.apothekedb.application.port.in.ApothekeUseCase;
import com.pharmamall.apothekedb.application.port.out.ApothekePort;
import com.pharmamall.apothekedb.domain.Apotheke;
import com.pharmamall.apothekedb.exception.BadRequestException;
import com.pharmamall.apothekedb.exception.ConflictException;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@ApplicationService
@AllArgsConstructor
public class ApothekeService implements ApothekeUseCase {

    private final ApothekePort apothekePort;
    @Override
    public Apotheke createApotheke(Apotheke apotheke) throws BadRequestException {

        if (apothekePort.existsByEmail(apotheke.getEmail())) {
            throw new ConflictException("Email is already in use!");
        }
        return apothekePort.write(apotheke);
    }

    @Override
    public ApothekeDTO findById(Long id) throws ResourceNotFoundException {

        Apotheke apotheke = apothekePort.findById(id);

        return ApothekeDTO.builder().
                name(apotheke.getName()).
                strasse(apotheke.getStrasse()).
                plz(apotheke.getPlz()).
                ort(apotheke.getOrt()).
                phoneNummer(apotheke.getPhoneNummer()).
                email(apotheke.getEmail()).
                apothekeGruppe(apotheke.getApothekeGruppe()).
                inhabers(apotheke.getInhabers()).
                build();

    }

    @Override
    public Apotheke updateApotheke(Long id, ApothekeDTO apothekeDTO) throws BadRequestException {

        boolean emailExists = apothekePort.existsByEmail(apothekeDTO.getEmail());
        Apotheke apothekeDetails = apothekePort.findById(id);

        if (emailExists && !apothekeDTO.getEmail().equals(apothekeDetails.getEmail())) {
            throw new ConflictException("E-Mail ist bereits vorhanden");
        }

        Apotheke apotheke = Apotheke.builder().
                id(id).
                name(apothekeDTO.getName()).
                strasse(apothekeDTO.getStrasse()).
                plz(apothekeDTO.getPlz()).
                ort(apothekeDTO.getOrt()).
                phoneNummer(apothekeDTO.getPhoneNummer()).
                email(apothekeDTO.getEmail()).
                apothekeGruppe(apothekeDTO.getApothekeGruppe()).
                inhabers(apothekeDTO.getInhabers()).
                build();

        return apothekePort.write(apotheke);
    }

    @Override
    public void removeById(Long id) throws ResourceNotFoundException {

        if (apothekePort.findById(id) != null) {

            apothekePort.deleteById(id);
        }

    }

    @Override
    public List<ApothekeDTO> fetchAllApotheken() {

        List<Apotheke> apothekeList = apothekePort.findAll();
        List<ApothekeDTO> apothekeDTOS = new ArrayList<>();
        ApothekeDTO apothekeDTO;
        for (Apotheke apotheke : apothekeList) {

            apothekeDTO = ApothekeDTO.builder().
                    name(apotheke.getName()).
                    strasse(apotheke.getStrasse()).
                    plz(apotheke.getPlz()).
                    ort(apotheke.getOrt()).
                    phoneNummer(apotheke.getPhoneNummer()).
                    email(apotheke.getEmail()).
                    apothekeGruppe(apotheke.getApothekeGruppe()).
                    inhabers(apotheke.getInhabers()).
                    build();

            apothekeDTOS.add(apothekeDTO);
        }
        return apothekeDTOS;
    }
}
