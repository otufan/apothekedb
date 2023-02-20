package com.pharmamall.apothekedb.adapter.controller;

import com.pharmamall.apothekedb.application.port.in.ABEZertifikatUseCase;
import com.pharmamall.apothekedb.domain.ABEZertifikat;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:8081")
@RequestMapping("/zertifikat")
public class ABEZertifikatController {

    private final ABEZertifikatUseCase abeZertifikatUseCase;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadAbeZertifikat(@RequestParam("zertifikat") MultipartFile zertifikat){

        try {
            ABEZertifikat abeZertifikat = abeZertifikatUseCase.store(zertifikat);
            Map<String, String> map = new HashMap<>();
            map.put("imageId", ""+abeZertifikat.getId());

            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (IOException e) {

            Map<String, String> map = new HashMap<>();
            map.put("message", "konnte die Datei nicht hochladen: " + zertifikat.getOriginalFilename() + "!");

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
        }
    }

}
