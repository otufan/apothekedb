package com.pharmamall.apothekedb.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class InhaberDTO {

    private String vorname;

    private String nachname;

    private LocalDate geburtsdatum;

    private String geburtsort;

}
