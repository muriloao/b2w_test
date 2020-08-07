/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.repositories;

import br.com.muriloao.b2w_test.entities.Planet;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Murilo Oliveira
 */
@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    public List<Planet> findByName(String name);

}
