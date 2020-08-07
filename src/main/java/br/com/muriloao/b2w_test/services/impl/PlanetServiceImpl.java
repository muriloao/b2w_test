/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.services.impl;

import br.com.muriloao.b2w_test.entities.Planet;
import br.com.muriloao.b2w_test.repositories.PlanetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.muriloao.b2w_test.services.IPlanetService;

/**
 *
 * @author Murilo Oliveira
 */
@Service
public class PlanetServiceImpl implements IPlanetService {

    @Autowired
    private PlanetRepository repository;

    @Override
    public List<Planet> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Planet> findById(String uuid) {
        return this.repository.findById(uuid);
    }

    @Override
    public Optional<Planet> update(String id, Planet planet) {
        Optional<Planet> optPlanet = this.repository.findById(id);
        if (optPlanet.isPresent()) {
            final Planet planetToUpdate = new Planet(id, planet.getSwapiId(), planet.getName(), planet.getTerrain(), planet.getClimate(), planet.getFilmAppearances());
            return Optional.of(this.repository.save(planetToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Planet insert(Planet planet) {
        return this.repository.insert(planet);
    }

    @Override
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Planet> findByName(String name) {
        return this.repository.findByName(name);
    }

}
