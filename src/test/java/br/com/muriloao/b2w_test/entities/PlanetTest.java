/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.entities;

import br.com.muriloao.b2w_test.dto.PlanetDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Murilo Oliveira
 */
public class PlanetTest {

    @Test
    public void shouldReturnDto() {
        final Planet planet = new Planet("1", "1", "Tatooine", "desert", "arid", 10);
        PlanetDto planetDto = planet.toDto();
        Assertions.assertThat(planet.getId()).isEqualTo(planetDto.getId());
        Assertions.assertThat(planet.getClimate()).isEqualTo(planetDto.getClimate());
        Assertions.assertThat(planet.getFilmAppearances()).isEqualTo(planetDto.getFilmAppearances());
        Assertions.assertThat(planet.getName()).isEqualTo(planetDto.getName());
        Assertions.assertThat(planet.getSwapiId()).isEqualTo(planetDto.getSwapiId());
        Assertions.assertThat(planet.getTerrain()).isEqualTo(planetDto.getTerrain());
    }
}
