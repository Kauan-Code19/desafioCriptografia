package com.desafios.cripftografia.controllers;

import com.desafios.cripftografia.dtos.SensitiveDataDto;
import com.desafios.cripftografia.services.SensitiveDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/sensitive-data")
public class SensitiveDataController {
    @Autowired
    private SensitiveDataService sensitiveDataService;

    @PostMapping
    public ResponseEntity<SensitiveDataDto> createSensitiveData(@RequestBody SensitiveDataDto sensitiveDataDto) {
        sensitiveDataDto = sensitiveDataService.createSensitiveData(sensitiveDataDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(sensitiveDataDto.getId()).toUri();

        return ResponseEntity.created(uri).body(sensitiveDataDto);
    }

    @GetMapping("/{idSensitiveData}")
    public ResponseEntity<SensitiveDataDto> getSensitiveData(@PathVariable Long idSensitiveData) {
        SensitiveDataDto sensitiveDataDto = sensitiveDataService.getSensitiveData(idSensitiveData);

        return ResponseEntity.ok().body(sensitiveDataDto);
    }

    @PutMapping("/{idSensitiveData}")
    public ResponseEntity<SensitiveDataDto> updateSensitiveData(@PathVariable Long idSensitiveData,
                                                                @RequestBody SensitiveDataDto sensitiveDataDto) {
        sensitiveDataDto = sensitiveDataService.updateSensitiveData(idSensitiveData, sensitiveDataDto);

        return ResponseEntity.ok(sensitiveDataDto);
    }

    @DeleteMapping("/{idSensitiveData}")
    public ResponseEntity<Void> deleteSensitiveData(@PathVariable Long idSensitiveData) {
        sensitiveDataService.deleteSensitiveData(idSensitiveData);

        return ResponseEntity.noContent().build();
    }

}
