/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.entities;

import br.com.muriloao.b2w_test.controllers.PlanetController;
import br.com.muriloao.b2w_test.dto.PlanetDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 *
 * @author Murilo Oliveira
 */
@Document
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Planet implements IEntity<PlanetDto> {

    @Id
    private String id;

    @Indexed(unique = true)
    private String swapiId;

    private String name;

    private String terrain;

    private String climate;

    private Integer filmAppearances = 0;

    @Override
    public PlanetDto toDto() {
        PlanetDto planet = new PlanetDto(this.id, this.swapiId, this.name, this.terrain, this.climate, this.filmAppearances);
        planet.add(linkTo(PlanetController.class)
                .slash(planet.getId()).withSelfRel());
        return planet;
    }

}
