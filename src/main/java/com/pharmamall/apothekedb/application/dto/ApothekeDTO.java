package com.pharmamall.apothekedb.application.dto;

import com.pharmamall.apothekedb.domain.Inhaber;
import com.pharmamall.apothekedb.domain.enumeration.ApothekeGruppen;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class ApothekeDTO {

    @NotNull(message = "Bitte den Apothekennamen eingeben")
    private String name;

    @NotNull(message = "Bitte die Straße und die Hausnummer eingeben")
    private String strasse;

    @Size(max = 5)
    @NotNull(message = "Bitte die Postleitzahl eingeben")
    private String plz;

    @NotNull(message = "Bitte den Ort eingeben")
    private String ort;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    @NotNull(message = "Bitte die Telefonnummer eingeben")
    private String phoneNummer;

    @Email(message = "Bitte die gültige Email eingeben")
    @NotNull(message = "Bitte die Email eingeben")
    private String email;

    private ApothekeGruppen apothekeGruppe;

    private Set<Inhaber> inhabers;

}
