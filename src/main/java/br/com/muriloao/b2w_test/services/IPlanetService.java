/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services;

import br.com.muriloao.b2w_test.entities.Planet;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Murilo Oliveira
 */
@Service
public interface IPlanetService {

    public List<Planet> findAll();

    public Optional<Planet> findById(String uuid);

    public List<Planet> findByName(String name);

    public Optional<Planet> update(String id, Planet planet);

    public Planet insert(Planet planet);

    public void deleteById(String id);

}
