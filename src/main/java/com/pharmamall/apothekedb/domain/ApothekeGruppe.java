package com.pharmamall.apothekedb.domain;

import com.pharmamall.apothekedb.annotations.DomainModel;
import com.pharmamall.apothekedb.domain.enumeration.ApothekeGruppen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "apotheke_gruppe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DomainModel
public class ApothekeGruppe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ApothekeGruppen gruppe;

    @OneToMany(mappedBy = "apothekeGruppe", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Apotheke> apotheken = new ArrayList<>();

}
