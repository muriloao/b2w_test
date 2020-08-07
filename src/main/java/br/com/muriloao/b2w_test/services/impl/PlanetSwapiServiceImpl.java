/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services.impl;

import br.com.muriloao.b2w_test.dto.SwapiPlanetDto;
import br.com.muriloao.b2w_test.services.ISwapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Murilo Oliveira
 */
@Service
public class PlanetSwapiServiceImpl implements ISwapiService {

    final String URI = "/planets";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseEntity<SwapiPlanetDto> findPlanetById(String id) {
        return this.restTemplate.getForEntity(this.URL + this.URI + "/" + id + "/", SwapiPlanetDto.class);
    }
}
