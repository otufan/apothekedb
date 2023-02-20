package com.pharmamall.apothekedb.adapter.controller;


import com.pharmamall.apothekedb.application.dto.InhaberDTO;
import com.pharmamall.apothekedb.application.port.in.InhaberUseCase;
import com.pharmamall.apothekedb.domain.Inhaber;
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
@RequestMapping("/inhaber")
public class InhaberController {

    private final InhaberUseCase inhaberUseCase;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Boolean>> registerInhaber(@Valid @RequestBody Inhaber inhaber) {

        inhaberUseCase.createInhaber(inhaber);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Inhaber ist erfolgreich erstellt!", true);
        return new ResponseEntity<>(map, HttpStatus.CREATED); //Response 201
    }

    @GetMapping("/{id}")
    public ResponseEntity<InhaberDTO> getInhaberById(@PathVariable Long id) {
        InhaberDTO inhaberDTO = inhaberUseCase.findById(id);
        return new ResponseEntity<>(inhaberDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Inhaber>> getAllInhabers() {
        List<Inhaber> inhaberList = inhaberUseCase.fetchAllInhabers();
        return new ResponseEntity<>(inhaberList, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Map<String, Boolean>> updateInhaber(@PathVariable Long id, @Valid @RequestBody InhaberDTO inhaberDTO) {

        inhaberUseCase.updateInhaber(id, inhaberDTO);
        Map<String, Boolean> map = new HashMap<>();
        map.put("erfolgreich", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<Map<String, Boolean>> deleteInhaber(@PathVariable Long id) {
        inhaberUseCase.removeById(id);

        Map<String, Boolean> map = new HashMap<>();
        map.put("erfolgreich", true);
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

}
