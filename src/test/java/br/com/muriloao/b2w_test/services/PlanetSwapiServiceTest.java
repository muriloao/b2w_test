/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services;

import br.com.muriloao.b2w_test.dto.SwapiPlanetDto;
import br.com.muriloao.b2w_test.services.impl.PlanetSwapiServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Murilo Oliveira
 */
@ExtendWith(MockitoExtension.class)
public class PlanetSwapiServiceTest {

    @Mock
    private PlanetSwapiServiceImpl planetSwapiService;
    final SwapiPlanetDto swapiPlanetDto = new SwapiPlanetDto("1", "1", "Tatooine", "desert", "arid", "172", "77", "male", "blond", "fair", "blue", "19BBY", null, null, null, null);

    @Test
    void shouldReturnPlanetOfSwapi_whenSwapiIdIsInformed() {
        final String swapiId = "1";
        when(this.planetSwapiService.findPlanetById(ArgumentMatchers.any(String.class))).then(AdditionalAnswers.answer(a -> ResponseEntity.ok(this.swapiPlanetDto)));
        ResponseEntity<SwapiPlanetDto> foundPlanets = this.planetSwapiService.findPlanetById(swapiId);
        Assertions.assertThat(foundPlanets).isNotNull();
        Assertions.assertThat(foundPlanets.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertThat(foundPlanets.getBody()).isEqualTo(this.swapiPlanetDto);

    }

}
