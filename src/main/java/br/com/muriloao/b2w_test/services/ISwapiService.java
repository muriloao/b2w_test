/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services;

import br.com.muriloao.b2w_test.dto.SwapiPlanetDto;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Murilo Oliveira
 */
public interface ISwapiService {

    public ResponseEntity<SwapiPlanetDto> findPlanetById(String id);
}
