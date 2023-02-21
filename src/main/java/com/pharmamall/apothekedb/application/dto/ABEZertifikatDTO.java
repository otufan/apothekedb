package com.pharmamall.apothekedb.application.dto;

import com.pharmamall.apothekedb.domain.Apotheke;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ABEZertifikatDTO {

    private String name;
    private String url;
    private String type;
    private long size;
    private Apotheke apotheke;

}
