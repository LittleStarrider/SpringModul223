package com.example.demo.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/description", produces = "application/json")
@Tag(name = "Description", description = "Description")
public class DescriptionController {

    @GetMapping
    String getDescription() {
        return "Beschreibung Beschreibung Beschreibung Beschreibung Beschreibung Beschreibung Beschreibung BeschreibungBeschreibung Beschreibung Beschreibung Beschreibung" +
                "Beschreibung Beschreibung Beschreibung BeschreibungBeschreibung Beschreibung Beschreibung BeschreibungBeschreibung Beschreibung Beschreibung Beschreibung" +
                "Beschreibung Beschreibung Beschreibung BeschreibungBeschreibung Beschreibung Beschreibung BeschreibungBeschreibung Beschreibung Beschreibung Beschreibung";
    }

}
