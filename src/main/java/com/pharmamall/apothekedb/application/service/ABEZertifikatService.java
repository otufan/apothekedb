package com.pharmamall.apothekedb.application.service;


import com.pharmamall.apothekedb.annotations.ApplicationService;
import com.pharmamall.apothekedb.application.port.in.ABEZertifikatUseCase;
import com.pharmamall.apothekedb.application.port.out.ABEZertifikatPort;
import com.pharmamall.apothekedb.domain.ABEZertifikat;
import com.pharmamall.apothekedb.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@ApplicationService
@AllArgsConstructor
public class ABEZertifikatService implements ABEZertifikatUseCase {

    private final ABEZertifikatPort abeZertifikatPort;


    @Override
    public ABEZertifikat getZertifikatById(Long id) throws ResourceNotFoundException {
        return abeZertifikatPort.findById(id).get();
    }

    @Override
    public ABEZertifikat store(MultipartFile zertifikat) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(zertifikat.getOriginalFilename()));
        ABEZertifikat abeZertifikat = ABEZertifikat.builder().
                name(fileName).
                type(zertifikat.getContentType()).
                data(zertifikat.getBytes()).build();

        abeZertifikatPort.save(abeZertifikat);
        return abeZertifikat;

    }

    @Override
    public Stream<ABEZertifikat> getAllAbeZertifikate() {
        return abeZertifikatPort.findAll();
    }
}
