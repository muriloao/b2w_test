/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.entities;

import br.com.muriloao.b2w_test.dto.IDto;

/**
 *
 * @author Murilo Oliveira
 * @param <T> the DTO class
 */
public interface IEntity<T extends IDto> {

    public T toDto();
}
