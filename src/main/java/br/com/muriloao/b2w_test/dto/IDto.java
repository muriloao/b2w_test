/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.dto;

import br.com.muriloao.b2w_test.entities.IEntity;

/**
 *
 * @author Murilo Oliveira
 * @param <T> the entity class
 */
public interface IDto<T extends IEntity> {

    public T toEntity();
}
