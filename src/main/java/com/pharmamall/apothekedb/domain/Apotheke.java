package com.pharmamall.apothekedb.domain;

import com.pharmamall.apothekedb.annotations.DomainModel;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "apotheke")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@DomainModel
public class Apotheke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Bitte den Apothekennamen eingeben")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Bitte die Straße und die Hausnummer eingeben")
    @Column(nullable = false)
    private String strasse;

    @Size(max = 5)
    @NotNull(message = "Bitte die Postleitzahl eingeben")
    @Column(nullable = false, length = 5)
    private String plz;

    @NotNull(message = "Bitte den Ort eingeben")
    @Column(nullable = false)
    private String ort;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    @NotNull(message = "Bitte die Telefonnummer eingeben")
    @Column(nullable = false, name = "phone_number")
    private String phoneNummer;

    @Email(message = "Bitte die gültige Email eingeben")
    @NotNull(message = "Bitte die Email eingeben")
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "apotheke_inhaber",
            joinColumns = @JoinColumn(name = "apotheke_id"),
            inverseJoinColumns = @JoinColumn(name = "inhaber_id"))
    private Set<Inhaber> inhabers;

    @ManyToOne
    @JoinColumn(name = "gruppe_id")
    private ApothekeGruppe apothekeGruppe;

}
