package com.pharmamall.apothekedb.adapter.controller;

import com.pharmamall.apothekedb.application.dto.ApothekeDTO;
import com.pharmamall.apothekedb.application.port.in.ApothekeUseCase;
import com.pharmamall.apothekedb.domain.Apotheke;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@RequestMapping("/apotheke")
public class ApothekeController {

    private final ApothekeUseCase apothekeUseCase;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> registerApotheke(@Valid @RequestBody Apotheke apotheke) {

        apothekeUseCase.createApotheke(apotheke);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Apotheke ist erfolgreich erstellt!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED); //Response 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApothekeDTO> getApothekeById(@PathVariable Long id) {

        ApothekeDTO apothekeDTO = apothekeUseCase.findById(id);
        return new ResponseEntity<>(apothekeDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApothekeDTO>> getAllApotheken() {

        List<ApothekeDTO> apothekeList = apothekeUseCase.fetchAllApotheken();
        return new ResponseEntity<>(apothekeList, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Map<String, Boolean>> updateApotheke(@PathVariable Long id, @Valid @RequestBody ApothekeDTO apothekeDTO) {

        apothekeUseCase.updateApotheke(id, apothekeDTO);
        Map<String, Boolean> map = new HashMap<>();
        map.put("erfolgreich", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Map<String, Boolean>> deleteApotheke(@PathVariable Long id) {

        apothekeUseCase.removeById(id);

        Map<String, Boolean> map = new HashMap<>();
        map.put("erfolgreich", true);

        return new ResponseEntity<>(map, HttpStatus.OK);


    }

}
