package com.pharmamall.apothekedb.application.dto;

import com.pharmamall.apothekedb.domain.ApothekeGruppe;
import com.pharmamall.apothekedb.domain.Inhaber;
import com.pharmamall.apothekedb.domain.enumeration.ApothekeGruppen;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
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

    private String gruppe;

    private Set<String> inhabers;

    public void setGruppe(ApothekeGruppe apothekeGruppe) {

        if (apothekeGruppe.getGruppe().equals(ApothekeGruppen.GRUPPE_KH)) {
            this.gruppe = "Krankenhaus";
        } else if (apothekeGruppe.getGruppe().equals(ApothekeGruppen.GRUPPE_OA)) {
            this.gruppe = "Öffentliche Apotheke";
        } else {
            this.gruppe = "Krankenhaus und öffentliche Apotheke";
        }
    }

    public void setInhabers(Set<Inhaber> inhabers) {

        Set<String> inh = new HashSet<>();

        Inhaber[] inhaber = inhabers.toArray(new Inhaber[inhabers.size()]);

        for (int i = 0; i < inhabers.size(); i++) {
            inh.add("Vorname : " + inhaber[i].getVorname());
            inh.add("Nachname : " + inhaber[i].getNachname());
            inh.add("Geburtsort : " + inhaber[i].getGeburtsort());
            inh.add("Geburtsdatum : " + inhaber[i].getGeburtsdatum().toString());
        }

        this.inhabers = inh;
    }
}
