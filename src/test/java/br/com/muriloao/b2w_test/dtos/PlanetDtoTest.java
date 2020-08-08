/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.dtos;

import br.com.muriloao.b2w_test.dto.PlanetDto;
import br.com.muriloao.b2w_test.entities.Planet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Murilo Oliveira
 */
public class PlanetDtoTest {

    @Test
    public void shouldReturnEntity() {
        final PlanetDto planetDto = new PlanetDto("1", "1", "Tatooine", "desert", "arid", 10);
        Planet planet = planetDto.toEntity();
        Assertions.assertThat(planetDto.getId()).isEqualTo(planet.getId());
        Assertions.assertThat(planetDto.getClimate()).isEqualTo(planet.getClimate());
        Assertions.assertThat(planetDto.getFilmAppearances()).isEqualTo(planet.getFilmAppearances());
        Assertions.assertThat(planetDto.getName()).isEqualTo(planet.getName());
        Assertions.assertThat(planetDto.getSwapiId()).isEqualTo(planet.getSwapiId());
        Assertions.assertThat(planetDto.getTerrain()).isEqualTo(planet.getTerrain());
    }
}
