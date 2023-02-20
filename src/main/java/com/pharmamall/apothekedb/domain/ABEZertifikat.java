package com.pharmamall.apothekedb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pharmamall.apothekedb.annotations.DomainModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "abe_zertifikat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DomainModel
public class ABEZertifikat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
    private Apotheke apothekeId;

    private String name;
    private String type;

    @JsonIgnore
    @Lob
    private byte[] data;

}
