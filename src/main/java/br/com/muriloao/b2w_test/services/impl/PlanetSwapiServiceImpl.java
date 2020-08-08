/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services.impl;

import br.com.muriloao.b2w_test.dto.SwapiPlanetDto;
import br.com.muriloao.b2w_test.services.ISwapiService;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Murilo Oliveira
 */
@Service
@AllArgsConstructor
public class PlanetSwapiServiceImpl implements ISwapiService {

    final String URL = "https://swapi.dev/api/planets/";

    private final RestTemplate restTemplate;

    public PlanetSwapiServiceImpl() {
        this.restTemplate = new RestTemplateBuilder().defaultHeader("Content-Type", "application/json").build();
    }

    @Override
    public ResponseEntity<SwapiPlanetDto> findPlanetById(String id) {
        return this.restTemplate.getForEntity(this.URL + id + "/", SwapiPlanetDto.class);
    }
}
