/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.repositories;

import br.com.muriloao.b2w_test.entities.Planet;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Murilo Oliveira
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class PlanetRepositoryIntegrationTest {

    @Autowired
    private PlanetRepository planetRepository;

    final Planet planet = new Planet("1", "1", "Tatooine", "desert", "arid", 10);

    @AfterEach
    public void afterEach() {
        this.planetRepository.deleteAll();
    }

    @Test
    void shouldSavePlanet() {
        Planet savedPlanet = this.planetRepository.insert(this.planet);
        Assertions.assertThat(savedPlanet).isNotNull();
        Assertions.assertThat(savedPlanet.getId()).isNotNull();
        Assertions.assertThat(savedPlanet.getId()).isNotEmpty();
    }

    @Test
    void shouldReturnPlanetList() {
        this.planetRepository.insert(this.planet);
        List<Planet> foundPlanets = this.planetRepository.findAll(Sort.unsorted());
        Assertions.assertThat(foundPlanets).isNotNull();
        Assertions.assertThat(foundPlanets).isNotEmpty();
        Assertions.assertThat(foundPlanets.size()).isEqualTo(1);
        foundPlanets.forEach(p -> {
            Assertions.assertThat(p).isNotNull();
            Assertions.assertThat(p).isEqualTo(this.planet);
        });
    }

    @Test
    void shouldReturnAPlanet_whenNameIsInformed() {
        this.planetRepository.insert(this.planet);
        final String findName = "Tatooine";
        List<Planet> foundPlanets = this.planetRepository.findByName(findName);
        Assertions.assertThat(foundPlanets).isNotNull();
        Assertions.assertThat(foundPlanets).isNotEmpty();
        Assertions.assertThat(foundPlanets.size()).isEqualTo(1);
        foundPlanets.forEach(p -> {
            Assertions.assertThat(p).isNotNull();
            Assertions.assertThat(p.getName()).isEqualTo(findName);
        });
    }

    @Test
    void shouldUpdatePlanet_whenIdAndPlanetAreInformed() {
        final String newName = "Yavin IV";
        Planet planetSaved = this.planetRepository.insert(this.planet);
        Planet planetToUpdate = new Planet(planetSaved.getId(), planetSaved.getSwapiId(), newName, planetSaved.getTerrain(), planetSaved.getClimate(), planetSaved.getFilmAppearances());
        Planet planetUpdated = this.planetRepository.save(planetToUpdate);
        Assertions.assertThat(planetUpdated).isNotNull();
        Assertions.assertThat(planetUpdated).isEqualTo(planetToUpdate);
        Assertions.assertThat(planetUpdated.getName()).isEqualTo(newName);
    }
}
