package com.pharmamall.apothekedb.application.port.in;

import com.pharmamall.apothekedb.annotations.Port;
import com.pharmamall.apothekedb.domain.ABEZertifikat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Port
public interface ABEZertifikatUseCase {

    ABEZertifikat getZertifikatById(Long id);
    ABEZertifikat store(MultipartFile zertifikat, Long apothekeId) throws IOException;

    Stream<ABEZertifikat> getAllAbeZertifikate();
}
