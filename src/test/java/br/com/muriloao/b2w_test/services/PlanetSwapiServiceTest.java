/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services;

import br.com.muriloao.b2w_test.entities.Planet;
import br.com.muriloao.b2w_test.repositories.PlanetRepository;
import br.com.muriloao.b2w_test.services.impl.PlanetServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Murilo Oliveira
 */
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetServiceImpl planetService;

    final Planet planet = new Planet("1", "1", "Tatooine", "desert", "arid", 10);

    @Test
    void shouldSavePlanet() {
        when(this.planetRepository.insert(ArgumentMatchers.any(Planet.class))).then(AdditionalAnswers.returnsFirstArg());
        Planet savedPlanet = this.planetService.insert(this.planet);
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
        List<Planet> foundPlanets = this.planetService.findByName(findName);
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
        when(this.planetRepository.findById(ArgumentMatchers.any(String.class))).then(AdditionalAnswers.answer(a -> Optional.of(this.planet)));
        Optional<Planet> optPlanetUpdated = this.planetService.update(this.planet.getId(), this.planet);
        Assertions.assertThat(optPlanetUpdated.isPresent()).isTrue();
        Planet planetUpdated = optPlanetUpdated.get();
        Assertions.assertThat(planetUpdated).isNotNull();
        Assertions.assertThat(planetUpdated).isEqualTo(this.planet);
    }
}
