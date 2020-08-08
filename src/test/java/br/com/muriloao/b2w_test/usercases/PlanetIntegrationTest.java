/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.usercases;

import br.com.muriloao.b2w_test.entities.Planet;
import br.com.muriloao.b2w_test.repositories.PlanetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Murilo Oliveira
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PlanetIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlanetRepository planetRepository;

    final Planet planet = new Planet(null, "1", "Tatooine", "desert", "arid", 10);

    @Test
    void shouldRegisterPlanet() throws Exception {

        mockMvc.perform(post("/planets")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(this.planet)))
                .andExpect(status().isCreated());

        List<Planet> planets = this.planetRepository.findByName(this.planet.getName());
        Assertions.assertThat(planets).isNotNull();
        Assertions.assertThat(planets).isNotEmpty();
        Assertions.assertThat(planets.size()).isEqualTo(1);
        planets.forEach(p -> {
            Assertions.assertThat(p.getId()).isNotNull();
            Assertions.assertThat(p.getName()).isEqualTo(this.planet.getName());
        });
    }

    @Test
    void shouldUpdatePlanet_whenPlanetIsInformed() throws Exception {
        final Planet savedPlanet = this.planetRepository.insert(this.planet);
        final Planet updatedPlanet = new Planet(null, "swapiId", "name", "terrain", "climate", 0);

        mockMvc.perform(patch("/planets/" + savedPlanet.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(updatedPlanet)))
                .andExpect(status().isOk());

        List<Planet> planets = this.planetRepository.findByName(updatedPlanet.getName());
        Assertions.assertThat(planets).isNotNull();
        Assertions.assertThat(planets).isNotEmpty();
        Assertions.assertThat(planets.size()).isEqualTo(1);
        planets.forEach(p -> {
            Assertions.assertThat(p.getId()).isNotNull();
            Assertions.assertThat(p.getName()).isEqualTo(updatedPlanet.getName());
        });
    }

    @Test
    void shouldDeletePlanet_whenIdIsInformed() throws Exception {
        final Planet savedPlanet = this.planetRepository.insert(this.planet);

        mockMvc.perform(delete("/planets/" + savedPlanet.getId())
                .contentType("application/json")
        )
                .andExpect(status().isOk());

        Optional<Planet> optPlanet = this.planetRepository.findById(savedPlanet.getId());
        Assertions.assertThat(optPlanet.isPresent()).isFalse();
    }

}
