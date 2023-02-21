package com.pharmamall.apothekedb.adapter.controller;

import com.pharmamall.apothekedb.application.dto.ABEZertifikatDTO;
import com.pharmamall.apothekedb.application.port.in.ABEZertifikatUseCase;
import com.pharmamall.apothekedb.domain.ABEZertifikat;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:8081")
@RequestMapping("/zertifikat")
public class ABEZertifikatController {

    private final ABEZertifikatUseCase abeZertifikatUseCase;

    @PostMapping("/apotheke/{apothekeId}/upload")
    public ResponseEntity<Map<String, String>> uploadAbeZertifikat(@PathVariable Long apothekeId, @RequestParam("zertifikat") MultipartFile zertifikat){

        try {
            ABEZertifikat abeZertifikat = abeZertifikatUseCase.store(zertifikat, apothekeId);
            Map<String, String> map = new HashMap<>();
            map.put("imageId", ""+abeZertifikat.getId());

            return ResponseEntity.status(HttpStatus.OK).body(map);

        } catch (IOException e) {

            Map<String, String> map = new HashMap<>();
            map.put("message", "konnte die Datei nicht hochladen: " + zertifikat.getOriginalFilename() + "!");

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(map);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> downloadAbeZertifikat(@PathVariable Long id) {

        ABEZertifikat abeZertifikat = abeZertifikatUseCase.getZertifikatById(id);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + abeZertifikat.getName() + "").
                body(abeZertifikat.getData());
    }

    @GetMapping("")
    public ResponseEntity<List<ABEZertifikatDTO>> getAllAbeZertifikat() {

        List<ABEZertifikatDTO> abeZertifikats = abeZertifikatUseCase.getAllAbeZertifikate().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder.
                    fromCurrentContextPath().
                    path("/zertifikat/").
                    path(dbFile.getId()+"").
                    toUriString();
            return new ABEZertifikatDTO(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length, dbFile.getApotheke());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(abeZertifikats);

    }

    @GetMapping("/display/{id}")
    public ResponseEntity<byte []> displayAbeZertifikat(@PathVariable Long id) {
        ABEZertifikat abeZertifikat = abeZertifikatUseCase.getZertifikatById(id);
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(abeZertifikat.getData(), headers, HttpStatus.CREATED);
    }

}
