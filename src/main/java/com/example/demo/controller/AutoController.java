package com.example.demo.controller;

import com.example.demo.entity.Auto;
import com.example.demo.entity.AutoSearchParams;
import com.example.demo.service.AutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RequestMapping("/auto")
@RestController
public class AutoController {


    private final AutoService autoService;

    public AutoController(AutoService customerService) {
        this.autoService = customerService;
    }


    @GetMapping()
    public List<Auto> getAll(AutoSearchParams searchParams) {
        return autoService.getAll(searchParams);
    }

    @GetMapping("/{id}")
    public Auto getAutoById(@PathVariable int id) {
        return autoService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<Auto> add(@RequestBody Auto auto) {
        autoService.add(auto);
        var location = UriComponentsBuilder.fromPath("/auto/" + auto.getVehicleId()).build().toUri();
        return ResponseEntity.created(location).body(auto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Auto> delete(@PathVariable int id) {
        autoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Auto update(@RequestBody Auto auto, @PathVariable int id) {
        return autoService.update(id, auto);
    }


}
