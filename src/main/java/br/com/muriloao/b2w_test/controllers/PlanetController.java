/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.controllers;

import br.com.muriloao.b2w_test.dto.SwapiPlanetDto;
import br.com.muriloao.b2w_test.dto.PlanetDto;
import br.com.muriloao.b2w_test.entities.Planet;
import br.com.muriloao.b2w_test.payloads.ResponsePayload;
import br.com.muriloao.b2w_test.services.impl.PlanetSwapiServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.muriloao.b2w_test.services.IPlanetService;

/**
 *
 * @author Murilo Oliveira
 */
@RestController()
@RequestMapping(
        value = {"/planets"},
        consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {"application/hal+json"}
)
@ResponseBody
public class PlanetController {

    @Autowired
    private IPlanetService planetService;

    @Autowired
    private PlanetSwapiServiceImpl swapiService;

    @GetMapping
    public ResponseEntity<List<PlanetDto>> index() {
        List<PlanetDto> planetsDto = this.planetService.findAll().stream().map(Planet::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(planetsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable(required = true) String id) {
        Optional<Planet> optPlanet = this.planetService.findById(id);
        if (optPlanet.isPresent()) {
            return ResponseEntity.ok(optPlanet.get().toDto());
        }
        return ResponsePayload.message(HttpStatus.NOT_FOUND.value(), "Planet with id " + id + " does not exist");
    }

    @GetMapping("/name/{planetName}")
    public ResponseEntity<List<PlanetDto>> getByName(@PathVariable(required = true) String planetName) {
        List<Planet> planets = this.planetService.findByName(planetName);
        return ResponseEntity.ok(planets.stream().map(Planet::toDto).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity store(@RequestBody @Valid PlanetDto planet) {
        ResponseEntity<SwapiPlanetDto> swapiPlanetResponse = this.swapiService.findPlanetById(planet.getSwapiId());
        if (swapiPlanetResponse.getStatusCode() == HttpStatus.OK) {
            planet.setFilmAppearances(swapiPlanetResponse.getBody().getFilms().size());
            final Planet persistedPlanet = this.planetService.insert(planet.toEntity());
            return ResponseEntity.status(HttpStatus.CREATED).body(persistedPlanet);
        } else {
            return ResponsePayload.message(HttpStatus.NOT_FOUND.value(), "Planet with id " + planet.getSwapiId() + " in Swapi API does not exist");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable(required = true) String id, @RequestBody PlanetDto planet) {
        final Optional<Planet> optPlanet = this.planetService.update(id, planet.toEntity());
        return optPlanet.isPresent()
                ? ResponseEntity.ok().build()
                : ResponsePayload.message(HttpStatus.NOT_FOUND.value(), "Planet with id " + planet.getSwapiId() + " does not exist");
    }

    @DeleteMapping("/{id]")
    public ResponseEntity delete(@PathVariable(required = true) String id) {
        this.planetService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
