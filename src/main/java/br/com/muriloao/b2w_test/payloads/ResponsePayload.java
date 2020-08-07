/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.muriloao.b2w_test.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Murilo Oliveira
 */
@Getter
@AllArgsConstructor
public class ResponsePayload<T> {

    int status;
    T content;
    String message;

    public static ResponseEntity<ResponsePayload> message(int status, String message) {
        return ResponseEntity.ok(new ResponsePayload(status, null, message));
    }

    public static <T extends Object> ResponseEntity<ResponsePayload<T>> content(int status, T data) {
        return ResponseEntity.ok(new ResponsePayload<>(status, data, null));
    }
}
