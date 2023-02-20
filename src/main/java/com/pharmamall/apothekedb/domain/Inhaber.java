package com.pharmamall.apothekedb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.pharmamall.apothekedb.annotations.DomainModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "inhaber")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DomainModel
public class Inhaber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Bitte den Vorname eingeben")
    @Column(nullable = false)
    private String vorname;

    @NotNull(message = "Bitte den Nachname eingeben")
    @Column(nullable = false)
    private String nachname;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Europe/Berlin")
    @NotNull(message = "Bitte den Geburtsort eingeben")
    @Column(nullable = false)
    private LocalDate geburtsdatum;

    @NotNull(message = "Bitte das Geburtsdatum eingeben")
    @Column(nullable = false)
    private String geburtsort;

}
