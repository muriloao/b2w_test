/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.repositories;

import br.com.muriloao.b2w_test.entities.Planet;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Murilo Oliveira
 */
@ExtendWith(MockitoExtension.class)
public class PlanetRepositoryTest {

    @Mock
    private PlanetRepository planetRepository;

    final Planet planet = new Planet("1", "1", "Tatooine", "desert", "arid", 10);

    @Test
    void shouldSavePlanet() {
        when(this.planetRepository.insert(ArgumentMatchers.any(Planet.class))).then(AdditionalAnswers.returnsFirstArg());
        Planet savedPlanet = this.planetRepository.insert(this.planet);
        Assertions.assertThat(savedPlanet).isNotNull();
        Assertions.assertThat(savedPlanet).isEqualTo(this.planet);
    }

    @Test
    void shouldReturnPlanetList() {
        when(this.planetRepository.findAll(ArgumentMatchers.any(Sort.class))).then(AdditionalAnswers.answer(a -> Arrays.asList(this.planet)));
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
        final String findName = "Tatooine";
        when(this.planetRepository.findByName(ArgumentMatchers.any(String.class))).then(AdditionalAnswers.answer(a -> Arrays.asList(this.planet)));
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
        when(this.planetRepository.save(ArgumentMatchers.any(Planet.class))).then(AdditionalAnswers.returnsFirstArg());
        Planet planetUpdated = this.planetRepository.save(this.planet);
        Assertions.assertThat(planetUpdated).isNotNull();
        Assertions.assertThat(planetUpdated).isEqualTo(this.planet);
    }
}
