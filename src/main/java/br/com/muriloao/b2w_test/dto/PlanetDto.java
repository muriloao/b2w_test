/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.dto;

import br.com.muriloao.b2w_test.entities.Planet;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

/**
 *
 * @author Murilo Oliveira
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDto extends RepresentationModel<PlanetDto> implements IDto<Planet> {

    private String id;

    private String swapiId;

    private String name;

    private String terrain;

    private String climate;

    private Integer filmAppearances = 0;

    @Override
    public Planet toEntity() {
        return new Planet(this.id, this.swapiId, this.name, this.terrain, this.climate, this.filmAppearances);
    }
}
